//Program IterativeDeepningSearchEngine.java
//Purpose: This program demonstrates a IterativeDeepingSearch Algorithm
//Developer: Carlos Portillo

package search;
import java.awt.Dimension;

public class IterativeDeepeningSearchEngine extends AbstractSearchEngine {
	
	private int depthBound = 0;
    //boolean flag that checks that you were able to get to the depth bound
	private boolean depthBoundReached = true;
	

	public IterativeDeepeningSearchEngine(Maze maze ) {
		super(maze);	
		runDeepeningSearch(startLoc);
	}
	 private void runDeepeningSearch(Dimension loc)
	 {
		//stop loop if you're no longer searching or failed to reach depth bound(meaning you can't go any further)
		 while(isSearching == true && depthBoundReached)
		 {
			 //have maze reset after each iteration
			 resetMaze();
			 //reset flag
			 depthBoundReached = false;
			 iterateSearch(startLoc, 1);
			 depthBound++;
			  
			 
			 
		 }
		 
	 }
	    private void iterateSearch(Dimension loc, int depth) {
	        maze.setValue(loc.width, loc.height, (short)depth);
	        while(depth <= depthBound) {
	        	//get possible moves from current position
		        //this can be thought of expanding a node/creating the branches
		        //in depth first search you have a path where go down the first child node
	        	Dimension [] moves = getPossibleMoves(loc);
	        	for (int i=0; i<4; i++) {
	        		if (moves[i] == null) {
	        			break; // out of loop if you can't progress any further on current path and return 
	        			//try to find another path from previous branch
	            	}
	        		//make search_path open up what moves are available 
	        		//keeps track of the movement on maze
	        		searchPath[depth] = moves[i];
	        		//check to see if we reach goal node
	        		if (equals(moves[i], goalLoc)) {
	        			if (MyMazes.DEBUG) {
	        				System.out.println("Found the goal at " + moves[i].width +
	                                   ", " + moves[i].height);
	        			}
	        			isSearching = false;
	        			maxDepth = depth;
	        			//return from function call and don't go any further 
	        			return;
	        		} 
	        		else {
	        			//recursive
	        			iterateSearch(moves[i], depth + 1);
	        			if (isSearching == false) return;
	        		}
	        	}     	
	        	return;
	    	}
	        //checks to see if you reached depth bound and if so set flag to true
	        if(depth >= depthBound)
        	{
        		depthBoundReached = true;
        	}

	    }
	    //resets maze 
	    public void resetMaze()
	    {
	    	 //create obstacles walls
	        for (int i=0; i<maze.getHeight()+2; i++) {
	            maze.maze[0][i] = maze.maze[maze.getWidth()+1][i] = Maze.OBSTICLE;
	        }
	        for (int i=0; i<maze.getWidth()+2; i++) {
	            maze.maze[i][0] = maze.maze[i][maze.getHeight()+1] = Maze.OBSTICLE;
	        }
	        //reset values back to 0, leave obstacles alone where they already were
	        for (int i=0; i<maze.getWidth()+2; i++) {
	            for (int j=0; j< maze.getHeight()+2; j++) {
	            	if(maze.maze[i][j] != Maze.OBSTICLE)
	            	{
	            		maze.maze[i][j] = 0;
	            	}
	            }
	        }
	        //* Specify the starting location
	         startLoc.width = 0;
	         startLoc.height = 0;
	       
	         maze.setValue(0, 0, Maze.START_LOC_VALUE);
	         //Specify the goal location
	        goalLoc.width = maze.getWidth() - 1;
	        goalLoc.height = maze.getHeight() - 1;
	        //make goal location in this case 10, 10
	        maze.setValue(maze.getWidth() - 1, maze.getHeight() - 1, Maze.GOAL_LOC_VALUE);
	          
	    }
	    
}
