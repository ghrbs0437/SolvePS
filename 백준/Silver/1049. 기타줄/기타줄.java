import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]); // 필요한 줄의 개수
        int M = Integer.parseInt(strs[1]); // 브랜드 수

        int[] packages = new int[M];
        int[] pieces = new int[M];
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            packages[i] = Integer.parseInt(strs[0]);
            pieces[i] = Integer.parseInt(strs[1]);
        }

        Arrays.sort(packages);
        Arrays.sort(pieces);

        int packageSize = N/6;
        int pieceSize = N%6;

        int answer = 0;
        if(packages[0] > pieces[0]*6){
            answer += pieces[0] * packageSize*6;
        }else{
            answer += packages[0] * packageSize;
        }

        if(packages[0] > pieces[0]*pieceSize){
            answer += pieces[0] * pieceSize;
        }else {
            answer += packages[0];
        }


        System.out.println(answer);

    }
}