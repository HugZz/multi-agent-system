import java.awt.Point;
public class Vect2D extends java.awt.Point {
    public Vect2D(){
        super();
    }
    public Vect2D(int x, int y) {
        super(x,y);
    }
    
    public void setV(int vx, int vy) {
        this.setLocation(vx,vy);
    }

   
    public void vMult(int v) {
        this.setLocation(  (int)this.getX() * v,
                    (int)this.getY() * v );
    }
    public void vDiv(int v) {
        this.setLocation(  (int)this.getX() / v,
                    (int)this.getY() / v );
    }
    public void vAdd(int v) {
        this.setLocation(  (int)this.getX() + v,
                    (int)this.getY() + v );
    }
    public void vSub(int v) {
        this.setLocation(  (int)this.getX() - v,
                    (int)this.getY() - v );
    }
    public void vMult(Point v) {
        this.setLocation(  (int)(this.getX() * v.getX()),
                    (int)(this.getY() * v.getY()) );
    }
    public void vDiv(Point v) {
        this.setLocation(  (int)(this.getX() / v.getY()),
                    (int)(this.getY() / v.getY()) );
    }
    public void vAdd(Point v) {
        this.setLocation(  (int)(this.getX() + v.getX()),
                    (int)(this.getY() + v.getY()) );
    }
    public void vSub(Point v) {
        this.setLocation(  (int)(this.getX() - v.getX()),
                    (int)(this.getY() - v.getY()) );
    }

}
