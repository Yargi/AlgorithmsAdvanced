import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static List<Edge> graph = new ArrayList<>();

    private static int[] distance;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int vertices = Integer.parseInt(rd.readLine());
        int edges = Integer.parseInt(rd.readLine());

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int source = tokens[0];
            int destination = tokens[1];
            int weight = tokens[2];
            graph.add(new Edge(weight, source, destination));
        }

        int source = Integer.parseInt(rd.readLine());
        int destination = Integer.parseInt(rd.readLine());

        rd.close();

        distance = new int[graph.size()];
        visited = new boolean[graph.size()];
        ArrayDeque<Edge> sorted = new ArrayDeque<>();

        //graph.sort(Comparator.comparing(Edge::getWeight).reversed());
        for (Edge edge :
                graph) {
            topologicalSort(edge.getSource(), sorted);
        }

        System.out.println();

    }

    private static void topologicalSort(int node, ArrayDeque<Edge> sorted) {

    }

    private static class Edge {

        private int weight;
        private int source;
        private int destination;


        public Edge(int weight, int source, int destination) {
            this.weight = weight;
            this.source = source;
            this.destination = destination;
        }

        public int getWeight() {
            return weight;
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }
    }
}