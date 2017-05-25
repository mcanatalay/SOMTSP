package SOM.SEARCH;

import SOM.*;

//performs simulated annealing search on the given neurons to find the closest neuron to given coordinates
public class SA1 {
    
    //FIELDS
    private static final double maxTemperature = 0.3;
    private static final double minTemperature = 0.01;
    private int best = -1;
    private Neuron data[];
    
    //CONSTRUCTOR
    public SA1(Neuron input[]){
        this.data = input;
        this.best = getRandomElementIndex();
    }
    
    //METHODS
    
    //calculates acceptance probability of given distances(energy) with respect to given temperature
    private double probability(double energy, double newEnergy, double temperature){
        return 1.0/(1 + Math.exp(-1.0 * (energy - newEnergy) / temperature));
    }
    
    //cools the max temperature with respect to given cooling rate and step no
    private double cool(int stepNo, double coolingRate){
        return maxTemperature * (Math.exp(-1.0 * stepNo * coolingRate));
    }
    
    //randomly choose an elements index from given dataset(neurons)
    private int getRandomElementIndex(){
        return (int) (Math.random() * data.length);
    }
    
    //calculates best cycle lenght according to number of neurons
    private int calculateCycleLength(){
        if(data.length < 5){
            return (int) data.length;
        } else if(data.length < 15){
            return (int) data.length;
        } else{
            return (int) (data.length/4.4);
        }
    }
    
    //calculates distance of the neuron to given coordinate
    private double getEnergy(double x, double y, Neuron n){
        return Math.sqrt(Math.pow(x - n.getWX(), 2) + Math.pow(y - n.getWY(), 2));
    }
    
    /*simulating annealing algorithm
    starts from a max temperature which is predefined
    gets a random node as current node 
    calculates cycle rate and with this cycle rate calculates cooling rate
    while cooling
        for cycle rate iteration:
            get for each cycle rate gets a neuron as next
            calculates accetance probability of distance of the next and current node to given coordinate
            if find a better result keeps it
        increases step number and cools the max temperature 
    return index of the best neuron*/
    public int anneal(double x, double y){
        int current;
        int stepNo = 0;
        int n = calculateCycleLength();
        double temperature = maxTemperature;
        double coolingRate = (double) (1.0/n);
        
        while(temperature > minTemperature){
            current = getRandomElementIndex();
            int temporaryCurrent = current;
            for(int i = 1; n > i; i++){
                int next = (temporaryCurrent + i) % data.length;
                
                if(probability(getEnergy(x,y,data[current]), getEnergy(x,y,data[next]), temperature) > Math.random()){
                    current = next;
                    
                    if(getEnergy(x,y,data[current]) < getEnergy(x,y,data[best])){
                        this.best = current;
                    }
                }
            }
            stepNo++;
            temperature = cool(stepNo, coolingRate);
        }
        
        return best;
    }
    
}
