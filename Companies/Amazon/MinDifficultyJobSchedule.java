package Companies.Amazon;

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To
 * work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 * 
 * You have to finish at least one task every day. The difficulty of a job
 * schedule is the sum of difficulties of each day of the d days. The difficulty
 * of a day is the maximum difficulty of a job done in that day.
 * 
 * Given an array of integers jobDifficulty and an integer d. The difficulty of
 * the i-th job is jobDifficulty[i].
 * 
 * Return the minimum difficulty of a job schedule. If you cannot find a
 * schedule for the jobs return -1.
 */
public class MinDifficultyJobSchedule {
    /**
     * Let's use a function to represent our target value, say, F(i, j) means the
     * minimum difficulty on i-th day who takes j-th work as its end.
     * 
     * However, as we fix the date and the last work, we cannot tell which work to
     * start with in a specific day. To make it clear, the problem is we can start
     * from any of the work on i-th day, but which one is the best?
     * 
     * Let's make a further step. On i-th day, the minimum index of work we can take
     * is i, because there should be at least one job done on each previous day,
     * when we reaches i-th day, there should be at least i-1 works finished in
     * total. So if we let k to be the start work, the range of k should be [i, j].
     * And each (k, j) pair means -- at i-th day, we start from k-th work and ends
     * at j-th work. The difficulty will depend on the one with highest difficulty
     * within the range. And we choose the k who creates minimum difficulty.
     * 
     * At last, in order to start at k-th work on i-th day, we should finish
     * (k-1)-th work at (i-1)-th day. Then we figure out the functional equation
     * 
     * F(i, j) = MIN{ F(i - 1, k - 1) + MAX_DIFFICULTY(k, j) }, k from i to j
     */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (d > n)
            return -1;
        int[][] F = new int[d + 1][n + 1];
        for (int i = 1; i <= n; i++)
            F[1][i] = Math.max(F[1][i - 1], jobDifficulty[i - 1]);
        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= n; j++) {
                F[i][j] = Integer.MAX_VALUE;
                int currMax = 0;
                for (int k = j; k >= i; k--) {
                    currMax = Math.max(currMax, jobDifficulty[k - 1]);
                    F[i][j] = Math.min(F[i][j], F[i - 1][k - 1] + currMax);
                }
            }
        }
        return F[d][n];
    }
}
