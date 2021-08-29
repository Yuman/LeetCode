package Coderbyte;

public class PerfectSquare {
    public boolean isSquareSubtract(int num) {
        for (int i = 1; num > 0; i += 2) {
            num -= i;
        }
        return num == 0;
    }

    public boolean isSquareSearch(int num) {
        if (num == 1)
            return true;
        long m = num / 2; // use long to avoid overflow
        for (long l = 1, r = num; l <= r; m = (l + r) / 2) {
            if (m * m == num)
                return true;
            if (m*m > num ) {// m too big
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    public boolean isSquareFactor(int num) {
        for (int f = 2; num > 1;) {
            if (num % f == 0) {
                num /= f;
                if (num % f == 0) {
                    num /= f;
                } else
                    return false;
            } // extracted f*f
            else
                f++;
        }
        return num == 1;
    }

    public static void main(String[] a) {
        PerfectSquare ps = new PerfectSquare();
        System.out.println("subtract: " + ps.isSquareSubtract(9));
        System.out.println("search: " + ps.isSquareSearch(9));
        System.out.println("factor: " + ps.isSquareFactor(8));
    }
}
