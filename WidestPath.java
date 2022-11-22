import java.util.*;

/**
 * Returns a widest path between two vertices in an undirected graph. A widest path between two
 * vertices maximizes the weight of the minimum-weight edge in the path.
 * <p/>
 * There are multiple ways to solve this problem. The following algorithm may be helpful:
 * - Kruskal's algorithm using Union Find
 * You are NOT allowed to use Prim's
 * Feel free to use any previous algorithms that you have already implemented.
 */
public final class WidestPath {
    private WidestPath() {
    }

    /**
     * Computes a widest path from {@param src} to {@param tgt} for an undirected graph.
     * If there are multiple widest paths, this method may return any one of them.
     * Input {@param g} guaranteed to be undirected.
     * Input {@param src} and {@param tgt} are guaranteed to be valid and in-bounds.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g   the graph
     * @param src the vertex from which to start the search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a widest path from {@code src} to {@code tgt}, or an
     * empty list if there is no such path. The first element is {@code src} and the last
     * element is {@code tgt}. If {@code src == tgt}, a list containing just that element is
     * returned.
     * @implSpec This method should run in worst-case O((n + m) log n) time.
     */
    public static List<Integer> getWidestPath(Graph g, int src, int tgt) {
        List<Integer> ansList = new ArrayList<>();
        if (src == tgt) {
            ansList.add(tgt);
            return ansList;
        }

        Graph maxTreeGraph = new Graph(g.getSize());
        BinaryMinHeapImpl<Integer, Edge> sortEWMinHeap = new BinaryMinHeapImpl<>();
        Set<Edge> pairSet = new HashSet<>();
        for (int i = 0; i < g.getSize(); i++) {
            for (int child : g.outNeighbors(i)) {
                if (!(pairSet.contains(new Edge(child, i)))) {
                    Edge newPairIn = new Edge(child, i);
                    Edge newPairOut = new Edge(i, child);
                    pairSet.add(newPairIn);
                    pairSet.add(newPairOut);
                    sortEWMinHeap.add(g.getWeight(child, i), newPairIn);
                }
            }
        }

        Edge[] smallestEWList = new Edge[sortEWMinHeap.size()];
        for (int i = sortEWMinHeap.size() - 1; i >= 0; i--) {
            smallestEWList[i] = sortEWMinHeap.extractMin().value;
        }

        UnionFind uf = new UnionFind(g.getSize());
        for (int i = 0; i < g.getSize(); i++) {
            uf.makeSet(i);
        }
        for (Edge ewPair : smallestEWList) {
            int u = ewPair.getKey();
            int v = ewPair.getValue();
            if (uf.findCompression(u) != uf.findCompression(v)) {
                uf.union(u, v);
                maxTreeGraph.addEdge(u, v, g.getWeight(u, v));
                maxTreeGraph.addEdge(v, u, g.getWeight(v, u));
            }
        }
        return bfs(maxTreeGraph, src, tgt);

    }

    private static List<Integer> bfs(Graph gt, int src, int tgt) {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        List<Integer> bfsList = new ArrayList<>();
        boolean[] discovered = new boolean[gt.getSize()];
        int[] parent = new int[gt.getSize()];

        deque.addLast(src);
        discovered[src] = true;

        while (deque.size() > 0) {
            int minNode = deque.pollFirst();
            if (minNode == tgt) {
                while (minNode != src) {
                    bfsList.add(minNode);
                    minNode = parent[minNode];
                }
                bfsList.add(src);

                List<Integer> tempList = new ArrayList<>();

                for (int i = bfsList.size() - 1; i >= 0; i--) {
                    tempList.add(bfsList.get(i));
                }
                bfsList = tempList;
                return bfsList;
            }

            for (int child : gt.outNeighbors(minNode)) {
                if (!discovered[child]) {
                    discovered[child] = true;
                    deque.addLast(child);
                    parent[child] = minNode;
                }
            }
        }

        return bfsList;
    }


    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int graphSize) {
            parent = new int[graphSize];
            rank = new int[graphSize];
        }

        public void makeSet(int x) {
            parent[x] = x;
            rank[x] = 0;
        }

        private int findCompression(int x) {
            if (parent[x] != x) {
                parent[x] = findCompression(parent[x]);
            }
            return parent[x];
        }

        private int findRootNode(int x) {
            while (parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = findRootNode(x);
            int rootY = findRootNode(y);

            if (rootX == rootY) {
                return;
            }
            //Root with largerRank (larger height node) <- Root with smallerRank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
                if (rank[rootX] == rank[rootY]) {
                    rank[rootY]++;
                }
            }
        }

    }

    public static class Edge {
        private final int key;
        private final int value;

        public Edge(int x, int y) {
            key = x;
            value = y;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }
}
