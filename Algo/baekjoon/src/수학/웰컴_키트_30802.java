package 수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 웰컴_키트_30802 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[6];

    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < 6; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    int shirt = Integer.parseInt(st.nextToken());
    int pen = Integer.parseInt(st.nextToken());

    int sum = 0;

    for (int i : arr) {
      sum += (i / shirt);
      if (i % shirt != 0) sum++;
    }

    System.out.println(sum);
    System.out.println((N / pen) + " " + (N % pen));
  }
}
