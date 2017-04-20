package SOM;

public class Kohonen {
    private static final double NEAR = 0.05;
    private int NNodes;
    private int NNeurons;
    private Node nodes[];
    private Neuron neurons[];
    private double theta, alpha, decay;
    
    public Kohonen(int NNodes){
        this.NNodes = NNodes;
        this.NNeurons = NNodes * 2;
        
        theta = 0.5;
        alpha = 0.5;
        decay = 0.995;
        
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
        double omega = 0.0;
        neurons = new Neuron[NNeurons];
        
        for(int i = 0; NNeurons > i; i++) {
            neurons[i] = new Neuron(0.5 + 0.5 * Math.cos(omega), 0.5 + 0.5 * Math.sin(omega));
            omega += Math.PI * 2.0 / (double) (NNeurons);
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
    
    private double calculatePhi(Neuron n1, Neuron n2){
        double distanceBetween = n1.distanceToNode(n2);//CHECK!
        
        return Math.exp(-1.0 * Math.pow(distanceBetween, 2) / (2.0 * Math.pow(theta, 2)));
    }
    
    private void updateNeuronWeights(int closestNeuron, double x, double y){
        for(int i = 0; NNeurons > i; i++){
            double phi = calculatePhi(neurons[i], neurons[closestNeuron]);
            
            double wx = neurons[i].getWX() + (alpha * phi * (x - neurons[i].getWX()));
            double wy = neurons[i].getWY() + (alpha * phi * (y - neurons[i].getWY()));
            neurons[i].setWXY(wx, wy);
        }
    }
    
    private void decreaseLearningRate(){
        alpha *= decay;
        theta *= decay;
    }
    
    public void run(int EPOCH){
        while(EPOCH-- > 0){
            int nodeIndex = getRandomNode();
            
            double x = calculateNearValue(nodes[nodeIndex].getX());
            double y = calculateNearValue(nodes[nodeIndex].getY());
            
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
    
    public void print(){
        System.out.println("Status");
        System.out.println("Nodes");
        printNodes();
        System.out.println("Neurons");
        printNeurons();
        System.out.println();
    }
    
}