import java.util.*;

public class Main {
    
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<T;i++){
            String s = sc.nextLine();
            int cnt = 0;
            for(int j=0;j<s.length();j++){
                if(s.charAt(j)=='('){
                    cnt++;
                }else{
                    cnt--;
                }
                if(cnt<0){
                    break;
                }
            }
            if(cnt!=0){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }

        }

    }


}