import java.util.*;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {

        PriorityQueue<Edge> orderedEdges = new PriorityQueue<>(edges);

        List<Edge> forest = new ArrayList<>();

        while (!orderedEdges.isEmpty()) {
            Edge edge = orderedEdges.poll();

            int source = edge.getStartNode();
            int destination = edge.getEndNode();

            int[] parents = new int[numberOfVertices];
        }

        return forest;
    }

    public static int findRoot(int node, int[] parents) {
        return 0;
    }
}
