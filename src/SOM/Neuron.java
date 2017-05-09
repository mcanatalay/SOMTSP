package SOM;

public class Neuron {
    private double x,y;
    private double wx,wy;
    private int update,choose;
    
    public void print(){
        System.out.print("X: " + this.wx + " Y: " + this.wy + " ");
    }
    
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
        
        this.update++;
    }
    
    public void setWXY(double wx, double wy){
        this.wx = wx;
        this.wy = wy;
        
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
    
    public double getWX(){
        return this.wx;
    }
    
    public double getWY(){
        return this.wy;
    }
    
    public Neuron(double x, double y){
        this.x = x;
        this.y = y;
        
        this.wx = Math.random();
        this.wy = Math.random();
    }
    
    public double distanceToNeuron(Neuron other){
        double distanceX = this.x - other.getX();
        double distanceY = this.y - other.getY();
        
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    }
    
    public double distanceWeightToNeuron(Neuron other){
        double distanceWX = this.wx - other.getWX();
        double distanceWY = this.wy - other.getWY();
        
        return Math.sqrt(distanceWX*distanceWX + distanceWY*distanceWY);
    }
}
