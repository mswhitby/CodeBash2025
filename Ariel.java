import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner scanner = new Scanner(System.in);
        int cases = Integer.parseInt(scanner.nextLine());
        
        while (cases-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            
            System.out.println(a + b);
        }
        
        scanner.close();
    }
}
