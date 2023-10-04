Design Documentation:

Changes made from HW8:
Added methods in Shape classes and Snapshot classes to output String of SVG markup for WebView.
This change was necessary so that the View can receive the necessary markup to display snapshots.
Model classes still handle the logic and the controller receives the markup from the model to send
to the View.

Added getDrawableShape method class in IShape, such that each Shape can be drawn by Graphics2D.
This change was necessary so that the Graphical View can draw 2DGraphics supported shapes.
The Model still handles the logic and the View simply receives a shape to draw.

Controller Design:
The Controller handles and manages all information flow between the Model and the View. To start the
program, the Controller's initializeProgram method is called, which consequently calls private
helper methods to initialize the Model and then the View. The Controller also handles any user input
 and manages the ActionListener. Upon user input, the Controller requests updates from the model and
  sends any state changes to the View.

The Controller also handles the command file reading and scanning in a separate private helper
method, that can be redesigned upon a Client's discretion.

View Design:
The WebView uses a skeleton template HTML file before replacing the body section of the markup with
a new body provided by the Controller. The Model handles all markup generation.

The GraphicalView uses the Swing library to create a frame composed of a container JPanel. This
container holds the top information panel, the canvas drawing panel and the control panel at the
bottom. The information panel and canvas panel are updated in the View by the Controller which
requests data from the Model's Snapshots.


