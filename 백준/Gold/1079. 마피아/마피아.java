import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        int[] guiltyPoints = new int[N];
        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){
            guiltyPoints[i] = Integer.parseInt(strs[i]);
        }

        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int me = Integer.parseInt(br.readLine());

        // 참가자가 홀수명이면 낮
        // 참가자가 짝수명이면 밤.
        // 이 게임을 가능한 오래동안 하고싶어.
        // 참가자 i가 죽었으면, 다른 참가자 j의 유죄지수는 [i][j] 만큼 변한다..

        solve(me,map,guiltyPoints,0,N,new boolean[N]);
        System.out.println(answer);

    }

    public static void solve(int me, int[][] map, int[] guiltyPoint, int depth, int remain, boolean[] dead){

        if(remain % 2 == 0){ // 짝수일때.. 밤
            for(int i = 0;i<map.length;i++){
                if(i == me){
                    continue;
                }
                if(dead[i]){
                    continue;
                }

                dead[i] = true;
                for(int j=0;j<map.length;j++){
                    guiltyPoint[j] += map[i][j];
                }

                solve(me,map,guiltyPoint,depth+1,remain-1, dead);
                for(int j=0;j<map.length;j++){
                    guiltyPoint[j] -= map[i][j];
                }
                dead[i] = false;
            }
        }else{ // 홀수..
            answer = Math.max(answer, depth);
            int maxIndex = 0;
            int max = -Integer.MAX_VALUE;
            for(int i=0;i<map.length;i++){
                if(!dead[i]){
                    if(max < guiltyPoint[i]){
                        max = guiltyPoint[i];
                        maxIndex = i;
                    }else if(max == guiltyPoint[i]){
                        maxIndex = Math.min(maxIndex,i);
                    }
                }
            }
            if(maxIndex == me){
                return;
            }else{
                dead[maxIndex] = true;
                solve(me,map,guiltyPoint,depth,remain-1,dead);
                dead[maxIndex] = false;
            }
        }
    }
}