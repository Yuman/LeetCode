package Coderbyte;

import java.util.ArrayList;
import java.util.List;

public interface PythagTripFinder {
    /**
     * Given a limit number C, find all primitive Pythagorean triples below that
     * limit. For definitions of the terms, refer to
     * https://www.mathsisfun.com/numbers/pythagorean-triples.html#:~:text=A%20Pythagorean%20Triple%20can%20never,number%20is%20an%20even%20number.
     * https://en.wikipedia.org/wiki/Pythagorean_triple. If (3 , 4, 5) is an answer,
     * (6, 8, 10) should be excluded. Euclid's formula may be handy for this
     * purpose. -- https://www.geeksforgeeks.org/check-two-numbers-co-prime-not/ --
     * http://www.blackwasp.co.uk/Coprime.aspx
     * 
     * @param cLimit The upper limit of C, such that the three sides meet the
     *               condition A < B < C < cLimit
     * @return
     */
    List<PythagTriple> getTriples(int cLimit);
}

class TripleFinder implements PythagTripFinder {

    @Override
    public List<PythagTriple> getTriples(int cLimit) {
        List<PythagTriple> ls = new ArrayList<>();
        cLimit = (cLimit % 2 == 0) ? --cLimit : cLimit;
        for (int c = cLimit; c > 3; c -= 2) {
            for (int b = c - 1; b > 3; b--) {
                for (int a = b - 1; a > 2; a--) {
                    if ((a + b) % 2 == 1 && gcd(c, b, a) == 1 && c * c == a * a + b * b) {
                        ls.add(new PTriple(a, b, c));
                    }
                }
            }
        }
        System.out.println(ls.size());
        return ls;
    }

    private int gcd(int m, int n) {
        if (n > m)
            return gcd(n, m); // ensure m > n
        if (n == 0)
            return m;
        if (n == 1)
            return 1;
        else
            return gcd(n, m % n); // driving both m and n down
    }

    private int gcd(int l, int m, int n) {
        return gcd(l, gcd(m, n));
    }

    public static void main(String[] args) {
        PythagTripFinder f = new TripleFinder();
        long startTime = System.nanoTime();
        System.out.println(f.getTriples(15).size());
        System.out.println(f.getTriples(200));
        long timeTaken = (System.nanoTime() - startTime) / 1000000;
        System.out.println("TimeTake: " + timeTaken);
        // System.out.println(new TripleFinder().gcd(12, 16));
        // System.out.println(new TripleFinder().gcd(5, 8, 10));
    }

}
