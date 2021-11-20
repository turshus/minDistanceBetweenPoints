import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MinDistance {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting Program\n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean keepRunning = true;
        while(keepRunning) {
            System.out.println("(S)imple Case  |  (E)mpirical Study  |  (H)ybrid  |  (Q)uit");
            System.out.print("Your choice: ");
            String usrChoice = reader.readLine().toLowerCase();

            switch(usrChoice) {
                case "s":
                    System.out.println("\nStarting the Simple Case...\n");

                    //make the simple case
                    ArrayList<double[]> simpleCoordinates = new ArrayList<>();
                    simpleCoordinates.add(new double[] { 3, 4 });
                    simpleCoordinates.add(new double[] { 2, 6 });
                    simpleCoordinates.add(new double[] { 7, 7 });
                    simpleCoordinates.add(new double[] { 4, 8 });
                    simpleCoordinates.add(new double[] { 9, 2 });
                    simpleCoordinates.add(new double[] { 8, 4 });
                    simpleCoordinates.add(new double[] { 4.9, 10 });
                    simpleCoordinates.add(new double[] { 5.1, 1 });
                    simpleCoordinates.add(new double[] { 4.9, 5.1 });
                    simpleCoordinates.add(new double[] { 5.1, 4.9 });

                    //send it to the graph
                    Graph simpleGraph = new Graph();
                    simpleGraph.setMaxX(10);
                    simpleGraph.setMaxY(10);
                    simpleGraph.set2DGraph(simpleCoordinates);

                    //tell the user
                    System.out.println("Graph Before Sort:");
                    for(int i = 0; i < simpleGraph.get2DGraph().size(); i++) {
                        System.out.printf("%d: { %f, %f }\n", 
                        i, 
                        simpleGraph.get2DGraph().get(i)[0],     
                        simpleGraph.get2DGraph().get(i)[1]);
                    }

                    //double for loop method to find the two closest points
                    simpleGraph.sort2DGraph('x');

                    System.out.println("Graph:\n");
                    for(int i = 0; i < simpleGraph.get2DGraph().size(); i++) {
                        System.out.printf("%d: { %f, %f }\n", i, simpleGraph.get2DGraph().get(i)[0], simpleGraph.get2DGraph().get(i)[1]);
                    }
                    System.out.printf("Double for loop closest points: %f\n", simpleGraph.minDistance_loops());
                    System.out.printf("Y Sort Method to find closest points: %f\n", simpleGraph.minDistance());
                break;
                case "e":
                    System.out.println("\nStarting the timing study\n");

                    long timingTotTime = System.currentTimeMillis();
                    int index = 2;
                    while(true) {
                        int n = (int)Math.pow(2, index);

                        System.out.printf("\nRound: %d\nn: %d\n", index - 1, n);

                        //generate the graph
                        Graph graph = new Graph(n, true);

                        //For loop 
                        long forLoopTimeStart = System.currentTimeMillis();
                        System.out.printf("Double for loop closest points: %f\n", graph.minDistance_loops());
                        long forLoopTime = System.currentTimeMillis() - forLoopTimeStart;

                        //y sort
                        long ySortTimeStart = System.currentTimeMillis();
                        graph.sort2DGraph('x');
                        System.out.printf("Y Sort Method to find closest points: %f\n", graph.minDistance());
                        long ySortTime = System.currentTimeMillis() - ySortTimeStart;

                        //output the results
                        System.out.printf("  TOTAL time this round: %d ms\n  For Loop Time: %d ms\n  y-sort time: %d ms\n",
                            System.currentTimeMillis() - forLoopTimeStart,
                            forLoopTime,
                            ySortTime
                        );
                        index++;

                        if(System.currentTimeMillis() - forLoopTimeStart > 7200000 || System.currentTimeMillis() - ySortTimeStart > 7200000) { //if either one takes longer than an hour to run, cut it
                            System.out.println("\n\nTiming Study Concluded\nTotal Time: " + ((System.currentTimeMillis() - timingTotTime) / 1000 / 60) + " min\n");
                            break;
                        }
                    }
                break;
                case "q":
                    System.out.println("\nThanks!");
                    keepRunning = false;
                break;
                case "h":
                    System.out.println("\nStarting hybrid algorithm");

                    long hybridStartTime = System.currentTimeMillis();
                    int hybridIndex = 2;

                    while(true) {
                        int n = (int)Math.pow(2, hybridIndex);

                        Graph hybridGraph = new Graph(n, true);

                        System.out.printf("Round: %d\nn: %d\n", hybridIndex - 1, n);

                        long start = System.currentTimeMillis();
                        if(n < 2000) { //use the simple algorithm
                            System.out.printf("Closest Points: %f", hybridGraph.minDistance_loops());
                        } else { //use the complex algorithm
                            System.out.printf("Closest Points: %f", hybridGraph.minDistance());
                        }
                        long end = System.currentTimeMillis() - start;

                        System.out.printf("\nTime: %d ms\n\n", end);

                        if(end > 3500000) {
                            break;
                        } else {
                            hybridIndex++;
                        }
                    }
                break;
                default:
                    System.out.println("\nInvalid Input.  Enter a 's', 'e', or 'q'...\n");
                break;
            }
        }
    }
}