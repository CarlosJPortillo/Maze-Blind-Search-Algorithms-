package search;

/**
 * Title:        DepthFirstSearchEngine<p>
 * Description:  Performs a depth first search in a maze<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

import java.awt.Dimension;

//recursive algorithm that keeps expanding and going down until there are no more branching paths
//if you can't go any futher down current path, return to a previous branch and see if another path
//exists that can be expanded, continue until goal node is reached or if there are not paths 
//that you can expand, which in that case there is no solution 
//this is a backtracking algorithm search


public class DepthFirstSearchEngine extends AbstractSearchEngine {
	
    public DepthFirstSearchEngine(Maze maze) {
        super(maze);
        iterateSearch(startLoc, 1);
    }

    // pass location and depth 
    private void iterateSearch(Dimension loc, int depth) {
    	//if you're not searching anymore return
        if (isSearching == false) return;
        //tracks movement on board, +1 +1 width and height 
        maze.setValue(loc.width, loc.height, (short)depth);
        //get possible moves from current position
        //this can be thought of expanding a node/creating the branches
        //in depth first search you have a path where go down the first child node
        Dimension [] moves = getPossibleMoves(loc);
        for (int i=0; i<4; i++) {
            if (moves[i] == null) {
            	break; // out of loop if you can't progress any further on current and return 
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
                //escape function 
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
}
