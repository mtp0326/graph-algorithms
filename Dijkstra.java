import java.util.*;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private static int[] nodeWeight;
    private static int[] parent;
    private static List<Integer> shortestPathList;

    private Dijkstra() {
    }

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
        BinaryMinHeapImpl<Integer, Integer> minHeap = new BinaryMinHeapImpl<>();
        Set<Integer> useSet = new HashSet<>();
        shortestPathList = new ArrayList<>();
        nodeWeight = new int[g.getSize()];
        parent = new int[g.getSize()];
        for (int i = 0; i < g.getSize(); i++) {
            nodeWeight[i] = Integer.MAX_VALUE;
            if (i != src) {
                minHeap.add(Integer.MAX_VALUE, i);
            } else {
                minHeap.add(0, src);
            }
        }
        nodeWeight[src] = 0;

        while (!minHeap.isEmpty()) {
            if (minHeap.peek().key >= Integer.MAX_VALUE) {
                if (useSet.contains(src) && useSet.contains(tgt)) {
                    int node = tgt;
                    while (node != src) {
                        shortestPathList.add(node);
                        node = parent[node];
                    }
                    shortestPathList.add(src);
                    List<Integer> reverseList = new ArrayList<>();
                    for (int i = shortestPathList.size() - 1; i >= 0; i--) {
                        reverseList.add(shortestPathList.get(i));
                    }
                    shortestPathList = reverseList;
                }
                return shortestPathList;
            }
            BinaryMinHeap.Entry<Integer, Integer> min = minHeap.extractMin();
            useSet.add(min.value);
            for (int child : g.outNeighbors(min.value)) {
                if (nodeWeight[child] > nodeWeight[min.value]
                        + g.getWeight(min.value, child) && !minHeap.isEmpty()
                        && !useSet.contains(child)) {
                    nodeWeight[child] = nodeWeight[min.value] + g.getWeight(min.value, child);
                    minHeap.decreaseKey(child, nodeWeight[child]);
                    parent[child] = min.value;
                }
            }
        }
        if (nodeWeight[tgt] >= Integer.MAX_VALUE) {
            return shortestPathList;
        }
        int node = tgt;
        while (node != src) {
            shortestPathList.add(node);
            node = parent[node];
        }
        shortestPathList.add(src);
        List<Integer> reverseList = new ArrayList<>();
        for (int i = shortestPathList.size() - 1; i >= 0; i--) {
            reverseList.add(shortestPathList.get(i));
        }
        shortestPathList = reverseList;
        return shortestPathList;

    }
}
