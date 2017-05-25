package SOM;

public class Node {
    
    //FIELDS
    private double x,y;
    
    //CONSTRUCTORS
    public Node(double x, double y){
        this.x = x;
        this.y = y;
    }
    //METHODS
    //SETTERS
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    //GETTERS
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    //calculates distance of the node to another node
    public double distanceToNode(Node other){
        double distanceX = this.x - other.getX();
        double distanceY = this.y - other.getY();
        
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    }
    
    //PRINT RESULTS
    public void print(){
        System.out.print("X: " + this.x + " Y: " + this.y + " ");
    }
}
