package Companies.Amazon;

import java.util.*;

/*

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
use a max heap.

no need for a full sort
*/
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        int l = 0, r = points.length - 1;
        while (l < r) {
            int mid = helper(points, l, r);
            if (mid == K) {
                break;
            }
            if (mid > K) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] points, int l, int r) {
        int[] p = points[l];
        while (l < r) {
            while (l < r && compare(points[r], p) >= 0) {
                r--;
            }
            points[l] = points[r];
            while (l < r && compare(points[l], p) <= 0) {
                l++;
            }
            points[r] = points[l];
        }
        points[l] = p;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
    ////////////// use max heap

    public int[][] kClosestH(int[][] points, int K) {

        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] left, int[] right) {
                return getDistance(right) - getDistance(left);
            }
        });

        for (int[] point : points) {
            heap.add(point);
            if (heap.size() > K)
                heap.poll();
        }

        int[][] result = new int[K][2];
        while (K > 0)
            result[--K] = heap.poll();

        return result;

    }

    private int getDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

}
