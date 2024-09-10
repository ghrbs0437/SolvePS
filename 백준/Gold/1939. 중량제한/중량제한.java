import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashMap<Integer, ArrayList<Integer>> hmap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
//        int[] parents = init(N);
        ArrayList<Token>[] lineList = new ArrayList[N];
        for(int i=0;i<N;i++){
            lineList[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int A = Integer.parseInt(strs[0])-1;
            int B = Integer.parseInt(strs[1])-1;
            int weight = Integer.parseInt(strs[2]);
            lineList[A].add(new Token(A,B,weight));
//            lineList[B].add(new Token(B,A,weight));
        }
        strs = br.readLine().split(" ");
        int from = Integer.parseInt(strs[0])-1;
        int to = Integer.parseInt(strs[1])-1;

        //!공장이 있는 두 섬을 연결하는 경로는 항상 존재
        // 1. 집합으로 목적지까지 가는 집합을 탐색한다.
        // 2. 해당 집합에 대해 weight가 answer 이하인 간선을 모두 제거했을 때,
        // 연결되어 있는지 확인한다.

        long answerWeight = 0;
        long dWeight = 1000000000;
        HashSet<Long> hset = new HashSet<>();

        long answer = 0;
        while(true){

            if(dWeight!=1){
                dWeight>>=1;
            }
            if(hset.contains(answerWeight)){
                break;
            }
            if(dWeight==1){
                hset.add(answerWeight);
            }
            int[] parents = init(N);
            for(int i=0;i<N;i++){
                for(Token token : lineList[i]){
                    if(token.value>=answerWeight){
                        union(token.start,token.end,parents);
                    }
                }
            }

            if(findRoot(from,parents)==findRoot(to,parents)){
                answer = Math.max(answer,answerWeight);
                answerWeight +=dWeight;
            }else{
                answerWeight -=dWeight;
            }
        }
        System.out.println(answer);
    }

    public static int[] init(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = i;
        }
        return arr;
    }
    public static int findRoot(int value , int[]parents){
        if(value == parents[value]){
            return value;
        }
        return parents[value] = findRoot(parents[value], parents);
    }

    public static boolean union(int a,int b, int[]parents){
        int rootA = findRoot(a,parents);
        int rootB = findRoot(b,parents);
        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }

    public static class Token{
        int start;
        int end;
        int value;

        Token(int start,int end,int value){
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}