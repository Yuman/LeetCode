package Companies.Amazon;

import java.util.Stack;

/**
 * Given an array of integers temperatures representing the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to
 * wait after the ith day to get a warmer temperature. If there is no future day
 * for which this is possible, keep answer[i] == 0 instead.
 */
public class DailyTemperatures {
    /**
     * Next greater position O(NW), N is length of array, W is the number of allowed
     * values for T[i]. W = 71, we can consider this complexity O(N).
     */
    public int[] dailyTemperatures(int[] T) {
        int[] re = new int[T.length];
        for (int i = T.length - 2; i >= 0; i--) {
            int next = i + 1;
            while (next < T.length && T[i] >= T[next]) {
                if (re[next] == 0) {
                    next = T.length;
                } else {
                    next += re[next];
                }
            }
            if (next < T.length) {
                re[i] = next - i;
            }
        }
        return re;
    }

    // https://leetcode.com/problems/daily-temperatures/discuss/157886/javascript-stack-solution-with-explaination
    /*
     * Use stack to store those indexes we have not find answers In the stack, from
     * the bottom(0) to the top(stack.length-1), the temperatures decend. We iterate
     * the array just once When we iterate through the array, to make sure if
     * temperatures[i] is the answer to the indexes in our stack, we compare it to
     * the top element in our stack, and if it is the answer, we will continue
     * comparing and poping the stack until it's empty.
     */
    public int[] dailyTemperaturesII(int[] T) {
        Stack<Integer> stk = new Stack<>();
        int[] res = new int[T.length];
        for (int curr = 0; curr < T.length; curr++) {
            while (!stk.isEmpty() && T[stk.peek()] < T[curr]) { // found warmer
                res[stk.peek()] = curr - stk.pop();
            }
            stk.push(curr);
        }
        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        int[] temps = new int[] { 73, 74, 75, 71, 69, 72, 76, 73 };
        int[] res = dt.dailyTemperaturesII(temps);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
