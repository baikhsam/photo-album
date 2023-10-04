import controller.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.SwingUtilities;

/**
 * Entry point for the Photo Album program.
 */
public class PhotoAlbumMain {
  /**
   * Entry point for program. Takes in arguments for inputs.
   * @param args command-line arguments as inputs for the controller to initialize the view.
   */
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg).append(" ");
    }
    String commands = sb.toString();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          new Controller().initializeProgram(new StringReader(commands));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }
}