/*
 * Tyler Spring
 * 10/12/2025
 * Chapter 14 Project 4
 * Write a Maze Explorer program that uses stacks and queues to implement an algorithm to escape from a maze. The overall pseudocode of the algorithm is the following. The algorithm can be implemented using a stack or a queue.
 * What are the pros and cons of each?
 * 
 *      create an empty stack of locations to explore.
 *      push the start location onto the stack.
 * 
 *      while(stack not empty) {
 *      pop a location L from the stack.
 *      if we have we pulled L from the stack before:
 *          no need to explore it again, so skip L.
 *      if L is the end location:
 *          the end was reachable!
 *      else, L is a new reachable non-finish location, so explore it:
 *          add all non-wall adjacent maze locations to the stack.
 *          record the fact that we have explored L.
 *      }
 *      if the stack is empty, the finish is unreachable.
 */

/*
 * Using a Stack (DFS)
       - Push start (1,1)
       - Pop it -> explore its neighbors.
       - Keep going deep in one direction before backtracking.

   This tends to go straight until stuck, then backtracks.
   Pros: smaller footprint, finds a path quickly
   Cons: might wander deep into dead ends, not the shortest path.

   Using a Queue (BFS)
           - Add start (1,1)
           - Remove oldest each time -> explore layer by layer outward.
   Pros: finds shortest path.
   Cons: higher memory use because it holds whole frontier.
 */

import java.util.List;
import java.util.Stack;
import java.util.Queue;

public class Chpt14_Project4 {
    public static void main(String[] args) {
        // Hard coding Maze to first test.
        char[][] maze = {
                { '#', '#', '#', '#', '#' },
                { '#', 'S', ' ', ' ', '#' },
                { '#', ' ', '#', 'E', '#' },
                { '#', ' ', ' ', ' ', '#' },
                { '#', '#', '#', '#', '#' }
        };

        // Find the start before entering the loop.
        Location start = null;
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c] == 'S') {
                    start = new Location(r, c);
                }
            }
        }

        // Stack to explore maze and push the start.
        Stack<Location> toExplore = new Stack<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        toExplore.push(start);
        // Exploration loop.
        while (!toExplore.isEmpty()) {
            // Push the start.
            Location current = toExplore.pop();

            // skip if already visited.
            // We check before visiting to prevent cycling, if was visited already by other
            // path.
            // If it is marked too early, mark before pushing, you can block its neighbors,
            // other valid paths.
            if (visited[current.row][current.col]) {
                continue;
            }

            // Mark visited.
            visited[current.row][current.col] = true;

            // Check for goal
            if (maze[current.row][current.col] == 'E') {
                System.out.println("Exit found at " + current);
                return;
            }

            // Adding neighbors.
            int[] dr = { -1, 1, 0, 0 }; // row offsets: up, down.
            int[] dc = { 0, 0, -1, 1 }; // col offsets: left, right.

            for (int i = 0; i < 4; i++) {
                int newRow = current.row + dr[i];
                int newCol = current.col + dc[i];

                // Bounds check. Like if a neighbor exists.
                // I am fully aware this is not proper formatting for conditionals. I don't
                // care. I'm tired of reading it across.
                if (newRow < 0 ||
                        newRow >= maze.length ||
                        newCol < 0 ||
                        newCol >= maze[0].length)
                    continue;

                // Wall check.
                if (maze[newRow][newCol] == '#')
                    continue;

                // Visited check.
                if (!visited[newRow][newCol]) {
                    toExplore.push(new Location(newRow, newCol));
                }

            }

        }

    }

    static class Location {
        int row;
        int col;

        Location(int r, int c) {
            row = r;
            col = c;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }
}

/*
 * Time Complexity:
 * Every cell in the maze is visited at most once, because of visited[][] check.
 * For each cell, we check up to 4 neighbors up, down, left, right.
 * So, if the maze is R rows x C columns:
 * Time Complexity: O(R * C)
 * 
 * DFS might not visit all cells if the exit is found early.
 * But in worst-case, every open cell is explored.
 * 
 * Space Complexity:
 * Stack Storage:
 * In DFS, the stack can grow as large as the longest path from start to any
 * cell.
 * In worst- case maze with no walls, the stack could hold all R*C cells.
 * 
 * Visited array:
 * boolean[][] visited is always R*C
 * 
 * Space Complexity: O(R * C)
 */

/*
 * PREIMPLENMENTATION NOTES
 * 
 * Step 1:
 * a stack -> depth-first search
 * a queue -> breadth-first search
 * 
 * Step 2: Identify the core data structures:
 * To remember places we still need to explore == stack or queue == controls the
 * order of exploration LIFO vs FIFO
 * 
 * To mark which places we've already visited == Set or 2D boolean array ==
 * Prevents cycles / re-exploration.
 * 
 * The stack/queue part is the heart of this chapter. Only using push, pop,
 * peek, isEmpty.
 * 
 * Step 3: Representing the Maze
 * 
 * class location {
 * int row;
 * int col;
 * }
 * 
 * The maze itself could be represented as a 2D array of chars where:
 * 'S' = start
 * 'E' = end
 * '#' = wall
 * ' ' = open space
 * 
 * Step 4: Build the plan
 * 1. Represent maze (char[][]).
 * 2. Find start and end coordinates.
 * 3. Create a stack or queue of Locations.
 * 4. Create a visited structure (boolean[][]).
 * 5. Push the start.
 * 6. While not empty:
 * - Pop/Remove a location.
 * - If visited -> skip.
 * - If end -> success.
 * - Otherwise -> mark visited; add neighbors (up, down, left, right) that are
 * in bounds and not walls.
 * 
 * 
 * 
 */