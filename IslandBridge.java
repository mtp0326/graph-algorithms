import java.util.*;

final public class IslandBridge {
    private IslandBridge() {
    }

    private static Graph graph;
    private static Graph graphT;
    private static Set<Integer> adjSet;
    private static Set<Integer> sccSet;
    private static int[] g1b2;
    private static int time;
    private static int[] justFinished;
    private static boolean xSCC;

    /**
     * See question details in the write-up. Input is guaranteed to be valid.
     *
     * @param g the input graph representing the islands as vertices and bridges as directed edges
     * @param x the starting node
     * @return true, if no matter how you navigate through the one-way bridges from node x,
     * there is always a way to drive back to node x, and false otherwise.
     * @implSpec This method should run in worst-case O(n + m) time.
     */
    public static boolean allNavigable(Graph g, int x) {
        graph = g;
        adjSet = new HashSet<>();
        sccSet = new HashSet<>();
        g1b2 = new int[g.getSize()];
        time = 0;
        justFinished = new int[g.getSize()];
        xSCC = false;

        dfs(g, x);

        adjSet.removeAll(adjSet);
        sccSet.removeAll(sccSet);

        graphT = new Graph(g.getSize());
        graphT = graphTranspose(g);
        int[] decFinishedIndex = new int[justFinished.length];

        for (int i = 0; i < justFinished.length; i++) {
            decFinishedIndex[justFinished[i]] = i;
        }

        g1b2 = new int[g.getSize()];
        time = 0;

        for (int i = decFinishedIndex.length - 1; i >= 0; i--) {
            if (g1b2[decFinishedIndex[i]] == 0) {
                dfsRec(graphT, decFinishedIndex[i], x);
                if (!xSCC) {
                    adjSet.removeAll(adjSet);
                    sccSet.removeAll(sccSet);
                } else {
                    break;
                }
            }
        }

        for (int adjVertex : adjSet) {
            if (!sccSet.contains(adjVertex)) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(Graph g, int x) {
        g1b2 = new int[g.getSize()];
        justFinished = new int[g.getSize()];

        int i = x;
        int counter = 0;
        while (counter < g.getSize()) {
            if (i >= g.getSize()) {
                i = 0;
            }
            if (g1b2[i] == 0) {
                dfsRec(g, i, -1);
            }
            i++;
            counter++;
        }
    }

    private static void dfsRec(Graph g, int x, int tgt) {
        if (x == tgt) {
            xSCC = true;
        }
        g1b2[x] = 1;
        for (int child : g.outNeighbors(x)) {
            if (g1b2[child] == 0) {
                dfsRec(g, child, tgt);
            }
        }
        g1b2[x] = 2;
        justFinished[x] = time;
        time++;

        sccSet.add(x);
        adjSet.add(x);
        adjSet.addAll(graph.outNeighbors(x));
    }

    private static Graph graphTranspose(Graph g) {
        Graph graphT = new Graph(g.getSize());
        for (int i = 0; i < g.getSize(); i++) {
            for (int child : g.outNeighbors(i)) {
                graphT.addEdge(child, i, 0);
            }
        }
        return graphT;
    }


}
