# DrawLine
Overview
-
Painter is a simple Java Swing drawing application that allows users to create and edit basic graphics using an intuitive graphical interface. The application supports drawing lines, rectangles, and ovals with customizable colors and styles, as well as freehand drawing and basic editing features.
_________________________________________________________________________________________________
Features :
-
 1.Draw three types of shapes:
  Line
  Rectangle
  Oval
2.Freehand drawing mode
3.Multiple drawing colors:
  Red
 Green
  Black
 4.Dotted line option
 5.Filled rectangle and oval option
 6.Eraser tool for removing shapes
 7.Undo the last drawing action
 8.Clear all drawings
 9.User-friendly Java Swing interface
______________________________________________________________________________________________

 
Technologies Used
-
 Java
 Java Swing (GUI)
 AWT Graphics
 Event Listeners
 Object-Oriented Programming (OOP)

______________________________________________________________________________________________

Project Structure
-
drawline/
│
├── DrawLine.java      # Main class that launches the application
├── DrawPanel.java     # Drawing panel and application logic
└── README.md
________________________________________________________________________________________________

How to Run
-
1.Clone or download the project.
2.Open the project in your preferred Java IDE (NetBeans, IntelliJ IDEA, or Eclipse).
3.Compile and run:
DrawLine.java

or from the command line:

javac drawline/*.java
java drawline.DrawLine

______________________________________________________________________________________________

How to Use
-
1.Launch the application.
2.Select a drawing color.
3.Choose one of the available drawing modes:
  Line
  Rectangle
  Oval
  Free Hand Drawing
4.Optionally enable:
  Dotted lines
  Filled shapes
5.Click and drag the mouse to draw.
6.Use:
  Undo to remove the last shape.
  Eraser to delete shapes by dragging over them.
  Clear All to remove every drawing.
  _________________________________________________________________________________________________
  
  Application Features
  -
Feature	           Description
-
Line Drawing	   Draw straight lines
Rectangle	       Draw outlined or filled rectangles
Oval	           Draw outlined or filled ovals
Freehand Drawing   Draw continuously with the mouse
Color Selection	   Choose between Red, Green, and Black
Dotted Lines	   Draw lines using a dotted style
Filled Shapes	   Fill rectangles and ovals with the selected color
Undo	           Remove the most recently drawn shape
Eraser	           Delete shapes by dragging over them
Clear All	       Remove all shapes from the canvas
___________________________________________________________________________________________________

Future Improvements
-
Color picker instead of predefined colors
Adjustable brush size
Save drawing as an image
Open existing drawings
Redo functionality
Shape preview while dragging
Keyboard shortcuts
Additional shapes (circle, polygon, triangle)
_______________________________________________________________________________________________
Author
-
Developed as a Java Swing graphics and event-driven programming project demonstrating GUI development, mouse event handling, and object-oriented design.
