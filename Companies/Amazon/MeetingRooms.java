package Companies.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @see Companies.Bloomberg.AvailableTime
 * @see Companies.Bloomberg.EmployeeFreeTime
 * @see Companies.Bloomberg.MaximumLengthofPairChain
 * @see Companies.Bloomberg.MergeIntervals
 * @see Companies.Bloomberg.NonoverlappingIntervals
 * @see Companies.Bloomberg.RemoveArrayElementsinGivenIndexRanges
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        /*
         * Question :Given an array of meeting time intervals where intervals[i] =
         * [starti, endi], determine if a person could attend all meetings.
         */
        // https://www.youtube.com/watch?v=aby3aYFjFT0
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);// sort by start time
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) { // overlapping
                return false;
            }
        }
        return true;
    }

    public boolean canAttendMeetings2(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        for (int i = 1; i < intervals.length; i++) {
            if (starts[i] < ends[i-1]) {
                return false;
            }
        }

        return true;
    }

    public int minMeetingRooms(int[][] intervals) {
        /*
         * Question : Given an array of meeting time intervals consisting of start and
         * end times [[s1,e1],[s2,e2],...] , find the minimum number of conference rooms
         * required.
         * https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/253.%20Meeting%20Rooms%20II.md
         */
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int room = 0, maxRoom =0;
        for (int started = 0, ended = 0; started < intervals.length; started++) {// two pointers, two tracks
                    // constant pace on primary track (starting ameeting), conditional pace on secondary track
            if (starts[started] < ends[ended]) {// either get a new room, or end a meeting
                room++;
            } else {
                room--;
                ended++;
            }
            maxRoom = Math.max(room, maxRoom);
        }
        return maxRoom;
    }

    public int minMeetingRoomsII(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>(); // endingTime
        q.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= q.peek()) {
                q.poll();
            }
            q.add(intervals[i][1]);
        }
        return q.size();
    }

    /**
     * @Follow-up: Assign room index to each interval
     */
    public static List<List<int[]>> minMeetingRoomsIII(int[][] intervals) {
        List<List<int[]>> re = new ArrayList<>();
        if (intervals == null || intervals.length == 0)
            return re;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]); // endingTime!
        q.add(new int[] { intervals[0][1], 0 });
        re.add(new ArrayList<>());
        re.get(0).add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= q.peek()[0]) {
                int[] prev = q.poll();
                re.get(prev[1]).add(intervals[i]);
                q.add(new int[] { intervals[i][1], prev[1] });
            } else {
                re.add(new ArrayList<>());
                re.get(re.size() - 1).add(intervals[i]);
                q.add(new int[] { intervals[i][1], re.size() - 1 });
            }
        }
        return re;
    }

    public static void main(String... args) {
        MeetingRooms mr = new MeetingRooms();
        int[][] intervals = new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } };
        System.out.println("can attend1: " + mr.canAttendMeetings(intervals));
        System.out.println("can attend2: " + mr.canAttendMeetings2(intervals));
        System.out.println("min rooms: " + mr.minMeetingRooms(intervals));
        minMeetingRoomsIII(new int[][] { { 7, 10 }, { 2, 4 } });
        minMeetingRoomsIII(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } });
        minMeetingRoomsIII(new int[][] { { 2, 15 }, { 36, 45 }, { 9, 29 }, { 16, 23 }, { 4, 9 } });
        minMeetingRoomsIII(new int[][] { { 1, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 5, 9 }, { 6, 10 }, { 7, 11 } });
    }
}
