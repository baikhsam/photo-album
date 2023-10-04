package view;

import model.shapes.IShape;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Concrete implementation of the View interface. WebView is a static HTML markup with embedded
 * SVG to draw graphics required in a Snapshot. All Snapshots are displayed.
 */
public class WebView implements IView {
  private File html;
  
  /**
   * WebView class constructor.
   */
  public WebView(String outputFileName) throws IOException {
    html = new File(String.format("%s", outputFileName));
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
  
  @Override
  public void populateView(String markup) {
    try (
        Scanner input = new Scanner(getResourceFileAsString("template.html"));
        PrintWriter output = new PrintWriter(html);
    ) {
      while (input.hasNext()) {
        String s1 = input.nextLine();
        String s2 = s1.replaceAll("%body%", markup);
        output.println(s2);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void populateView(String id, String description, List<IShape> shapes) {
    throw new UnsupportedOperationException("Wrong overloaded populateView method used.");
  }
  
  @Override
  public void setActionListener(ActionListener listener) {
    throw new UnsupportedOperationException("This method is not used for WebView.");
  }
  
  @Override
  public String getViewType() {
    return "web";
  }
}
