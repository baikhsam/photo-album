import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IController;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

/**
 * JUnit4 test class for WebView.
 */
public class WebViewTest {
  private IController controller;
  
  /**
   * Initialize controller object.
   */
  @Before
  public void setUp() {
    controller = new Controller();
  }
  
  /**
   * Test program using demo_input.txt.
   * Test passed if correct SVG markup is generated in the output file.
   */
  @Test
  public void testDemo() throws IOException {
    controller.initializeProgram(new StringReader("-in demo_input.txt -view web -out demoTest"
        + ".html"));
    StringBuilder sb = new StringBuilder();
    try {
      Scanner scanner = new Scanner(new File("resources/demoTest.html"));
      while (scanner.hasNextLine()) {
        sb.append(scanner.nextLine()).append("\n");
      }
    }
    catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    String output = sb.toString();
    String firstSnap = "<p> After first selfie</p>\n<svg width=\"1000\" height=\"1000\">\n<rect id=\"myrect\" x=\"100.00\" y=\"300.00\" width=\"25.00\" height=\"100.00\" fill=\"rgb(0.00,0.00,255.00)\"></rect>\n<ellipse id=\"myoval\" cx=\"500.00\" cy=\"400.00\" rx=\"60.00\" ry=\"30.00\" fill=\"rgb(0.00,255.00,1.00)\"></ellipse>\n</svg>";
    assertTrue(output.contains(firstSnap));
    String secondSnap = "<p> 2nd selfie</p>\n<svg width=\"1000\" height=\"1000\">\n<rect id=\"myrect\" x=\"100.00\" y=\"300.00\" width=\"25.00\" height=\"100.00\" fill=\"rgb(0.00,0.00,255.00)\"></rect>\n<ellipse id=\"myoval\" cx=\"500.00\" cy=\"400.00\" rx=\"60.00\" ry=\"30.00\" fill=\"rgb(0.00,255.00,1.00)\"></ellipse>\n</svg>";
    assertTrue(output.contains(secondSnap));
    String thirdSnap = "</h2>\n<svg width=\"1000\" height=\"1000\">\n<rect id=\"myrect\" x=\"100.00\" y=\"300.00\" width=\"25.00\" height=\"100.00\" fill=\"rgb(0.00,0.00,255.00)\"></rect>\n<ellipse id=\"myoval\" cx=\"500.00\" cy=\"400.00\" rx=\"60.00\" ry=\"30.00\" fill=\"rgb(0.00,255.00,1.00)\"></ellipse>\n</svg>";
    assertTrue(output.contains(thirdSnap));
    String fourthSnap = "<p> Selfie after removing the rectangle from the picture</p>\n<svg width=\"1000\" height=\"1000\">\n<ellipse id=\"myoval\" cx=\"500.00\" cy=\"400.00\" rx=\"60.00\" ry=\"30.00\" fill=\"rgb(0.00,255.00,1.00)\"></ellipse>\n</svg>";
    assertTrue(output.contains(fourthSnap));
  }
  
