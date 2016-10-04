package graph.traversal.bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
public class BFS {

	static int[] visit;
	
	public static void bfs(ArrayList<Edge>[] edges, int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			node = queue.poll();
			visit[node] = 1;
			
			System.out.println(node);
			
			for(int i=0; i<edges[node].size(); i++) {
				int child = edges[node].get(i).v;
				if(visit[child] == 1) continue;
				
				queue.add(child);
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
		visit = new int[V+1];
		
		bfs(edges, 1);
	}
}
