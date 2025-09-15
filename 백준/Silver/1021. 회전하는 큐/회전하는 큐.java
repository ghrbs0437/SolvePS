import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");

        int[] map = new int[N];
        for (int i = 1; i <= N; i++) {
            map[i - 1] = i;
        }

        int[] arr = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }

        int index = 0;
        int answer = 0;
        while (true) {
//            System.out.println(Arrays.toString(map));
            if(index >= M){
                break;
            }
            if(arr[index] == map[0]){
                int[] next = new int[map.length-1];
                for (int j = 1; j < map.length; j++) {
                    next[j-1] = map[j];
                }
                map = next;
                index ++;
                continue;
            }

//            boolean flag = false;
//            for(int i=0;i<M;i++){
//                if(arr[i] == map[0]){
//                    flag = true;
//                }
//            }
//            if(!flag){
//                int[] next = new int[map.length-1];
//                for (int j = 1; j < map.length; j++) {
//                    next[j-1] = map[j];
//                }
//                map = next;
//                continue;
//            }

            for(int i=0;i<N;i++){
                if(arr[index] == map[i]){
                    int[] next = new int[map.length];
                    if( 2 * i >= map.length){ // 중간 넘었으면
                        for(int j=0;j<map.length-1;j++){
                            next[j+1] = map[j];
                        }
                        next[0] = map[map.length-1];
                        map = next;
                    }else{
                        for(int j=1;j<map.length;j++){
                            next[j-1] = map[j];
                        }
                        next[map.length-1] = map[0];
                        map = next;
                    }
                    break;
                }
            }
            answer++;
        }
        System.out.println(answer);


    }
}
