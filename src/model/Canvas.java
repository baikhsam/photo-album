package model;

import model.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

/**
 * Canvas is a photo comprised of various Shapes.
 */
public class Canvas implements ICanvas {
  private List<IShape> shapeList;
  private List<ISnapshot> album; // map of Snapshots saved
  
  /**
   * Canvas class constructor.
   */
  public Canvas() {
    shapeList = new ArrayList<>();
    album = new ArrayList<>();
  }
  
  @Override
  public ISnapshot createSnapshot(String description) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }
    ISnapshot snap = new Snapshot(this, description);
    album.add(snap);
    return snap;
  }
  
  @Override
  public List<ISnapshot> getAlbum() {
    return new ArrayList<>(album);
  }
  
  @Override
  public void addShape(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Cannot add null shape");
    }
    shapeList.add(shape);
  }
  
  @Override
  public boolean removeShape(IShape shape) {
    if (shape == null) {
      return false;
    }
    return shapeList.remove(shape);
  }
  
  @Override
  public void clearCanvas() {
    shapeList = new ArrayList<>();
  }
  
  @Override
  public IShape getShape(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("ID of shape cannot be empty/null");
    }
    if (shapeList.isEmpty()) {
      return null;
    }
    for (IShape shape: shapeList) {
      if (shape.getName().equals(name)) {
        return shape;
      }
    }
    return null;
  }
  
  @Override
  public List<IShape> getShapeList() {
    List<IShape> newList = new ArrayList<>();
    for (IShape shape: shapeList) {
      newList.add(shape);
    }
    return newList;
  }
  
  @Override
  public void setShapeList(List<IShape> newShapeList) {
    if (newShapeList == null) {
      throw new IllegalArgumentException("New shape list cannot be null");
    }
    this.shapeList = newShapeList;
  }
  
  @Override
  public String printSnapshots() {
    String result = "Printing Snapshots\n";
    for (ISnapshot snap: album) {
      result = result.concat(snap.print() + "\n");
    }
    return result;
  }
  
  private String getShapesMarkup() {
    StringBuilder markup = new StringBuilder();
    if (shapeList.isEmpty()) {
      return "";
    }
    markup = new StringBuilder("<svg width=\"1000\" height=\"1000\">\n");
    for (IShape shape: shapeList) {
      markup.append(shape.getMarkup());
    }
    markup.append("</svg>\n");
    return markup.toString();
  }
  
  @Override
  public String getSnapshotsMarkup() {
    StringBuilder bodyMarkup = new StringBuilder();
    for (ISnapshot snap: album) {
      snap.restore();
      bodyMarkup.append("<div class=\"snapshot\">\n");
      bodyMarkup.append(String.format("<h2>%s</h2>\n", snap.getID()));
      if (!snap.getDescription().isEmpty()) {
        bodyMarkup.append(String.format("<p>%s</p>\n", snap.getDescription()));
      }
      bodyMarkup.append(getShapesMarkup());
      bodyMarkup.append("</div>\n");
      bodyMarkup.append("<p></p>\n");
    }
    return bodyMarkup.toString();
  }
}
