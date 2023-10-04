package view;

import model.shapes.IShape;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents the Canvas on view in which shapes are drawn.
 */
public class DrawPanel extends JPanel {
  private List<Object> drawableShapes = new ArrayList<>();
  private List<Color> drawableShapeColors = new ArrayList<>();
  
  /**
   * DrawPanel class constructor.
   * @param width of the panel.
   * @param height of the panel.
   */
  public DrawPanel(int width, int height) {
    this.setPreferredSize(new Dimension(width, height));
    this.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.setBackground(new Color(173, 216, 230));
    this.setVisible(true);
  }
  
  /**
   * Update the Shapes to be drawn on the panel.
   * @param shapes to draw.
   */
  public void updateDrawPanel(List<IShape> shapes) {
    drawableShapes = new ArrayList<>();
    drawableShapeColors = new ArrayList<>();
    for (IShape shape: shapes) {
      drawableShapes.add(shape.getDrawableShape());
      drawableShapeColors.add(new Color((int) shape.getColor().getR(),
          (int) shape.getColor().getG(), (int) shape.getColor().getB()));
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if (!drawableShapes.isEmpty()) {
      for (int i = 0; i < drawableShapes.size(); i++) {
        Object s = drawableShapes.get(i);
        g2.setColor(drawableShapeColors.get(i));
        g2.fill((Shape) s);
      }
      
    }
  }
}
