package Companies.Amazon;

import java.util.*;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
 * nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return dfs(nums, 3, 0, 0);
    }

    private List<List<Integer>> dfs(int[] nums, int k, int start, int target) {
        List<List<Integer>> re = new ArrayList<>();
        if (k == 2) {
            int l = start, r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    re.add(new ArrayList<>(Arrays.asList(nums[l], nums[r])));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (nums[l] + nums[r] < target) {
                    l++;
                } else {
                    r--;
                }
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                List<List<Integer>> afters = dfs(nums, k - 1, i + 1, target - nums[i]);
                for (List<Integer> after : afters) {
                    after.add(0, nums[i]);
                    re.add(after);
                }
            }
        }
        return re;
    }

    public List<List<Integer>> threeSumIter(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3)
            return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0)
                break;
            if (i == 0 || (i > 0) && (nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1])
                            lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1])
                            hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum)
                        lo++;
                    else
                        hi--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2SUm(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // corner case {0,0,0,0}
            twoSum(list, nums, i + 1, nums.length - 1, -nums[i]);
        }
        return list;
    }

    private void twoSum(List<List<Integer>> list, int[] nums, int low, int high, int target) {
        if (low > high)
            return;
        HashSet<Integer> set = new HashSet<>();
        for (int i = low; i <= high; i++) {
            if (set.contains(target - nums[i])) {
                list.add(Arrays.asList(-target, nums[i], target - nums[i]));
                while (i + 1 <= high && nums[i] == nums[i + 1])
                    i++; // corner case {0,0,0,0}
            } else {
                set.add(nums[i]);
            }
        }
    }
}
