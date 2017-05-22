package SOM;

public class Node {
    private double x,y;
    
    public void print(){
        System.out.print("X: " + this.x + " Y: " + this.y + " ");
    }
    
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public Node(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double distanceToNode(Node other){
        double distanceX = this.x - other.getX();
        double distanceY = this.y - other.getY();
        
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    }
}
