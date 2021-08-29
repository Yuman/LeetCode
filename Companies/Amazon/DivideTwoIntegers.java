package Companies.Amazon;

/*
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.
*/
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // Reduce the problem to positive long integer to make it easier.
        // Use long to avoid integer overflow cases.
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            sign = -1;
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        // Take care the edge cases.
        if (ldivisor == 0)
            return Integer.MAX_VALUE;
        if ((ldividend == 0) || (ldividend < ldivisor))
            return 0;

        long lans = ldivide(ldividend, ldivisor);

        int ans;
        if (lans > Integer.MAX_VALUE) { // Handle overflow.
            ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }
        return ans;
    }

    private long ldivide(long ldividend, long ldivisor) {
        // Recursion exit condition
        if (ldividend < ldivisor)
            return 0;

        // Find the largest multiple so that (divisor * multiple <= dividend),
        // whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance
        // reason.
        // Think this as a binary search.
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        // Look for additional value for the multiple from the reminder (dividend - sum)
        // recursively.
        return multiple + ldivide(ldividend - sum, ldivisor);
    }
}
