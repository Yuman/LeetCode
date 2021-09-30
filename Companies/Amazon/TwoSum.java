package Companies.Amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.youtube.com/watch?v=BoHO04xVeU0
 * 
 * @Follow-up1: find all pairs sum equals target, may contain duplicates
 *
 * @Follow-up2: two byte arrays(only in range [0, 2^8-1]), need to consider
 *              overflow error.
 *
 * @Follow-up3: Sorted array
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(target - nums[i])) {
                return new int[] { i, m.get(target - nums[i]) };
            }
            m.put(nums[i], i);
        }
        throw new IllegalArgumentException("..");
    }

    /* find all pairs sum equals target, may contain duplicates */
    public List<int[]> findAllTwoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        boolean equalFind = false;
        List<int[]> re = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(nums[i])) {
                if (target % 2 == 0 && nums[i] == target / 2 && !equalFind) {
                    re.add(new int[] { m.get(nums[i]), i });
                    equalFind = true;
                } else {
                    continue;
                }
            } else if (m.containsKey(target - nums[i])) {
                re.add(new int[] { m.get(target - nums[i]), i });
            } else {
                m.put(nums[i], i);
            }
        }
        return re;
    }

    /*
     * Two byte arrays(only in range [0, 2^8-1]), need to consider overflow error.
     * java byte is in range [-128,127]
     */
    public int[] twoArraySum(int[] nums1, int[] nums2, int target) {
        boolean[] exists = new boolean[256];
        for (int num : nums1) {
            exists[num] = true;
        }
        for (int num : nums2) {
            if (target - num < 0) {
                continue;
            }
            if (exists[target - num]) {
                return new int[] { num, target - num };
            }
        }
        return new int[] { -1, -1 };
    }

    /* Sorted */
    public int[] twoSumII(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] + numbers[end] == target)
                break;
            if (numbers[start] + numbers[end] < target)
                start++;
            else
                end--;
        }
        return new int[] { start + 1, end + 1 };
    }

}
