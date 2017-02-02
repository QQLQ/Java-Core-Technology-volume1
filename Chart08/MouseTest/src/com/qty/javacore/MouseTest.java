package com.qty.javacore;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MouseTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MouseFrame frame = new MouseFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame containing a panel for testing mouse operations
 */
class MouseFrame extends JFrame {
	
	public MouseFrame() {
		setTitle("MouseTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// add component to frame
		MouseComponent component = new MouseComponent();
		add(component);
	}
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
}

/**
 * A component with mouse operations for adding and removing squares.
 */
class MouseComponent extends JComponent {
	
	public MouseComponent() {
		squares = new ArrayList<Rectangle2D>();
		current = null;
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// draw all squares
		for (Rectangle2D r : squares) {
			g2.draw(r);
		}
	}
	
	/**
	 * Finds the first square containing a point
	 * @param p a point
	 * @return the first square that contains p
	 */
	public Rectangle2D find(Point2D p) {
		for (Rectangle2D r : squares) {
			if (r.contains(p)) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Adds a square to the collection.
	 * @param p the center of the square
	 */
	public void add(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		
		current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		squares.add(current);
		repaint();
	}
	
	/**
	 * Removes a square from the collection.
	 * @param s the square to remove
	 */
	public void remove(Rectangle2D s) {
		if (s == null) {
			return;
		}
		if (s == current) {
			current = null;
		}
		squares.remove(s);
		repaint();
	}
	
	private static final int SIDELENGTH = 10;
	private ArrayList<Rectangle2D> squares;
	private Rectangle2D current;
	
	// the square containing the mouse cursor
	
	private class MouseHandler extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent event) {
			// add a new square is the cursor isn't inside a square
			current = find(event.getPoint());
			
			if (current == null) {
				add(event.getPoint());
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent event) {
			// remove the current square if double clicked
			current = find(event.getPoint());
			if (current != null && event.getClickCount() >= 2) {
				remove(current);
			}
		}
	}
	
	private class MouseMotionHandler implements MouseMotionListener {
		
		@Override
		public void mouseMoved(MouseEvent event) {
			// set the mouse cursor to cross hairs if it is inside
			// a rectangle
			if (find(event.getPoint()) == null) {
				setCursor(Cursor.getDefaultCursor());
			} else {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}
		
		public void mouseDragged(MouseEvent event) {
			if (current != null) {
				int x = event.getX();
				int y = event.getY();
				
				// drag the current rectangle to center it at (x, y)
				current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
				repaint();
			}
		}
	}
}