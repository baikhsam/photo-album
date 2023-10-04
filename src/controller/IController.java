package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for Controller class. Controller handles information flow between Model and View.
 */
public interface IController {
  
  /**
   * Translates command line arguments and feeds the arguments into the initializeView method.
   * @param r Readable input source for commands.
   */
  void initializeProgram(Readable r) throws IOException;
  
  /**
   * Calls the ICanvas to create a shape based on given arguments.
   * @param id of the Shape.
   * @param type of the Shape.
   * @param x coordinate of the Shape.
   * @param y coordinate of the Shape.
   * @param firstDimension of the Shape.
   * @param secondDimension of the Shape.
   * @param red value of RGB color of Shape.
   * @param green value of RGB color of Shape.
   * @param blue value of RGB color of Shape.
   */
  void createShape(String id, String type, double x, double y, double firstDimension,
                   double secondDimension, double red, double green, double blue);
  
  /**
   * Translate the shape to given coordinates.
   * @param id of the Shape to move.
   * @param x coordinate to move the Shape to.
   * @param y coordinate to move the Shape to.
   */
  void moveShape(String id, double x, double y);
  
  /**
   * Change the color of the Shape.
   * @param id of the Shape to change.
   * @param red value of RGB color of Shape.
   * @param green value of RGB color of Shape.
   * @param blue value of RGB color of Shape.
   */
  void changeShapeColor(String id, double red, double green, double blue);
  
  /**
   * Resize the dimensions of the Shape.
   * @param id of the Shape to change.
   * @param firstDimension of the Shape to resize.
   * @param secondDimension of the Shape to resize.
   */
  void resizeShape(String id, double firstDimension, double secondDimension);
  
  /**
   * Remove a Shape from the Canvas.
   * @param id of the Shape to remove.
   */
  void removeShape(String id);
  
  /**
   * Create a Snapshot of the current Canvas.
   * @param description of the Snapshot.
   */
  void takeSnapshot(String description);
}
