package 재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N과_M4_15652 {

    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        arr = new int[M];

        dfs( 1, 0, N, M);

        System.out.println(sb);
    }

    static void dfs(int num, int depth, int N, int M) {
        if(depth == M) {
            for(int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = num; i <= N; i++) {
            arr[depth] = i;
            dfs(i,depth+1, N, M);
        }

    }

}
