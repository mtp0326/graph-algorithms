import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class GraphTest {
    private Graph graph;
    private Graph edgeGraph;

    //Graph()
    @Before
    public void setUpGraphTest() {
        graph = new Graph(4);
        edgeGraph = new Graph(4);

        edgeGraph.addEdge(0, 1, 1);
        edgeGraph.addEdge(0, 2, 4);
        edgeGraph.addEdge(1, 2, 6);
        edgeGraph.addEdge(3, 1, 8);
        edgeGraph.addEdge(1, 3, 2);
    }

    //getSize()
    @Test
    public void getSize4Graph() {
        assertEquals(4, graph.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void graphErrorZeroGraph() {
        graph = new Graph(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void graphErrorNegativeGraph() {
        graph = new Graph(-40);
    }

    //hasEdge(), getWeight(), addEdge()
    @Test
    public void addEdgeEdgeGraph() {
        assertTrue(edgeGraph.hasEdge(0, 1));
        assertTrue(edgeGraph.hasEdge(0, 2));
        assertTrue(edgeGraph.hasEdge(1, 3));
        assertTrue(edgeGraph.hasEdge(1, 2));
        assertFalse(edgeGraph.hasEdge(1, 0));

        assertEquals(1, edgeGraph.getWeight(0, 1));
        assertEquals(4, edgeGraph.getWeight(0, 2));
        assertEquals(2, edgeGraph.getWeight(1, 3));
        assertEquals(6, edgeGraph.getWeight(1, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void hasEdgeNegativeErrorGraph() {
        graph.hasEdge(0, -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hasEdgeNegativeErrorEdgeGraph1() {
        edgeGraph.hasEdge(-1, 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void getWeightNoEdgeErrorEdgeGraph() {
        edgeGraph.getWeight(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWeightNegativeEdgeErrorEdgeGraph() {
        edgeGraph.getWeight(-1, 0);
    }

    @Test
    public void graphErrorSameEdgeGraph() {
        assertTrue(graph.addEdge(0, 1, 1));
        assertFalse(graph.addEdge(0, 1, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void graphErrorWrongEdgeGraph() {
        graph.addEdge(0, 4, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void graphErrorSameVertexGraph() {
        graph.addEdge(1, 1, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeNegativeEdgeErrorEdgeGraph() {
        graph.addEdge(-1, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeNegativeEdgeErrorEdgeGraph1() {
        graph.addEdge(-1, -1, 2);
    }

    //outNeighbors()
    @Test
    public void outNeighborsEdgeGraph() {
        Set<Integer> resSet = new HashSet<>();
        resSet.add(1);
        resSet.add(2);
        assertEquals(resSet, edgeGraph.outNeighbors(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void outNeighborsErrorGraph() {
        graph.addEdge(1, 1, 9);
    }


}
