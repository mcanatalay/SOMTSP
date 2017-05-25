package SOM;

import SOM.SEARCH.*;

public class Kohonen {
    
    //FIELDS
    private static final double NEAR = 0.05;
    private int NNodes;
    private int NNeurons;
    private Node nodes[];
    private Neuron neurons[];
    private double theta, alpha, decay;
    private String searchStrategy ;
    
    
    //CONSTRUCTOR
    public Kohonen(int NNodes, String searchStrategy){
        this.NNodes = NNodes;
        this.NNeurons = NNodes * 2;
        this.searchStrategy = searchStrategy;
        this.theta = 0.3;
        this.alpha = 0.3;
        this.decay = 0.99992;
        
        createNodes();
        createNeurons();
    }
    
    //METHODS
    
    //calculates total distance of the tour
    public double getTotalDistance(){
        double total = 0;
        
        for(int i = 0; NNeurons-1 > i; i++){
            total += neurons[i].distanceWeightToNeuron(neurons[i+1]);
        }
        
        total += neurons[0].distanceWeightToNeuron(neurons[neurons.length-1]);
        
        return total;
    }
    
    //creates random nodes(cities)
    private void createNodes(){
        nodes = new Node[NNodes];
        
        for(int i = 0; NNodes > i; i++) {
            nodes[i] = new Node(Math.random(), Math.random());
        }
    }
    
    //creates neurons with respect to number of nodes(cities)
    private void createNeurons(){
        double omega = 0.0;
        neurons = new Neuron[NNeurons];
        
        for(int i = 0; NNeurons > i; i++) {
            neurons[i] = new Neuron(0.5 + 0.5 * Math.cos(omega), 0.5 + 0.5 * Math.sin(omega));
            omega += Math.PI * 2.0 / (double) (NNeurons);
        }
    }
    
    //according to search method calculates closest node to given coordinates
    //SA: simulated annealing       LS: linear search
    private int findClosestNeuron(double x, double y){
        if("LS".equals(this.searchStrategy)){
            LS linearSearch = new LS();
            return linearSearch.search(x, y, neurons);
        } else if("SA1".equals(this.searchStrategy)){
            SA1 simulatingAnnealling = new SA1(neurons);
            return simulatingAnnealling.anneal(x, y);
        } else if("SA2".equals(this.searchStrategy)){
            SA2 simulatingAnnealling = new SA2(neurons);
            return simulatingAnnealling.anneal(x, y);
        }
        
        return -1;
    }
    
    //returns a random node index
    private int getRandomNode(){
        int nodeIndex = (int) (Math.random() * NNodes);
        
        return nodeIndex;
    }
    
    //calculates near value based on a constant value NEAR
    private double calculateNearValue(double value){
        return value + (Math.random() * NEAR) - NEAR / 2;
    }
    
    //calculates phi value
    public double calculatePhi(Neuron n1, Neuron n2){
        double distanceBetween = n1.distanceToNeuron(n2);//CHECK!
        
        return Math.exp(-1.0 * (distanceBetween * distanceBetween) / (2.0 * Math.pow(theta, 2)));
    }
    
    //GETTERS
    public double getTetha(){ return theta; }
    public double getAlpha(){ return alpha; }
    public int numberOfNeurons(){ return neurons.length; }
    public int numberOfNodes(){ return nodes.length; }
    public Neuron getNeuron(int i){ return neurons[i]; }
    public Node getNode(int i){ return nodes[i]; }
    
    //updates weights of neurons with respect to phi value
    private void updateNeuronWeights(int closestNeuron, double x, double y){
        for(int i = 0; NNeurons > i; i++){
            double phi = calculatePhi(neurons[i], neurons[closestNeuron]);
            
            double wx = neurons[i].getWX() + (alpha * phi * (x - neurons[i].getWX()));
            double wy = neurons[i].getWY() + (alpha * phi * (y - neurons[i].getWY()));
            neurons[i].setWXY(wx, wy);
        }
    }
    
    //updates learning rates
    private void decreaseLearningRate(){
        this.alpha *= decay;
        this.theta *= decay;
    }
    
    /*trains the network so algorithm can learn
    for learning calculates near values for coordinates
    find closest neuron for these coordinates
    update weight of the closest neuron
    updates learningrate*/
    public void train(int EPOCH){
        while(EPOCH-- > 0){
            int nodeIndex = getRandomNode();
            double x = calculateNearValue(nodes[nodeIndex].getX());
            double y = calculateNearValue(nodes[nodeIndex].getY());
            
            int closestNeuron = findClosestNeuron(x, y);
            
            updateNeuronWeights(closestNeuron, x, y);
            
            decreaseLearningRate();
        }
    }
    
    // PRINTS THE RESULTS
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

    public void print(){
        System.out.println("Status");
        System.out.println("Nodes");
        printNodes();
        System.out.println("Neurons");
        printNeurons();
        System.out.println();
    }
    
}