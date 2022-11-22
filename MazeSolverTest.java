import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class MazeSolverTest {
    private int[][] smallMaze;
    private int[][] largeMaze;
    private int[][] blankMaze;
    private int[][] blockMaze;

    //Graph()
    @Before
    public void setUpMazeSolverTest() {
        blockMaze = new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        smallMaze = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        blankMaze = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        largeMaze = new int[][]{
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
    }

    //getShortestPath()
    @Test
    public void getShortestPathSmallMazeSrcIsTGT() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(0, 0);

        List<Coordinate> tstArr = MazeSolver.getShortestPath(smallMaze, src, tgt);

        List<Coordinate> ansArr = new ArrayList<>();
        ansArr.add(src);

        assertEquals(ansArr, tstArr);
    }

    @Test
    public void getShortestPathSmallMazeSrcIsTGT2() {
        Coordinate src = new Coordinate(2, 2);
        Coordinate tgt = new Coordinate(2, 2);

        List<Coordinate> tstArr = MazeSolver.getShortestPath(smallMaze, src, tgt);

        List<Coordinate> ansArr = new ArrayList<>();
        ansArr.add(src);

        assertEquals(ansArr, tstArr);
    }

    @Test
    public void getShortestPathSmallMaze() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(2, 2);

        List<Coordinate> tstArr = MazeSolver.getShortestPath(smallMaze, src, tgt);

        List<Coordinate> ansArr = new ArrayList<>();
        ansArr.add(new Coordinate(0, 0));
        ansArr.add(new Coordinate(1, 0));
        ansArr.add(new Coordinate(2, 0));
        ansArr.add(new Coordinate(2, 1));
        ansArr.add(new Coordinate(2, 2));


        assertEquals(ansArr, tstArr);
    }

    @Test
    public void getShortestPathBlockedMaze() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(2, 2);

        List<Coordinate> tstArr = MazeSolver.getShortestPath(blockMaze, src, tgt);

        List<Coordinate> ansArr = new ArrayList<>();

        assertEquals(ansArr, tstArr);
    }

    @Test
    public void getShortestPathBlankMaze() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(4, 4);

        List<Coordinate> tstArr = MazeSolver.getShortestPath(blankMaze, src, tgt);

        List<Coordinate> ansArr = new ArrayList<>();
        ansArr.add(new Coordinate(0, 0));
        ansArr.add(new Coordinate(0, 1));
        ansArr.add(new Coordinate(0, 2));
        ansArr.add(new Coordinate(0, 3));
        ansArr.add(new Coordinate(0, 4));
        ansArr.add(new Coordinate(1, 4));
        ansArr.add(new Coordinate(2, 4));
        ansArr.add(new Coordinate(3, 4));
        ansArr.add(new Coordinate(4, 4));

        assertEquals(ansArr, tstArr);
    }

    @Test
    public void getShortestPathLargeMaze() {
        Coordinate src = new Coordinate(2, 0);
        Coordinate tgt = new Coordinate(4, 0);

        List<Coordinate> tstArr = MazeSolver.getShortestPath(largeMaze, src, tgt);

        List<Coordinate> ansArr = new ArrayList<>();
        ansArr.add(new Coordinate(2, 0));
        ansArr.add(new Coordinate(1, 0));
        ansArr.add(new Coordinate(0, 0));
        ansArr.add(new Coordinate(0, 1));
        ansArr.add(new Coordinate(0, 2));
        ansArr.add(new Coordinate(1, 2));
        ansArr.add(new Coordinate(2, 2));
        ansArr.add(new Coordinate(3, 2));
        ansArr.add(new Coordinate(4, 2));
        ansArr.add(new Coordinate(4, 1));
        ansArr.add(new Coordinate(4, 0));

        assertEquals(ansArr, tstArr);
    }

    @Test
    public void getShortestPathLargeMap() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(0, 0);

        List<Coordinate> tstArr = MazeSolver.getShortestPath(smallMaze, src, tgt);

        List<Coordinate> ansArr = new ArrayList<>();
        ansArr.add(src);

        assertEquals(ansArr, tstArr);
    }
}