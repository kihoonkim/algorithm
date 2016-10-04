package graph.shortestPath.ployd_warshal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// multi sorce. 음수 가중치 가능
// i -> j 사이에 정점 k를 추가하면서 최단거리를 구함
// O(V^3)
public class FloydWarshal {

	public static final int INF = 210000000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\graph\\shortestPath\\ployd_warshal\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);
		
		int[][] D = new int[V+1][V+1];
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				D[i][j] = INF;
			}
		}
		for(int e=0; e < E; e++) {
			split = br.readLine().split(" ");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			int weight = Integer.parseInt(split[2]);
			D[start][end] = weight;
		}

		for(int k=1; k<=V; k++) {
			for(int i=1; i<=V; i++) {
				for(int j=1; j<=V; j++) {
					if(D[i][j] > D[i][k] + D[k][j]) {
						D[i][j] = D[i][k] + D[k][j];
					}
				}
			}
		}
		System.out.println("1 -> " + V + " : " + D[1][V]);
	}
}
