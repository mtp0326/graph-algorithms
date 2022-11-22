import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class WidestPathTest {
    private Graph smallGraph;
    private Graph isoGraph;
    private Graph bothEdgeLargeGraph;
    private Graph bothEdgeLargeGraph1;

    @Before
    public void setUpWidestPathTest() {
        smallGraph = new Graph(3);
        smallGraph.addEdge(0, 1, 11);
        smallGraph.addEdge(1, 0, 11);
        smallGraph.addEdge(0, 2, 1);
        smallGraph.addEdge(2, 0, 1);
        smallGraph.addEdge(1, 2, 3);
        smallGraph.addEdge(2, 1, 3);

        isoGraph = new Graph(5);
        isoGraph.addEdge(3, 4, 12);
        isoGraph.addEdge(4, 3, 12);
        isoGraph.addEdge(0, 1, 11);
        isoGraph.addEdge(1, 0, 11);
        isoGraph.addEdge(0, 2, 1);
        isoGraph.addEdge(2, 0, 1);
        isoGraph.addEdge(1, 2, 3);
        isoGraph.addEdge(2, 1, 3);

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
        bothEdgeLargeGraph.addEdge(2, 3, 3);
        bothEdgeLargeGraph.addEdge(3, 5, 1);

        bothEdgeLargeGraph.addEdge(1, 0, 1);
        bothEdgeLargeGraph.addEdge(2, 0, 3);
        bothEdgeLargeGraph.addEdge(3, 1, 2);
        bothEdgeLargeGraph.addEdge(2, 1, 5);
        bothEdgeLargeGraph.addEdge(5, 2, 4);
        bothEdgeLargeGraph.addEdge(4, 3, 1);
        bothEdgeLargeGraph.addEdge(6, 3, 10);
        bothEdgeLargeGraph.addEdge(6, 5, 2);
        bothEdgeLargeGraph.addEdge(6, 4, 1);
        bothEdgeLargeGraph.addEdge(3, 2, 3);
        bothEdgeLargeGraph.addEdge(5, 3, 1);

        bothEdgeLargeGraph1 = new Graph(7);
        bothEdgeLargeGraph1.addEdge(0, 1, 1);
        bothEdgeLargeGraph1.addEdge(0, 2, 3);
        bothEdgeLargeGraph1.addEdge(1, 3, 2);
        bothEdgeLargeGraph1.addEdge(1, 2, 5);
        bothEdgeLargeGraph1.addEdge(2, 5, 4);
        bothEdgeLargeGraph1.addEdge(3, 4, 1);
        bothEdgeLargeGraph1.addEdge(3, 6, 10);
        bothEdgeLargeGraph1.addEdge(5, 6, 2);
        bothEdgeLargeGraph1.addEdge(4, 6, 2);
        bothEdgeLargeGraph1.addEdge(2, 3, 3);
        bothEdgeLargeGraph1.addEdge(3, 5, 1);

        bothEdgeLargeGraph1.addEdge(1, 0, 1);
        bothEdgeLargeGraph1.addEdge(2, 0, 3);
        bothEdgeLargeGraph1.addEdge(3, 1, 2);
        bothEdgeLargeGraph1.addEdge(2, 1, 5);
        bothEdgeLargeGraph1.addEdge(5, 2, 4);
        bothEdgeLargeGraph1.addEdge(4, 3, 1);
        bothEdgeLargeGraph1.addEdge(6, 3, 10);
        bothEdgeLargeGraph1.addEdge(6, 5, 2);
        bothEdgeLargeGraph1.addEdge(6, 4, 2);
        bothEdgeLargeGraph1.addEdge(3, 2, 3);
        bothEdgeLargeGraph1.addEdge(5, 3, 1);
    }

    //getWidestPath()
    @Test
    public void getWidestPathSmallGraph() {
        List<Integer> tstList = WidestPath.getWidestPath(smallGraph, 1, 2);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(1);
        ansList.add(2);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getWidestPathSmallGraphSrcIsTgt() {
        List<Integer> tstList = WidestPath.getWidestPath(smallGraph, 1, 1);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(1);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getWidestPathIsoGraphDividedSrcTgt() {
        List<Integer> tstList = WidestPath.getWidestPath(isoGraph, 0, 4);

        List<Integer> ansList = new ArrayList<>();

        assertEquals(ansList, tstList);
    }

    @Test
    public void getWidestPathIsoGraphConnectedSrcTgt() {
        List<Integer> tstList = WidestPath.getWidestPath(isoGraph, 0, 2);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(1);
        ansList.add(2);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getWidestPathBothEdgeLargeGraph() {
        List<Integer> tstList = WidestPath.getWidestPath(bothEdgeLargeGraph, 0, 4);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(2);
        ansList.add(3);
        ansList.add(4);

        assertEquals(ansList, tstList);
    }

    @Test
    public void getWidestPathBothEdgeLargeGraph1() {
        List<Integer> tstList = WidestPath.getWidestPath(bothEdgeLargeGraph1, 0, 4);

        List<Integer> ansList = new ArrayList<>();
        ansList.add(0);
        ansList.add(2);
        ansList.add(3);
        ansList.add(6);
        ansList.add(4);

        assertEquals(ansList, tstList);
    }


}
