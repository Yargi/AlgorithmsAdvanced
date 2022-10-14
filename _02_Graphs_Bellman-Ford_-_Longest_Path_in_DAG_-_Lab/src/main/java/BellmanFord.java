import java.util.*;
import java.util.stream.Collectors;

public class BellmanFord {

    public static int[][] graph;
    public static int[] distance;
    public static int[] previous;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nodes = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());

        graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = tokens[0];
            int destination = tokens[1];
            int weight = tokens[2];

            graph[source][destination] = weight;
        }

        int source = Integer.parseInt(sc.nextLine());
        int destination = Integer.parseInt(sc.nextLine());

        try {
            bellmanFord(source);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Integer> path = new ArrayList<>();
        path.add(destination);
        int node = previous[destination];

        while (node != -1) {
            path.add(node);
            node = previous[node];
        }

        Collections.reverse(path);

        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        System.out.println(distance[destination]);
    }

    private static void bellmanFord(int sourceNode) {

        distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        previous = new int[graph.length];
        Arrays.fill(previous, -1);

        distance[sourceNode] = 0;

        for (int i = 1; i < graph.length - 1; i++) {
            for (int r = 1; r < graph.length; r++) {
                for (int c = 1; c < graph[r].length; c++) {
                    int weight = graph[r][c];
                    if (weight != 0) {
                        int source = r;
                        int destination = c;

                        if (distance[source] != Integer.MAX_VALUE) {
                            int newValue = distance[source] + weight;
                            if (newValue < distance[destination]) {
                                distance[destination] = newValue;
                                previous[destination] = source;
                            }
                        }

                    }
                }
            }
        }


        for (int r = 1; r < graph.length; r++) {
            for (int c = 1; c < graph[r].length; c++) {
                int weight = graph[r][c];
                if (weight != 0) {
                    int source = r;
                    int destination = c;

                    if (distance[source] != Integer.MAX_VALUE) {
                        int newValue = distance[source] + weight;
                        if (newValue < distance[destination]) {
                            throw new IllegalStateException("Negative Cycle Detected");
                        }
                    }

                }
            }
        }
    }
}

/*
6
8
1 2 8
1 3 10
2 4 1
3 6 2
4 3 -4
4 6 -1
6 5 -2
5 3 1
1
6

4
4
1 2 1
2 3 -1
3 4 -1
4 1 -1
1
4
*/