import java.util.*;
import java.util.stream.Collectors;

public class CableNetwork {

    public static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();

    public static int cost = 0;

    public static class Edge implements Comparable<Edge> {

        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {

            this.from = from;
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge other) {

            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int budget = Integer.parseInt(sc.nextLine().split("\\s+")[1]);
        int nodes = Integer.parseInt(sc.nextLine().split("\\s+")[1]);
        int edgeCount = Integer.parseInt(sc.nextLine().split("\\s+")[1]);

        boolean[] connected = new boolean[nodes];

        for (int i = 0; i < edgeCount; i++) {

            String[] tokens = sc.nextLine().split("\\s+");

            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);

            if (tokens.length > 3) {

                connected[from] = connected[to] = true;
            }
        }

        sc.close();

        prim(connected, budget);

        System.out.println("Budget used: " + cost);
    }

    private static boolean prim(boolean[] used, int budget) {

        PriorityQueue<Edge> edges = graph.values()
                .stream().flatMap(List::stream)
                .filter(e -> (used[e.from] && !used[e.to]) || (!used[e.from] && used[e.to]))
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (!edges.isEmpty()) {

            Edge minEdge = edges.poll();

            int from = minEdge.from;
            int to = minEdge.to;
            int weight = minEdge.weight;
            int removedValue = -1;

            if (used[from] && !used[to]) {

                used[to] = true;
                removedValue = weight;
            } else if (!used[from] && used[to]) {

                used[from] = true;
                removedValue = weight;
            }

            edges = graph.values()
                    .stream().flatMap(List::stream)
                    .filter(e -> (used[e.from] && !used[e.to]) || (!used[e.from] && used[e.to]))
                    .collect(Collectors.toCollection(PriorityQueue::new));

            if (removedValue != -1 && budget - removedValue > 0) {

                budget -= removedValue;
                cost += removedValue;
            } else if (budget - removedValue < 0) {

                return false;
            }
        }

        return true;
    }
}

/*
Budget: 20
Nodes: 9
Edges: 15
1 4 8
4 0 6 connected
1 7 7
4 7 10
4 8 3
7 8 4
0 8 5 connected
8 6 9
8 3 20 connected
0 5 4
0 3 9 connected
6 3 8
6 2 12
5 3 2
3 2 14 connected

 */