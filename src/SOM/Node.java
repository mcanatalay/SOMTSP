package SOM;

public class Node {
    private double x,y;
    private int update,choose;
    
    public void print(){
        System.out.print("X: " + this.x + " Y: " + this.y + " ");
    }
    
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
        
        this.update++;
    }
    
    public void choosen(){
        this.choose++;
    }
    
    public int getNChoose(){
        return this.choose;
    }
    
    public int getNUpdate(){
        return this.update;
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
        
        this.update = 0;
        this.choose = 0;
    }
    
    public double distanceToNode(Node other){
        double distanceX = this.x - other.getX();
        double distanceY = this.y - other.getY();
        
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    }
}
