import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    static ArrayList<int[]> answerList = new ArrayList<>();
    public static void main(String args[]) throws IOException {


            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int test_case = Integer.parseInt(br.readLine());
            for (int tc = 1; tc <= test_case; tc++) {
                answerList = new ArrayList<>();
                String[] strs = br.readLine().split(" ");
                // 햄스터 우리의 개수 (10개 이하)
                int N = Integer.parseInt(strs[0]);
                // 각 우리에 있을 수 있는 햄스터외 최대 마리수 (10마리 이하)
                int X = Integer.parseInt(strs[1]);
                // 기록의 개수 ( 다음 입력의 개수) (10개 이하)
                int M = Integer.parseInt(strs[2]);


                ArrayList<Boundary> boundarylist = new ArrayList<>();
                for (int i = 0; i < M; i++) {
                    strs = br.readLine().split(" ");
                    int start = Integer.parseInt(strs[0]) - 1;
                    int end = Integer.parseInt(strs[1]) - 1;
                    int count = Integer.parseInt(strs[2]);
                    boundarylist.add(new Boundary(start, end, count));
                }
                int[] cages = new int[N];

                getAns(boundarylist, cages, X, 0);
                Collections.sort(answerList, (a, b) -> {
                    int sumA = 0;
                    for (int i : a) {
                        sumA += i;
                    }
                    int sumB = 0;
                    for (int i : b) {
                        sumB += i;
                    }

//                    if(sumA ==sumB){
//                        return Arrays.toString(a).compareTo(Arrays.toString(b));
//                    }
//                    사전순이면 문자순서..................................

                    if (sumA == sumB) {
                        for(int i=0;i<a.length;i++){
                            if(a[i]<b[i]){
                                return -1;
                            }else if(a[i]>b[i]){
                                return 1;
                            }
                        }
                        return 1;
                    } else {
                        return -sumA + sumB;
                    }
                });

                if (answerList.size() == 0) {
                    sb.append("#" + tc + " " + "-1");
                } else {
                    sb.append("#").append(tc);
                    for (int i : answerList.get(0)) {
                        sb.append(" ").append(i);
                    }

                }
                sb.append("\n");
            }
            System.out.println(sb);
        }


    public static void getAns(ArrayList<Boundary> boundaryList, int[] cages, int maxHam, int index){

        // 바운더리가 유효하지 않게되었다면 더이상 진행하지 않는다.
        // 바운더리가 유효하지 않다는것은
        // 1. 바운더리 범위를 벗어났는데, 남는게 있을때
        // 2. 바운더리 범위 안에서 넘칠때
        for(Boundary boundary : boundaryList){
            int sum = 0;
            for(int i=boundary.start;i<= boundary.end;i++){
                sum += cages[i];
            }
            if(index>boundary.end && sum!=boundary.value){
                return;
            }
            if(sum>boundary.value){
                return;
            }
        }
        // 마지막처리부분//
        // 마지막은 바운더리가 정확하게 처리가 되어야한다..
        if(index==cages.length){
            for(Boundary boundary : boundaryList){
                int sum = 0;
                for(int i=boundary.start;i<=boundary.end;i++){
                    sum += cages[i];
                }
                if(sum!=boundary.value){
                    return;
                }
            }
            answerList.add(acopy(cages));
            return;
        }

        // 만약 아무런 바운더리에 속해있지 않으면. max헴
        boolean noBoundary = true;
        for(Boundary boundary : boundaryList){
            if(boundary.start>index || boundary.end<index ){
            }else{
                noBoundary = false;
            }
        }
        if(noBoundary){
            cages[index] = maxHam;
            getAns(boundaryList,cages,maxHam,index+1);
            return;
        }

        for(int i=0;i<=maxHam;i++){
            cages[index] = i;
            getAns(boundaryList,cages,maxHam,index+1);
            cages[index] = 0;
        }
    }

    public static class Boundary{
        int start;
        int end;
        int value;

        Boundary(int start,int end,int value){
            this.start = start;
            this.end = end;
            this. value = value;
        }
    }

    public static int[] acopy(int[] array){
        int[] newArray = new int[array.length];
        for(int i=0;i< array.length;i++){
            newArray[i] = array[i];
        }
        return newArray;
    }
}