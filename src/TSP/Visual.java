package TSP;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import acm.graphics.*;
import acm.program.*;

import SOM.*;

public class Visual extends GraphicsProgram {
    private static final int DOTSIZE = 4;
    private static final int MARGIN = 20;
    private static final int width = 800;
    private static final int height = 600;
    
    private Map<Integer, GLine> lineGraphic = new HashMap<Integer, GLine>();
    private Map<Integer, GOval> neuronGraphic = new HashMap<Integer, GOval>();
    Kohonen solver = new Kohonen(20,"LS");
    
    @Override
    public void init(){
        setSize(width, height);
    }
    
    @Override
    public void run(){
        drawAllNodes();
        
    }
    
    //Finds real pane coordinates of points
    private double toRealX(double x){ return (width-MARGIN)*x + MARGIN/2; }
    private double toRealY(double y){ return (height-MARGIN)*y + MARGIN/2; }
    
    private void drawMapLine(int id, Neuron firstInstance, Neuron nextInstance) {
        GLine prevLine = lineGraphic.get(id);
        if(prevLine != null){
            remove(prevLine);
        }
        
        GLine line = new GLine(
                toRealX(firstInstance.getWX()),toRealY(firstInstance.getWY()),
                toRealX(nextInstance.getWX()),toRealY(nextInstance.getWY()));
        line.setColor(Color.BLUE);
        add(line);		
        lineGraphic.put(id, line);
    }

    private void addNode(Node instant){
        GOval point = new GOval(
                toRealX(instant.getX()),
                toRealY(instant.getY()),DOTSIZE,DOTSIZE);
        point.setFilled(true);
        point.setFillColor(Color.RED);
        add(point);
    }
    
    private void drawAllNodes(){
        for(int i = 0; solver.numberOfNodes() > i; i++){
            addNode(solver.getNode(i));
        }
    }
        
    public void draw(){
        for(int i = 0; solver.numberOfNeurons() - 1 > i; i++){
            Neuron firstInstant = solver.getNeuron(i);
            Neuron nextInstant = solver.getNeuron(i+1);
            drawMapLine(i, firstInstant, nextInstant);
        }
        
        Neuron firstInstant = solver.getNeuron(0);
        Neuron lastInstant = solver.getNeuron(solver.numberOfNeurons()-1);
        drawMapLine(solver.numberOfNeurons(), firstInstant, lastInstant);
        System.out.println("Total distance is "+solver.getTotalDistance());
    }
    
    public void train(int EPOCH){
        solver.train(EPOCH);
    }
}
