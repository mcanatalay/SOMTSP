package SOM.SEARCH;

import java.util.Random;
import SOM.*;

public class SA {
    public double temperature = 1000;
    public double coolingRate = 0.997;
    public int best = -1;
    
    public int anneal(double x, double y, Neuron neurons[]){
        //initial best val
        int index1 = randomElement(neurons);
        Neuron n1 = neurons[index1];
        this.best = index1;
        
        while(temperature>1.0){
           int index2 = randomElement(neurons); 
           Neuron n2 = neurons[index2];
           if(findProbability(x, y, n1, n2) > Math.random()){
               index1 = index2;
           }
           if(distance(x,y,n1)< distance(x,y,neurons[this.best])){
              this.best = index1;
           }
           
           this.temperature*=this.coolingRate;
        }
        return best;
    }
    private int randomElement(Neuron neurons[]){
        Random gen = new Random();
        int index = gen.nextInt(neurons.length);
      
        return index;
        
    }
    public double distance(double x, double y, Neuron n){
        return Math.sqrt(Math.pow(x-n.getWX(),2)+Math.pow(y-n.getWY(), 2));
    }
    public double findProbability(double x, double y, Neuron n1, Neuron n2){
        if(distance(x,y,n1)>distance(x,y,n2)){
            return 1.0;
        }
        return Math.exp((distance(x, y, n1)-distance(x, y, n2))/this.temperature);
    }
    
}
