package Companies.Google;

import java.util.*;

/*
A frog is crossing a river. The river is divided into some number of units, and at each unit, 
there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, 
determine if the frog can cross the river by landing on the last stone. 
Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. 
The frog can only jump in the forward direction.

*/
public class FrogJump {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int i : stones) {
            m.put(i, new HashSet<>());
        }
        m.get(0).add(0);
        for (int stone : stones) {
            for (int step : m.get(stone)) {
                for (int i = step - 1; i <= step + 1; i++) {
                    if (i > 0 && m.containsKey(stone + i)) {
                        m.get(stone + i).add(i);
                    }
                }
            }
        }
        return m.get(stones[stones.length - 1]).size() > 0;
    }

    /*
     * Use map to represent a mapping from the stone (not index) to the steps that
     * can be taken from this stone.
     * 
     * The key of the map is stone. The value is, if the frog stand on this stone,
     * how many steps this frog can jump.
     * 
     * so this will be
     * 
     * [0,1,3,5,6,8,12,17]
     * 
     * {17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 6=[1, 2, 3, 4], 8=[1, 2,
     * 3, 4], 12=[3, 4, 5]}
     * 
     * Notice that no need to calculate the last stone.
     * 
     * On each step, we look if any other stone can be reached from it, if so, we
     * update that stone's steps by adding step, step + 1, step - 1. If we can reach
     * the final stone, we return true. No need to calculate to the last stone.
     * 
     */
    public boolean canCrossDP(int[] stones) {
        if (stones.length == 0) {
            return true;
        }

        HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);
        map.put(0, new HashSet<Integer>());
        map.get(0).add(1); // one unit from first stone, a forward step
        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }

        for (int i = 0; i < stones.length - 1; i++) {
            int stone = stones[i]; // position of a stone
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                HashSet<Integer> set = map.get(reach);
                if (set != null) {
                    set.add(step);
                    if (step - 1 > 0)
                        set.add(step - 1);
                    set.add(step + 1);
                }
            }
        }

        return false;
    }

    public boolean canCrossII(int[] stones) {

        for (int i = 3; i < stones.length; i++) {
            if (stones[i] > stones[i - 1] * 2) {
                return false;
            }
        }

        Deque<Integer> positions = new ArrayDeque<>();
        Deque<Integer> jumps = new ArrayDeque<>();
        positions.add(0);
        jumps.add(0);

        Set<Integer> stonePositions = new HashSet<>();
        for (int stone : stones) { // turn array to set for .contains()
            stonePositions.add(stone);
        }
        Set<String> visited = new HashSet<>();
        visited.add("0-0");

        int lastStone = stones[stones.length - 1];

        while (!positions.isEmpty()) {
            int currentPosition = positions.removeLast(); // examine one position
            int currentJump = jumps.removeLast();       // last jump

            for (int nextJump = currentJump - 1; nextJump <= currentJump + 1; nextJump++) {
                if (nextJump <= 0)
                    continue;

                int nextPosition = currentPosition + nextJump;

                if (nextPosition == lastStone) {
                    return true;
                }
                String key = nextPosition + "-" + nextJump;
                if (stonePositions.contains(nextPosition) && !visited.contains(key)) {
                    visited.add(key);
                    positions.addLast(nextPosition);
                    jumps.addLast(nextJump);
                }
            }
        }
        return false;
    }

    public static void main(String[] a) {
        int[] stones = { 0, 1, 3, 5, 6, 8, 12, 17 };
        // Output: true
        FrogJump fj = new FrogJump();
        System.out.println("can: " + fj.canCross(stones));
        System.out.println("DP: " + fj.canCrossDP(stones));
        System.out.println("II: " + fj.canCrossII(stones));
    }
}
