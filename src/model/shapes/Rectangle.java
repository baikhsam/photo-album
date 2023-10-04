package model.shapes;

import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * Rectangle Shape. Each rectangle has the normal IShape properties but has a width and height.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;
  private final String type;
  
  /**
   * Rectangle shape constructor.
   * @param color of the rectangle.
   * @param x coordinate of the rectangle's lower left corner.
   * @param y coordinate of the rectangle's lower left corner.
   * @param width of the rectangle.
   * @param height of the rectangle.
   */
  public Rectangle(Color color, double x, double y, double width, double height, String name) {
    super(color, x, y, name);
    if (width <= 0.0 || height <= 0.0) {
      throw new IllegalArgumentException("Width or height cannot be negative value");
    }
    this.width = width;
    this.height = height;
    this.type = "rectangle";
  }
  
  @Override
  public String getType() {
    return this.type;
  }
  
  @Override
  public void resize(double... args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Oval has one xRadius and one yRadius");
    }
    if (args[0] <= 0.0 || args[1] <= 0.0) {
      throw new IllegalArgumentException("Radius cannot be negative value");
    }
    this.width = args[0];
    this.height = args[1];
  }
  
  @Override
  public String getDescription() {
    String description = String.format("Name: %s\n", name);
    description += String.format("Type: %s\n", type);
    description += String.format("Min corner: (%.2f, %.2f), Width: %.2f, Height: %.2f, ", x, y,
        width, height);
    description += color.toString();
    return description;
  }
  
  @Override
  public String getMarkup() {
    String markup = "";
    markup += String.format("<rect id=\"%s\" x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" height=\"%.2f\" "
        + "fill=\"rgb(%.2f,%.2f,%.2f)\"></rect>\n", name, x, y, width, height, color.getR(),
        color.getG(), color.getB());
    return markup;
  }
  
  @Override
  public Object getDrawableShape() {
    return new Rectangle2D.Double(x, y, width, height);
  }
  
  /**
   * Return width of rectangle.
   * @return width.
   */
  public double getWidth() {
    return width;
  }
  
  /**
   * Return height of rectangle.
   * @return height.
   */
  public double getHeight() {
    return height;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Rectangle other = (Rectangle) o;
    return x == other.x && y == other.y && width == other.width && height == other.height
        && color.equals(other.color) && name.equals(other.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(x, y, width, height, color, name);
  }
}
