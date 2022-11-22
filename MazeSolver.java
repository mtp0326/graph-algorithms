import java.util.ArrayList;
import java.util.List;

final public class MazeSolver {
    private MazeSolver() {
    }

    private static Coordinate[][] parent;
    private static List<Coordinate> ansList;
    private static Coordinate gSrc;

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     * Please note, you MUST use your ResizingDeque implementation as the BFS queue for this method.
     * <p>
     * Input {@code maze} guaranteed to be a non-empty and valid matrix.
     * Input {@code src} and {@code tgt} are guaranteed to be valid, in-bounds, and not blocked.
     * <p>
     * Do NOT modify this method header.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src  The starting Coordinate of the path on the matrix
     * @param tgt  The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * @implSpec This method should run in worst-case O(m x n) time.
     */
    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        ResizingDequeImpl<Coordinate> deque = new ResizingDequeImpl<>();
        int mazeHeight = maze.length;
        int mazeWidth = maze[0].length;
        Coordinate[][] mapCoord = new Coordinate[mazeHeight][mazeWidth];
        parent = new Coordinate[mazeHeight][mazeWidth];
        gSrc = new Coordinate(src.getX(), src.getY());

        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                if (maze[y][x] == 0) {
                    mapCoord[y][x] = new Coordinate(x, y);
                }
            }
        }

        ansList = new ArrayList<>();

        deque.addLast(src);
        maze[src.getY()][src.getX()] = 2;

        while (deque.size() > 0) {
            Coordinate v = deque.pollFirst();
            if (v.getX() == tgt.getX() && v.getY() == tgt.getY()) {
                return findPath(new Coordinate(v.getX(), v.getY()));
            }

            if (v.getX() >= 0 && v.getX() < mazeWidth
                    && v.getY() + 1 >= 0 && v.getY() + 1 < mazeHeight
                    && maze[v.getY() + 1][v.getX()] == 0) {
                maze[v.getY() + 1][v.getX()] = 2;
                deque.addLast(mapCoord[v.getY() + 1][v.getX()]);
                parent[v.getY() + 1][v.getX()] = mapCoord[v.getY()][v.getX()];

                if (v.getX() == tgt.getX() && (v.getY() + 1) == tgt.getY()) {
                    return findPath(new Coordinate(v.getX(), v.getY() + 1));
                }

            }
            if (v.getX() >= 0 && v.getX() < mazeWidth
                    && v.getY() - 1 >= 0 && v.getY() - 1 < mazeHeight
                    && maze[v.getY() - 1][v.getX()] == 0) {
                maze[v.getY() - 1][v.getX()] = 2;
                deque.addLast(mapCoord[v.getY() - 1][v.getX()]);
                parent[v.getY() - 1][v.getX()] = mapCoord[v.getY()][v.getX()];

                if (v.getX() == tgt.getX() && v.getY() - 1 == tgt.getY()) {
                    return findPath(new Coordinate(v.getX(), v.getY() - 1));
                }
            }
            if (v.getX() + 1 >= 0 && v.getX() + 1 < mazeWidth
                    && v.getY() >= 0 && v.getY() < mazeHeight
                    && maze[v.getY()][v.getX() + 1] == 0) {
                maze[v.getY()][v.getX() + 1] = 2;
                deque.addLast(mapCoord[v.getY()][v.getX() + 1]);
                parent[v.getY()][v.getX() + 1] = mapCoord[v.getY()][v.getX()];

                if (v.getX() + 1 == tgt.getX() && v.getY() == tgt.getY()) {
                    return findPath(new Coordinate(v.getX() + 1, v.getY()));
                }
            }
            if (v.getX() - 1 >= 0 && v.getX() - 1 < mazeWidth
                    && v.getY() >= 0 && v.getY() < mazeHeight
                    && maze[v.getY()][v.getX() - 1] == 0) {
                maze[v.getY()][v.getX() - 1] = 2;
                deque.addLast(mapCoord[v.getY()][v.getX() - 1]);
                parent[v.getY()][v.getX() - 1] = mapCoord[v.getY()][v.getX()];

                if (v.getX() - 1 == tgt.getX() && v.getY() == tgt.getY()) {
                    return findPath(new Coordinate(v.getX() - 1, v.getY()));
                }
            }
        }

        return ansList;
    }

    private static List<Coordinate> findPath(Coordinate v) {
        backTrack(v);
        ansList.add(gSrc);

        List<Coordinate> tempList = new ArrayList<>();

        for (int i = ansList.size() - 1; i >= 0; i--) {
            tempList.add(ansList.get(i));
        }
        ansList = tempList;
        return ansList;
    }

    private static Coordinate backTrack(Coordinate v) {
        if (v.getX() == gSrc.getX() && v.getY() == gSrc.getY()) {
            return parent[v.getY()][v.getX()];
        }
        ansList.add(v);
        return backTrack(parent[v.getY()][v.getX()]);
    }

}