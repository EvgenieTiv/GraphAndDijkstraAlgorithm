package graph;

import dijkstra.Dijkstra;

public class Main 
{

	public static void main(String[] args)
	{
		Vertice v1 = new Vertice("1");
		Vertice v2 = new Vertice("2");
		Vertice v3 = new Vertice("3");
		Vertice v4 = new Vertice("4");
		Vertice v5 = new Vertice("5");
		Vertice v6 = new Vertice("6");
		
		Graph.getInstance().add(v1);
		Graph.getInstance().add(v2);
		Graph.getInstance().add(v3);
		Graph.getInstance().add(v4);
		Graph.getInstance().add(v5);
		Graph.getInstance().add(v6);
		
		Graph.getInstance().setStart(v1);
		Graph.getInstance().setEnd(v5);
		
		Edge v1_to_v2 = new Edge(v1, v2, 7);
		Edge v1_to_v3 = new Edge(v1, v3, 9);
		Edge v1_to_v6 = new Edge(v1, v6, 14);
		
		Edge v2_to_v3 = new Edge(v2, v3, 10);
		Edge v2_to_v4 = new Edge(v2, v4, 15);
		
		Edge v3_to_v4 = new Edge(v3, v4, 11);
		Edge v3_to_v6 = new Edge(v3, v6, 2);
		
		Edge v4_to_v5 = new Edge(v4, v5, 6);
		
		Edge v5_to_v6 = new Edge(v5, v6, 9);
		
		Graph.getInstance().add(v1_to_v2);
		Graph.getInstance().add(v1_to_v3);
		Graph.getInstance().add(v1_to_v6);
		Graph.getInstance().add(v2_to_v3);
		Graph.getInstance().add(v2_to_v4);
		Graph.getInstance().add(v3_to_v4);
		Graph.getInstance().add(v3_to_v6);
		Graph.getInstance().add(v4_to_v5);
		Graph.getInstance().add(v5_to_v6);

		Dijkstra.getInstance().runDijkstra();
	}

}
