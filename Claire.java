import java.io.*;
import java.util.*;


public class Claire {
    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();

        Set<Long> primes = getPrimes();

        while (cases-- > 0) {
            long n = scanner.nextLong();
            Map<Long, Long> factorization = getFactorization(n, primes);
            Set<Long> factors = getFactors(factorization);
          
            System.out.println(
                factors.stream()
                        .map(String::valueOf)
                        .collect(java.util.stream.Collectors.joining(", "))
            );
        }
        scanner.close();
    }

    static Set<Long> getPrimes() {

        int limit = 1_000_000;
        boolean[] primeChecker = new boolean[limit+1];

        for (int i=2; i <= 1_000_000; i++) {
            if (primeChecker[i]) continue;

            for (long j = (long) i * i; j <= limit; j += i) {
                primeChecker[(int) j] = true;
            }
        }

        Set<Long> primes = new TreeSet<>();

        for (int i = 2; i <= limit; i++) {
            if (!primeChecker[i]) primes.add((long) i);
        }

        return primes;
    }

    static Map<Long, Long> getFactorization(long n, Set<Long> primes) {
        Map<Long, Long> factors = new TreeMap<>();
        long remaining = n;

        for (long p: primes) {
            if (p > n) break;
            int count = 0;

            while (remaining % p == 0) {
                count += 1;
                remaining /= p;
            }

            if (count > 0) factors.put(p, (long) count);
        }

        if (remaining > 1) factors.put(remaining, 1L);
        return factors;
    }

    private static Set<Long> getFactors(Map<Long, Long> factorization) {
        Set<Long> factors = new TreeSet<>();
        factors.add(1L);

        for (Map.Entry<Long, Long> entry : factorization.entrySet()) {
            long prime = entry.getKey();
            long exponent = entry.getValue();

            Set<Long> currentFactors = new TreeSet<>(factors);
            long power = 1;
            for (int e = 1; e <= exponent; e++) {
                power = (long) Math.pow(prime, e);
                for (long current : currentFactors) {
                    factors.add(current * power);
                }
            }
        }
        factors.removeAll(factorization.keySet());
        return factors;
    }
}
