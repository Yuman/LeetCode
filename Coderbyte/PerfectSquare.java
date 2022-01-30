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
            System.out.println("Search Trying: " + m);
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
        if (num == 1)
            return true;
        for (int f = 2; num > 1 && num >= f * f;) {
            System.out.println("Factor Trying: " + num);
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

    public boolean isSquareFactor2(int num) {
        if (num == 1)
            return true;
        for (int f = 2; num > 1 && num >= f * f;) {
            System.out.println("Factor2 Trying: " + num);
            if (num % (f * f) == 0) {
                num /= (f * f);
            } // extracted f*f
            else
                f++;
        }
        return num == 1;
    }

    public boolean isSqureBab(int num) {
        if (num == 1)
            return true;
        int previous = num;
        int x = num;
        while (x * x != num) {
            System.out.println("Bab Trying: " + x);
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
        System.out.println("search: " + ps.isSquareSearch(1000000));
        System.out.println("factor: " + ps.isSquareFactor(1000000));
        System.out.println("factor2: " + ps.isSquareFactor2(1000000));
        System.out.println("bab: " + ps.isSqureBab(10000));
    }
}
