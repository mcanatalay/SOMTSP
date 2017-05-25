package SOM.SEARCH;

import java.util.Random;
import SOM.*;

//performs simulated annealing search on the given neurons to find the closest neuron to given coordinates
public class SA2 {
   
    //FIELDS
    public double temperature = 5;
    public double coolingRate = 0.997;
    public int best = -1;
    public Neuron neurons[];
    
    //CONSTRUCTORS
    public SA2(Neuron input[]){
        this.neurons = input;
    }
    
    //METHODS
    
    //Simulated Annealing algorithm to find closest neuron to given coordinates
    public int anneal(double x, double y){
        
        //initial best neuron
        int current = getRandomElementIndex();
        //Neuron n1 = neurons[index1];
        this.best = current;
        
        
        
        //untill cooldown
        while(temperature>1.0){
            //get a random neuron
           int comparison = getRandomElementIndex(); 
            
           if(findProbability(x,y,current, comparison) > Math.random()){
               current = comparison;
               
           }
          if(distance(x,y,neurons[current]) < distance(x,y,neurons[this.best])){
              this.best = current;
           }
           
           this.temperature*=this.coolingRate;
        }
        return best;
    }
 
    //gets a random neuron's index
    private int getRandomElementIndex(){
        Random gen = new Random();
        int index = gen.nextInt(this.neurons.length-1);
      
        return index;
    }
    
    //calculates distance between a position and a neuron
    public double distance(double x, double y, Neuron n){
        return Math.sqrt(Math.pow(x-n.getWX(),2)+Math.pow(y-n.getWY(), 2));
    }
    
    //calculates acceptance probability
    public double findProbability(double x, double y, int current, int comparison){
        if(distance(x,y,this.neurons[current])>distance(x,y,this.neurons[comparison])){
            return 1.0;
        }
        return Math.exp((distance(x,y,this.neurons[current])-distance(x,y,this.neurons[comparison]))/this.temperature);
    }
    
}
