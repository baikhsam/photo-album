package view;

import model.shapes.IShape;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GraphicalView implementation of the IView. GraphicalView is an interactive view based on Swing.
 */
public class GraphicalView extends JFrame implements IView {
  private final double canvas_size_ratio = 0.9;
  private JFrame frame;
  private JPanel infoPanel;
  private DrawPanel canvasPanel;
  private JPanel controlPanel;
  private int frameWidth;
  private int frameHeight;
  private JButton previousButton;
  private JButton selectButton;
  private JButton nextButton;
  private JButton quitButton;
  private JComboBox<String> selectMenu;
  private JLabel details;
  
  /**
   * GraphicalView class constructor.
   */
  public GraphicalView(int frameWidth, int frameHeight) {
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    frame = new JFrame("Photo Album");
    frame.setSize(frameWidth, frameHeight);
    initializeView();
  }
  
  private void initializeView() {
    JPanel container = new JPanel();
    container.setPreferredSize(new Dimension(frameWidth, frameHeight));
    container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
    
    // Panel One: ID + Optional Description
    infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    infoPanel.setBackground(Color.pink);
    details = new JLabel();
    infoPanel.add(details);
    container.add(infoPanel);
    
    // Panel Two: canvasPanel
    canvasPanel = new DrawPanel(frameWidth, (int) (frameHeight * canvas_size_ratio));
    container.add(canvasPanel);
    
    // Panel Three: controlPanel
    controlPanel = new JPanel();
    controlPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    controlPanel.setBackground(Color.orange);
    container.add(controlPanel);
    initializeButtons();
    
    frame.add(container);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);
  }
  
  private void initializeButtons() {
    previousButton = new JButton("<< Prev <<");
    previousButton.setActionCommand("prev");
    
    selectButton = new JButton("^^ Select ^^");
    selectButton.setActionCommand("select");
    
    nextButton = new JButton(">> Next >>");
    nextButton.setActionCommand("next");
    
    quitButton = new JButton("xx Quit xx");
    quitButton.setActionCommand("quit");
    
    controlPanel.add(previousButton);
    controlPanel.add(selectButton);
    controlPanel.add(nextButton);
    controlPanel.add(quitButton);
  }
  
  @Override
  public void setActionListener(ActionListener listener) {
    previousButton.addActionListener(listener);
    selectButton.addActionListener(listener);
    nextButton.addActionListener(listener);
    quitButton.addActionListener(listener);
  }
  
  @Override
  public String getViewType() {
    return "graphical";
  }
  
  @Override
  public void populateView(String markup) {
    throw new UnsupportedOperationException("Wrong overloaded populateView method used.");
  }
  
  @Override
  public void populateView(String id, String description, List<IShape> shapes) {
    // populate Snap ID and optional description
    details.setText(String.format("<html>%s<br/>%s</html>", id, description));
    details.setVisible(true);
    
    // draw Shapes
    canvasPanel.updateDrawPanel(shapes);
    canvasPanel.validate();
    canvasPanel.repaint();
  }
  
  /**
   * Display JDialog message indicating no previous snapshot to show on View.
   */
  public void showNoPreviousDialog() {
    JOptionPane.showMessageDialog(frame, "At the start of the photo album. No"
        + " previous Snapshot to show.");
  }
  
  /**
   * Display JDialog message indicating no next snapshot to show on View.
   */
  public void showNoNextDialog() {
    JOptionPane.showMessageDialog(frame, "At the end of the photo album. No"
        + " next Snapshot to show.");
  }
  
  /**
   * Display Select Snapshot menu. Choosing cancel option cancels selection.
   * @param idList list of Snapshot IDs to choose from.
   * @param index of the current selected Snapshot.
   * @return index of selected Snapshot.
   */
  public int showSelectMenu(String[] idList, int index) {
    selectMenu = new JComboBox<String>(idList);
    selectMenu.setSelectedIndex(index);
    String[] options = {"Confirm", "Cancel"};
    int option = JOptionPane.showOptionDialog(null, selectMenu, "Choose",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
    if (option == 1) {
      return index;
    }
    return selectMenu.getSelectedIndex();
  }
}
