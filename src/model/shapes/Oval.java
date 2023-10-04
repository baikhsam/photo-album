package model.shapes;

import java.awt.geom.Ellipse2D;
import java.util.Objects;

/**
 * Oval Shape. Each oval has the normal IShape properties but has a xRadius and a yRadius.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;
  private final String type;
  
  /**
   * Oval shape constructor.
   * @param color of the oval.
   * @param x coordinate of the oval.
   * @param y coordinate of the oval.
   * @param xRadius of the oval.
   * @param yRadius of the oval.
   */
  public Oval(Color color, double x, double y, double xRadius, double yRadius, String name) {
    super(color, x, y, name);
    if (xRadius <= 0.0 || yRadius <= 0.0) {
      throw new IllegalArgumentException("Radius cannot be negative value");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    this.type = "oval";
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
    xRadius = args[0];
    yRadius = args[1];
  }
  
  @Override
  public String getDescription() {
    String description = String.format("Name: %s\n", name);
    description += String.format("Type: %s\n", type);
    description += String.format("Center: (%.2f, %.2f), X radius: %.2f, Y radius: %.2f, ", x, y,
        xRadius,
        yRadius);
    description += color.toString();
    return description;
  }
  
  @Override
  public String getMarkup() {
    String markup = "";
    markup += String.format("<ellipse id=\"%s\" cx=\"%.2f\" cy=\"%.2f\" rx=\"%.2f\" ry=\"%.2f\" "
        + "fill=\"rgb(%.2f,%.2f,%.2f)\"></ellipse>\n", name, x, y, xRadius, yRadius, color.getR(),
        color.getG(), color.getB());
    return markup;
  }
  
  @Override
  public Object getDrawableShape() {
    return new Ellipse2D.Double(x, y, xRadius, yRadius);
  }
  
  /**
   * Return x radius of oval.
   * @return x radius.
   */
  public double getXRadius() {
    return xRadius;
  }
  
  /**
   * Return y radius of oval.
   * @return y radius.
   */
  public double getYRadius() {
    return yRadius;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Oval other = (Oval) o;
    return x == other.x && y == other.y && xRadius == other.xRadius && yRadius == other.yRadius
        && color.equals(other.color) && name.equals(other.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(x, y, xRadius, yRadius, color, name);
  }
}
