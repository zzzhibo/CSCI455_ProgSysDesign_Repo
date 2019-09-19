// Name: Zhibo Wang
// USC NetID: 7476147450
// CS 455 PA1
// Fall 2019

/**
 * class CoinSimComponent The component draws three bars depending on the
 * cointoss outcome
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import jdk.internal.dynalink.beans.StaticClass;

public class CoinSimComponent extends JComponent {

    private static final long serialVersionUID = 1L; // Default Serialization ID
    private CoinTossSimulator cointoss = new CoinTossSimulator();
    private int tossnum;
    private final int barwidth = 80;
    private final int verticalbuffer = 40;
    private int left;
    private int labelheight;
    private double scale;
    private String twohead_label;
    private String headtail_label;
    private String twotail_label;

    /**
     * CoinSimComponent initialize CoinTossSimulator with given user input
     * 
     */
    public CoinSimComponent(int tossnum) {
        this.tossnum = tossnum;
        cointoss.run(tossnum);
    }

    /**
     * paintcomponent to draw the bar graph by utilizing Bar class and other Java
     * librarys
     * 
     */

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D labelbounds = g.getFont().getStringBounds(" ", g2.getFontRenderContext());

        left = (int) (g.getClipBounds().getMaxX() - 3 * barwidth) / 4;
        labelheight = (int) labelbounds.getHeight();
        scale = (g.getClipBounds().getMaxY() - 2 * verticalbuffer - labelheight) / cointoss.getNumTrials();

        twohead_label = "Two Heads: " + cointoss.getTwoHeads() + "("
                + Math.round((float) cointoss.getTwoHeads() / (float) cointoss.getNumTrials() * 100) + "%)";
        headtail_label = "A Head and a Tail: " + cointoss.getHeadTails() + "("
                + Math.round((float) cointoss.getHeadTails() / (float) cointoss.getNumTrials() * 100) + "%)";
        twotail_label = "Two Tail: " + cointoss.getTwoTails() + "("
                + Math.round((float) cointoss.getTwoTails() / (float) cointoss.getNumTrials() * 100) + "%)";

        Bar two_head_bar = new Bar(verticalbuffer, left, barwidth, cointoss.getTwoHeads(), scale, Color.RED,
                twohead_label);
        Bar head_tail_bar = new Bar(verticalbuffer, left + left + barwidth, barwidth, cointoss.getHeadTails(), scale,
                Color.GREEN, headtail_label);
        Bar two_tail_bar = new Bar(verticalbuffer, left + left + barwidth + left + barwidth, barwidth,
                cointoss.getTwoTails(), scale, Color.BLUE, twotail_label);

        two_head_bar.draw(g2);
        head_tail_bar.draw(g2);
        two_tail_bar.draw(g2);

    }
}
