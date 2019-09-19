// Name: Zhibo Wang
// USC NetID: 7476147450
// CS 455 PA1
// Fall 2019

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class A labeled bar that can serve as a single bar in a bar graph. The
 * text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change the
 * public interface. You can add private instance variables, constants, and
 * private methods to the class. You will also be completing the implementation
 * of the methods given.
 * 
 */
public class Bar {
   private int bottom; // initialize instance variables
   private int left;
   private int width;
   private int barHeight;
   private double scale;
   private Color color;
   private String label;
   // initialize instance variables for draw method
   private int labelwidth;
   private int labelheight;
   private int x; // x-y axis variables for bars and labels
   private int y;
   private int x2;
   private int y2;
   private int height; // bar height

   /**
    * Creates a labeled bar. You give the height of the bar in application units
    * (e.g., population of a particular state), and then a scale for how tall to
    * display it on the screen (parameter scale).
    * 
    * @param bottom    location of the bottom of the label
    * @param left      location of the left side of the bar
    * @param width     width of the bar (in pixels)
    * @param barHeight height of the bar in application units
    * @param scale     how many pixels per application unit
    * @param color     the color of the bar
    * @param label     the label at the bottom of the bar
    */
   public Bar(int bottom, int left, int width, int barHeight, double scale, Color color, String label) {
      this.bottom = bottom;
      this.left = left;
      this.width = width;
      this.barHeight = barHeight;
      this.scale = scale;
      this.color = color;
      this.label = label;
   }

   /**
    * Draw the labeled bar.
    * 
    * @param g2 the graphics context
    */
   public void draw(Graphics2D g2) {
      Rectangle2D labelbounds = g2.getFont().getStringBounds(label, g2.getFontRenderContext());
      labelwidth = (int) labelbounds.getWidth();
      labelheight = (int) labelbounds.getHeight();

      height = Math.round(barHeight * (float) scale);
      x = left; // bar x axis location
      y = (int) g2.getClipBounds().getMaxY() - height - bottom - labelheight; // bar y axis location

      Rectangle bar = new Rectangle(x, y, width, height);
      g2.setColor(color);
      g2.fill(bar);

      x2 = (int) (x + width / 2) - labelwidth / 2; // to center the label under the bar
      y2 = y + Math.round(barHeight * (float) scale) + labelheight; // label should be right below the bar with fixed
                                                                    // vertical distance
      g2.setColor(Color.black);
      g2.drawString(label, x2, y2);

   }
}
