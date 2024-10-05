import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        long ATK = Integer.parseInt(strs[1]);


        int[] types = new int[N];
        long[][] values = new long[N][2];

        // 용사의 체력에 대해 이분탐색을 하자...
        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            int type = Integer.parseInt(strs[0]);
            types[i] = type;
            values[i][0] = Integer.parseInt(strs[1]);
            values[i][1] = Integer.parseInt(strs[2]);
        }

        long minHP = 0 ;
        long maxHP = Long.MAX_VALUE;
        long answer = Long.MAX_VALUE;
        while(maxHP>=minHP){
            long middle = (minHP + maxHP)/2;
            long currentHeroHp = middle;
            long currentHeroATK = ATK;
            for(int i=0;i<N;i++){
                if(types[i]==1){
                    long monATK = values[i][0];
                    long monHP = values[i][1];
                    if(monHP % currentHeroATK ==0){
                        currentHeroHp -= monATK * ((monHP / currentHeroATK) -1);
                    }else{
                        currentHeroHp -= monATK * ((monHP / currentHeroATK));
                    }
                    if(currentHeroHp<=0){ // 실패한테케
                        break;
                    }
                }else if(types[i]==2){
                    long encATK = values[i][0];
                    long recHP = values[i][1];
                    currentHeroHp = Math.min(currentHeroHp + recHP,middle);
                    currentHeroATK += encATK;
                }
            }
            if(currentHeroHp<=0){
                minHP = middle+1;
            }else{
                maxHP = middle-1;
                answer = Math.min(answer,middle);
            }
//            System.out.println(minHP + " " + middle + " " + maxHP);
        }
        System.out.println(answer);
    }
}