import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int tc =1 ; tc<=testCase;tc++){
            int commandSize = Integer.parseInt(br.readLine());
            Magnet[] magnets = new Magnet[4];

            for(int i=0;i<4;i++){
                magnets[i] = new Magnet();
                String[] strs = br.readLine().split(" ");
                for(int j=0;j<8;j++){
                    magnets[i].plain[j] = Integer.parseInt(strs[j]);
                }
            }

            for(int i=0;i<commandSize;i++){
                String[] strs = br.readLine().split(" ");
                int index = Integer.parseInt(strs[0])-1;
                int direction = Integer.parseInt(strs[1]);
                rotate(index,magnets,direction,new boolean[4]);
            }
            int answer = 0 ;
            for(int i=0;i<4;i++){
                if(magnets[i].plain[0]==1){
                    answer+=(magnets[i].plain[0]<<i);
                }
            }
            sb.append("#"+tc+" "+answer+"\n");
        }
        System.out.println(sb);
    }

    public static void rotate(int index, Magnet[] magnets, int direction,boolean[] visits){
        if(visits[index]){
            return;
        }
        visits[index] = true;
        
        if(index+1<4
                &&!visits[index+1]
                &&magnets[index+1].plain[6] != magnets[index].plain[2]){
            rotate(index+1,magnets,-direction,visits);
        }
        if(index-1>=0
                &&!visits[index-1]
                &&magnets[index-1].plain[2] != magnets[index].plain[6]) {
            rotate(index-1,magnets,-direction,visits);
        }

        if(direction == 1){ // 시계방향
            magnets[index].shiftRight();
        }else if(direction==-1){
            magnets[index].shiftLeft();
        }


    }

    public static class Magnet {
        int[] plain = new int[8];

        public void shiftLeft(){
            int temp = plain[0];
            for(int i=0;i<7;i++){
                plain[i] = plain[i+1];
            }
            plain[7] = temp;
        }
        public void shiftRight(){
            int temp = plain[7];
            for(int i=7;i>0;i--){
                plain[i] = plain[i-1];
            }
            plain[0] = temp;
        }
    }
}