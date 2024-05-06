package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 그대로_출력하기2_11719 {

    public static void main(String[] args) throws IOException {

        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while((str = br.readLine()) != null) {
            sb.append(str);
            sb.append("\n");
//            System.out.println(str);
        }

        System.out.println(sb);
    }
}
