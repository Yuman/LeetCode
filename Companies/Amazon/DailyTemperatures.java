package Companies.Amazon;

import java.util.*;

/**
 * Given an array of integers temperatures representing the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to
 * wait after the ith day to get a warmer temperature. If there is no future day
 * for which this is possible, keep answer[i] == 0 instead.
 * 
 * @see NextGreaterElement
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
        Stack<Integer> stk = new Stack<>();  // to keep positions to look for a warmer
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stk.isEmpty() && T[stk.peek()] < T[i]) { // found warmer
                res[stk.peek()] = i - stk.pop();
            }
            stk.push(i);
        }
        return res;
    }

    public int[] dailyTemperaturesIII(int[] T) {
        if(T == null || T.length == 0){
            return new int[]{};
        }
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[T.length];
        //the idea is to keep all element in acsending order!
        for(int i = 0; i < T.length; i++){  
            if(stack.isEmpty()){  
                //case1 : stack is empty, feel free to push
                stack.offerFirst(i);
            }else if(!stack.isEmpty() && T[i] > T[stack.peekFirst()]){  
                //case2 : descending order happens: 
                //pop all the smaller element out, then push the current element
                while(!stack.isEmpty() && T[i] > T[stack.peekFirst()]){
                    res[stack.peekFirst()] = i - stack.peekFirst();
                    stack.pollFirst();
                }
                stack.offerFirst(i); //push current element to keep ascending order
            }else{
                //case3 : push current element to keep ascending order
                stack.offerFirst(i);
            }
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