  /**
   * Test program using buildings.txt.
   * Test passed if correct SVG markup is generated in the output file.
   */
  @Test
  public void testBuilding() throws IOException {
    controller.initializeProgram(new StringReader("-in buildings.txt -view web -out buildingsOut"
        + ".html"));
    StringBuilder sb = new StringBuilder();
    try {
      Scanner scanner = new Scanner(new File("resources/buildingsOut.html"));
      while (scanner.hasNextLine()) {
        sb.append(scanner.nextLine()).append("\n");
      }
    }
    catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    String output = sb.toString();
    String firstSnap = "</h2>\n<svg width=\"1000\" height=\"1000\">\n<rect id=\"background\" x=\"0.00\" y=\"0.00\" width=\"800.00\" height=\"800.00\" fill=\"rgb(33.00,94.00,248.00)\"></rect>\n<rect id=\"B0\" x=\"80.00\" y=\"424.00\" width=\"100.00\" height=\"326.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B1\" x=\"260.00\" y=\"365.00\" width=\"100.00\" height=\"385.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B2\" x=\"440.00\" y=\"375.00\" width=\"100.00\" height=\"375.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B3\" x=\"620.00\" y=\"445.00\" width=\"100.00\" height=\"305.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"window000\" x=\"100.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window001\" x=\"140.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window010\" x=\"100.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window011\" x=\"140.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n</svg>";
    assertTrue(output.contains(firstSnap));
    String secondSnap = "</h2>\n<svg width=\"1000\" height=\"1000\">\n<rect id=\"background\" x=\"0.00\" y=\"0.00\" width=\"800.00\" height=\"800.00\" fill=\"rgb(33.00,94.00,248.00)\"></rect>\n<rect id=\"B0\" x=\"80.00\" y=\"424.00\" width=\"100.00\" height=\"326.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B1\" x=\"260.00\" y=\"365.00\" width=\"100.00\" height=\"385.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B2\" x=\"440.00\" y=\"375.00\" width=\"100.00\" height=\"375.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B3\" x=\"620.00\" y=\"445.00\" width=\"100.00\" height=\"305.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"window000\" x=\"100.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window001\" x=\"140.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window010\" x=\"100.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window011\" x=\"140.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window002\" x=\"280.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window021\" x=\"320.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window022\" x=\"280.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window200\" x=\"320.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n</svg>";
    assertTrue(output.contains(secondSnap));
    String thirdSnap = "<p> Turn on the Lights!</p>\n<svg width=\"1000\" height=\"1000\">\n<rect id=\"background\" x=\"0.00\" y=\"0.00\" width=\"800.00\" height=\"800.00\" fill=\"rgb(33.00,94.00,248.00)\"></rect>\n<rect id=\"B0\" x=\"80.00\" y=\"424.00\" width=\"100.00\" height=\"326.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B1\" x=\"260.00\" y=\"365.00\" width=\"100.00\" height=\"385.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B2\" x=\"440.00\" y=\"375.00\" width=\"100.00\" height=\"375.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"B3\" x=\"620.00\" y=\"445.00\" width=\"100.00\" height=\"305.00\" fill=\"rgb(0.00,0.00,0.00)\"></rect>\n<rect id=\"window000\" x=\"100.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window001\" x=\"140.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window010\" x=\"100.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window011\" x=\"140.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window002\" x=\"280.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window021\" x=\"320.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window022\" x=\"280.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window200\" x=\"320.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window003\" x=\"460.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window033\" x=\"500.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window333\" x=\"460.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window313\" x=\"500.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window004\" x=\"640.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window044\" x=\"680.00\" y=\"500.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window444\" x=\"640.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<rect id=\"window414\" x=\"680.00\" y=\"600.00\" width=\"20.00\" height=\"20.00\" fill=\"rgb(255.00,255.00,255.00)\"></rect>\n<ellipse id=\"moon\" cx=\"200.00\" cy=\"200.00\" rx=\"100.00\" ry=\"100.00\" fill=\"rgb(229.00,229.00,255.00)\"></ellipse>\n</svg>";
    assertTrue(output.contains(thirdSnap));
  }
  
  /**
   * Test CL input for bogus command file input.
   */
  @Test(expected = NullPointerException.class)
  public void testNoOutputCommandLineInput() throws IOException {
    controller.initializeProgram(new StringReader("-in error.txt -view web -out test.html"));
  }
  
  /**
   * Test CL input for missing input file arg.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMissingCommandFileInput() throws IOException {
    controller.initializeProgram(new StringReader("-view web -out test.html"));
  }
  
  /**
   * Test CL input for missing view type arg.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMissingViewTypeInput() throws IOException {
    controller.initializeProgram(new StringReader("-in demo_input.txt -out test.html"));
  }
  
  /**
   * Test CL input for missing view type arg.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMissingOutputArgInput() throws IOException {
    controller.initializeProgram(new StringReader("-in buildings.txt -view web"));
  }
  
  /**
   * Test CL input for a missing pair of commands.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMissingViewPairCommandLineArgInput() throws IOException {
    controller.initializeProgram(new StringReader("-in buildings.txt -view -out test.html"));
  }
  
  /**
   * Test CL input for a missing pair of commands.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMissingInputPairCommandLineArgInput() throws IOException {
    controller.initializeProgram(new StringReader("-in -v web -out test.html"));
  }
}