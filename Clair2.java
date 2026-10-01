import java.io.*;
import java.util.*;

public class Clair {

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        // Scanner scanner = new Scanner(new File("clair.dat"));
        int cases = scanner.nextInt();

        Set<Long> primeNumbers = new HashSet<>();
        Map<Long, Set<Long>> factorsMap = new HashMap<>();

        while (cases-- > 0) {
            long n = scanner.nextLong();
            Set<Long> factors = new TreeSet<>();

            for (long i = 1; (i * i) <= n ; i++) {

                if (n % i == 0) {
                    if (factorsMap.containsKey(i)) {
                        factors.addAll(factorsMap.get(i));
                    }

                    if (!isPrime(i, primeNumbers)) {
                        factors.add(i);
                    }

                    if (!isPrime(n / i, primeNumbers)) {
                        factors.add(n / i);
                    }
                }
            }

            factorsMap.put(n, factors);
            System.out.println(
                    factors.stream()
                            .map(String::valueOf)
                            .collect(java.util.stream.Collectors.joining(", "))
            );
        }
    }

    static boolean isPrime(long n, Set<Long> primeNumbers) {
        if (n < 2) return false;

        if (primeNumbers.contains(n)) return true;

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }

        primeNumbers.add(n);
        return true;
    }
}
