package 문제복기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 키보드_문제 {

    static Set<String> set = new HashSet<>();
    static boolean[] visited;
    static int delay;

    static class Node{
        char c;
        int idx;

        public Node(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }

//        @Override
//        public int compareTo(Node n) {
//            if(this.idx <= n.idx) return -1;
//            return 1;
//        }

        @Override
        public String toString() {
            return this.c+"";
        }
    }

    public static void main(String[] args) throws IOException {

        // 입력 String "a-b-", t = 2(지연 시간)
        // 출력은 4
        // "", a, ab, b -> 정답인 경우

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        delay = Integer.parseInt(br.readLine());
        visited = new boolean[s.length()];

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.idx - o2.idx);

        for(int i = 0; i <= s.length(); i++) {
            perm(s, "",  i, 0, pq);
        }
        System.out.println(set);
        System.out.println(set.size());

    }

    static void perm(String full, String now, int end, int depth, PriorityQueue<Node> pq) {
        if(depth == end) {
            // now에 선택된 숫자를 문자로 지정해놨음
            int[] arr = new int[full.length()];
            for(char c : now.toCharArray()) {
                int n = c - '0';
                arr[n]++;
            }

            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == 0) {
                    pq.offer(new Node(full.charAt(i), i));
                } else {
                    pq.offer(new Node(full.charAt(i), i + delay + 1));
                }
            }

            StringBuilder temp = new StringBuilder();
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                if(cur.c == '-') {
                    if(!temp.isEmpty()) {
                        temp.deleteCharAt(temp.length() - 1);
                    }
                } else {
                    temp.append(cur.c);
                }
            }

            set.add(temp.toString());
            return;
        }

        // 문자열 중 딜레이 할 문자열을 선택하는 수가 필요함(1개 선택, 2개 선택...)
        for(int i = depth; i < full.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
//                pq.offer(new Node(full.charAt(i), i + delay));
                perm(full, now + i, end, depth + 1, pq);
                visited[i] = false;
            }
        }

    }

}
