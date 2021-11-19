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
     * They have been slightly modified to accomdate my program*/
    private int partition(ArrayList<double[]> arr, int low, int high, int a_sortByIndex)
    {
        double pivot = arr.get(high)[a_sortByIndex]; 
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j)[a_sortByIndex] <= pivot)
            {
                i++;
                double tempX = 0.0;
                double tempY = 0.0;
  
                // swap arr[i] and arr[j]
                switch(a_sortByIndex) {
                    case 0: //case of 'x'
                        tempX = arr.get(i)[a_sortByIndex];
                        tempY = arr.get(i)[a_sortByIndex + 1];

                        arr.get(i)[a_sortByIndex] = arr.get(j)[a_sortByIndex];
                        arr.get(i)[a_sortByIndex + 1] = arr.get(j)[a_sortByIndex + 1];
                        
                        arr.get(j)[a_sortByIndex] = tempX;
                        arr.get(j)[a_sortByIndex + 1] = tempY;

                        if(arr.get(i).length > 2) {
                            double tempZ = arr.get(i)[a_sortByIndex + 2];
                            arr.get(i)[a_sortByIndex + 2] = arr.get(j)[a_sortByIndex + 2];
                            arr.get(j)[a_sortByIndex + 2] = tempZ;
                        }
                    break;
                    case 1: //case of 'y'
                        tempX = arr.get(i)[a_sortByIndex - 1];
                        tempY = arr.get(i)[a_sortByIndex];

                        arr.get(i)[a_sortByIndex - 1] = arr.get(j)[a_sortByIndex - 1];
                        arr.get(i)[a_sortByIndex] = arr.get(j)[a_sortByIndex];

                        arr.get(j)[a_sortByIndex - 1] = tempX;
                        arr.get(j)[a_sortByIndex] = tempY;

                        if(arr.get(i).length > 2) {
                            double tempZ = arr.get(i)[a_sortByIndex + 1];
                            arr.get(i)[a_sortByIndex + 1] = arr.get(j)[a_sortByIndex + 1];
                            arr.get(j)[a_sortByIndex + 1] = tempZ;
                        }
                    break;
                    case 2: //case of 'z'
                    break;
                }
            }
        }
  
        // swap arr[i+1] and arr[high] (or pivot)
        double tempX = 0.0;
        double tempY = 0.0;
        switch(a_sortByIndex) {
            case 0: //case of 'x'
                tempX = arr.get(i + 1)[a_sortByIndex];
                tempY = arr.get(i + 1)[a_sortByIndex + 1];

                arr.get(i + 1)[a_sortByIndex] = arr.get(high)[a_sortByIndex];
                arr.get(i + 1)[a_sortByIndex + 1] = arr.get(high)[a_sortByIndex + 1];

                arr.get(high)[a_sortByIndex] = tempX;
                arr.get(high)[a_sortByIndex + 1] = tempY;

                //in case our graph is a_3DGraph
                if(arr.get(i + 1).length > 2) {
                    double tempZ = arr.get(i + 1)[a_sortByIndex + 2];
                    arr.get(i + 1)[a_sortByIndex + 2] = arr.get(high)[a_sortByIndex + 2];
                    arr.get(high)[a_sortByIndex + 2] = tempZ;
                }
            break;
            case 1: //case of 'y'
                tempX = arr.get(i + 1)[a_sortByIndex - 1];
                tempY = arr.get(i + 1)[a_sortByIndex];

                arr.get(i + 1)[a_sortByIndex - 1] = arr.get(high)[a_sortByIndex - 1];
                arr.get(i + 1)[a_sortByIndex] = arr.get(high)[a_sortByIndex];

                arr.get(high)[a_sortByIndex - 1] = tempX;
                arr.get(high)[a_sortByIndex] = tempY;

                //in the case our graph is a 3DGraph
                if(arr.get(i + 1).length > 2) {
                    double tempZ = arr.get(i + 1)[a_sortByIndex + 1];
                    arr.get(i + 1)[a_sortByIndex + 1] = arr.get(high)[a_sortByIndex + 1];
                    arr.get(high)[a_sortByIndex + 1] = tempZ;
                }
            break;
            case 2: //case of 'z'
            break;
        }
  
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
            for(int j = 0; j < this.twoDGraph.size(); j++) {
                //compare minDistance to the distance between 2 points
                if(i != j) {
                    double x = this.twoDGraph.get(j)[0] - this.twoDGraph.get(i)[0]; //x2 - x1
                    double y = this.twoDGraph.get(i)[1] - this.twoDGraph.get(j)[1]; //y1 - y2

                    double l_distanceBetweenPoints = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                    if(l_minDistance > l_distanceBetweenPoints) l_minDistance = l_distanceBetweenPoints;
                }  
            }
        }

        return l_minDistance;
    }

    //"under"loaded function because java doesn't support default args (boo!)
    public double minDistance() {
        return minDistance_ySort(this.twoDGraph);
    }

    public double minDistance_ySort(ArrayList<double[]> a_graph) {
        double l_minDistance = Double.MAX_VALUE;

        //base case
        if(a_graph.size() == 2) {
            return Math.sqrt( //distance between points
                Math.pow(a_graph.get(0)[0] - a_graph.get(1)[0], 2) + //x2 - x1 
                Math.pow(a_graph.get(1)[1] - a_graph.get(0)[1], 2)   //y1 - y2
            );
        }

        //split the graph in half
        ArrayList<double[]> l_graph_1stHalf = new ArrayList<>();
        ArrayList<double[]> l_graph_2ndHalf = new ArrayList<>();

        for(int i = 0; i < a_graph.size() / 2; i++) {
            l_graph_1stHalf.add(a_graph.get(i));
            l_graph_2ndHalf.add(a_graph.get(a_graph.size() / 2 + i));            
        }

        //recursively find the min distance on each side of the graph
        l_minDistance = Math.min(
            minDistance_ySort(l_graph_1stHalf),
            minDistance_ySort(l_graph_2ndHalf)
        );

        //solution construction step
        ArrayList<double[]> closePts = new ArrayList<>();
        for(int i = 0; i < a_graph.size(); i++) {
            if( a_graph.get(i)[0] > l_graph_1stHalf.get(l_graph_1stHalf.size() - 1)[0] - l_minDistance &&
                a_graph.get(i)[0] < l_graph_1stHalf.get(l_graph_1stHalf.size() - 1)[0] + l_minDistance) {

                closePts.add(a_graph.get(i));
            }
        }

        //sort by y
        sort(closePts, 0, closePts.size() - 1, 1);

        for(int i = 0; i < a_graph.size(); i++) { //loop through each point
            int secondLoopDist = 0;
            if(closePts.size() >= 7) {
                secondLoopDist = 7;
            } else {
                secondLoopDist = closePts.size();
            }
            for(int j = 0; j < secondLoopDist; j++) { //loop through the next 6 points according to Y
                if(a_graph.get(i)[0] != closePts.get(j)[0]       //make sure the x's and y's don't match up ( will always result in a distance of 0 )
                   && a_graph.get(i)[1] != closePts.get(j)[1]) {

                    double x = a_graph.get(i)[0] - closePts.get(j)[0]; //x2 - x1
                    double y = a_graph.get(i)[1] - closePts.get(j)[1]; //y1 - y2

                    double l_distanceBetweenPoints = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                    if(l_distanceBetweenPoints < l_minDistance) { //update minDistance
                        l_minDistance = l_distanceBetweenPoints;
                    }
                }
                
            }
        }

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