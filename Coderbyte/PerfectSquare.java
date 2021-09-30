package Coderbyte;

public class PerfectSquare {
    // https://qr.ae/pGSBzB
    public boolean isSquareSubtract(int num) {
        for (int odd = 1; num > 0; odd += 2) {
            num -= odd;
        }
        return num == 0;
    }

    public boolean isSquareAdd(int num) {
        int sum = 0;
        for (int odd = 1; num > sum; odd += 2) {
            sum += odd;
        }
        return num == sum;
    }

    public boolean isSquareSearch(int num) {
        if (num == 1)
            return true;
        long m = num / 2; // use long to avoid overflow
        for (long l = 1, r = num; l <= r; m = (l + r) / 2) {
            if (m * m == num)
                return true;
            if (m * m > num) {// m too big
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

    public boolean isSqureBab(int num) {
        int previous = num;
        int x = num / 2;
        while (x * x != num) {
            x = (x + (num / x)) / 2;
            if (x >= previous)
                return false;
            previous = x;
        }
        return true;
    }

    public static void main(String[] a) {
        PerfectSquare ps = new PerfectSquare();
        System.out.println("subtract: " + ps.isSquareSubtract(256));
        System.out.println("add: " + ps.isSquareAdd(256));
        System.out.println("search: " + ps.isSquareSearch(256));
        System.out.println("factor: " + ps.isSquareFactor(256));
        System.out.println("bab: " + ps.isSqureBab(256));
    }
}
