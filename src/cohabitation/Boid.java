import java.awt.Point;

public class Boid {
    private Point p;
    private Vect2D v;

    public Boid(){
        Point pos = new Point(0,0);
        Vect2D vit = new Vect2D(1,0);
        this.p = pos;
        this.v = vit;
    }

    public void setP(int px, int py) {
        this.p.setLocation(px,py);
    }
    public void setV(int vx, int vy) {
        this.v.setLocation(vx,vy);
    }

    public Point getP () {
        return this.p;
    }
    public Vect2D getV () {
        return this.v;
    }

    public int getPx () {
        return (int)this.p.getX();
    }
    public int getPy () {
        return (int)this.p.getY();
    }
    public int getVx () {
        return (int)this.v.getX();
    }
    public int getVy () {
        return (int)this.v.getY();
    }

}
