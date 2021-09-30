package Companies.GfG;

/*
The following is a description of the instance of this famous puzzle involving n=2 eggs and a building with k=36 floors.
Suppose that we wish to know which stories in a 36-story building are safe to drop eggs from, and which will cause the eggs to break on landing. We make a few assumptions:
…..An egg that survives a fall can be used again. 
…..A broken egg must be discarded. 
…..The effect of a fall is the same for all eggs. 
…..If an egg breaks when dropped, then it would break if dropped from a higher floor. 
…..If an egg survives a fall then it would survive a shorter fall. 
…..It is not ruled out that the first-floor windows break eggs, nor is it ruled out that the 36th-floor do not cause an egg to break.
If only one egg is available and we wish to be sure of obtaining the right result, the experiment can be carried out in only one way. Drop the egg from the first-floor window; if it survives, drop it from the second-floor window. Continue upward until it breaks. In the worst case, this method may require 36 droppings. Suppose 2 eggs are available. What is the least number of egg-droppings that is guaranteed to work in all cases? 
The problem is not actually to find the critical floor, but merely to decide floors from which eggs should be dropped so that the total number of trials are minimized.
https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
*/
public class EggDroppingPuzzle {
    /*
     * Function to get minimum number of trials needed in worst case with n eggs and
     * k floors
     */
    static int eggDrop(int eggs, int floors ) {
        // If there are no floors, then
        // no trials needed. OR if there
        // is one floor, one trial needed.
        if (floors == 1 || floors == 0)
            return floors;

        // We need k trials for one egg and k floors
        // This case make the problem bad: it's impratical to carry out.
        if (eggs == 1)
            return floors;

        int min = Integer.MAX_VALUE;
        //int x, res;

        // Consider all droppings from
        // 1st floor to kth floor and
        // return the minimum of these
        // values plus 1.
        for (int fl = 1, trials = 0; fl <= floors; fl++) {
            trials = Math.max(eggDrop(eggs - 1, fl - 1), eggDrop(eggs, floors - fl));
            min = Math.min(trials, min);
        }

        return min + 1;
    }

    public static void main(String args[]) {
        int n = 2, k = 10;
        System.out.print("Minimum number of " + "trials in worst case with " + n + " eggs and " + k + " floors is "
                + eggDrop(n, k));
    }
}
