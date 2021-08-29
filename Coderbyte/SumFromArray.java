package Coderbyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// Dynamic Programming by Alvin
public class SumFromArray {
    public boolean canSum(int targetSum, int[] nums) {
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) {
            return false;
        }

        for (int num : nums) {
            if (canSum(targetSum - num, nums))
                return true;
        }
        return false;
    }

    public boolean canSumIter(int targetSum, int[] nums) {
        int runningSum = targetSum;
        while (runningSum > 0) {
            boolean decreasing = false;
            for (int num : nums) {
                if (runningSum - num > 0) {
                    runningSum -= num;
                    decreasing = true;
                }else if(runningSum - num == 0){
                    return true;
                }
            }
            if(!decreasing) return false;
        }
        return false;
    }

    List<Integer> howSum(int targetSum, int[] nums) {
        if (targetSum == 0)
            return new ArrayList<Integer>();
        if (targetSum < 0)
            return null;
        for (int num : nums) {
            int remainder = targetSum - num;
            List<Integer> remainderResult = howSum(remainder, nums);
            if (remainderResult != null) {
                remainderResult.add(num);
                return remainderResult;
            }
        }
        return null;
    }

    private int[] mergeArrays(int[] a, int[] b) {
        int[] both = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).toArray();
        return both;
    }

    public static void main(String[] args) {
        SumFromArray sum = new SumFromArray();
        int[] nums = { 25, 10, 5 };
        // System.out.println(sum.mergeArrays(nums, new int[] { 1 }));
        System.out.println(sum.canSum(53, nums));
        System.out.println(sum.howSum(55, nums));
        System.out.println(sum.canSumIter(153, nums));
    }
}
