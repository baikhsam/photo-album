package controller;

import model.Canvas;
import model.ICanvas;
import model.ISnapshot;
import model.shapes.Color;
import model.shapes.Oval;
import model.shapes.Rectangle;
import view.GraphicalView;
import view.IView;
import view.WebView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Controller class handles information flow between Model and View.
 */
public class Controller implements IController, ActionListener {
  private ICanvas canvas;
  private IView view;
  private int snapshotIndex;
  private final int default_size = 1000;
  
  /**
   * Class constructor. Initialize new Canvas object and snapshotIndex to zero.
   */
  public Controller() {
    this.canvas = new Canvas();
    this.snapshotIndex = 0;
  }
  
  @Override
  public void createShape(String id, String type, double x, double y, double firstDimension,
                          double secondDimension, double red, double green, double blue) {
    switch (type) {
      case "rectangle":
        canvas.addShape(new Rectangle(new Color(red, green, blue), x, y, firstDimension,
            secondDimension, id));
        break;
      case "oval":
        canvas.addShape(new Oval(new Color(red, green, blue), x, y, firstDimension,
            secondDimension, id));
        break;
    }
  }
  
  @Override
  public void moveShape(String id, double x, double y) {
    canvas.getShape(id).setCoordinates(x, y);
  }
  
  @Override
  public void changeShapeColor(String id, double red, double green, double blue) {
    canvas.getShape(id).setColor(red, green, blue);
  }
  
  @Override
  public void resizeShape(String id, double firstDimension, double secondDimension) {
    canvas.getShape(id).resize(firstDimension, secondDimension);
  }
  
  @Override
  public void removeShape(String id) {
    canvas.removeShape(canvas.getShape(id));
  }
  
  @Override
  public void takeSnapshot(String description) {
    canvas.createSnapshot(description);
  }
  
  @Override
  public void initializeProgram(Readable r) throws IOException {
    int xMax = default_size;
    int yMax = default_size;
    String commandFileName = "";
    String viewType = "";
    String outputFileName = "";
    
    Scanner scanner = new Scanner(r);
    Pattern p = Pattern.compile("-");
    while (scanner.hasNext()) {
      if (scanner.hasNext("-in")) {
        scanner.next();
        commandFileName = scanner.next();
        Matcher m = p.matcher(commandFileName);
        if (m.find()) {
          commandFileName = "";
        }
      }
      else if (scanner.hasNext("-view") || scanner.hasNext("-v")) {
        scanner.next();
        viewType = scanner.next();
        Matcher m = p.matcher(viewType);
        if (m.find()) {
          viewType = "";
        }
      }
      else if (scanner.hasNext("-out")) {
        scanner.next();
        outputFileName = scanner.next();
      }
      else if (scanner.hasNextInt()) {
        xMax = scanner.nextInt();
        yMax = scanner.nextInt();
      }
      else {
        scanner.next();
      }
    }
    
    if (commandFileName.isEmpty() || viewType.isEmpty()) {
      throw new IllegalArgumentException("Input file and View type are mandatory args.");
    }
    if (viewType.equals("web") && outputFileName.isEmpty()) {
      throw new IllegalArgumentException("Output file is mandatory for WebView.");
    }
    initializeView(commandFileName, viewType, outputFileName, xMax, yMax);
  }
  
  private String getResourceFileAsString(String fileName) throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(fileName)) {
      if (is == null) return null;
      try (InputStreamReader isr = new InputStreamReader(is);
           BufferedReader reader = new BufferedReader(isr)) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    }
  }
  
  private void initializeCanvas(String commandFileName) throws IOException {
    Scanner scanner = null;
    scanner = new Scanner(Objects.requireNonNull(getResourceFileAsString(commandFileName)));
    while (scanner.hasNextLine()) {
      if (scanner.hasNext("#")) {
        scanner.nextLine();
      }
      else if (scanner.hasNext("shape")) {
        scanner.next();
        String id = scanner.next();
        String type = scanner.next();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int firstDimension = scanner.nextInt();
        int secondDimension = scanner.nextInt();
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        createShape(id, type, x, y, firstDimension, secondDimension, r, g, b);
      }
      else if (scanner.hasNext("move")) {
        scanner.next();
        String id = scanner.next();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        moveShape(id, x, y);
      }
      else if (scanner.hasNext("color")) {
        scanner.next();
        String id = scanner.next();
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        changeShapeColor(id, r, g, b);
      }
      else if (scanner.hasNext("resize")) {
        scanner.next();
        String id = scanner.next();
        int firstDimension = scanner.nextInt();
        int secondDimension = scanner.nextInt();
        resizeShape(id, firstDimension, secondDimension);
      }
      else if (scanner.hasNext("remove")) {
        scanner.next();
        String id = scanner.next();
        removeShape(id);
      }
      else if (scanner.hasNext("snapshot") || scanner.hasNext("snapShot")) {
        // check for optional description
        String line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
          String[] snapshotLineList = line.split(" ");
          String description =
              String.join(" ", snapshotLineList).substring(snapshotLineList[0].length());
          takeSnapshot(description);
        }
      }
      else {
        scanner.nextLine();
      }
    }
  }
  
  private void initializeView(String commandFileName, String viewType, String outputName,
                                int xMax,
                           int yMax) throws IOException {
    switch (viewType) {
      case "web":
        view = new WebView(outputName);
        initializeCanvas(commandFileName);
        view.populateView(canvas.getSnapshotsMarkup());
        break;
      case "graphical":
        view = new GraphicalView(xMax, yMax);
        this.view.setActionListener(this);
        initializeCanvas(commandFileName);
        ISnapshot first = canvas.getAlbum().get(0);
        view.populateView(first.getID(), first.getDescription(), first.getShapeList());
        break;
    }
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    GraphicalView gView = (GraphicalView) view;
    ISnapshot snap;
    switch (e.getActionCommand()) {
      case "prev":
        if (snapshotIndex - 1 >= 0) {
          snapshotIndex--;
          snap = canvas.getAlbum().get(snapshotIndex);
          view.populateView(snap.getID(), snap.getDescription(), snap.getShapeList());
        }
        else {
          gView.showNoPreviousDialog();
        }
        break;
      case "select":
        String[] idList = new String[canvas.getAlbum().size()];
        for (int i = 0; i < canvas.getAlbum().size(); i++) {
          idList[i] = canvas.getAlbum().get(i).getID();
        }
        snapshotIndex = gView.showSelectMenu(idList, snapshotIndex);
        snap = canvas.getAlbum().get(snapshotIndex);
        view.populateView(snap.getID(), snap.getDescription(), snap.getShapeList());
        break;
      case "next":
        if (snapshotIndex + 1 < canvas.getAlbum().size()) {
          snapshotIndex++;
          snap = canvas.getAlbum().get(snapshotIndex);
          view.populateView(snap.getID(), snap.getDescription(), snap.getShapeList());
        }
        else {
          gView.showNoNextDialog();
        }
        break;
      case "quit":
        System.exit(0);
        break;
    }
  }
}
