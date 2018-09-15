package search;

/**
 * Title:        MyMazes<p>
 * Description:  A driver program drives blind search strategies<p>
 * @author Li Xu
 * @version 1.0
 */

public class MyMazes {
    static boolean DEBUG = false; 
    public static void main(String[] args) {
    	Maze maze = new Maze(10, 10); 
    	//create a copy of the maze and create an instance of the Depth First Search Engine
        DepthFirstSearchEngine searchE1 = new DepthFirstSearchEngine(new Maze(maze));
        MazeView view1 = new MazeView(searchE1); ;
      //create a copy of the maze and create an instance of the Breadth First Search Engine
        BreadthFirstSearchEngine searchE2 = new BreadthFirstSearchEngine(new Maze(maze));
        MazeView view2 = new MazeView(searchE2); 
      //create a copy of the maze and create an instance of the Iterative Search Engine
        IterativeDeepeningSearchEngine searchE3 = new IterativeDeepeningSearchEngine(new Maze(maze));
        MazeView view3 = new MazeView(searchE3); 
    }
}
