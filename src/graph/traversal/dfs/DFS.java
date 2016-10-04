package graph.traversal.dfs;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Edge implements Comparable<Edge> {
	int v;
	int weight;
	public Edge(int v, int w){
		this.v = v; this.weight =w;
	}
	@Override
	public int compareTo(Edge arg0) {
		return this.weight - arg0.weight;
	}
}
public class DFS {

	static int[] visit;
	
	public static void dfs(ArrayList<Edge>[] edges, int node) {
		visit[node] = 1;
		System.out.println(node);
		
		for(int i=0; i<edges[node].size(); i++) {
			int child = edges[node].get(i).v;
			if(visit[child] == 1) continue;
			
			dfs(edges, child);
		}
	}

	static Stack<Integer> stack = new Stack<>();
	public static void nonRecursiveDFS(ArrayList<Edge>[] edges) {

		while(!stack.isEmpty()) {
			int node = stack.pop();
			System.out.println(node);
			
			for(int i=0; i<edges[node].size(); i++) {
				int child = edges[node].get(i).v;
				if(visit[child] == 1) continue;
				stack.add(child);
				visit[child] = 1;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\graph\\lca\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = br.readLine().split(" ");
		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);
		
		ArrayList<Edge>[] edges = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			edges[i] = new ArrayList<Edge>();
		}
		
		for(int e=0; e < E; e++) {
			split = br.readLine().split(" ");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			int weight = Integer.parseInt(split[2]);
			edges[start].add(new Edge(end,weight));
		}

		System.out.println("---recursive dfs---");
		visit = new int[V+1];
		dfs(edges, 1);
		
		System.out.println("---nonRrecursive dfs---");
		visit = new int[V+1];
		stack.add(1);
		visit[1] = 1;
		nonRecursiveDFS(edges);
	}
}
