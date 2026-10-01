import java.io.*;
import java.util.*;

public class Georgio {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        // Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("georgio.dat"));
        int cases = scanner.nextInt();

        while (cases-- > 0) {
            int val = scanner.nextInt();

            boolean prime = true;
            for (int i=2; i < val / 2 + 1; i++) {
                if (val % i == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                System.out.println("Optimal Prime");
            } else {
                System.out.println("Mega Dumb");
            }
        }
        scanner.close();
    }
}
