package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 뒤집기2_1439 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String s = br.readLine();
    int length = s.length();
    int[] arr = new int[2];

    boolean checkA = false;
    boolean checkB = false;

    for (int j = 0; j < length; j++) {
      int now = s.charAt(j) - '0';
      if (now == 0) checkA = true;
      else checkB = true;

      if (now != 0 && checkA) {
        checkA = false;
        arr[0]++;
      } else if (now != 1 && checkB) {
        checkB = false;
        arr[1]++;
      }

      if (j == length - 1 && checkA) arr[0]++;
      else if (j == length - 1 && checkB) arr[1]++;
    }

    System.out.println(Math.min(arr[0], arr[1]));
  }
}
