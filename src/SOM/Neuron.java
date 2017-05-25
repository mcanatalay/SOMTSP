package SOM;

public class Neuron {
    
    //FIELDS
    private double x,y;
    private double wx,wy;
    
    //CONSTRUCTORS
    public Neuron(double x, double y){
        this.x = x;
        this.y = y;
        
        this.wx = Math.random();
        this.wy = Math.random();
    }
     
    //METHODS
    
    //SETTERS
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void setWXY(double wx, double wy){
        this.wx = wx;
        this.wy = wy;
    }

    //GETTERS
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
    
    //calculates distance of the neuron to another neuron
    public double distanceToNeuron(Neuron other){
        double distanceX = this.x - other.getX();
        double distanceY = this.y - other.getY();
        
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    }
    
    //calculates weight distance of the neuron to another neuron
    public double distanceWeightToNeuron(Neuron other){
        double distanceWX = this.wx - other.getWX();
        double distanceWY = this.wy - other.getWY();
        
        return Math.sqrt(distanceWX*distanceWX + distanceWY*distanceWY);
    }
    
    //PRINT RESULTS
    public void print(){
        System.out.print("X: " + this.wx + " Y: " + this.wy + " ");
    }
}
