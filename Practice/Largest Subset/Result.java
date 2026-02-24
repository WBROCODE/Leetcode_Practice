import java.util.Arrays;

class Result {
    /*
    Largest Subset

    In a scheduling system, there are n events represented by two arrays, start and finish, both of size n.
    The i-th event begins at start[i] and ends at finish[i] (where 0 ≤ i < n).

    A subset of these events is considered high-priority if at least one event within it overlaps or intersects with every other event in the subset.

    Determine the maximum size of a high-priority subset of events.

    Notes
        •	Events that overlap at their start or end times are considered to intersect.
        •	There may be multiple events with the same start and end times.

    Example

    n = 4
    start  = [1, 2, 3, 4]
    finish = [2, 3, 5, 5]

    The largest high-priority subset is:

    { [2,3], [3,5], [4,5] }

    where event [3,5] intersects the remaining events in the subset.

    Therefore, the largest size of such a subset is:

    3


    ⸻

    Function Description

    Complete the function getSubsetLength in the editor with the following parameter(s):
        •	int start[n]: event start times
        •	int finish[n]: event end times

    ⸻

    Returns
        •	int: length of the largest high-priority subset of events

    ⸻

    Constraints

    1 ≤ n ≤ 2 * 10^5
    1 ≤ start[i] ≤ finish[i] ≤ 10^9


    ⸻

    Input Format for Custom Testing

    Sample Case 0

    Input

    n = 5
    start  = [1, 3, 4, 6, 9]
    finish = [2, 8, 5, 7, 10]

    Output

    3

    Explanation

    The largest high-priority subset is:

    { [3,8], [4,5], [6,7] }

    where event [3,8] intersects the remaining events in the subset.

    ⸻

    Sample Case 1

    Input

    n = 3
    start  = [1, 2, 4]
    finish = [7, 5, 6]

    Output

    3

    Explanation

    The largest high-priority subset is:

    { [1,7], [2,5], [4,6] }

    where event [2,5] intersects the remaining events in the subset.
 
    */
    public static int largestSubSet(int[] start, int[] finished, int n) {
        int ans = 0;
        int[] copyS = start.clone();
        int[] copyE = finished.clone();

        Arrays.sort(copyS);
        Arrays.sort(copyE);
        for(int i = 0; i < n; i++){
            // find small than nums[i]
            int s = start[i];
            int e = finished[i];
            // finish < s
            int endEarly = findLowerBound(copyE, s);
            // start > e
            int startLate = n - findUpperBound(copyS, e);

            int intersect = n - endEarly - startLate;
            ans = Math.max(ans, intersect);
        }
        return ans;

    }

    public static int findUpperBound(int[]arr, int target){
        int l = 0, r = arr.length;

        while(l < r){
            int m = l + (r - l) / 2;

            if(arr[m] <= target){
                l = m + 1;
            }else r = m ;
        }
        return l;

    }

    public static int findLowerBound(int[]arr, int target){
        int l = 0, r = arr.length;
        while(l < r){
            int m = l + (r - l) / 2;
            if(arr[m] >= target){
                r = m ;
            }else l = m + 1;
        }
        return l;

    }
}
