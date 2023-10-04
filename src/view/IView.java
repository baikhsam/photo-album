package view;

import model.shapes.IShape;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interface for the Program View.
 */
public interface IView {
  
  /**
   * Populate View with all Snapshots in markup form.
   * @param markup SVG markup of Snapshots to inject into WebView's HTML body.
   */
  void populateView(String markup);
  
  /**
   * Populate View with specific Snapshot's shape list.
   * @param id of the Snapshot.
   * @param description of the Snapshot.
   * @param shapes to draw on the View.
   */
  void populateView(String id, String description, List<IShape> shapes);
  
  /**
   * Set the ActionListener for GraphicalView's user input.
   * @param listener the ActionListener.
   */
  void setActionListener(ActionListener listener);
  
  /**
   * Return String of View type.
   * @return String describing view type.
   */
  String getViewType();
  
}
