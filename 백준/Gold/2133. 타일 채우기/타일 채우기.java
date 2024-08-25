import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 일단 N이 홀수인경우 0 밖에 안된다..


        int size = Integer.parseInt(br.readLine());

        if(size%2==1){
            System.out.println(0);
            return;
        }
        size /=2;

        int[] map = new int[size+1];
        map[0] = 0;
        map[1] = 3;
        for(int i=2;i<=size;i++){
            map[i] = map[i-1] * map[1]; // 뒤에다가 2칸 타일을 붙인경우
            for(int j=i-1;j>=2;j--){ // 뒤가 개똥블럭인경우, j는 개똥블럭의 싸이즈
                map[i] += map[i-j]*2;
            }
            map[i] +=2; // 이번경우의 개똥블럭
        }
        System.out.println(map[size]);
    }
}