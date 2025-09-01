import java.math.BigInteger;
import java.util.*;

public class PolynomialSolver {
    public static void main(String[] args) {
        // Roots from the second test case JSON
        String[][] roots = {
                {"6",  "13444211440455345511"},
                {"15", "aed7015a346d635"},
                {"15", "6aeeb69631c227c"},
                {"16", "e1b5e05623d881f"},
                {"8",  "316034514573652620673"},
                {"3",  "2122212201122002221120200210011020220200"},
                {"3",  "20120221122211000100210021102001201112121"},
                {"6",  "20220554335330240002224253"},
                {"12", "45153788322a1255483"},
                {"7",  "1101613130313526312514143"}
        };

        int k = 7; // "keys": { "k": 7 } → polynomial degree = 6
        List<BigInteger> decimalRoots = new ArrayList<>();

        // Step 1: Convert values to decimal using given base
        for (String[] root : roots) {
            int base = Integer.parseInt(root[0]);
            String value = root[1];
            BigInteger num = new BigInteger(value, base);
            decimalRoots.add(num);
        }

        System.out.println("Converted Roots:");
        for (int i = 0; i < decimalRoots.size(); i++) {
            System.out.println("Root " + (i + 1) + ": " + decimalRoots.get(i));
        }

        // Step 2: Pick first k roots
        List<BigInteger> selected = decimalRoots.subList(0, k);

        // Step 3: Polynomial expansion
        // Represent polynomial as coefficients array:
        // P(x) = coeffs[0] + coeffs[1]x + ... + coeffs[k]x^k
        BigInteger[] coeffs = new BigInteger[k + 1];
        Arrays.fill(coeffs, BigInteger.ZERO);
        coeffs[0] = BigInteger.ONE; // Start with P(x) = 1

        for (BigInteger r : selected) {
            BigInteger[] newCoeffs = new BigInteger[k + 1];
            Arrays.fill(newCoeffs, BigInteger.ZERO);

            for (int i = 0; i < coeffs.length; i++) {
                if (coeffs[i].equals(BigInteger.ZERO)) continue;

                // Contribution from multiplying by "x"
                if (i + 1 < coeffs.length) {
                    newCoeffs[i + 1] = newCoeffs[i + 1].add(coeffs[i]);
                }

                // Contribution from multiplying by "(-r)"
                newCoeffs[i] = newCoeffs[i].add(coeffs[i].multiply(r.negate()));
            }
            coeffs = newCoeffs;
        }

        // Step 4: Print final polynomial coefficients
        System.out.println("\nPolynomial Coefficients (from constant term to x^" + (k) + "):");
        for (int i = 0; i < coeffs.length; i++) {
            System.out.println("x^" + i + ": " + coeffs[i]);
        }

        // Optional: Pretty-print polynomial
        System.out.print("\nPolynomial P(x) = ");
        for (int i = coeffs.length - 1; i >= 0; i--) {
            if (!coeffs[i].equals(BigInteger.ZERO)) {
                System.out.print(coeffs[i] + "*x^" + i);
                if (i > 0) System.out.print(" + ");
            }
        }
        System.out.println();
    }
}
