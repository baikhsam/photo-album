package model;

import model.shapes.IShape;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Snapshot captures the state of the Canvas and saves it.
 */
public class Snapshot implements ISnapshot {
  private ICanvas canvas;
  private String description;
  private String id;
  private String timestamp;
  private List<IShape> shapeList;
  
  /**
   * Snapshot class constructor.
   * @param canvas to take a snapshot of.
   * @param description of the snapshot.
   */
  public Snapshot(ICanvas canvas, String description) {
    this.canvas = canvas;
    this.description = description;
    
    Timestamp stamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat dateFormatUS = new SimpleDateFormat("MM-dd-yyyy");
    SimpleDateFormat dateFormatWorld = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat timeFormatLong = new SimpleDateFormat("HH:mm:ss.SSSSSS");
    id = dateFormatWorld.format(stamp) + "T" + timeFormatLong.format(stamp);
    
    timestamp = dateFormatUS.format(stamp) + " " + timeFormat.format(stamp);
    this.shapeList = canvas.getShapeList();
  }
  
  @Override
  public void restore() {
    canvas.setShapeList(shapeList);
  }
  
  @Override
  public String getID() {
    return id;
  }
  
  @Override
  public String print() {
    String result = String.format("Snapshot ID: %s\n", id);
    result += String.format("Timestamp: %s\n", timestamp);
    result += String.format("Description: %s\n", description);
    result += "Shape Information:\n";
    for (IShape shape: shapeList) {
      result = result.concat(shape.getDescription() + "\n");
    }
    return result;
  }
  
  @Override
  public String getDescription() {
    return description;
  }
  
  @Override
  public List<IShape> getShapeList() {
    return shapeList;
  }
}
