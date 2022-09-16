import java.util.*;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {

        Collections.sort(edges);

        int[] parents = new int[numberOfVertices];

        for (int i = 0; i < parents.length; i++) {

            parents[i] = i;
        }

        List<Edge> forest = new ArrayList<>();

        for (Edge edge : edges) {

            int source = edge.getStartNode();
            int destination = edge.getEndNode();

            int sourceRoot = findRoot(source, parents);
            int destinationRoot = findRoot(destination, parents);

            if (sourceRoot != destinationRoot) {

                forest.add(edge);
                parents[sourceRoot] = destinationRoot;
            }
        }

        return forest;
    }

    public static int findRoot(int node, int[] parents) {

        while (parents[node] != node) {
            node = parents[node];
        }

        return node;
    }
}
