import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        HashMap<Integer,String> hmap1 = new HashMap<>();
        HashMap<String,Integer> hmap2 = new HashMap<>();
        for(int i=1;i<=N;i++){
            String str = br.readLine();
            hmap1.put(i,str);
            hmap2.put(str,i);
        }
        for(int i=0;i<M;i++){
            String str = br.readLine();
            if(str.charAt(0) >='0' && str.charAt(0)<='9'){
                sb.append(hmap1.get(Integer.parseInt(str)));
            }else{
                sb.append(hmap2.get(str));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
