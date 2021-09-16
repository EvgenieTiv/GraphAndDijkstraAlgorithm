package graph;

public class Edge 
{
	private int weight;
	
	private Vertice verticeA;
	private Vertice verticeB;

	
	public Edge(Vertice verticeA, Vertice verticeB, int weight) 
	{
		super();
		setWeight(weight);
		setVerticeA(verticeA);
		setVerticeB(verticeB);
	}

	public int getWeight() 
	{
		return weight;
	}
	
	protected void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	public Vertice getVerticeA() 
	{
		return verticeA;
	}
	
	protected void setVerticeA(Vertice verticeA) 
	{
		this.verticeA = verticeA;
	}
	
	public Vertice getVerticeB() 
	{
		return verticeB;
	}
	
	protected void setVerticeB(Vertice verticeB) 
	{
		this.verticeB = verticeB;
	}

	public Vertice getOtherVertice(Vertice vertice)
	{
		Vertice answer = null;
		
		if (getVerticeA().equals(vertice))
			answer = getVerticeB();
		else if  (getVerticeB().equals(vertice))
			answer = getVerticeA();
		
		return answer;
	}
	
	@Override
	public boolean equals(Object other)
	{
		boolean answer = false;
		
		try
		{
			Edge otherEdge = (Edge) other;
			
			Vertice otherVerticeA = otherEdge.getVerticeA();
			Vertice otherVerticeB = otherEdge.getVerticeB();
			
			if (((otherVerticeA.equals(getVerticeA())) && (otherVerticeB.equals(getVerticeB())))
				|| ((otherVerticeB.equals(getVerticeA())) && (otherVerticeA.equals(getVerticeB()))))
			{
				answer = true;
			}
		}
		
		catch(ClassCastException e){}
		
		finally
		{
			return answer;
		}
	}
	
}
