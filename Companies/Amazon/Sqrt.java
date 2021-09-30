package Companies.Amazon;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 * 
 * Since the return type is an integer, the decimal digits are truncated, and
 * only the integer part of the result is returned.
 * 
 * Note: You are not allowed to use any built-in exponent function or operator,
 * such as pow(x, 0.5) or x ** 0.5.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: x = 4 Output: 2 Example 2:
 * 
 * Input: x = 8 Output: 2 Explanation: The square root of 8 is 2.82842..., and
 * since the decimal part is truncated, 2 is returned.
 * 
 * @see Pow
 */
public class Sqrt {

    /**
     * mySqrt(x) = 2*mySqrt(x/4)
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int left = mySqrt(x >> 2) << 1;
        // int left = (int)Math.pow(Math.E, 0.5*Math.log(x));

        int right = left + 1;
        return (long) right * right > x ? left : right;
    }

    public int mySqrtI(int x) {
        int mid = x;
        for (int left = 1, right = x; left < right;) {
            if ((left + 1) > x / (left + 1))
                return left;
            if (right * right <= x)
                return right;
            mid = (left + right) / 2;
            if (mid < x / mid) {// mid is too low
                left = mid;
            } else
                right = mid;
        }
        return mid;
    }

    public int mySqrtII(int x) {
        int left = 1;
        int right = x;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                left = mid + 1;
                ans = mid;
            } else
                right = mid - 1;
        }

        return ans;
    }

    public int sqrtRec(int x) {
        if (x < 4) {
            return x == 0 ? 0 : 1;
        }
        int floor = 2 * sqrtRec(x / 4);
        // the floor is off by sqrt(x%4), at most 1 when x=3.
        // sqrt(4) =2; sqrt(7) = 2.64
        // sqrt(16^2) = 16; sqrt(16^2 + 3) = 16.09
        if ((floor + 1) > x / (floor + 1))
            return floor;
        else
            return floor + 1;
    }

    public int sqrtRec2(int x) { // binary search + recursion
        return sqrtShrink(1, x, x);
    }

    private int sqrtShrink(int lower, int upper, int x) {
        // if (lower + 1 > x / (lower + 1) && upper > x / upper) {
        if (lower + 1 > x / (lower + 1)) {
            return lower;
        }
        int mid = (lower + upper) / 2;
        if (mid > x / mid) {
            return sqrtShrink(lower, mid, x);
        } else
            return sqrtShrink(mid, upper, x);
    }

    public int sqrtSearch(int x) {
        long curr = 0;
        while ((curr + 1) * (curr + 1) <= x) {
            curr++;
        }
        return (int) curr;
    }

    // https://www.geeksforgeeks.org/square-root-of-a-perfect-square/
    public int sqrtBab(int x) {
        for (int r = x; x > r / x; x = (x + r / x) / 2) {
        }
        return x;
    }

    public int sqrtBabR(int x) {
        if (x < 4)
            return 1;
        return sqrtBabRhelp(x, x / 2);
    }

    private int sqrtBabRhelp(int x, int guess) {
        if (guess * guess == x || ((guess + 1) * (guess + 1) > x && guess * guess < x)) {
            return guess;
        } else
            return sqrtBabRhelp(x, (guess + x / guess) / 2);
    }

    // http://compsci.hunter.cuny.edu/~sweiss/course_materials/csci135/csci136labs/lab02.pdf
    public int sqrtBakh(int s) {
        double guess = s / 2;
        while ( Math.abs(guess * guess - s) > 0.1) {
            double diff = s - guess * guess;
            double ratio = diff / 2 / guess;
            double sum = guess + ratio;
            guess = sum - ratio * ratio / 2 / sum;
        }
        return (int) guess;
    }

    public int sqrtBakhR(int s) {
        return (int) sqrtBakhRHelp(s, s / 2);
    }

    private double sqrtBakhRHelp(double sq, double guess) {
        if (Math.abs(guess * guess - sq) < 0.01)
            return guess;
        else {
            double diff = sq - guess * guess;
            double ratio = diff / 2 / guess;
            double sum = guess + ratio;
            guess = sum - ratio * ratio / 2 / sum;
            return sqrtBakhRHelp(sq, guess);
        }
    }

    public static void main(String[] args) {
        Sqrt rt = new Sqrt();
        System.out.println("mySqrt: " + rt.mySqrt(122));
        System.out.println("sqrtSearch: " + rt.sqrtSearch(122));
        System.out.println("mySqrtI: " + rt.mySqrtI(122));
        System.out.println("mySqrtII: " + rt.mySqrtII(122));
        System.out.println("sqrtRec: " + rt.sqrtRec(122));
        System.out.println("sqrtRec2: " + rt.sqrtRec2(122));
        System.out.println("sqrtBab: " + rt.sqrtBab(122));
        System.out.println("sqrtBabR: " + rt.sqrtBabR(122));
        // System.out.println("sqrtBabR: " + rt.sqrtBabR(22123456));
        System.out.println("sqrtBakh: " + rt.sqrtBakh(122));
        System.out.println("sqrtBakhR: " + rt.sqrtBakhR(122));
    }
}
