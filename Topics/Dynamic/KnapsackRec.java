package Topics.Dynamic;
//https://www.educative.io/blog/0-1-knapsack-problem-dynamic-solution

public class KnapsackRec {
    /**
     * we start from the beginning of the weight array and check if the item is
     * within the maximum capacity. If it is, we call the knapsack() function
     * recursively with the item and save the result in profit1.
     * 
     * Then we recursively call the function, exclude the item, and save the result
     * in the profit2 variable. On line 21, we return the greater of profit1 and
     * profit2
     */
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return this.knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we
        // shouldn't process this
        int takeCurr = 0;
        if (weights[currentIndex] <= capacity)
            takeCurr = profits[currentIndex]
                    + knapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        int skipCurr = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        return Math.max(takeCurr, skipCurr);
    }

    public static void main(String[] args) {
        KnapsackRec ks = new KnapsackRec();
        int[] profits = { 1, 6, 10, 16 };
        int[] weights = { 1, 2, 3, 5 };
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
