import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashSet<Integer> all = new HashSet<>();
        StringBuilder sb= new StringBuilder();
        for(int i=1;i<=20;i++){
            all.add(i);
        }

        HashSet<Integer> hset = new HashSet<>();
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");

            String command = strs[0];
            int num = -1;
            if(command.equals("all")){

            }else if(command.equals("empty")){

            }else{
                num = Integer.parseInt(strs[1]);
            }


            switch(command){
                case "add":
                    hset.add(num);
                    break;
                case "check":
                    if(hset.contains(num)){
                        sb.append(1).append("\n");
                    }else{
                        sb.append(0).append("\n");
                    }
                    break;
                case "remove":
                    hset.remove(num);
                    break;
                case "toggle":
                    if(hset.contains(num)){
                        hset.remove(num);
                    }else{
                        hset.add(num);
                    }
                    break;
                case "all":
                    hset = new HashSet<>(all);
                    break;
                case "empty":
                    hset = new HashSet<>();
                    break;
            }
        }
        System.out.println(sb);

    }
}