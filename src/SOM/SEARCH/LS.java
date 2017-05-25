package SOM.SEARCH;

import SOM.*;

//performs linear search on the given neurons to find the closest neuron to given coordinates
public class LS {
    public int search(double x, double y, Neuron neurons[]){
        int closestNeuron = -1;
        double minDistance = Double.MAX_VALUE;

        for(int i = 0; neurons.length > i ; i++){
            double distance = (Math.pow(x - neurons[i].getWX(), 2)) + (Math.pow(y - neurons[i].getWY(), 2));
            
            if(distance < minDistance){
                minDistance = distance;
                closestNeuron = i;
            }
        }
        
        return closestNeuron;
    }
}
