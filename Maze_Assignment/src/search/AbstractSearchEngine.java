package search;

/**
 * Title:        AbstractSearchEngine<p>
 * Description:  Abstract search engine for searching paths in a maze<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

import java.awt.Dimension;

public class AbstractSearchEngine {
	final static short MAXSTEPS = 1000; 

    public AbstractSearchEngine(Maze maze) {
        this.maze = maze;
        initSearch();
    }
    public Maze getMaze() { return maze; }
    protected Maze maze;
    /**
     * We will use the Java type Dimension (fields width and height will
     * encode the coordinates in x and y directions) for the search path:
     */
    //protected int depthBound = 0;
    protected Dimension [] searchPath = null;
    protected int pathCount;
    //max depeth of search algorithm
    protected int maxDepth;
    protected Dimension startLoc, goalLoc, currentLoc;
    protected boolean isSearching = true;

    protected void initSearch() {
        if (searchPath == null) {
            searchPath = new Dimension[MAXSTEPS];
            for (int i=0; i<MAXSTEPS; i++) {
                searchPath[i] = new Dimension();
            }
        }
        pathCount = 0;
        startLoc = maze.startLoc;
        currentLoc = startLoc;
        goalLoc = maze.goalLoc;
        searchPath[pathCount++] = currentLoc;
    }

    protected boolean equals(Dimension d1, Dimension d2) {
        return d1.getWidth() == d2.getWidth() && d1.getHeight() == d2.getHeight();
    }

    public Dimension [] getPath() {
      Dimension [] ret = new Dimension[maxDepth];
      for (int i=0; i<maxDepth; i++) {
        ret[i] = searchPath[i];
      }
      return ret;
    }
    protected Dimension [] getPossibleMoves(Dimension loc) {
    	//creates an array of 4 possible moves
        Dimension tempMoves [] = new Dimension[4];
        //initializes them to null
        tempMoves[0] = tempMoves[1] = tempMoves[2] = tempMoves[3] = null;
        int x = loc.width;
        int y = loc.height;
        int num = 0;
        //goes through each possible step
        if (maze.getValue(x - 1, y) == 0 || maze.getValue(x - 1, y) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x - 1, y);
        }
        if (maze.getValue(x + 1, y) == 0 || maze.getValue(x + 1, y) == Maze.GOAL_LOC_VALUE) {
        	//adds move to tempMoves array and then increments
            tempMoves[num++] = new Dimension(x + 1, y);
        }
        if (maze.getValue(x, y - 1) == 0 || maze.getValue(x, y - 1) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x, y - 1);
        }
        if (maze.getValue(x, y + 1) == 0 || maze.getValue(x, y + 1) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x, y + 1);
        }
        return tempMoves;
    }
}
