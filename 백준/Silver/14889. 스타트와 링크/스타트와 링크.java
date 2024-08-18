import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 사람이 최대 20명.
        // 20C10.. 180번..?
        //
        // 선택하고 안하고.. 선택하는 사람이 10명이 될 때 까지.. 계싼
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(strs[j]);
            }
        }

        getAns(0,0,arr,0,new boolean[N]);

        System.out.println(answer);
        // 0번을 선택하고 9명 선택 그룹
        // 0번을 선택하지 않고 10명 선택하는 그룹
    }

    public static void getAns(int selectCnt,int passCnt,int[][] map,int index,boolean[] selected){
        if(index==map.length){
            return;
        }

        if(selectCnt==map.length/2){
            int[] teamA = new int[selected.length/2];
            int[] teamB = new int[selected.length/2];
            int teamAIdx = 0;
            int teamBIdx = 0;
            for(int i=0;i<map.length;i++){
                if(selected[i]){
                    teamA[teamAIdx] = i;
                    teamAIdx++;
                }else{
                    teamB[teamBIdx] = i;
                    teamBIdx++;
                }
            }
            int Apower = getValue(teamA,map);
            int Bpower = getValue(teamB,map);
            int powerGap = Math.abs(Apower-Bpower);
            if(answer>powerGap){
                answer=powerGap;
            }
            return;
        }
        if(passCnt>map.length/2){
            return;
        }

        selected[index] = true;
        getAns(selectCnt+1,passCnt,map,index+1,selected);
        selected[index] = false;
        getAns(selectCnt,passCnt+1,map,index+1,selected);

    }

    public static int getValue(int[] people,int[][] map){
        // people에는 사람들의 인덱스가 담겨있다..

//        map i j = i가 j랑 같이있을떄 i가 내는 힘
        int sum = 0;
        for(int i=0;i<people.length;i++){
            for(int j=0;j<people.length;j++){
                sum+=map[people[i]][people[j]];
            }
        }
        return sum;
    }


}