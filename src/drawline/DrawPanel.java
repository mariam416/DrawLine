package drawline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private final ArrayList<Shape> shapes = new ArrayList<>();
    private Point startPoint = null;
    private JButton toggle, clear, eraser, undo;
    private JCheckBox dottedCheckbox, filledCheckbox;
    private JButton rectButton, ovalButton, lineButton;
    private boolean freehandDrawing = false;
    private boolean erasingEnabled = false;
    private boolean dottedLine = false;
    private boolean filledShape = false;
    private Color currentColor = Color.RED;
    private ShapeType selectedShape = ShapeType.LINE; // Default shape

    public DrawPanel() {
   

        // Button to toggle freehand drawing
        toggle = new JButton("Free Hand Drawing");
        toggle.addActionListener(e -> {
            freehandDrawing = !freehandDrawing;
            erasingEnabled = false;
            resetShapeSelection();
            toggle.setText(freehandDrawing ? "Stop Free Hand Drawing" : "Free Hand Drawing");
        });
        add(toggle);

        // Button to clear all shapes
        clear = new JButton("Clear All");
        clear.addActionListener(e -> {
            shapes.clear();
            repaint();
        });
        add(clear);

        // Button to enable eraser mode
        eraser = new JButton("Eraser");
        eraser.addActionListener(e -> {
            freehandDrawing = false;
            erasingEnabled = true;
            resetShapeSelection();
            toggle.setText("Free Hand Drawing");
        });
        add(eraser);

        // Button to undo the last action
        undo = new JButton("Undo");
        undo.addActionListener(e -> {
            if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1);
                repaint();
            }
        });
        add(undo);

        // Color buttons
        JButton redButton = new JButton("Red");
        redButton.setForeground(Color.RED);
        redButton.addActionListener(new ColorButtonListener(Color.RED));
        add(redButton);

        JButton greenButton = new JButton("Green");
        greenButton.setForeground(Color.GREEN);
        greenButton.addActionListener(new ColorButtonListener(Color.GREEN));
        add(greenButton);

        JButton blueButton = new JButton("Black");
        blueButton.setForeground(Color.BLACK);
        blueButton.addActionListener(new ColorButtonListener(Color.BLACK));
        add(blueButton);

        // Dotted line checkbox
        dottedCheckbox = new JCheckBox("Dotted");
        dottedCheckbox.addItemListener(e -> {
            dottedLine = e.getStateChange() == ItemEvent.SELECTED;
            repaint();
        });
        add(dottedCheckbox);

        // Filled checkbox
        filledCheckbox = new JCheckBox("Filled");
        filledCheckbox.addItemListener(e -> {
            filledShape = e.getStateChange() == ItemEvent.SELECTED;
            repaint();
        });
        add(filledCheckbox);

        // Shape buttons
        rectButton = new JButton("Rectangle");
        rectButton.addActionListener(e -> {
            freehandDrawing = false;
            erasingEnabled = false;
            selectedShape = ShapeType.RECTANGLE;
            toggle.setText("Free Hand Drawing");
        });
        add(rectButton);

        ovalButton = new JButton("Oval");
        ovalButton.addActionListener(e -> {
            freehandDrawing = false;
            erasingEnabled = false;
            selectedShape = ShapeType.OVAL;
            toggle.setText("Free Hand Drawing");
        });
        add(ovalButton);

        lineButton = new JButton("Line");
        lineButton.addActionListener(e -> {
            freehandDrawing = false;
            erasingEnabled = false;
            selectedShape = ShapeType.LINE;
            toggle.setText("Free Hand Drawing");
        });
        add(lineButton);

        // Mouse event handlers
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    private class ColorButtonListener implements ActionListener {
        private final Color color;

        public ColorButtonListener(Color color) {
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currentColor = color;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : shapes) {
            g.setColor(shape.color);
            if (shape.dotted && shape.type == ShapeType.LINE) {
                drawDottedLine(g, shape.start.x, shape.start.y, shape.end.x, shape.end.y);
            } else if (shape.type == ShapeType.LINE) {
                g.drawLine(shape.start.x, shape.start.y, shape.end.x, shape.end.y);
            } else if (shape.type == ShapeType.RECTANGLE) {
                int x = Math.min(shape.start.x, shape.end.x);
                int y = Math.min(shape.start.y, shape.end.y);
                int width = Math.abs(shape.end.x - shape.start.x);
                int height = Math.abs(shape.end.y - shape.start.y);
                if (shape.filled) {
                    g.fillRect(x, y, width, height);
                } else {
                    g.drawRect(x, y, width, height);
                }
            } else if (shape.type == ShapeType.OVAL) {
                int x = Math.min(shape.start.x, shape.end.x);
                int y = Math.min(shape.start.y, shape.end.y);
                int width = Math.abs(shape.end.x - shape.start.x);
                int height = Math.abs(shape.end.y - shape.start.y);
                if (shape.filled) {
                    g.fillOval(x, y, width, height);
                } else {
                    g.drawOval(x, y, width, height);
                }
            }
        }
    }

    private void drawDottedLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;
        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            if (i % 2 == 0) {
                g.fillRect((int) x, (int) y, 1, 1);
            }
            x += xIncrement;
            y += yIncrement;
        }
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {
        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (startPoint != null) {
                shapes.add(new Shape(startPoint, e.getPoint(), currentColor, dottedLine, selectedShape, filledShape));
                startPoint = null;
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (freehandDrawing && startPoint != null) {
                shapes.add(new Shape(startPoint, e.getPoint(), currentColor, dottedLine, selectedShape, filledShape));
                startPoint = e.getPoint();
                repaint();
            } else if (erasingEnabled) {
                eraseShape(e.getPoint());
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        private void eraseShape(Point point) {
            shapes.removeIf(shape -> shapeIntersects(shape, point));
            repaint();
        }

        private boolean shapeIntersects(Shape shape, Point point) {
            if (shape.type == ShapeType.LINE) {
                return pointToLineDistance(shape.start, shape.end, point) <= 5;
            } else if (shape.type == ShapeType.RECTANGLE) {
                Rectangle rect = new Rectangle(
                    Math.min(shape.start.x, shape.end.x),
                    Math.min(shape.start.y, shape.end.y),
                    Math.abs(shape.end.x - shape.start.x),
                    Math.abs(shape.end.y - shape.start.y)
                );
                return rect.contains(point);
            } else if (shape.type == ShapeType.OVAL) {
                Ellipse2D oval = new Ellipse2D.Double(
                    Math.min(shape.start.x, shape.end.x),
                    Math.min(shape.start.y, shape.end.y),
                    Math.abs(shape.end.x - shape.start.x),
                    Math.abs(shape.end.y - shape.start.y)
                );
                return oval.contains(point);
            }
            return false;
        }

        private double pointToLineDistance(Point a, Point b, Point p) {
            double ax = a.x, ay = a.y;
            double bx = b.x, by = b.y;
            double px = p.x, py = p.y;

            double lengthSquared = Math.pow(bx - ax, 2) + Math.pow(by - ay, 2);
            if (lengthSquared == 0) return a.distance(p);

            double t = ((px - ax) * (bx - ax) + (py - ay) * (by - ay)) / lengthSquared;
            t = Math.max(0, Math.min(1, t));

            double closestX = ax + t * (bx - ax);
            double closestY = ay + t * (by - ay);

            return p.distance(closestX, closestY);
        }
    }

    private static class Shape {
        final Point start;
        final Point end;
        final Color color;
        final boolean dotted;
        final ShapeType type;
        final boolean filled;

        Shape(Point start, Point end, Color color, boolean dotted, ShapeType type, boolean filled) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.dotted = dotted;
            this.type = type;
            this.filled = filled;
        }
    }

    private enum ShapeType {
        LINE, RECTANGLE, OVAL
    }

    private void resetShapeSelection() {
        selectedShape = ShapeType.LINE;
        dottedLine = false;
        filledShape = false;
        dottedCheckbox.setSelected(false);
        filledCheckbox.setSelected(false);
    }
}
