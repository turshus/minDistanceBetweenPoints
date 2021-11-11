import java.util.ArrayList;

public class Graph {
    private int numPoints;
    private boolean isDecimal; //if true, the points will have up to 2 decimal places

    //default grid is 100x100
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private double zMin;
    private double zMax;

    private ArrayList<double[]> twoDGraph;
    private ArrayList<double[]> threeDGraph;

    public Graph() {
        this.numPoints = 10;
        this.isDecimal = false;

        this.xMin = 0;
        this.xMax = 10;
        this.yMin = 0;
        this.yMax = 10;
        this.zMin = 0;
        this.zMax = 10;

        this.twoDGraph = this.make2DGraph();
        this.threeDGraph = this.make3DGraph();
    }

    //overloaded constructor
    public Graph(int a_numPoints, boolean a_isDecimal) {
        this.numPoints = a_numPoints;
        this.isDecimal = a_isDecimal;

        this.xMin = 0;
        this.xMax = 100;
        this.yMin = 0;
        this.yMax = 100;
        this.zMin = 0;
        this.zMax = 100;

        //generate my two graphs
        this.twoDGraph = this.make2DGraph();
        this.threeDGraph = this.make3DGraph();
    }

    //Creates a list of 2D arrays where index 0 represents x and index 1 represents y
    private ArrayList<double[]> make2DGraph() {
        ArrayList<double[]> l_graph = new ArrayList<>();

        for(int i = 0; i < this.numPoints; i++) {
            if(this.isDecimal) {
                l_graph.add(new double[] { 
                    Math.random() * ((this.xMax - this.xMin) + 1) + this.xMin, //x value
                    Math.random() * ((this.yMax - this.yMin) + 1) + this.yMin  //y value
                }); 
            } else {
                l_graph.add(new double[] { 
                    (int)(Math.random() * ((this.xMax - this.xMin) + 1)) + this.xMin, //x value
                    (int)(Math.random() * ((this.yMax - this.yMin) + 1)) + this.yMin  //y value
                }); 
            }  
        }

        return l_graph;
    }

    //Creates a list of 3D arrays where index 0 = x, index 1 = y, and indez 2 = z
    private ArrayList<double[]> make3DGraph() {
        ArrayList<double[]> l_graph = new ArrayList<>();

        for(int i = 0; i < this.numPoints; i++) {
            if(this.isDecimal) {
                l_graph.add(new double[] {
                    Math.random() * ((this.xMax - this.xMin) + 1) + this.xMin, //x value 
                    Math.random() * ((this.yMax - this.yMin) + 1) + this.yMin, //y value
                    Math.random() * ((this.zMax - this.zMin) + 1) + this.zMin //z value
                });
            } else {
                l_graph.add(new double[] {
                    (int)(Math.random() * ((this.xMax - this.xMin) + 1) + this.xMin), //x value
                    (int)(Math.random() * ((this.yMax - this.yMin) + 1) + this.yMin), //y value
                    (int)(Math.random() * ((this.zMax - this.zMin) + 1) + this.zMin)  //z value
                });
            }
        }

        return l_graph;
    }

    //Quick Sort, argument determines if the graph is sorted by x, y, or z
    public void sort2DGraph(char a_sortByIndex) {
        if(a_sortByIndex > 122 || a_sortByIndex < 120) {
            System.out.println("Argument should be 'x', 'y', or 'z'");
            return;
        }

        //determine what index the argument is referring to
        int l_sortByIndex = 0; //x by default
        switch(a_sortByIndex) {
            case 'x':
                l_sortByIndex = 0;
                break;
            case 'y':
                l_sortByIndex = 1;
                break;
        }

        sort(this.twoDGraph, 0, this.twoDGraph.size() - 1, l_sortByIndex);
    }
    
    public void sort3DGraph(char a_sortByIndex) {
        if(a_sortByIndex > 3 || a_sortByIndex < 0) {
            System.out.println("Argument should be 0 to sort by X, 1 to sort by Y, and 2 to sort by Z...");
            return;
        }

        //determine what index the argument is referring to
        int l_sortByIndex = 0; //x by default
        switch(a_sortByIndex) {
            case 'x':
                l_sortByIndex = 0;
                break;
            case 'y':
                l_sortByIndex = 1;
                break;
            case 'z':
                l_sortByIndex = 2;
                break;
        }

        sort(this.threeDGraph, 0, this.threeDGraph.size() - 1, l_sortByIndex);
    }

