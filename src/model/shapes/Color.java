package model.shapes;

import java.util.Objects;

/**
 * Color enum class. Each color has an RGB value.
 */
public class Color {
  
  private double r;
  private double g;
  private double b;
  
  /**
   * Color constructor.
   * @param r value of RGB.
   * @param g value of RGB.
   * @param b value of RGB.
   */
  public Color(double r, double g, double b) {
    if (r < 0.0 || g < 0.0 || b < 0.0) {
      throw new IllegalArgumentException("RGB values cannot be negative");
    }
    if (r > 255.0 || g > 255.0 || b > 255.0) {
      throw new IllegalArgumentException("RGB values have a max of 255.0");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }
  
  /**
   * Color constructor to create Red, Green or Blue colors quickly.
   * @param color Red, Green or Blue string input.
   */
  public Color(String color) {
    if (color == null || color.isEmpty()) {
      throw new IllegalArgumentException("Color input cannot be null/empty");
    }
    color = color.toLowerCase();
    if (!(color.equals("red") || color.equals("green") || color.equals("blue"))) {
      throw new IllegalArgumentException("Color presets include only Red, Green and Blue");
    }
    switch (color) {
      case ("red"):
        this.r = 255.0;
        this.g = 0.0;
        this.b = 0.0;
        break;
      case ("green"):
        this.r = 0.0;
        this.g = 255.0;
        this.b = 0.0;
        break;
      case ("blue"):
        this.r = 0.0;
        this.g = 0.0;
        this.b = 255.0;
        break;
    }
  }
  
  /**
   * Set the R value of RGB color.
   * @param r value of RGB.
   */
  public void setR(double r) {
    if (r < 0.0 || r > 255.0) {
      throw new IllegalArgumentException("RGB values must be between 0.0 and 255.0");
    }
    this.r = r;
  }
  
  /**
   * Set the G value of RGB color.
   * @param g value of RGB.
   */
  public void setG(double g) {
    if (g < 0.0 || g > 255.0) {
      throw new IllegalArgumentException("RGB values must be between 0.0 and 255.0");
    }
    this.g = g;
  }
  
  /**
   * Set the B value of RGB color.
   * @param b value of RGB.
   */
  public void setB(double b) {
    if (b < 0.0 || b > 255.0) {
      throw new IllegalArgumentException("RGB values must be between 0.0 and 255.0");
    }
    this.b = b;
  }
  
  /**
   * Return R value of RGB color.
   * @return r value of RGB.
   */
  public double getR() {
    return r;
  }
  
  /**
   * Return G value of RGB color.
   * @return g value of RGB.
   */
  public double getG() {
    return g;
  }
  
  /**
   * Return B value of RGB color.
   * @return b value of RGB.
   */
  public double getB() {
    return b;
  }
  
  @Override
  public String toString() {
    return String.format("Color: (%.1f, %.1f, %.1f)\n", r, g, b);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Color other = (Color) o;
    return r == other.r && g == other.g && b == other.b;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(r, g, b);
  }
}
