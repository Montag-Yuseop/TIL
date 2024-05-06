package 재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 하노이_탑_이동_순서_11729 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        sb.append((int) Math.pow(2, N) - 1).append("\n");

        hanoi(N, 1, 2, 3, sb, 0);

        System.out.println(sb);
    }

    static void hanoi(int N, int start, int mid, int end, StringBuilder sb, int depth) {
        System.out.println(depth + " = " + start + " " + mid +" " + end);
        if(N == 1) {
            sb.append(start +" " + end + " " + depth+"번째 꺼").append("\n");
            return;
        }

        hanoi(N-1, start, end, mid, sb, depth+1);
        sb.append(start +" " + end +" "+ depth+"번째꺼").append("\n");
        hanoi(N-1, mid, start, end, sb, depth+1);

    }
}
