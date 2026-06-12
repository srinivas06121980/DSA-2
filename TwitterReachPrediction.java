import java.util.*;

public class TwitterReachPrediction {

    public static void main(String[] args) {

        Map<Character, List<Character>> graph = new HashMap<>();

        graph.put('A', Arrays.asList('B', 'C'));
        graph.put('B', Arrays.asList('D', 'E'));
        graph.put('C', Arrays.asList('F'));
        graph.put('D', Arrays.asList('G'));
        graph.put('E', Arrays.asList('H'));
        graph.put('F', Arrays.asList('I'));

        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        queue.add('A');
        visited.add('A');

        int reach = 0;

        while (!queue.isEmpty()) {
            char user = queue.poll();
            reach++;

            if (graph.containsKey(user)) {
                for (char follower : graph.get(user)) {
                    if (!visited.contains(follower)) {
                        visited.add(follower);
                        queue.add(follower);
                    }
                }
            }
        }

        System.out.println("Predicted Reach = " + reach + " users");
    }
}