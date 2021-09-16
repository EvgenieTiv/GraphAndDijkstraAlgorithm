package dijkstra;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertice;

public class Dijkstra
{
	private static Dijkstra instance;
	
	private List<Node> nodes = new ArrayList<Node>();
	private int[][] weights;	
	
	private static List<Node> path = new ArrayList<Node>();
	
	private static final int INFINITE_MARK = (int) Math.pow(10, 12);
	
	private static boolean pathFound = false;
	
	private Dijkstra() 
	{		
		super();
		
		setNodes();
		setWeights();
	}
	
	public static Dijkstra getInstance()
	{
		if (instance == null)
			instance = new Dijkstra();
		
		return instance;
	}
	
	public void runDijkstra()
	{
		processAllNodes();
		calculateShortestPath();

		displayShortestPath();
	}
	
	private Node findCorrectNodeBackwards(Node currentNode)
	{
		Node answer = null;
		
		int row = getNodeSerial(currentNode);

		for (int i=0; i<getWeights()[row].length; i++)
		{
			int column = i;
			int weight = getWeight(row, column);

			Node other = getNodes().get(i);
			
			int markSubtractWeight = currentNode.getMark() - weight;
			int markOther = other.getMark();
			
			if ((weight != 0)&&(markSubtractWeight == markOther))
					answer = other;			
		}
		
		return answer;
	}
	
	
	public void calculateShortestPath()
	{
		Vertice end = Graph.getInstance().getEnd();
		Node currentNode = verticeToNode(end);
		
		for(Node node: getNodes())
			node.setVisited(false);

		while(!isPathFound())
		{		
			if (currentNode!=null)
			{

				addToPath(currentNode);
				currentNode.setVisited(true);	
				currentNode = findCorrectNodeBackwards(currentNode);
			}
			
			else
			{
				setPathFound(true);
				break;
			}
		}
		
	}
	
	public String shortestPath()
	{
		String answer = "";
		
		for (int i=getPath().size()-1; i>=0; i--)
		{
			answer += getPath().get(i).getVertice().getName();
			
			if (i>0)
				answer += " -> ";
		}

		return answer;
	}
	
	public void displayShortestPath()
	{
		System.out.println(shortestPath());
	}

	public void processAllNodes()
	{
		 Node currentNode = getNodeWithMinMark();
		 Node previousNode = null;
		 
		 while (!isAllNodesProcessed())
		 {
			 processCurrentNode(currentNode);
		 	 currentNode.setVisited(true);	
		 	 previousNode = currentNode;
			 currentNode = getNodeWithMinMark(currentNode);		 			 
		 }
	}
	
	private boolean isAllNodesProcessed()
	{
		boolean answer = true;
		
		for (Node node: getNodes())
			if (!node.isVisited())
				answer = false;
			
		return answer;	
	}
	
	private void processCurrentNode(Node sourceNode)
	{
		int row = getNodeSerial(sourceNode);
		
		for(Edge edge : sourceNode.getVertice().getEdges())
		{
			Node otherNode = verticeToNode(edge.getOtherVertice(sourceNode.getVertice()));
			int column = getNodeSerial(otherNode);			
			
			int weight = sourceNode.getMark() + edge.getWeight();
			
			if (weight < otherNode.getMark())	
			{					
				otherNode.setMark(weight);
			}
		}
	}
	
	protected List<Node> getNodes() 
	{
		return nodes;
	}
	
	protected void setNodes()
	{
		for(Vertice vertice: Graph.getInstance().getVertices())
		{
			if (!vertice.equals(Graph.getInstance().getStart()))			
				nodes.add(new Node(vertice, INFINITE_MARK));
			else
				nodes.add(new Node(vertice, 0));
		}
	}
	
	protected int[][] getWeights() 
	{
		return weights;
	}
	
	public void printWeights()
	{
		for (int i=0; i<getWeights().length; i++)
		{
			for (int j=0; j<getWeights()[i].length; j++)
			{
				System.out.print(getWeight(i,j)+" ");
			}
			
			System.out.println("");
		}
	}
	
	protected void setWeights() 
	{
		int verticesCount = Graph.getInstance().getVertices().size();
		weights = new int[verticesCount][verticesCount];
			
		for (int i=0; i<verticesCount; i++)
			for (int j=0; j<verticesCount; j++)
			{
				if (i!=j)		
				{
					Vertice verticeA = getNodes().get(i).getVertice();
					Vertice verticeB = getNodes().get(j).getVertice();
					
					try
					{
						Edge edge = verticeA.getEdgeWithAnotherVertice(verticeB);
						setWeight(i, j, edge.getWeight());
					}
					
					catch (NullPointerException e) {}
				}
			}
	}

	private Edge getEdge(Node nodeA, Node nodeB)
	{
		Edge answer =null;
		
		for(Edge edge : nodeA.getVertice().getEdges())
			if (edge.getOtherVertice(nodeA.getVertice()).equals(nodeB.getVertice()))
			{
				answer = edge;
			}
			
		return answer;
	}
	
	private Node getNodeWithMinMark(Node sourceNode)
	{
		Node answer = sourceNode;
		
		int min = INFINITE_MARK;

		List<Edge> edges = sourceNode.getVertice().getEdges();
		
		for (Edge edge: edges)
		{
			if (edge.getWeight() < min)
			{				
				Vertice other = edge.getOtherVertice(sourceNode.getVertice());				
			
				if (!verticeToNode(other).isVisited())
				{
					min = edge.getWeight();
					answer =  verticeToNode(other);
				}
			}
		}
		
		if (!answer.getVertice().equals(sourceNode.getVertice()))		
			return answer;
		
		else
			return verticeToNode(Graph.getInstance().getStart());
	}
	
	private int getNodeSerial(Node node)
	{
		return getNodes().indexOf(node);
		
	}
	
	private Node verticeToNode(Vertice vertice)
	{
		Node answer = null;
		
		for(Node node : getNodes())
		{
			if (node.getVertice().equals(vertice))
				answer = node;
		}
		
		return answer;
	}
	
	private Node getNodeWithMinMark()
	{
		Node answer = getNodes().get(0);
		
		int min = INFINITE_MARK;
		
		for(Node node: getNodes())
			if (node.getMark()<min)
			{
				answer = node;
				min = node.getMark();
			}
		
		return answer;
	}
	
	public void printNodes()
	{
		for (Node node: getNodes())
			System.out.println(node.toString());
	}
	
	protected int getWeight(int row, int column)
	{
		return weights[row][column];
	}	
	
	protected void setWeight(int row, int column, int weight)
	{
		weights[row][column] = weight;
	}
	
	private void addToPath(Node node)
	{
		path.add(node);
	}

	protected static List<Node> getPath() 
	{
		return path;
	}

	protected static void setPath(List<Node> path)
	{
		Dijkstra.path = path;
	}

	protected static boolean isPathFound() 
	{
		return pathFound;
	}

	protected static void setPathFound(boolean pathFound) 
	{
		Dijkstra.pathFound = pathFound;
	}
	
	
}