    /* !! The Partition and Sort Methods were found at https://www.geeksforgeeks.org/java-program-for-quicksort/ !!
     * They have been slightly modified to accomidate my program*/
    private int partition(ArrayList<double[]> arr, int low, int high, int a_sortByIndex)
    {
        double pivot = arr.get(high)[a_sortByIndex]; 
        int i = (low-1); // index of smaller element
        for (int j = low; j < high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j)[a_sortByIndex] <= pivot)
            {
                i++;
  
                // swap arr[i] and arr[j]
                double temp = arr.get(i)[a_sortByIndex];
                arr.get(i)[a_sortByIndex] = arr.get(j)[a_sortByIndex];
                arr.get(j)[a_sortByIndex] = temp;
            }
        }
  
        // swap arr[i+1] and arr[high] (or pivot)
        double temp = arr.get(i+1)[a_sortByIndex];
        arr.get(i+1)[a_sortByIndex] = arr.get(high)[a_sortByIndex];
        arr.get(high)[a_sortByIndex] = temp;
  
        return i+1;
    }
  
    private void sort(ArrayList<double[]> arr, int low, int high, int a_sortByIndex)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(arr, low, high, a_sortByIndex);
  
            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1, a_sortByIndex);
            sort(arr, pi+1, high, a_sortByIndex);
        }
    }

    //Nested loop solution for finding the minimum distance between points
    public double minDistance_loops() {
        double l_minDistance = Double.MAX_VALUE;

        for(int i = 0; i < this.twoDGraph.size(); i++) {
            for(int j = 0; j < this.twoDGraph.size(); i++) {
                //compare minDistance to the distance between 2 points
            }
        }

        return l_minDistance;
    }

    public double minDistance_ySort() {
        double l_minDistance = Double.MAX_VALUE;

        return l_minDistance;
    }

    public double minDistance_merge() {
        double l_minDistance = Double.MAX_VALUE;

        return l_minDistance;
    }

    //Getters & Setters
    public ArrayList<double[]> get2DGraph() { return this.twoDGraph; }
    public void set2DGraph(ArrayList<double[]> a_2DGraph) { this.twoDGraph = a_2DGraph; }

    public ArrayList<double[]> get3DGraph() { return this.threeDGraph; }
    public void set3DGraph(ArrayList<double[]> a_3DGraph) { this.threeDGraph = a_3DGraph; }

    public int getNumPoints() { return this.numPoints; }
    public void setNumPoints(int a_numPoints) { 
        this.numPoints = a_numPoints;
        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }

    public boolean getIsDecimal() { return this.isDecimal; }
    public void setIsDecimal(boolean isDecimal) { 
        this.isDecimal = isDecimal; 
        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }

    //Setters:  Checks if the value makes sense, then sets it
    public double getMinX() { return this.xMin; }
    public void setMinX(double a_minX) { 
        if(a_minX < this.xMax) this.xMin = a_minX; 

        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }

    public double getMaxX() { return this.xMax; }
    public void setMaxX(double a_maxX) { 
        if(a_maxX > this.xMin) this.xMax = a_maxX; 

        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }

    public double getMinY() { return this.yMin; }
    public void setMinY(double a_minY) { 
        if(a_minY < this.yMax) this.yMin = a_minY;

        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }

    public double getMaxY() { return this.yMax; }
    public void setMaxY(double a_maxY) { 
        if(a_maxY > this.yMin) this.yMax = a_maxY;
        
        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }

    public double getMinZ() { return this.zMin; }
    public void setMinZ(double a_minZ) { 
        if(a_minZ > this.zMax) this.zMin = a_minZ;
        
        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }

    public double getMaxZ() { return this.zMax; }
    public void setMaxZ(double a_maxZ) { 
        if(a_maxZ < this.zMin) this.zMax = a_maxZ;
        
        this.twoDGraph = make2DGraph();
        this.threeDGraph = make3DGraph();
    }
}