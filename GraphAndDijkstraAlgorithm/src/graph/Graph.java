package graph;

import java.util.*;

public class Graph 
{
	private static Graph instance = null;
	
	private Vertice start;
	private Vertice end;
	private List<Vertice> vertices = new ArrayList<Vertice>();
	
	private Graph()
	{
		
	}

	public static Graph getInstance()
	{
		if (instance == null)
			instance = new Graph();
		
		return instance;
	}

	public void add(Vertice vertice)
	{		
		if (!verticeExists(vertice))
			vertices.add(vertice);
	}
	
	//public void add(Vertice verticeA, Edge edge, Vertice verticeB)
	public void add(Edge edge)
	{
		Vertice verticeA = edge.getVerticeA();
		Vertice verticeB = edge.getVerticeB();
		
		if  (!edgeExists(verticeA, edge, verticeB))
		{
			add(verticeA);
			add(verticeB);
		
			verticeA.addEdge(edge);
			verticeB.addEdge(edge);
		}
	}	
	
	private boolean verticeExists(Vertice newVertice)
	{
		boolean answer = false;
		
		for(Vertice vertice : getVertices())
			if (vertice.equals(newVertice))
				answer = true;
		
		return answer;
	}
	
	private boolean edgeExists(Vertice verticeA, Edge edge, Vertice verticeB)
	{
		boolean answer = false;
		
		for(Edge otherEdge : verticeA.getEdges())
			if (edge.equals(otherEdge))
				answer = true;
		
		for(Edge otherEdge : verticeB.getEdges())
			if (edge.equals(otherEdge))
				answer = true;		
		
		return answer;
	}
	
	@Override
	public String toString()
	{
		String answer = "";
		
		for (Vertice vertice : getVertices())
			answer += vertice.toString() + "\n\n";
		
		return answer;
	}
	
	public void display()
	{
		System.out.println(toString());
	}
	
	public Vertice getStart() 
	{
		return start;
	}

	protected void setStart(Vertice start) 
	{
		this.start = start;
	}

	public List<Vertice> getVertices() 
	{
		return vertices;
	}

	public Vertice getEnd() 
	{
		return end;
	}

	protected void setEnd(Vertice end) 
	{
		this.end = end;
	}
}
