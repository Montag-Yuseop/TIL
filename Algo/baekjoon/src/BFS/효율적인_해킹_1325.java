package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 효율적인_해킹_1325 {
    static int N, M;
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 노드 수
        M = Integer.parseInt(st.nextToken()); // 간선 수


        answer = new int[N+1];
        adj = new ArrayList[N+1];

        for(int i = 0; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
        }

        for(int i = 1; i < N+1; i++ ) {
            visited = new boolean[N+1];
//            bfs(i);
            dfs(i);
        }

        for(int i = 1; i < N+1; i++) {
            max = Math.max(max, answer[i]);
        }

        for(int i = 0; i < answer.length; i++) {
            if(max == answer[i]) {
                sb.append(i +" ");
            }
        }
        System.out.println(sb);

    }

    public static void dfs(int i) {

        for(int j : adj[i]) {
            if(!visited[j]) {
                answer[j]++;
                dfs(j);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int node : adj[cur]) {
                if(!visited[node]) {
                    visited[node] = true;
                    answer[node]++;
                    max = Math.max(max, answer[node]);
                    q.offer(node);
                }
            }
        }

    }
}
