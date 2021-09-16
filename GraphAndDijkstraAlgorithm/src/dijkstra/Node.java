package dijkstra;

import graph.Vertice;

public class Node 
{
	private Vertice vertice;
	private boolean visited = false;
	private int mark;
	
	
	public Node() 
	{
		super();
	}

	public Node(Vertice vertice, int mark) 
	{
		super();
		this.vertice = vertice;
		this.mark = mark;
	}

	protected Vertice getVertice()
	{
		return vertice;
	}

	protected void setVertice(Vertice vertice) 
	{
		this.vertice = vertice;
	}

	protected boolean isVisited() 
	{
		return visited;
	}

	protected void setVisited(boolean visited)
	{
		this.visited = visited;
	}

	protected int getMark()
	{
		return mark;
	}

	protected void setMark(int mark) 
	{
		this.mark = mark;
	}
	
	@Override
	public String toString()
	{
		return vertice.getName()+" "+getMark();
	}
}
