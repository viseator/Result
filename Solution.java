import java.lang.*;
import java.util.*;

class Solution {

    private static int solve(List<Set<Integer>> lines, int pre, int target) {
        if (lines == null || lines.isEmpty()) {
            return -1;
        }
        if (pre == target) {
            return 0;
        }
        Map<Integer, Set<Integer>> map = new TreeMap<>();
        for (Set<Integer> set : lines) {
            for (int n : set) {
                if (!map.containsKey(n)) {
                    map.put(n, new HashSet<>(set));
                } else {
                    map.get(n).addAll(set);
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(pre);
        Map<Integer, Integer> result = new HashMap<>();
        for (int i : map.keySet()) {
            map.get(i).remove(i);
            result.put(i, Integer.MAX_VALUE);
        }
        for (int i : map.get(pre)) {
            result.put(i, 1);
        }
        result.put(pre, 0);
        for (int i : map.keySet()) {
            int min = Integer.MAX_VALUE;
            int cur = pre;
            for (int j : map.keySet()) {
                if (visited.contains(j)) {
                    continue;
                }
                if (result.get(j) < min) {
                    min = result.get(j);
                    cur = j;
                }
            }
            visited.add(cur);
            Set<Integer> adj = map.get(cur);
            for (int j : map.keySet()) {
                if (visited.contains(j)) {
                    continue;
                }
                if (adj.contains(j) && min + 1 < result.get(j)) {
                    result.put(j, min + 1);
                }
            }
        }
        return result.get(target) == Integer.MAX_VALUE ? -1 : result.get(target);
    }

    public static void main(String[] args) {
        List<Set<Integer>> lines = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(4);
        set.add(5);
        lines.add(set);
        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(7);
        lines.add(set2);
        Set<Integer> set3 = new HashSet<>();
        set3.add(7);
        set3.add(9);
        set3.add(10);
        lines.add(set3);

        System.out.println(solve(lines, 2, 10));
    }
}