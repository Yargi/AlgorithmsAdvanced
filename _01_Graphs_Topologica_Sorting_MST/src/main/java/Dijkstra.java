import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {

        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);

        boolean[] visited = new boolean[graph.length];


        distances[sourceNode] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(sourceNode);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;
            int[] children = graph[parent];

            for (int childNode = 0; childNode < children.length; childNode++) {
                if (children[childNode] != 0 && !visited[childNode]) {
                    queue.offer(childNode);

                    int distance = distances[parent] + graph[parent][childNode];

                    if (distance < distances[childNode]) {
                        distances[childNode] = distance;
                        previous[childNode] = parent;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        path.add(destinationNode);

        int n = previous[destinationNode];

        while (n != -1) {
            path.add(n);
            n = previous[n];
        }

        Collections.reverse(path);

        return path.size() == 1 ? null : path;
    }
}