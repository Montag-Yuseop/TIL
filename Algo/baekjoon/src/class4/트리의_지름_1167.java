package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리의_지름_1167 {

    static class Node {
        int x, value;

        public Node(int x, int value) {
            this.x = x;
            this.value = value;
        }

    }

    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int target = 0;

    public static void main(String[] args) throws IOException {
        
        // 임의의 한 정점에서 가장 먼 정점을 찾고, 해당 정점에서부터 가장 먼 정점의 거리를 탐색하면
        // 해당 트리의 지름이 나오게 된다

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        for(int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }


        for(int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()) {
                int end = Integer.parseInt(st.nextToken());
                if(end == -1) continue;
                int val = Integer.parseInt(st.nextToken());
                adj.get(start).add(new Node(end, val));
            }
        }

        visited = new boolean[V+1];
        dfs(1, 0);

        visited = new boolean[V+1];
        dfs(target, 0);

        System.out.println(max);


    }

    static void dfs(int v, int sum) {
        visited[v] = true;

        if(sum > max) {
            max = sum;
            target = v;
        }

        for(Node cur : adj.get(v)) {
            int now = cur.x;
            int val = cur.value;
            if(!visited[now]) {
                dfs(now, sum+val);
            }
        }

    }
}
