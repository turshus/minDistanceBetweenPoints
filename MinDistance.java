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
            System.out.println("(S)imple Case  |  (E)mpirical Study  |  (Q)uit");
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
                    System.out.println("Graph:\n");
                    for(int i = 0; i < simpleGraph.get2DGraph().size(); i++) {
                        System.out.printf("%d: { %f, %f }\n", i, simpleGraph.get2DGraph().get(i)[0], simpleGraph.get2DGraph().get(i)[1]);
                    }

                    //double for loop method to find the two closest points
                    simpleGraph.sort2DGraph('x');

                    System.out.println("Graph:\n");
                    for(int i = 0; i < simpleGraph.get2DGraph().size(); i++) {
                        System.out.printf("%d: { %f, %f }\n", i, simpleGraph.get2DGraph().get(i)[0], simpleGraph.get2DGraph().get(i)[1]);
                    }
                    System.out.printf("Double for loop closest points: %f", simpleGraph.minDistance_loops());
                    System.out.printf("Y Sort Method to find closest points: %f", simpleGraph.minDistance_ySort());
                    System.out.printf("Merg Sort Method to find closest points: %f", simpleGraph.minDistance_merge());

                break;
                case "e":
                    break;
                case "q":
                    System.out.println("\nThanks!");
                    keepRunning = false;
                break;
                default:
                    System.out.println("\nInvalid Input.  Enter a 's', 'e', or 'q'...\n");
                break;
            }
        }
    }
}