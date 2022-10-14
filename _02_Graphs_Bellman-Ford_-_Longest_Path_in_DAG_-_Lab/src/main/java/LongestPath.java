import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class LongestPath {

    public static int[][] graph;
    public static int[] distance;
    public static boolean[] visited;


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

        sc.close();

        distance = new int[graph.length];
        Arrays.fill(distance, Integer.MIN_VALUE);
        distance[source] = 0;
        visited = new boolean[graph.length];

        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {

            topologicalSort(i, sorted);
        }

        while (!sorted.isEmpty()) {

            int node = sorted.pop();
            for (int i = 1; i < graph[node].length; i++) {
                int weight = graph[node][i];
                if (weight != 0) {
                    if (distance[node] + weight > distance[i]) {
                        distance[i] = distance[node] + weight;
                    }
                }
            }
        }

        System.out.println(distance[destination]);
    }

    private static void topologicalSort(int node, ArrayDeque<Integer> sorted) {

        if (visited[node]) {

            return;
        }

        visited[node] = true;

        for (int i = 1; i < graph[node].length; i++) {

            if (graph[node][i] != 0) {
                topologicalSort(i, sorted);
            }
        }

        sorted.push(node);
    }
}

/*
// 21
6
11
1 2 5
1 3 3
2 4 6
2 3 2
3 5 4
3 6 2
3 4 7
4 6 1
4 5 3
5 6 4
6 2 1
1
6

//13
4
4
1 2 5
1 3 3
3 4 6
4 2 4
1
2

 */