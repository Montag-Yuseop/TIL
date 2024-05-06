package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 열_개씩_끊어_출력하기_11721 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        int N = s.length() / 10;

        for(int i = 0; i < N; i++ ) {
            sb.append(s, 0, 10);
            sb.append('\n');
            s = s.substring(10);
        }
        sb.append(s, 0, s.length());
        System.out.println(sb);
    }

}
