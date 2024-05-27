package 문제복기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잡지_문제 {
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String magazine = br.readLine(); // 주어진 잡지의 글자
        String target = br.readLine(); // 만들고자 하는 타겟 글자

        for (int i = 0; i < target.length() - 1; i++) {
            cut(magazine, target, i); // 잡지 단어, 만들 단어, 자를 위치
        }
        if(ans == Integer.MAX_VALUE) System.out.println(1);
        else System.out.println(ans); // 정답 출력
    }

    // 분할 정복과도 유사한 듯
    static void cut(String magazine, String target, int idx) {
        // 앞과 뒤로 분할
        String start = target.substring(0, idx);
        String end = target.substring(idx, target.length());

        ans = Math.min(ans, find(magazine, start) + find(magazine, end));
    }

    static int find(String magazine, String target) {
        if(target.isEmpty()) return 0; // 종료 조건 생성
        // target 단어를 순회하면서 값 내기

        int tempCnt = 0;
        int start = 0;
        int end = 1;
        while(!target.isEmpty()) {
            // 끝까지 가버렸으면? 1번만 자르면 된다는 뜻!? 1 return
            if(end > target.length()) {
                tempCnt++;
                break;
            }

            String temp = target.substring(start, end); // 탐색할 단어

            // 만일 단어가 있다면?
            if(magazine.contains(temp)) {
                end++;
            } else {
                // 단어가 없다면?
                temp = target.substring(start, end - 1);
                target = target.replace(temp, "");
                tempCnt++;
                start = 0;
                end = 1;
            }


        }

        return tempCnt;
    }

}
