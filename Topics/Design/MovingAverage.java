package Topics.Design;

public class MovingAverage {
    private int[] nums;
    private double sum = 0;
    private int count = 0, size = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        nums = new int[size]; // running cache
        this.size = size;
    }

    public double next(int val) {
        sum -= nums[count % size]; // current position in running cache = count % size
        sum += nums[count % size] = val;
        count++;
        return sum / Math.min(count, size);
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        for (int i = 3; i < 9; i++) {
            System.out.println(ma.next(i));
        }
        // System.out.println(ma.next(21));
    }
}
