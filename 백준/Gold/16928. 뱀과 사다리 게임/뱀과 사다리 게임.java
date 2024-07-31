import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        // 사다리의 수
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        //from -> to;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<N+M;i++){
            strs = br.readLine().split(" ");
            int from = Integer.parseInt(strs[0]);
            int to = Integer.parseInt(strs[1]);
            hashMap.put(from,to);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        boolean[] visits = new boolean[101];
        int answer = 0;
        w:while(true){
            answer ++;
            int size = queue.size();
            for(int i=0;i<size;i++){
                int position = queue.poll();
                if(visits[position]){
                    continue;
                }
                visits[position] = true;
                for(int j=1;j<=6;j++){
                    int next = position+j;
                    if(next==100){
                        break w;
                    }
                    if(hashMap.get(next)==null){
                        queue.add(next);
                    }else{
                        queue.add(hashMap.get(next));
                    }
                }

            }
        }
        System.out.println(answer);


    }


    public static int[][] copyArr(int[][] arr){
        int[][] newArr = new int[arr.length][arr[0].length];
        for(int i=0;i< arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
//        System.out.println();
    }
}