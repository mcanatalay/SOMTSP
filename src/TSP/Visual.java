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
    Kohonen solver = new Kohonen(2,"SA");
    
    public void init(){
        setSize(width, height);
    }
    
    public void run(){
        drawAllNodes();
        
    }
    
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
    
    private void addNeuron(int id, Neuron instant){
        GOval prevPoint = neuronGraphic.get(id);
        if(prevPoint != null){
            remove(prevPoint);
        }
        
        GOval point = new GOval(
                toRealX(instant.getWX()),
                toRealY(instant.getWY()),DOTSIZE,DOTSIZE);
        point.setFilled(true);
        point.setFillColor(Color.YELLOW);
        add(point);
        neuronGraphic.put(id, point);
    }

    private void addNode(Node instant){
        GOval point = new GOval(
                toRealX(instant.getX()),
                toRealY(instant.getY()),DOTSIZE,DOTSIZE);
        point.setFilled(true);
        point.setFillColor(Color.RED);
        add(point);
    }

    private void drawLine(Node firstInstant, Node nextInstant){
            GLine line = new GLine(
                    toRealX(firstInstant.getX())/2+MARGIN, toRealY(firstInstant.getY())/2+MARGIN,
                    toRealX(nextInstant.getX())/2+MARGIN,toRealY(nextInstant.getY())/2+MARGIN);
            line.setColor(Color.BLACK);
            add(line);
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
            addNeuron(i,firstInstant);
            drawMapLine(i, firstInstant, nextInstant);
        }
        
        Neuron firstInstant = solver.getNeuron(0);
        Neuron lastInstant = solver.getNeuron(solver.numberOfNeurons()-1);
        firstInstant.print();
        addNeuron(solver.numberOfNeurons(),lastInstant);
        drawMapLine(solver.numberOfNeurons(), firstInstant, lastInstant);
        System.out.println("Total distance is "+solver.getTotalDistance());
    }
    
    public void train(int EPOCH){
        solver.train(EPOCH);
    }
}
