
public class MinDistance {
    public static void main(String[] args) {
        Graph graph = new Graph(5, false);

        System.out.println("Before sort: \n");
        for(int i = 0; i < graph.get2DGraph().size(); i++) {
            System.out.printf("%d: { X: %f, Y: %f }\n", i, 
                graph.get2DGraph().get(i)[0], 
                graph.get2DGraph().get(i)[1]
            );
        }

        System.out.println("\nAfter sort: \n");
        graph.sort2DGraph('y');
        for(int i = 0; i < graph.get2DGraph().size(); i++) {
            System.out.printf("%d: { X: %f, Y: %f }\n", i, 
                graph.get2DGraph().get(i)[0], 
                graph.get2DGraph().get(i)[1]
            );
        }
    }
}