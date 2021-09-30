package Companies.Amazon;

/*
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. 
The relative order of the elements may be changed.

Since it is impossible to change the length of the array in some languages, 
you must instead have the result be placed in the first part of the array nums. 
More formally, if there are k elements after removing the duplicates, 
then the first k elements of nums should hold the final result. 
It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array 
in-place with O(1) extra memory.
*/
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            while (nums[len - 1] == val) {
                len--;
            }
            if (nums[i] == val) {
                nums[i] = nums[len - 1];
                len--;
            }
        }
        return len;
    }

    int removeElementInc(int A[], int n, int elem) {// two pointers
        int begin = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] != elem) {
                A[begin] = A[i];
                begin++;
            }
        }
        return begin;
    }

    public static void main(String[] a) {
        RemoveElement re = new RemoveElement();
        System.out.println(re.removeElement(new int[] { 3, 2, 2, 3 }, 3));
    }
}
