package model;

import model.shapes.IShape;

import java.util.List;

/**
 * Canvas interface implements the logic and backend of the photo album. Canvas  implements the
 * model state and any changes to that state. A Canvas has various Shapes on it to create a photo.
 */
public interface ICanvas {
  
  /**
   * Create a snapshot of the Canvas and save it to the album list.
   * @param description of the Snapshot picture.
   * @return ISnapshot object that saves the state of the canvas.
   */
  ISnapshot createSnapshot(String description);
  
  /**
   * Return the album of all Snapshots saved.
   * @return List of Snapshots saved.
   */
  List<ISnapshot> getAlbum();
  
  /**
   * Add a Shape to the canvas.
   * @param shape to put on the canvas.
   */
  void addShape(IShape shape);
  
  /**
   * Remove a Shape from the canvas.
   * @param shape to remove from canvas.
   * @return true if shape was removed, false if not.
   */
  boolean removeShape(IShape shape);
  
  /**
   * Clears the canvas by removing all Shapes.
   */
  void clearCanvas();
  
  /**
   * Get Shape from the canvas.
   * @param id or name of the Shape.
   * @return Shape requested by id.
   */
  IShape getShape(String id);
  
  /**
   * Get the list of Shapes on the canvas. List is a deep copy of the Canvas shape list.
   * @return ArrayList of Shapes.
   */
  List<IShape> getShapeList();
  
  /**
   * Set the Canvas shape list to a new shape list.
   * @param newShapeList to replace the current shape list.
   */
  void setShapeList(List<IShape> newShapeList);
  
  /**
   * Print the details of all Snapshots saved.
   * @return String of all saved Snapshots.
   */
  String printSnapshots();
  
  /**
   * Loop through Canvas' shape list and generate the SVG markup for each shape in the Snapshot.
   * @return SVG formatted string of the body to inject into the HTML file output.
   */
  String getSnapshotsMarkup();
}
