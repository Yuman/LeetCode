package Coderbyte;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class RecursiveSum {
    public int sum(Queue<Integer> nums) {
        if (nums.size() == 0)
            return 0;
        if (nums.size() == 1)
            return nums.poll();
        if (nums.size() > 1)
            return nums.poll() + sum(nums);
        else
            return -1;
    }


    public static void main(String[] args) {
        int[] nums = new int[] { 2, 50, 100, 2500 };
        RecursiveSum adder = new RecursiveSum();
        Queue<Integer> q = new LinkedList<>(Arrays.stream( nums ).boxed().collect( Collectors.toList() ));
        int res = adder.sum(q);
        System.out.println(res);

    }
}
