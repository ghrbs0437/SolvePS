import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        int K = Integer.parseInt(strs[2]);
        // 심판의 수는 최대 50

        strs = br.readLine().split(" ");
        int[] arr = new int[K];

        for(int i=0;i<K;i++){
            int num = Integer.parseInt(strs[i]);
            arr[i] = num;
        }

        int min = 0;
        int max = N;


        ArrayList<String> al = new ArrayList<>();
        while(true){
            int remain = M-1;
            int middle = (min + max) / 2;
            if(min > max){
                break;
            }
//            System.out.println(middle);
            StringBuilder sb = new StringBuilder();
            sb.append(1);
            int current = 0;
            while(true){
                boolean flag = true;
                for(int i = current+1; i<K; i++){

                    if(remain>0 && arr[i] - arr[current] >= middle){
                        remain--;
                        current = i;
                        flag = false;
                        sb.append(1);
                        break;
                    }else{
                        sb.append(0);
                    }
                }
                if(flag){
                    // 한번도 안되는경우..
                    break;
                }
            }
//            System.out.println(sb);

            if(remain<=0){ // 정답이 가능한 녀석
                min = middle+1;
                al.add(sb.toString());
            }else{
                max = middle-1;
            }
        }
//        System.out.println(al);
        System.out.println(al.get(al.size()-1));

    }
}
