import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String T = br.readLine();
        String P = br.readLine();

        int tLength = T.length();
        int pLength = P.length();

        int[] pi = new int[T.length()];

        if(pLength>tLength){
            System.out.println(0);
            return;
        }
        int i = 1; // 여태까지 확인한 문자열 이후 인덱스
        int j = 0; // 패턴의 인덱스(일치하는 개수)

        while(i<pLength){
            if(P.charAt(i)==P.charAt(j)){
                j++;
                pi[i] = j;
                i++;
            }else{
                if(j==0){
                    pi[i] = 0;
                    i++;
                }else{
                    j = pi[j-1];
                }
            }
        }

        i =0;
        j =0;
        ArrayList<Integer> answer = new ArrayList<>();
        while(i<tLength){
            if(T.charAt(i)==P.charAt(j)){
                i++;
                j++;
                if(j==pLength){
                    answer.add(i-j);
                    j = pi[j-1];
                }
            }else{
                if( j == 0){
                    i++;
                }else{
                    j = pi[j-1];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for(int num : answer){
            sb.append(num+1).append(" ");
        }

        System.out.println(sb);
    }
}