package SOM.SEARCH;

import SOM.Neuron;

public class SA {
    private double temperature;
    private double coolingRate;
    private int best = -1;
    private Neuron data[];
    
    public SA(Neuron input[]){
        this.temperature = 1000.0;
        this.coolingRate = 0.997;
        this.data = input;
    }
    
    private double probability(double energy, double newEnergy){
        if(energy > newEnergy){
            return 1.0;
        } else{
            return Math.exp((energy-newEnergy)/temperature);
        }
    }
    
    private void cool(){
        this.temperature *= coolingRate;
    }
    
    private int getRandomElementIndex(){
        return (int) (Math.random() * data.length);
    }
    
    public int anneal(double x, double y){
        this.best = getRandomElementIndex();
        while(temperature > 1){
            int random = getRandomElementIndex();
            double distanceBest = getDistance(x, y, data[best].getWX(), data[best].getWY());
            double distanceRandom = getDistance(x, y, data[random].getWX(), data[random].getWY());

            if(probability(distanceBest,distanceRandom) > Math.random()){
                this.best = random;
            }
            cool();
        }
        return best;
    }
    
    private double getDistance(double x, double y, double wx, double wy){
        return (Math.pow(x - wx, 2)) + (Math.pow(y - wy, 2));
    }
}
