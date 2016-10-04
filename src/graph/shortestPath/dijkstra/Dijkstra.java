package graph.shortestPath.dijkstra;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Single source, 음수 가중치 불가.
// 방문 가능 한 곳에서 가장 작은 가중치를 가진 간선을 방문해 가면서 최단거리 계산 
// O(V^2)
// Heap사용 : O(ElogE)
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
public class Dijkstra {

	public static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\graph\\shortestPath\\dijkstra\\input.txt"));
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
		
		int[] D = new int[V+1];
		int[] visit = new int[V+1];
		for(int i=1; i<=V; i++) {
			D[i] = INF;	//oo
		}
		D[1] = 0;
		
		ArrayList<Integer> path = new ArrayList<>();
		Queue<Integer> pq = new LinkedList<>();
		pq.add(1);
		while(!pq.isEmpty()) {
			int vi = pq.poll();	// mininum weight edge
			visit[vi] = 1;
			
			for(int i=0; i<edges[vi].size(); i++) {
				Edge edge = edges[vi].get(i);
				if(D[edge.v] > D[vi] + edge.weight) {
					D[edge.v] = D[vi] + edge.weight;
					
					int r = path.indexOf(edge.v);
					if(r >= 0) path.remove(r);
					else path.add(edge.v);
				}
				
				if(visit[edge.v] == 0){
					pq.add(edge.v);
				}
 			}
		}
		
		System.out.print(1 + " - ");
		for (Integer p : path) {
			System.out.print(p + " - ");
		}
		System.out.print(5);
		System.out.println();
		System.out.println("1 -> " + V + " : " + D[V]);
	}
}
