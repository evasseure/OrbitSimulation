
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * V = W * R
 * Created by Erwan on 21/05/2015.
 */
public class Simulation
{
    public static Double G = 6.67428 * Math.pow(10, -11);
    public static Integer dt = 24 * 3600;
    public static Double au = 149.6 * Math.pow(10, 6) * 1000;
    public static Double dScale = 175.0 / au;
    public static Double rScale = 1.0 / 200000.0;
    public static Integer delay = 10;

    private MainPanel panel;
    private ArrayList<Body> bodies;

    public Simulation(MainPanel p)
    {
        panel = p;
        bodies = new ArrayList<>();

        Body sun = new Body(
                "Sun",
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0),
                1.98892 * Math.pow(10, 30),
                10000.0 * 1000
        );

        Body mercury = new Body(
                "Mercury",
                new Point2D.Double(-0.387 * au, 0),
                new Point2D.Double(0, 47.362 * 1000),
                0.3301 * Math.pow(10, 24),
                2439.7 * 1000
        );

        Body venus = new Body(
                "Venus",
                new Point2D.Double(-0.722 * au, 0),
                new Point2D.Double(0, 35.0214 * 1000),
                4.8676 * Math.pow(10, 24),
                6051.0 * 1000
        );

        Body earth = new Body(
                "Earth",
                new Point2D.Double(-1 * au, 0),
                new Point2D.Double(0, 29.783 * 1000),
                5.9726 * Math.pow(10, 24),
                6356.8 * 1000
        );


        Body mars = new Body(
                "Mars",
                new Point2D.Double(-1.52 * au, 0),
                new Point2D.Double(0, 24.1309 * 1000),
                6.4185 * Math.pow(10, 23),
                3376.2 * 1000
        );

        bodies.add(sun);
        bodies.add(mercury);
        bodies.add(venus);
        bodies.add(earth);
        bodies.add(mars);
    }

    private Point.Double getAttraction(Body b1, Body b2)
    {
        // Distance from the two bodies
        Double xd = (b2.getPos().getX() - b1.getPos().getX());
        Double yd = (b2.getPos().getY() - b1.getPos().getY());
        Double distance = Math.sqrt(xd * xd + yd * yd);

        // Isaac Newton's law of universal gravitation
        Double f = G * (b1.getMass() * b2.getMass()) / (distance * distance);

        // Direction of the force
        Double theta = Math.atan2(yd, xd);
        return new Point2D.Double(Math.cos(theta) * f, Math.sin(theta) * f);

    }

    public void launchSim(){
        ActionListener taskPerformer = evt -> {
            panel.updateDisplay(bodies, dScale, rScale);

            ArrayList<Point.Double> forces = new ArrayList<>();
            for (Body b : bodies)
            {
                Double totalXF = 0.0;
                Double totalYF = 0.0;
                for (Body ob : bodies)
                {
                    if (b.equals( ob))
                        continue;
                    Point.Double attraction = getAttraction(b, ob);
                    totalXF += attraction.getX();
                    totalYF += attraction.getY();
                }
                forces.add(new Point2D.Double(totalXF, totalYF));
            }

            int i = 0;
            for (Body b : bodies)
            {
                Point.Double nv = new Point2D.Double(b.getVel().getX() + forces.get(i).getX() / b.getMass() * dt,
                        b.getVel().getY() + forces.get(i).getY() / b.getMass() * dt);
                b.setVel(nv);

                Point.Double np = new Point2D.Double(b.getPos().getX() + b.getVel().getX() * dt,
                        b.getPos().getY() + b.getVel().getY() * dt);
                b.setPos(np);
                i++;
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }
}
