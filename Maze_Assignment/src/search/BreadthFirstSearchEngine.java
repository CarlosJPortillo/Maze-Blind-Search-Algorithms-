package search;

/**
 * Title:        BreadthFirstSearchEngine<p>
 * Description:  Performs a Breadth first search in a maze<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

import java.awt.Dimension;

public class BreadthFirstSearchEngine extends AbstractSearchEngine {
    public BreadthFirstSearchEngine(Maze maze) {
        super(maze); //calling the constructor method in the superclass AbstractSearchEngine
        doSearchOn2DGrid();
    }

    private void doSearchOn2DGrid() {
        int width = maze.getWidth();
        int height = maze.getHeight();
        //keeps track of places already visited
        boolean alReadyVisitedFlag[][] = new boolean[width][height];
        //float distanceToNode[][] = new float[width][height];
        Dimension predecessor[][] = new Dimension[width][height];
        //creates queue data structure, FIFO. Think grocery store line
        //uses an own self implemented data structure
        DimensionQueue queue = new DimensionQueue();

        //initializes  alreadyVisitedFlags array to false every space
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                alReadyVisitedFlag[i][j] = false;
                //distanceToNode[i][j] = 10000000.0f;
                predecessor[i][j] = null;
            }
        }

        //sets the inital starting spot visited to true so you never go back to it
        alReadyVisitedFlag[startLoc.width][startLoc.height] = true;
        //distanceToNode[startLoc.width][startLoc.height] = 0.0f;
        //adds starting position to the back of the queue 
        queue.addToBackOfQueue(startLoc);
        boolean success = false;
    outer:      
        while (queue.isEmpty() == false) {
            Dimension head = queue.peekAtFrontOfQueue();
            //head is null break out and calculate shortest path
            if (head == null) break; 
            //get possible moves
            Dimension [] connected = getPossibleMoves(head);
            //step through all possible direction
            for (int i=0; i<4; i++) {
                if (connected[i] == null) break;
                int w = connected[i].width;
                int h = connected[i].height;
                if (alReadyVisitedFlag[w][h] == false) {
                    //distanceToNode[w][h] = distanceToNode[w][h] + 1.0f;
                	
                    alReadyVisitedFlag[w][h] = true;
                    //keeps track of the previous position
                    predecessor[w][h] = head;
                    //the location we haven't visited gets added to back of queue
                    // and then it continues to searcharound there
                    queue.addToBackOfQueue(connected[i]);
                    if (equals(connected[i], goalLoc)) {
                        success = true;
                        break outer; // we are done
                    }
                }
            }
            //once we looked all possible directions, search items that are in 
            //in front of queue to the end
            queue.removeFromFrontOfQueue(); // ignore return value
        }
        // now calculate the shortest path from the predecessor array:
        maxDepth = 0;
        if (success) {
            searchPath[maxDepth++] = goalLoc;
            //MAXSTEPS = 1000 is defined in AbstractSearchEngine
            for (int i=0; i<MAXSTEPS; i++) {
                searchPath[maxDepth] = predecessor[searchPath[maxDepth - 1].width][searchPath[maxDepth - 1].height];
                maxDepth++;
                if (equals(searchPath[maxDepth - 1], startLoc))  break;  // back to starting node
            }
            
            Dimension[] path = this.getPath(); 
            for (int i = 1; i < (path.length); i ++){
            	int x = path[i].width; 
            	int y = path[i].height; 
            	maze.setValue(x, y, (short)(path.length - i)); 
            }
        }
    }
    
    protected class DimensionQueue {
    	final static short DEFAULTSIZE=400; 
    	
        public DimensionQueue(int num) {
            queue = new Dimension[num];
            head = tail = 0;
            len = num;
        }
        public DimensionQueue() {
            this(DEFAULTSIZE);
        }
        public void addToBackOfQueue(Dimension n) {
            queue[tail] = n;
            if (tail >= (len - 1)) {
                tail = 0;
            } else {
                tail++;
            }
        }
        
        public Dimension removeFromFrontOfQueue() {
            Dimension ret = queue[head];
            if (head >= (len - 1)) {
                head = 0;
            } else {
                head++;
            }
            return ret;
        }
        public boolean isEmpty() {
            return head == (tail + 1);
        }
        public Dimension peekAtFrontOfQueue() {
            return queue[head];
        }       
        private Dimension [] queue;
        private int tail, head, len;
    }
}
