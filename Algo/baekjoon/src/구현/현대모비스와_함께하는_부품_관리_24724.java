package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 현대모비스와_함께하는_부품_관리_24724 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer sb = new StringBuffer();

    int T = Integer.parseInt(br.readLine()); // 부품 관리 횟수

    for (int tc = 1; tc <= T; tc++) {
      sb.append("Material Management ")
          .append(tc)
          .append("\n")
          .append("Classification ---- End!")
          .append("\n");
    }
    System.out.println(sb);
  }
}
