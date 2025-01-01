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

        int pack = Integer.MAX_VALUE;
        int piece = Integer.MAX_VALUE;
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            pack = Math.min(pack, Integer.parseInt(strs[0]));
            piece = Math.min(piece, Integer.parseInt(strs[1]));
        }

        int packageSize = N/6;
        int pieceSize = N%6;

        int answer = 0;
        if(pack > piece*6){
            answer += piece * packageSize*6;
        }else{
            answer += pack * packageSize;
        }

        if(pack >piece *pieceSize){
            answer += piece* pieceSize;
        }else {
            answer += pack;
        }


        System.out.println(answer);

    }
}