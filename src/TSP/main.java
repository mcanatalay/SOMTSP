package TSP;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Visual test = new Visual();
        test.start();
        test.draw(); 
        for(int i = 0; 30000 > i; i++){
            try{Thread.sleep(5);}catch(InterruptedException e){};
            test.train(2);
            test.draw(); 
        }
    }
}
