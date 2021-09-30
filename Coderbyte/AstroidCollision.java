package Coderbyte;

import java.util.*;

/*
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents 
its direction (positive meaning right, negative meaning left). 
Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, 
the smaller one will explode. If both are the same size, both will explode. 
Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
*/
public class AstroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> alist = new ArrayList<>(asteroids.length);
        for (int as : asteroids) {
            alist.add(as);
        }

        // loop over ashrinking list, with possible rewind sweep
        for (int i = 0, shrink = 0; i < alist.size() - 1;) {
            if (alist.get(i).intValue() > 0 && alist.get(i + 1).intValue() < 0) {
                shrink++;
                if (alist.get(i).intValue() + alist.get(i + 1).intValue() == 0) {
                    alist.remove(i + 1);
                    alist.remove(i);
                    i = 0;
                } else {
                    int sign = alist.get(i).intValue() + alist.get(i + 1).intValue() > 0 ? 1 : -1;
                    int value = Math.max(alist.get(i).intValue(), -alist.get(i + 1).intValue());
                    alist.set(i, sign * value);
                    alist.remove(i + 1);
                    i = 0;
                }
            } else {
                i++;
                if (i == alist.size() && shrink > 0) {
                    shrink = 0;
                    i = 0;
                }
            }

        }
        int[] res = new int[alist.size()];
        int k = 0;
        for (Integer as : alist) {
            res[k++] = as.intValue();
        }
        return res;
    }

    public static void main(String[] args) {
        AstroidCollision ac = new AstroidCollision();
        int[] asteroids = { 5, 10, -5 };
        int[] asteroids2 = { 10, 2, -5 };
        int[] asteroids3 = { 8, -8 };
        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids)));
        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids2)));
        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids3)));
    }
}
