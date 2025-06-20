package Deadlocks;

import java.util.*;

public class Main {
    static class Solution {
        private static Map<Character, Pair> map;

        static {
            map = new HashMap<>();
            initialize();
        }

        public static int maxDistance(String s, int replace) {
            Set<Pair> set = new HashSet<>();
            StringBuilder str = new StringBuilder(s);
            int index = 0;
            int currentX = 0, currentY = 0;
            while (index < str.length()) {
                Pair direction = map.get(str.charAt(index));
                Pair nextCoordinate = new Pair(currentX + direction.x, currentY + direction.y);
                if (set.contains(nextCoordinate) && replace > 0) {
                    str.setCharAt(index, 'N');
                    replace--;
                } else {
                    index++;
                    set.add(nextCoordinate);
                    currentY += direction.y;
                    currentX += direction.x;
                }
            }
            return set.size();
        }

        private static class Pair {
            int x;
            int y;

            Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        private static void initialize() {
            map.put('S', new Pair(0, -1));
            map.put('N', new Pair(0, 1));
            map.put('W', new Pair(1, 0));
            map.put('E', new Pair(-1, 0));
        }
    }

    public static void main(String[] args) {
        String s = "NSWWEW";
        int k = 3;
        Solution solution = new Solution();
        System.out.println(solution.maxDistance(s, k));
    }
}
