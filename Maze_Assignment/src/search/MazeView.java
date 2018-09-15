package search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeView extends JFrame {
	    JPanel jPanel1 = new JPanel();
	    AbstractSearchEngine currentSearchEngine = null; 
	    Dimension [] path = null;

	    public MazeView(AbstractSearchEngine searchEngine) {
	        try {
	          jbInit();
	        } catch (Exception e) {
	          System.out.println("GUI initilization error: " + e);
	        }
	        currentSearchEngine = searchEngine;
	        //sets the title for the algorithm onto gui
	        this.setTitle(currentSearchEngine.getClass().getName() +" is working ");
	        repaint();
	    }

	    public void paint(Graphics g_unused) {
	    	super.paint(g_unused);
	        if (currentSearchEngine == null) return;
	        Maze maze = currentSearchEngine.getMaze();
	        int width = maze.getWidth();
	        int height = maze.getHeight();
	        if (MyMazes.DEBUG)
	        	System.out.println("Size of current maze: " 
	        			+ width + " by " + height);
	        Graphics g = jPanel1.getGraphics();
	        BufferedImage image = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
	        Graphics g2 = image.getGraphics();
	        g2.setColor(Color.white);
	        g2.fillRect(0, 0, 320, 320);
	        g2.setColor(Color.black);
	        for (int x=0; x<width; x++) {
	            for (int y=0; y<height; y++) {
	                short val = maze.getValue(x,y);
	                if ( val == Maze.OBSTICLE) {
	                    g2.setColor(Color.lightGray);
	                    g2.fillRect(6 + x * 28, 3 + y * 29, 29, 30);
	                } else if (val == Maze.START_LOC_VALUE || val == 1) {
	                    g2.setColor(Color.blue);
	                    g2.drawString("S", 16 + x * 28, 19 + y * 29);
	                } else if (val == Maze.GOAL_LOC_VALUE) {
	                    g2.setColor(Color.red);
	                    g2.drawString("G", 16 + x * 28, 19 + y * 29);
	                } 
	            }
	        }
	        // redraw the path in black:
	        g2.setColor(Color.black);
	        Dimension [] path = currentSearchEngine.getPath();
	        for (int i=1; i< path.length; i++) {
	          int x = path[i].width;
	          int y = path[i].height;
	          short val = maze.getValue(x,y);
	          if (val == 1) continue; //startLoc was drawn before
	          g2.drawString("" + val, 16 + x * 28, 19 + y * 29);
	        }
	        g.drawImage(image, 30, 40, 320, 320, null);

	    }


	    private void jbInit() throws Exception {

	        this.setContentPane(jPanel1);
	        this.setCursor(null);
	        this.setDefaultCloseOperation(3);
	        
	        this.getContentPane().setLayout(null);
	        jPanel1.setBackground(Color.white);
	        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
	        jPanel1.setDoubleBuffered(false);
	        jPanel1.setRequestFocusEnabled(false);
	        jPanel1.setLayout(null);
	        this.setSize(370, 420);
	        this.setVisible(true);
	    }
}
