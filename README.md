# DrawLine
Overview

Painter is a simple Java Swing drawing application that allows users to create and edit basic graphics using an intuitive graphical interface. The application supports drawing lines, rectangles, and ovals with customizable colors and styles, as well as freehand drawing and basic editing features.

Features
 Draw three types of shapes:
Line
Rectangle
Oval
Freehand drawing mode
 Multiple drawing colors:
Red
Green
Black
 Dotted line option
 Filled rectangle and oval option
 Eraser tool for removing shapes
 Undo the last drawing action
 Clear all drawings
 User-friendly Java Swing interface
Technologies Used
Java
Java Swing (GUI)
AWT Graphics
Event Listeners
Object-Oriented Programming (OOP)
Project Structure
drawline/
│
├── DrawLine.java      # Main class that launches the application
├── DrawPanel.java     # Drawing panel and application logic
└── README.md
How to Run
Clone or download the project.
Open the project in your preferred Java IDE (NetBeans, IntelliJ IDEA, or Eclipse).
Compile and run:
DrawLine.java

or from the command line:

javac drawline/*.java
java drawline.DrawLine
How to Use
Launch the application.
Select a drawing color.
Choose one of the available drawing modes:
Line
Rectangle
Oval
Free Hand Drawing
Optionally enable:
Dotted lines
Filled shapes
Click and drag the mouse to draw.
Use:
Undo to remove the last shape.
Eraser to delete shapes by dragging over them.
Clear All to remove every drawing.
Application Features
Feature	Description
Line Drawing	Draw straight lines
Rectangle	Draw outlined or filled rectangles
Oval	Draw outlined or filled ovals
Freehand Drawing	Draw continuously with the mouse
Color Selection	Choose between Red, Green, and Black
Dotted Lines	Draw lines using a dotted style
Filled Shapes	Fill rectangles and ovals with the selected color
Undo	Remove the most recently drawn shape
Eraser	Delete shapes by dragging over them
Clear All	Remove all shapes from the canvas
Future Improvements
Color picker instead of predefined colors
Adjustable brush size
Save drawing as an image
Open existing drawings
Redo functionality
Shape preview while dragging
Keyboard shortcuts
Additional shapes (circle, polygon, triangle)
Author

Developed as a Java Swing graphics and event-driven programming project demonstrating GUI development, mouse event handling, and object-oriented design.
