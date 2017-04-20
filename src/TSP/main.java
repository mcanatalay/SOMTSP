package TSP;

import SOM.Kohonen;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kohonen test = new Kohonen(20);
        test.print();
        test.run(2000);
        test.print();
    }
    
}
