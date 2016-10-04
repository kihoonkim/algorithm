package graph.shortestPath.bellman_ford;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Single source. 음수 가중치 가능
// i -> j 에 간선 k를 추가해가며 최단 거리를 계산.
// O (EV)
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
public class BellmanFord {

	public static final int INF = 210000000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\graph\\shortestPath\\bellman_ford\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);
		
		ArrayList<Edge>[] edges = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			edges[i] = new ArrayList<Edge>();
		}
		
		for(int i=0; i < E; i++) {
			split = br.readLine().split(" ");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			int weight = Integer.parseInt(split[2]);
			edges[start].add(new Edge(end, weight));
		}

		int[] D = new int[V+1];
		for(int i=1; i<=V; i++) {
			D[i] = INF;
		}
		D[1] = 0;
		boolean update = true;
		for(int cnt=0; cnt<=V; cnt++) {	// N-1번 반복. N번쩨도 변경된다면 음수 사이클 존재
			update = false;
			for(int i=1; i<=V; i++) {
				for(int j=0; j<edges[i].size(); j++) {
					Edge edge = edges[i].get(j);
					if(D[edge.v] > D[i] + edge.weight) {
						D[edge.v] = D[i] + edge.weight;
						update = true;
					}
				}
			}
			if(cnt==V && update==true) {
				System.out.println("음수 사이클 존재");
			}
		}
		System.out.println("1 -> " + V + " : " + D[V]);
	}
}
