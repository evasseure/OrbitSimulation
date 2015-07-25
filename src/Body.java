import java.awt.geom.Point2D;

/**
 * Created by Erwan on 21/05/2015.
 */
public class Body {

    // add size

    private String name;
    private Point2D.Double pos;
    private Point2D.Double vel;
    private Double mass;
    private Double radius;

    public Body(String name, Point2D.Double pos, Point2D.Double vel, Double mass, Double radius) {
        this.name = name;
        this.pos = pos;
        this.vel = vel;
        this.mass = mass;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D.Double getPos() {
        return pos;
    }

    public void setPos(Point2D.Double pos) {
        this.pos = pos;
    }

    public Point2D.Double getVel() {
        return vel;
    }

    public void setVel(Point2D.Double vel) {
        this.vel = vel;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}