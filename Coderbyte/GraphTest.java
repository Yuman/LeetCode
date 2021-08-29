package Coderbyte;

import java.util.List;
/*
       BOS    MCO
      /      /  |
    JFK -- MIA  |
    /        \  |
DFW           PBI
   \
    LAX -- EWR
     / \
   HNL  SAN -- HKG


*/
public class GraphTest {
    public static void main(String[] args) {
        GraphNode<String> DFW = new GraphNode<>("DFW");
        GraphNode<String> JFK = new GraphNode<>("JFK");
        GraphNode<String> LAX = new GraphNode<>("LAX");
        GraphNode<String> HNL = new GraphNode<>("HNL");
        GraphNode<String> SAN = new GraphNode<>("SAN");
        GraphNode<String> EWR = new GraphNode<>("EWR");
        GraphNode<String> BOS = new GraphNode<>("BOS");
        GraphNode<String> MIA = new GraphNode<>("MIA");
        GraphNode<String> MCO = new GraphNode<>("MCO");
        GraphNode<String> PBI = new GraphNode<>("PBI");
        // connection is only one way. inside a node, the reverse is created.
        DFW.connect(LAX);
        DFW.connect(JFK);
        LAX.connect(HNL);
        LAX.connect(EWR);
        LAX.connect(SAN);
        JFK.connect(BOS);
        JFK.connect(MIA);
        MIA.connect(MCO);
        MCO.connect(PBI);
        MIA.connect(PBI);

        Graph<GraphNode<String>> graph = new Graph<>(List.of(DFW, JFK, LAX, HNL, SAN, EWR, BOS, MIA, MCO, PBI));
        System.out.println(graph);
        Graph.bfs(DFW, MIA);
        System.out.println("Shortest:" + Graph.shortestPath(DFW, MCO));
        System.out.println("Shortest:" + Graph.shortestPath(JFK, LAX));
        System.out.println("Shortest:" + Graph.shortestPath(HNL, PBI));
    }
}
