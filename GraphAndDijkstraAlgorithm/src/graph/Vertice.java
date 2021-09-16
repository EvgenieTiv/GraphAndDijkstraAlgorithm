package graph;

import java.util.*;

public class Vertice 
{
	private List<Edge> edges = new ArrayList<Edge>();
	private String name;
	
	public Vertice(String name)
	{
		setName(name);
		setEdges(new ArrayList<Edge>());
	}

	public List<Edge> getEdges()
	{
		return edges;
	}

	protected void setEdges(List<Edge> edges) 
	{
		this.edges = edges;
	}

	public String getName() 
	{
		return name;
	}

	protected void setName(String name)
	{
		this.name = name;
	}

	public void addEdge(Edge edge)
	{
		edges.add(edge);
	}
	
	public Edge getEdgeWithAnotherVertice(Vertice other)
	{
		Edge answer = null;
		
		for(Edge edge : getEdges())
		{
			String nameA = edge.getVerticeA().getName();
			String nameB = edge.getVerticeB().getName();
			
			if ((nameA.equals(getName()))&&(nameB.equals(other.getName())) ||
			(nameB.equals(getName()))&&(nameA.equals(other.getName())))
			{
				answer = edge;
			}
		}
		
		return answer;
	}
	
	public boolean equals(Object other)	
	{
		try
		{
			Vertice otherVertice = (Vertice) other;
			
			return (otherVertice.getName().equals(getName()));
		}
		
		catch(ClassCastException e) 
		{
			return false;
		}
	}

	@Override
	public String toString()
	{
		String answer = "Vertice "+getName()+" edges"+"\n";
		
		for (Edge edge : getEdges())
		{
			answer += "From "+ edge.getVerticeA().getName() + " To " + edge.getVerticeB().getName() +" " + edge.getWeight()+"\n";
		}
		
		return answer;
	}
}
