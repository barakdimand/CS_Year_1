import java.util.ArrayList;
import java.awt.Color; 
 
/** 
 * Barak Dimand 329951131
 * A class for decomposing images into connected components. 
 */ 
public class Maze {
    private UnionFind uf;
    private DisplayImage image;
    private int startX;   // x-coordinate for the start pixel
    private int startY;	 // y-coordinate for the start pixel
    private int endX;	 // x-coordinate for the end pixel
    private int endY;	 // y-coordinate for the end pixel
  
    /**
    * Constructor - reads image, and decomposes it into its connected components.
    * After calling the constructor, the mazeHasSolution() and areConnected() methods
	* are expect to return a correct solution.
    * 
    * @param fileName name of image file to process. 
    */ 
    public Maze (String fileName) {
        image = new DisplayImage(fileName);
        int numPixels = image.height() * image.width();
        uf = new UnionFind(numPixels);
        boolean found = false;

        /* Iterates through image pixels.
           Connects each pixel with its right and lower neighbors. */
        for (int j = 0; j < image.height() - 1; j++) {
            int i;
            for (i = 0; i < image.width() - 1; i++) {

                // Checks for starting and end points.
                found = RedSetter(i, j, found);
                found = RedSetter(i + 1, j, found);
                found = RedSetter(i,j + 1, found);
                connect(i, j,i + 1, j);
                connect(i, j, i,j + 1);
            }

            // Check right most column.
            connect(i, j, i,j + 1);
        }

        // Checks bottom most row.
        for (int k = 0; k < image.width() - 1; k++) {
            connect(k,image.height() - 1,k + 1,image.height() - 1);
        }
    }

    /**
     * RedSetter - checks whether a pixel is red.
     * If it is the first red pixel, sets as start point.
     * If it is the second (and last) red pixel, saves as end point.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param found False if we haven't found the first red pixel,
     *              true otherwise.
     * @return the new boolean value of found.
     */
    private boolean RedSetter (int x, int y, boolean found) {

        // Checks if the pixel is red. If not, change nothing.
        if (image.isRed(x,y)) {
            if (!found) { //Sets the start point.
                startX = x;
                startY = y;
                image.set(x, y, new Color(0xFF,0xFF,0xFF));
            } else { // Sets the end point.
                endX = x;
                endY = y;
                image.set(x, y, new Color(0xFF, 0xFF, 0xFF));
            }
            found = true;
        }
        return found;
    }
 
   /** 
    * Generates a unique integer id from (x, y) coordinates. 
    * It is suggested you implement this function, in order to transform
	* pixels into valid indices for the UnionFind data structure.
    *
	* @param x x-coordinate. 
    * @param y y-coordinate. 
    * @return unique id. 
    */ 
    private int pixelToId (int x, int y) {
        return y * image.width() + x + 1;
    }
 
   /** 
    * Connects two pixels if they both belong to the same image 
    * area (background or foreground), and are not already connected. 
    * It is assumed that the pixels are neighbours.
    * 
    * @param x1 x-coordinate of first pixel. 
    * @param y1 y-coordinate of first pixel. 
    * @param x2 x-coordinate of second pixel. 
    * @param y2 y-coordinate of second pixel. 
    */ 
    public void connect (int x1, int y1, int x2, int y2) {
        if (image.isOn(x1, y1) && image.isOn(x2, y2) ||
               !image.isOn(x1, y1) && !image.isOn(x2, y2)) {
            int coord1 = pixelToId(x1, y1);
            int coord2 = pixelToId(x2, y2);
            uf.union(coord1, coord2);
        }
    }
 

 
   /** 
    * Checks if two pixels are connected (belong to the same component). 
    * 
    * @param x1 x-coordinate of first pixel. 
    * @param y1 y-coordinate of first pixel. 
    * @param x2 x-coordinate of second pixel. 
    * @param y2 y-coordinate of second pixel. 
    * @return true if the pixels are connected, false otherwise. 
    */ 
    public boolean areConnected (int x1, int y1, int x2, int y2) {
        return (uf.find(pixelToId(x1, y1)) == uf.find(pixelToId(x2, y2)));
    }
 
   /** 
    * Finds the number of components in the image. 
    * 
    * @return the number of components in the image 
    */ 
    public int getNumComponents() {
        return uf.getNumSets();
    }
 
   /**
    * Returns true if and only if the maze has a solution.
    * In this case, the start and end pixel will belong to the same connected component.
    * 
    * @return true if and only if the maze has a solution
    */
    public boolean mazeHasSolution(){
	    return areConnected(startX, startY, endX, endY);
    }

   /** 
    * Creates a visual representation of the connected components. 
    * 
    * @return a new image with each component colored in a random color. 
    */
    public DisplayImage getComponentImage() {

        DisplayImage compImg = new DisplayImage (image.width(), image.height());
        ArrayList<Integer> usedIds = new ArrayList<Integer>();
        int numComponents = getNumComponents();
        final int MAX_COLOR = 0xffffff;
        Color colors[] = new Color[numComponents];

        colors[0] = new Color (0, 0, 100);
        for (int c = 1; c < numComponents; c++)
            colors[c] = new Color ((int)(Math.random() * MAX_COLOR));

        for (int x = 0; x < image.width(); x++)
            for (int y = 0; y < image.height(); y++) {
                int componentId = uf.find (pixelToId (x, y));
                // Check if this id already exists (inefficient for a large number of components).
                int index = -1;
                for (int i = 0; i < usedIds.size(); i++)
                    if (usedIds.get (i) == componentId)
                        index = i;
                if (index == -1) {
                    usedIds.add (componentId);
                    index = usedIds.size() - 1;
                }
                compImg.set (x, y, colors[index]);
            }

        return compImg;
    }

   /** 
    * Various tests for the segmentation functionality. 
    * 
    * @param args command line arguments - name of file to process. 
    */ 
    public static void main (String[] args) {
 
	    Maze maze = new Maze (args[0]);
 
        System.out.println (maze.mazeHasSolution());
 
        System.out.println ("Number of components: " + maze.getNumComponents());
 
        DisplayImage compImg = maze.getComponentImage();
        compImg.show();
    }
}