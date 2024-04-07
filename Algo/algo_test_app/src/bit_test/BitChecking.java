package bit_test;

public class BitChecking {


    public static void main(String[] args) {
        int x = 2; // 010
        int y = 4; // 100
        int z = 128; // 10000000


        System.out.println(x >> 1); // 나누기 2와 유사
        System.out.println(y >> 2); // 나누가 4와 유사
        System.out.println(z >>> 7);

    }
}
