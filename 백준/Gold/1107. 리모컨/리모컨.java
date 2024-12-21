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

        String target = br.readLine();
        int cnt = Integer.parseInt(br.readLine());

        boolean[] button = new boolean[10];
        Arrays.fill(button, true);

        if(cnt!=0){
            String[] strs = br.readLine().split(" ");
            for(int i=0;i<strs.length;i++){
                button[Integer.parseInt(strs[i])] = false;
            }
        }

        int current = 100;

        getPressCnt(target,button,new int[target.length()+1],0);


        ANSWER = Math.min(ANSWER ,Math.abs(Integer.parseInt(target) - current));
        System.out.println(ANSWER);
//        System.out.println(TEMP);
    }

    static int ANSWER = Integer.MAX_VALUE;
    static int TEMP = Integer.MAX_VALUE;
    public static void getPressCnt(String target, boolean[] button, int[] arr, int depth){
        if(depth >= target.length()-1){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<depth;i++){
                sb.append(arr[i]);
            }
            if(sb.length()==0){

            }else{

                int made = Integer.parseInt(sb.toString());
                int t = Integer.parseInt(target);

                int cal = Math.abs(made - t);
                int pressCnt = cal + sb.length();
                if(sb.charAt(0)==0){
                    pressCnt--;
                }
                if(pressCnt < ANSWER){
                    TEMP = made;
                    ANSWER = Math.min(pressCnt,ANSWER);
                }
            }
        }

        if(depth == target.length()+1){
            return;
        }

        for(int i=0;i< button.length;i++){
            if(button[i]){
                arr[depth] = i;
                getPressCnt(target, button, arr, depth+1);
            }
        }
    }
}