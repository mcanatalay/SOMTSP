package SOM;

import SOM.SEARCH.*;

public class Kohonen {
    private static final double NEAR = 0.05;
    private int NNodes;
    private int NNeurons;
    private Node nodes[];
    private Neuron neurons[];
    private double theta, alpha, decay;
    private String searchStrategy = "SA";
    
    public Kohonen(int NNodes, String searchStrategy){
        this.NNodes = NNodes;
        this.NNeurons = NNodes * 2;
                
        this.searchStrategy = searchStrategy;
                
        
        this.theta = 0.8;
        this.alpha = 0.8;
        this.decay = 0.995;
        
        /* TODO: Creating Nodes will be external.
        Only Nodes will be accepted, NNodes will be calculated from here!
        This is not a random node creation library!
        */
        createNodes();
        createNeurons();
    }
    
    public double getTotalDistance(){
        double total = 0;
        
        for(int i = 0; NNeurons-1 > i; i++){
            total += neurons[i].distanceWeightToNeuron(neurons[i+1]);
        }
        
        total += neurons[0].distanceWeightToNeuron(neurons[neurons.length-1]);
        
        return total;
    }
    
    private void createNodes(){
        nodes = new Node[NNodes];
        
        for(int i = 0; NNodes > i; i++) {
            nodes[i] = new Node(Math.random(), Math.random());
        }
    }
    
    private void createNeurons(){
        double omega = 0.0;
        neurons = new Neuron[NNeurons];
        
        for(int i = 0; NNeurons > i; i++) {
            neurons[i] = new Neuron(0.5 + 0.5 * Math.cos(omega), 0.5 + 0.5 * Math.sin(omega));
            omega += Math.PI * 2.0 / (double) (NNeurons);
        }
    }
    
    private int findClosestNeuron(double x, double y){
        if(searchStrategy == "LS"){
            LS linearSearch = new LS();
            return linearSearch.search(x, y, neurons);
        } else if(searchStrategy == "SA"){
            SA simulatingAnnealling = new SA();
            return simulatingAnnealling.anneal(x, y, neurons);
        }
        
        return -1;
    }
    
    private int getRandomNode(){
        int nodeIndex = (int) (Math.random() * NNodes);
        nodes[nodeIndex].choosen();
        
        return nodeIndex;
    }
    
    private double calculateNearValue(double value){
        return value + (Math.random() * NEAR) - NEAR / 2;
    }
    
    public double calculatePhi(Neuron n1, Neuron n2){
        double distanceBetween = n1.distanceToNeuron(n2);//CHECK!
        
        return Math.exp(-1.0 * (distanceBetween * distanceBetween) / (2.0 * Math.pow(theta, 2)));
    }
    
    public double getTetha(){ return theta; }
    public double getAlpha(){ return alpha; }
    
    private void updateNeuronWeights(int closestNeuron, double x, double y){
        for(int i = 0; NNeurons > i; i++){
            double phi = calculatePhi(neurons[i], neurons[closestNeuron]);
            
            double wx = neurons[i].getWX() + (alpha * phi * (x - neurons[i].getWX()));
            double wy = neurons[i].getWY() + (alpha * phi * (y - neurons[i].getWY()));
            neurons[i].setWXY(wx, wy);
        }
    }
    
    private void decreaseLearningRate(){
        this.alpha *= decay;
        this.theta *= decay;
    }
    
    public void train(int EPOCH){
        while(EPOCH-- > 0){
            int nodeIndex = getRandomNode();
            double x = nodes[nodeIndex].getX();
            double y = nodes[nodeIndex].getY();
            
            int closestNeuron = findClosestNeuron(x, y);
            
            updateNeuronWeights(closestNeuron, x, y);
            
            decreaseLearningRate();
        }
    }
    
    private void printNodes(){
        for(int i = 0; NNodes > i; i++){
            System.out.print("ID: " + i + " ");
            nodes[i].print();
            System.out.print("\n");
        }
    }
    
    private void printNeurons(){
        for(int i = 0; NNeurons > i; i++){
            System.out.print("ID: " + i + " ");
            neurons[i].print();
            System.out.print("\n");
        }
    }
    
    public int numberOfNeurons(){ return neurons.length; }
    public int numberOfNodes(){ return nodes.length; }
    
    public Neuron getNeuron(int i){ return neurons[i]; }
    public Node getNode(int i){ return nodes[i]; }

    public void print(){
        System.out.println("Status");
        System.out.println("Nodes");
        printNodes();
        System.out.println("Neurons");
        printNeurons();
        System.out.println();
    }
    
}