import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());


        int[] arr1 = new int[N];
        int[] arr2 = new int[N];

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            int 신맛 = Integer.parseInt(strs[0]);
            int 쓴맛 = Integer.parseInt(strs[1]);
            arr1[i] = 신맛;
            arr2[i] = 쓴맛;
        }
        // 신맛은 곱, 쓴맛은 합

        getAns(arr1,arr2,0,new boolean[N],N);
        System.out.println(ANSWER);

    }

    static int ANSWER = Integer.MAX_VALUE;

    public static void getAns(int[] arr1, int[] arr2, int index, boolean[] visit, int max){
        if(index == max){
            int 신맛 = 1;
            int 쓴맛 = 0;
            boolean flag = true;
            for(int i=0;i<max;i++){
                if(visit[i]){
                    신맛*=arr1[i];
                    쓴맛+=arr2[i];
                    flag = false;
                }
            }
            if(flag){
                return;
            }
            ANSWER = Math.min(ANSWER, Math.abs(신맛-쓴맛));
            return;
        }
        visit[index] = true;
        getAns(arr1,arr2,index+1,visit,max);
        visit[index] = false;
        getAns(arr1,arr2,index+1,visit,max);
    }
}