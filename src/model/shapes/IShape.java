package model.shapes;

/**
 * Interface for Shape objects. Each Shape has a name, type of shape, coordinates, color, and
 * dimensions.
 */
public interface IShape {
  
  /**
   * Resize IShape to new inputted dimensions.
   * @param args new dimensions to resize Shape to.
   */
  void resize(double... args);
  
  /**
   * Set the coordinates of the Shape.
   * @param x coordinate.
   * @param y coordinate.
   */
  void setCoordinates(double x, double y);
  
  /**
   * Set the color of the Shape.
   * @param r value of the RGB color.
   * @param g value of the RGB color.
   * @param b value of the RGB color.
   */
  void setColor(double r, double g, double b) throws IllegalArgumentException;
  
  /**
   * Set the name of the Shape.
   * @param name of the Shape.
   */
  void setName(String name);
  
  /**
   * Get x coordinate of the Shape.
   * @return x coordinate.
   */
  double getX();
  
  /**
   * Get y coordinate of the Shape.
   * @return y coordinate.
   */
  double getY();
  
  /**
   * Get the name of the Shape.
   * @return name of Shape.
   */
  String getName();
  
  /**
   * Get the type of the Shape.
   * @return type of Shape.
   */
  String getType();
  
  /**
   * Get the color of the Shape.
   * @return Color of shape.
   */
  Color getColor();
  
  /**
   * Get description of the Shape's properties.
   * @return String description of the Shape.
   */
  String getDescription();
  
  /**
   * Get markup of Shape in SVG format.
   * @return SVG markup of Shape.
   */
  String getMarkup();
  
  /**
   * Return Graphics2D supported shape that can be drawn.
   * @return drawable Shape object.
   */
  Object getDrawableShape();
}
