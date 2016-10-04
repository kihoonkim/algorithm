package graph.lca;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// O(logN) 으로 LCA 찾기
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
public class LCA {

	static final int NODE_MAX_CNT = 100001;	// < 2^17
	static final int NLEN = 17;
	static int[] depth = new int[NODE_MAX_CNT] ;
	static int[][] parents = new int[NODE_MAX_CNT][NLEN+1];
	static int[] visit = new int[NODE_MAX_CNT];
	
	public static void dfs(ArrayList<Edge>[] edges, int node) {
		visit[node] = 1;
		
		for(int i=0; i<edges[node].size(); i++) {
			int child = edges[node].get(i).v;
			if(visit[child] == 1) continue;
			
			depth[child] = depth[node] +1;
			parents[child][0] = node;	// 2^0
			for(int j=1; j<=NLEN; j++) {
				parents[child][j] = parents[parents[child][j-1]][j-1];
			}
			
			dfs(edges, child);
		}
	}
	
	public static int lca(int v1, int v2) {
		if(depth[v1] < depth[v2]) {
			int t = v1; v1 = v2; v2 = t;
		}
		for(int i=NLEN; i>=0; i--) {
			if((1 << i ) <= depth[v1] - depth[v2]) {
				v1 = parents[v1][i];
			}
		}
		
		if(v1 == v2) return v1;
		
		for(int i=NLEN; i>= 0; i--) {
			if(parents[v1][i] != parents[v2][i]) {
				v1 = parents[v1][i];
				v2 = parents[v2][i];
			}
		}
		return parents[v1][0];
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
		
		dfs(edges, 1);
		
		System.out.println(lca(2, 5));
	}
}
