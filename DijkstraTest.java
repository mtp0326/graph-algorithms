import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class DijkstraTest {
    private Graph smallGraph;
    private Graph isoGraph;
    private Graph bothEdgeSmallGraph;
    private Graph largeGraph;
    private Graph largeGraph2;
    private Graph bothEdgeLargeGraph;
    private Graph blockGraph;

    //getShortestPath()
    @Before
    public void setUpDijkstraTest() {
        smallGraph = new Graph(5);
        smallGraph.addEdge(0, 1, 6);
        smallGraph.addEdge(0, 2, 2);
        smallGraph.addEdge(1, 2, 5);
        smallGraph.addEdge(1, 3, 1);
        smallGraph.addEdge(2, 4, 4);
        smallGraph.addEdge(3, 4, 1);

        isoGraph = new Graph(4);
        isoGraph.addEdge(0, 1, 2);
        isoGraph.addEdge(1, 2, 3);

        bothEdgeSmallGraph = new Graph(5);
        bothEdgeSmallGraph.addEdge(0, 1, 6);
        bothEdgeSmallGraph.addEdge(0, 2, 2);
        bothEdgeSmallGraph.addEdge(1, 2, 5);
        bothEdgeSmallGraph.addEdge(1, 3, 1);
        bothEdgeSmallGraph.addEdge(2, 4, 4);
        bothEdgeSmallGraph.addEdge(3, 4, 1);
        bothEdgeSmallGraph.addEdge(1, 0, 1);
        bothEdgeSmallGraph.addEdge(2, 0, 1);
        bothEdgeSmallGraph.addEdge(2, 1, 1);
        bothEdgeSmallGraph.addEdge(3, 1, 1);
        bothEdgeSmallGraph.addEdge(4, 2, 1);
        bothEdgeSmallGraph.addEdge(4, 3, 1);

        largeGraph = new Graph(7);
        largeGraph.addEdge(0, 1, 1);
        largeGraph.addEdge(0, 2, 3);
        largeGraph.addEdge(1, 3, 2);
        largeGraph.addEdge(1, 2, 5);
        largeGraph.addEdge(2, 5, 4);
        largeGraph.addEdge(3, 4, 1);
        largeGraph.addEdge(3, 6, 10);
        largeGraph.addEdge(5, 6, 2);
        largeGraph.addEdge(6, 4, 1);

        largeGraph2 = new Graph(7);
        largeGraph2.addEdge(0, 1, 1);
        largeGraph2.addEdge(0, 2, 3);
        largeGraph2.addEdge(1, 3, 2);
        largeGraph2.addEdge(1, 2, 5);
        largeGraph2.addEdge(2, 5, 4);
        largeGraph2.addEdge(3, 4, 1);
        largeGraph2.addEdge(3, 6, 10);
        largeGraph2.addEdge(5, 6, 2);
        largeGraph2.addEdge(4, 6, 1);

        bothEdgeLargeGraph = new Graph(7);
        bothEdgeLargeGraph.addEdge(0, 1, 1);
        bothEdgeLargeGraph.addEdge(0, 2, 3);
        bothEdgeLargeGraph.addEdge(1, 3, 2);
        bothEdgeLargeGraph.addEdge(1, 2, 5);
        bothEdgeLargeGraph.addEdge(2, 5, 4);
        bothEdgeLargeGraph.addEdge(3, 4, 1);
        bothEdgeLargeGraph.addEdge(3, 6, 10);
        bothEdgeLargeGraph.addEdge(5, 6, 2);
        bothEdgeLargeGraph.addEdge(4, 6, 1);
        bothEdgeLargeGraph.addEdge(1, 0, 1);
        bothEdgeLargeGraph.addEdge(2, 0, 1);
        bothEdgeLargeGraph.addEdge(3, 1, 1);
        bothEdgeLargeGraph.addEdge(2, 1, 1);
        bothEdgeLargeGraph.addEdge(5, 2, 1);
        bothEdgeLargeGraph.addEdge(4, 3, 1);
        bothEdgeLargeGraph.addEdge(6, 3, 1);
        bothEdgeLargeGraph.addEdge(6, 5, 1);
        bothEdgeLargeGraph.addEdge(6, 4, 1);

        blockGraph = new Graph(7);
        blockGraph.addEdge(0, 1, 1);
        blockGraph.addEdge(0, 2, 3);
        blockGraph.addEdge(1, 3, 2);
        blockGraph.addEdge(1, 2, 5);
        blockGraph.addEdge(2, 5, 4);
        blockGraph.addEdge(3, 4, 1);
        blockGraph.addEdge(6, 3, 10);
        blockGraph.addEdge(6, 5, 2);
        blockGraph.addEdge(6, 4, 1);
    }

    //getShortestPath()
    @Test
    public void getShortestPathBlockGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(blockGraph, 0, 6);

        List<Integer> ansList = new ArrayList<>();

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathSmallGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(smallGraph, 0, 4);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(2);
        ansList.add(4);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathBothEdgeSmallGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(bothEdgeSmallGraph, 0, 4);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(2);
        ansList.add(1);
        ansList.add(3);
        ansList.add(4);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathIsoGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(isoGraph, 1, 2);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(1);
        ansList.add(2);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathBlockedIsoGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(isoGraph, 1, 3);

        List<Integer> ansList = new ArrayList<>();

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathLargeGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(largeGraph, 0, 6);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(2);
        ansList.add(5);
        ansList.add(6);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathSrc0IsTgt0LargeGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(largeGraph, 0, 0);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathSrc2IsTgt2LargeGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(largeGraph, 2, 2);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(2);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathLargeGraph2() {
        List<Integer> tstList = Dijkstra.getShortestPath(largeGraph2, 0, 6);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(1);
        ansList.add(3);
        ansList.add(4);
        ansList.add(6);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathBothEdgeLargeGraph0to6() {
        List<Integer> tstList = Dijkstra.getShortestPath(bothEdgeLargeGraph, 0, 6);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(1);
        ansList.add(3);
        ansList.add(4);
        ansList.add(6);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getShortestPathBothEdgeLargeGraph0to5() {
        List<Integer> tstList1 = Dijkstra.getShortestPath(bothEdgeLargeGraph, 0, 5);

        List<Integer> ansList1 = new ArrayList<>();
        ansList1.add(0);
        ansList1.add(1);
        ansList1.add(3);
        ansList1.add(4);
        ansList1.add(6);
        ansList1.add(5);

        assertEquals(ansList1, tstList1);
    }

    @Test
    public void getShortestPathMultipleInfinityBlockGraph() {
        List<Integer> tstList = Dijkstra.getShortestPath(blockGraph, 6, 0);

        List<Integer> ansList = new ArrayList<>();

        assertEquals(ansList, tstList);
    }
}