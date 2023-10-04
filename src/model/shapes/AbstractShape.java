package model.shapes;

/**
 * AbstractShape class with properties and operations that all IShapes should have.
 */
public abstract class AbstractShape implements IShape {
  protected double x;
  protected double y;
  protected Color color;
  protected String name;
  
  /**
   * AbstractShape constructor.
   * @param color of the shape.
   * @param x coordinate of shape.
   * @param y coordinate of shape.
   * @param name or ID of the shape.
   */
  public AbstractShape(Color color, double x, double y, String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name of Shape cannot be empty/null");
    }
    this.color = color;
    this.x = x;
    this.y = y;
    this.name = name;
  }
  
  @Override
  public abstract void resize(double... args);

  @Override
  public void setCoordinates(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public void setColor(double r, double g, double b) {
    color.setR(r);
    color.setG(g);
    color.setB(b);
  }
  
  @Override
  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name of Shape cannot be empty/null");
    }
    this.name = name;
  }
  
  @Override
  public double getX() {
    return this.x;
  }
  
  @Override
  public double getY() {
    return this.y;
  }
  
  @Override
  public String getName() {
    return this.name;
  }
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public String getDescription() {
    return "AbstractShape";
  }
}
