import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int testCase=Integer.parseInt(br.readLine());
        tc : for(int i=0;i<testCase;i++){
            String command = br.readLine();
            br.readLine();

            String tmp = br.readLine();
            tmp = tmp.substring(1,tmp.length()-1);
            StringBuilder sb = new StringBuilder(tmp);
            int cnt = 0;
            for(int j=0;j<command.length();j++){
                if(command.charAt(j)=='R'){
                    cnt++;
                }else{
                    if(sb.length()==0){
                        System.out.println("error");
                        continue tc;
                    }
                    int firstIndex = sb.indexOf(",");
                    int lastIndex = sb.lastIndexOf(",");
                    if(cnt%2==1){ // 리버스 상태
                        if(lastIndex==-1)
                            sb.delete(0,sb.length());
                        else
                            sb.delete(lastIndex,sb.length());
                    }else{
                        if(firstIndex==-1)
                            sb.delete(0,sb.length());
                        else
                            sb.delete(0,firstIndex+1);
                    }
                }
            }
            String[] split = sb.toString().split(",");
            StringBuilder tmsb = new StringBuilder("[");
            if(cnt%2==1){
                for(int j=split.length;j>0;j--){
                    tmsb.append(split[j-1]);
                    if(j!=1)
                        tmsb.append(",");
                }
                tmsb.append("]");
            }else{
                for(int j=0;j<split.length;j++){
                    tmsb.append(split[j]);
                    if(j!= split.length-1){
                        tmsb.append(",");
                    }
                }
                tmsb.append("]");
            }
            System.out.println(tmsb);
        }

    }
}

