package graph.mst.kruskal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Edge implements Comparable<Edge> {
	int v1, v2;
	int weight;
	public Edge(int v1, int v2, int w){
		this.v1 = v1; this.v2 = v2; this.weight =w;
	}
	@Override
	public int compareTo(Edge arg0) {
		return this.weight - arg0.weight;
	}
}
public class Kruskal {

	public static int find(int[] parent, int v) {
		return v == parent[v] ? v : (parent[v] = find(parent, parent[v]));
	}
	public static void union(int[] parent, int v1, int v2) {
		int v1p = find(parent, v1);
		int v2p = find(parent, v2);
		parent[v1p] = parent[v2p];
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\kruskal\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);
		
		Edge[] edges = new Edge[E];
		for(int e=0; e < E; e++) {
			split = br.readLine().split(" ");
			int v1 = Integer.parseInt(split[0]);
			int v2 = Integer.parseInt(split[1]);
			int w = Integer.parseInt(split[2]);
			edges[e] = new Edge(v1,v2,w);
		}
		
		Arrays.sort(edges);
		
		int[] parent = new int[V+1];
		for(int i=1; i<V; i++) {
			parent[i] = i;
		}
		
		ArrayList<Edge> mst = new ArrayList<>();
		for(int i=0; i<E; i++) {
			Edge e = edges[i];
			if(find(parent, e.v1) != find(parent, e.v2)) {
				union(parent, e.v1, e.v2);
				mst.add(e);
			}
		}
		
		for(Edge e : mst) {
		System.out.println(e.v1 + "-> " + e.v2 + " w:" + e.weight );
		}
	}
}
