package model;

import model.shapes.IShape;

import java.util.List;

/**
 * A Snapshot is a freeze-frame of the model state. Analogous to a page in a photo album, a
 * Snapshot captures the state of the Shapes and their locations.
 */
public interface ISnapshot {
  
  /**
   * Change the previous state of the Canvas to the state capture by the Snapshot.
   */
  void restore();
  
  /**
   * Return the Snapshot ID.
   * @return String of Snapshot ID.
   */
  String getID();
  
  /**
   * Return Snapshot information and all shapes saved in state.
   * @return String of Snapshot ID, timestamp, description, and shape information.
   */
  String print();
  
  /**
   * Return Snapshot optional description.
   * @return Snapshot description. Can optionally be empty String.
   */
  String getDescription();
  
  /**
   * Return List of Shapes saved from the Snapshot.
   * @return List of Shapes.
   */
  List<IShape> getShapeList();
}
