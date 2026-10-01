import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner scanner = new Scanner(System.in);
        int cases = Integer.parseInt(scanner.nextLine());
        Random random = new Random();
        
        while (cases-- > 0) {
            long seed = scanner.nextLong();
            int cars = scanner.nextInt();
            scanner.nextLine();
            
            random.setSeed(seed);
            int carsBetween = 0;
            
            String output = "";
            
            for (int i=0; i < cars; i++) {
                int color = random.nextInt(50) + 1;
                
                if (color != 13) {
                    carsBetween++;
                } else {
                    output += carsBetween + " ";
                    carsBetween =0;                  
                }
            }
            
            output += carsBetween + " ";
            System.out.println(output);
            
        }
        
        scanner.close();
    }
}
