import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String str = br.readLine();
            if(str.equals("0")){
                break;
            }
            String[] strs = str.split(" ");
            int k = Integer.parseInt(strs[0]);

            int[] arr = new int[k];
            for(int i=0;i<k;i++){
                arr[i] = Integer.parseInt(strs[i+1]);
            }
            Combination(arr,0,new int[6],0);
            sb.append("\n");
        }
        System.out.println(sb);

    }
    public static void Combination(int[] arr,int arrIndex , int [] select,int selectIndex){

        if(selectIndex==select.length){
//            System.out.println(Arrays.toString(select));
            for(int i=0;i<selectIndex;i++){
                sb.append(select[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=arrIndex;i<arr.length;i++){
            select[selectIndex] = arr[i];
            Combination(arr,i+1,select,selectIndex+1);
        }
    }

}