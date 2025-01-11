import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        strs = br.readLine().split(" ");
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
        Arrays.sort(arr);
        getAnswer(arr,-1,new boolean[N],M,0);

        StringBuilder sb = new StringBuilder();

        for(String s : list) {
            sb.append(s+"\n");
        }
        System.out.println(sb);
    }

    static HashSet<String> hset = new HashSet<>();
    static ArrayList<String> list = new ArrayList<>();
    public static void getAnswer(int[] arr, int index,boolean[] visit, int max, int cnt){
        if(max==cnt){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i< visit.length;i++){
                if(visit[i]){
                    sb.append(arr[i]+" ");
                }
            }
            if(!hset.contains(sb.toString())){
                hset.add(sb.toString());
                list.add(sb.toString());
            }
            return;
        }

        for(int i=index+1;i<arr.length;i++){
            visit[i] = true;
            getAnswer(arr,i,visit,max,cnt+1);
            visit[i] = false;
        }
    }
}