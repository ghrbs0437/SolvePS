import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[][] masks =
            {
                    {1,0,0,1,0,0,1,0,0},
                    {0,1,0,0,1,0,0,1,0},
                    {0,0,1,0,0,1,0,0,1},

                    {1,1,1,0,0,0,0,0,0},
                    {0,0,0,1,1,1,0,0,0},
                    {0,0,0,0,0,0,1,1,1},

                    {1,0,0,0,1,0,0,0,1},
                    {0,0,1,0,1,0,1,0,0},
            };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        tc: for(int tc=0;tc<testCase;tc++){
            StringBuilder map = new StringBuilder();
            for(int i=0;i<3;i++) {
                String[] strs = br.readLine().split(" ");
                for (int j = 0; j < strs.length; j++) {
                    if (strs[j].equals("H")) {
                        map.append(0);
                    } else {
                        map.append(1);
                    }
                }
            }
            String str = map.toString();

            HashSet<String> hset = new HashSet<>();
            Queue<String> queue = new LinkedList<>();

            hset.add(str);
            queue.add(str);

            int cnt = 0;
            while(true){
                if(queue.isEmpty()){
                    sb.append("-1\n");
                    continue tc;
                }
                int size = queue.size();
                for(int i=0;i<size;i++){

                    str = queue.poll();

                    if(str.toString().equals("111111111")
                            || str.toString().equals("000000000")){
                        sb.append(cnt+"\n");
                        continue tc;
                    }

                    for(int[] mask : masks){
                        StringBuilder tmp = new StringBuilder(str);
                        for(int j=0;j<9;j++){
                            if(mask[j] == 1){
                                if(tmp.charAt(j)=='0'){
                                    tmp.setCharAt(j,'1');
                                }else{
                                    tmp.setCharAt(j,'0');
                                }
                            }
                        }
                        if(hset.contains(tmp.toString())){
                        }else{
                            queue.add(tmp.toString());
                            hset.add(tmp.toString());
                        }
                    }
                }
                cnt++;
            }
        }

        System.out.println(sb);





    }

}