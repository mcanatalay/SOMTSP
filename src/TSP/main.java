package TSP;

import java.util.Scanner;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        Scanner s = new Scanner(System.in);
        System.out.println("Type the number of city: ");
 
        int city = s.nextInt();
        System.out.println("Type the search method(LS/SA1/SA2): ");

        String searchMethod = s.next();
        if("LS".equals(searchMethod) || "SA1".equals(searchMethod) || "SA2".equals(searchMethod)){
            Visual test = new Visual();
            test.doTheThing(city, searchMethod);
            for(int i = 0; 30000 > i; i++){
                // try{Thread.sleep(5);}catch(InterruptedException e){};
                test.train(2);
                test.draw();
            }
        }
        else{
            System.out.println("Invalid Visualization method!!!");
        }
        s.close();
    }catch(Exception e){
        System.err.println(e.getMessage());
    }
    }
}
