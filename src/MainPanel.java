import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Erwan on 21/05/2015.
 */
public class MainPanel extends JPanel {

    private BufferedImage display;

    public MainPanel() {
        this.setLayout(new BorderLayout());

        this.display = new BufferedImage(1027, 720, BufferedImage.TYPE_INT_ARGB);
        paintToWhite();
        this.setVisible(true);
    }

    private void paintToWhite()
    {
        for (int i = 0; i < this.display.getWidth(); ++ i)
            for (int j = 0; j < this.display.getHeight(); j++)
                this.display.setRGB(i, j, new Color(255, 255, 255).getRGB());
    }

    public void updateDisplay(ArrayList<Body> bodies, Double unit, Double scale)
    {
        paintToWhite();
        for (Body b : bodies)
        {
            Graphics2D g = display.createGraphics();
            g.setColor(new Color(0, 0, 0));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            final double r = b.getRadius() * scale;
            final double x = (display.getWidth() / 2 + b.getPos().getX() * unit) - r;
            final double y = (display.getHeight() / 2 + b.getPos().getY() * unit) - r;

            g.drawString(b.getName(), (int) x, (int) y);
            g.fillOval((int) x, (int) y, (int) r, (int) r);
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.display, 0, 0, null);
    }
}
