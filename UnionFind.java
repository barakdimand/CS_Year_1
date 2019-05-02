/**
 * Barak Dimand	
 * 329951131
 * Implementation of the Union-Find ADT. 
 */ 
public class UnionFind { 
 
    private int up[];
    private int weight[];
    int numSets;
 
 
   /** 
    * Constructor - initializes up and weight arrays. 
    * 
    * @param numElements initial number of singleton sets. 
    */ 
    public UnionFind (int numElements) {
        numSets = numElements;
        up = new int[numElements + 1];
        weight = new int[numElements + 1];

        // Initializes each singleton set
        for (int i = 1; i <= numElements; i++) {
            up[i] = -1;
            weight[i] = 1;
        }
    }
 
   /** 
    * Unites two sets using weigthed union. 
    * 
    * @param i representative of first set. 
    * @param j representative of second set. 
    */ 
    public void union (int i, int j) {
        int iRep = find(i);
        int jRep = find(j);

        // If the representatives are the same, do nothing
        if (iRep == jRep){
            return;
        }

        // Connects smaller set to representative of larger set
        if (weight[iRep] < weight[jRep]) {
            up[iRep] = jRep;
            weight[jRep] += weight[iRep];
            weight[iRep] = 0;
        } else {
            up[jRep] = iRep;
            weight[iRep] += weight[jRep];
            weight[jRep] = 0;
        }
        numSets--;
    }
 
   /** 
    * Finds the set representative, and applies path compression. 
    * 
    * @param i the element to search. 
    * @return the representative of the group that contains i. 
    */ 
    public int find (int i) {
        int temp = i;
        while (up[temp] != -1) {
            temp = up[temp];
        }

        // Applies path compression
        int x;
        while (up[i] != -1) {
            x = up[i];
            up[i] = temp;
            i = x;
        }
        return temp;
    }
 
   /** 
    * Find the current number of sets. 
    * 
    * @return the number of set. 
    */ 
    public int getNumSets() {
        return numSets;
    }
 
    /**
    * Prints the contents of the up array. 
    */ 
    private void debugPrintUp() {
 
        System.out.print ("up:     ");
        for (int i = 1; i < up.length; i++)
            System.out.print (up[i] + " ");
        System.out.println ("");
    }
 
    /**
    * Prints the results of running find on all elements. 
    */ 
    private void debugPrintFind() {
 
        System.out.print ("find:   ");
        for (int i = 1; i < up.length; i++)
            System.out.print (find (i) + " ");
        System.out.println ("");
    }
 
    /**
    * Prints the contents of the weight array. 
    */ 
    private void debugPrintWeight() {
 
        System.out.print ("weight: ");
        for (int i = 1; i < weight.length; i++)
            System.out.print (weight[i] + " ");
        System.out.println ("");
    }
 
    /**
    * Various tests for the Union-Find functionality. 
    * 
    * @param args command line arguments - not used. 
    */ 
    public static void main (String[] args) {
 
        UnionFind uf = new UnionFind (10);
 
        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println ("");
 
        uf.union (2, 1);
        uf.union (3, 2);
        uf.union (4, 2);
        uf.union (5, 2);
 
        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println();
 
        uf.union (6, 7);
        uf.union (8, 9);
        uf.union (6, 8);
 
        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println();
 
        uf.find (8);
 
        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println();
    }
}