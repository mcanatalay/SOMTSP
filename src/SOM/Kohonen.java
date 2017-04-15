package SOM;

public class Kohonen {
    private static final double NEAR = 0.05;
    private int NNodes;
    private int NNeurons;
    private Node nodes[];
    private Neuron neurons[];
    private double result[][];
    private double theta,phi,momentum;
    
    public Kohonen(int NNodes){
        this.NNodes = NNodes;
        this.NNeurons = NNodes * 2;
        
        theta = 0.5;
        phi = 0.5;
        momentum = 0.995;
        
        /* TODO: Creating Nodes will be external.
        Only Nodes will be accepted, NNodes will be calculated from here!
        This is not a random node creation library!
        */
        createNodes();
        createNeurons();
    }
    
    private void createNodes(){
        nodes = new Node[NNodes];
        
        for(int i = 0; NNodes > i; i++) {
            nodes[i] = new Node(Math.random(), Math.random());
        }
    }
    
    private void createNeurons(){
        double alpha = 0.0;
        neurons = new Neuron[NNeurons];
        
        for(int i = 0; NNeurons > i; i++) {
            neurons[i] = new Neuron(0.5 + 0.5 * Math.cos(alpha), 0.5 + 0.5 * Math.sin(alpha));
            alpha += Math.PI * 2.0 / (double) (NNeurons);
        }
    }
    
    private void calculateResultMatrix(){
        result = new double[NNeurons][NNeurons];
        
        for(int i = 0; NNeurons > i; i++){
            result[i][i] = 1.0;
            for(int j = i + 1; NNeurons > j; j++){
                double distanceBetween = neurons[i].distanceToNode(neurons[j]);
                result[i][j] = Math.exp(-1.0 * Math.pow(distanceBetween, 2) / (2.0 * Math.pow(theta, 2)));
                result[j][i] = result[i][j];
            }
        }
    }
    
    private int findClosestNeuron(double x, double y){
        int closestNeuron = -1;
        double minDistance = Double.MAX_VALUE;

        for(int i = 0; NNeurons > i ; i++){
            double distance = Math.pow(x - neurons[i].getWX(), 2) + Math.pow(y - neurons[i].getWY(), 2);
            
            if(distance < minDistance){
                minDistance = minDistance;
                closestNeuron = i;
            }
        }
        
        return closestNeuron;
    }
    
    private int getRandomNode(){
        int nodeIndex = (int) (Math.random() * NNodes);
        nodes[nodeIndex].choosen();
        
        return nodeIndex;
    }
    
    private double calculateNearValue(double value){
        return value + (Math.random() * NEAR) - NEAR / 2;
    }
    
    private void updateNeuronWeights(int closestNeuron, double x, double y){
        for(int i = 0; NNeurons > i; i++){
            double wx = neurons[i].getWX() + (phi * result[i][closestNeuron] * (x - neurons[i].getWX()));
            double wy = neurons[i].getWY() + (phi * result[i][closestNeuron] * (y - neurons[i].getWY()));
            neurons[i].setWXY(wx, wy);
        }
    }
    
    private void decreaseLearningRate(){
        phi *= momentum;
        theta *= momentum;
    }
    
    public void run(){
        while(true){
            int nodeIndex = getRandomNode();
            
            double x = calculateNearValue(nodes[nodeIndex].getX());
            double y = calculateNearValue(nodes[nodeIndex].getY());
            
            int closestNeuron = findClosestNeuron(x, y);
            
            updateNeuronWeights(closestNeuron, x, y);
            
            decreaseLearningRate();
            
            calculateResultMatrix();            
        }
    }
    
}
