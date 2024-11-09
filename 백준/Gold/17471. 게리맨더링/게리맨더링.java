import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        String[] strs = br.readLine().split(" ");

        int[] population = new int[N];
        for(int i=0;i<N;i++){
            population[i] = Integer.parseInt(strs[i]);
        }

        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=1;j< strs.length;j++){
                map[i][Integer.parseInt(strs[j])-1] = true;
            }
        }

        boolean[] select = new boolean[N];

        // 조합을 구하고
        // 조합에서 가능한지 체크해서 answer update
        getAns(select,population,0,0,map);
        if(ANSWER == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ANSWER);
        }
    }

    public static void getAns(boolean[] select,int[] population, int index,int tCnt,boolean[][] map){

        if(index == select.length){

            if(tCnt == index || tCnt==0){
                return;
            }
            //정답확인
            if(checkPos(select,map)){
                int red = 0;
                int blue = 0;
                for(int i=0;i< select.length;i++){
                    if(select[i]){
                        red+=population[i];
                    }else{
                        blue+=population[i];
                    }
                }
                ANSWER = Math.min(Math.abs(red-blue),ANSWER);
            }
            return;
            //여기서체크.
        }
        select[index] = true;
        getAns(select,population,index+1, tCnt+1,map);
        select[index] = false;
        getAns(select,population,index+1, tCnt,map);
    }

    public static boolean checkPos(boolean[] select, boolean[][] map){
        int redIndex = -1;
        int blueIndex = -1;
        int redCnt = 0;
        int blueCnt = 0;
        for(int i=0;i< select.length;i++){
            if(select[i]){
                redIndex = i;
                redCnt++;
            }else{
                blueIndex = i;
                blueCnt++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(redIndex);
        boolean[] visits = new boolean[select.length];
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                Integer next = q.poll();
                if(visits[next]){
                    continue;
                }
                visits[next] = true;
                redCnt--;
                for(int j=0;j<select.length;j++){
                    if(map[next][j]){
                        if(select[j]){ // red
                            q.add(j);
                        }
                    }
                }
            }
        }
        if(redCnt!=0){
            return false;
        }


        q.add(blueIndex);
        visits = new boolean[select.length];
        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0;i<size;i++){
                Integer next = q.poll();
                if(visits[next]){
                    continue;
                }
                visits[next] = true;
                blueCnt--;
                for(int j=0;j<select.length;j++){
                    if(map[next][j]){
                        if(!select[j]){ // red
                            q.add(j);
                        }
                    }
                }
            }
        }
        if(blueCnt!=0){
            return false;
        }
        return true;
    }

    public static int ANSWER = Integer.MAX_VALUE;
}