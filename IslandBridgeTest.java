import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IslandBridgeTest {
    private Graph singleVertexGraph;
    private Graph smallGraph;
    private Graph completelyConnectedGraph;
    private Graph smallGraphExceptOne;
    private Graph graphWithSccIn;
    private Graph graphWithSccIn1;

    //allNavigable()
    @Before
    public void setUpIslandBridgeTest() {
        singleVertexGraph = new Graph(1);

        smallGraph = new Graph(4);
        smallGraph.addEdge(0, 1, 1);
        smallGraph.addEdge(0, 2, 1);
        smallGraph.addEdge(1, 2, 1);
        smallGraph.addEdge(1, 3, 1);
        smallGraph.addEdge(2, 0, 1);
        smallGraph.addEdge(3, 0, 1);

        completelyConnectedGraph = new Graph(3);
        completelyConnectedGraph.addEdge(0, 1, 1);
        completelyConnectedGraph.addEdge(1, 0, 1);
        completelyConnectedGraph.addEdge(2, 0, 1);
        completelyConnectedGraph.addEdge(0, 2, 1);
        completelyConnectedGraph.addEdge(1, 2, 1);
        completelyConnectedGraph.addEdge(2, 1, 1);

        smallGraphExceptOne = new Graph(4);
        smallGraphExceptOne.addEdge(0, 1, 1);
        smallGraphExceptOne.addEdge(0, 2, 1);
        smallGraphExceptOne.addEdge(1, 2, 1);
        smallGraphExceptOne.addEdge(1, 3, 1);
        smallGraphExceptOne.addEdge(3, 0, 1);

        graphWithSccIn = new Graph(7);
        graphWithSccIn.addEdge(0, 1, 1);
        graphWithSccIn.addEdge(0, 2, 1);
        graphWithSccIn.addEdge(1, 2, 1);
        graphWithSccIn.addEdge(1, 3, 1);
        graphWithSccIn.addEdge(2, 0, 1);
        graphWithSccIn.addEdge(3, 0, 1);
        graphWithSccIn.addEdge(4, 0, 1);
        graphWithSccIn.addEdge(5, 4, 1);
        graphWithSccIn.addEdge(4, 5, 1);
        graphWithSccIn.addEdge(6, 0, 1);

        graphWithSccIn1 = new Graph(7);
        graphWithSccIn1.addEdge(0, 1, 1);
        graphWithSccIn1.addEdge(0, 2, 1);
        graphWithSccIn1.addEdge(1, 2, 1);
        graphWithSccIn1.addEdge(1, 3, 1);
        graphWithSccIn1.addEdge(2, 0, 1);
        graphWithSccIn1.addEdge(3, 0, 1);
        graphWithSccIn1.addEdge(4, 0, 1);
        graphWithSccIn1.addEdge(0, 4, 1);
        graphWithSccIn1.addEdge(5, 4, 1);
        graphWithSccIn1.addEdge(4, 5, 1);
    }

    //allNavigable()
    @Test
    public void allNavigableSingleVertex() {
        assertTrue(IslandBridge.allNavigable(singleVertexGraph, 0));
    }

    @Test
    public void allNavigableSmallGraph() {
        assertTrue(IslandBridge.allNavigable(smallGraph, 0));
        assertTrue(IslandBridge.allNavigable(smallGraph, 1));
        assertTrue(IslandBridge.allNavigable(smallGraph, 2));
        assertTrue(IslandBridge.allNavigable(smallGraph, 3));
    }

    @Test
    public void allNavigableSpanExceptOne() {
        assertFalse(IslandBridge.allNavigable(smallGraphExceptOne, 0));
    }

    @Test
    public void allNavigableInwardsSCC() {
        assertTrue(IslandBridge.allNavigable(graphWithSccIn, 0));
        assertFalse(IslandBridge.allNavigable(graphWithSccIn, 4));
        assertFalse(IslandBridge.allNavigable(graphWithSccIn, 5));
        assertFalse(IslandBridge.allNavigable(graphWithSccIn, 6));
    }

    @Test
    public void allNavigableInwardsSCC1() {
        assertTrue(IslandBridge.allNavigable(graphWithSccIn1, 0));
        assertTrue(IslandBridge.allNavigable(graphWithSccIn1, 4));
        assertTrue(IslandBridge.allNavigable(graphWithSccIn1, 5));
    }

    @Test
    public void allNavigableCompletelyConnectedGraph() {
        assertTrue(IslandBridge.allNavigable(completelyConnectedGraph, 0));
        assertTrue(IslandBridge.allNavigable(completelyConnectedGraph, 4));
        assertTrue(IslandBridge.allNavigable(completelyConnectedGraph, 5));
        assertTrue(IslandBridge.allNavigable(completelyConnectedGraph, 6));
    }
}