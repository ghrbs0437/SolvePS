import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        int[] arrs = new int[n+1];
        for(int i=0;i<n;i++) {
            arrs[i] = Integer.parseInt(strs[i]);
        }

        int[] origin = new int[n];

        // arr =  역순..

        LinkedList<Integer> linkedList = new LinkedList<>();
//        linkedList.add(n-1);
        // 가장 깊게까지 탐색한 범위를 확인해서, 그거보다 낮은건 list에 넣지도 마..
        int maxExplore = n;
        for(int i=n-1;i>=0;i--) {
            int val = arrs[i];
//            System.out.println(i+"번째 시작" + linkedList.size());
//            System.out.println(Arrays.toString(origin));
            if(!linkedList.isEmpty()){

                if(val<linkedList.size()){// lined list의 인덱스에서 해결가능한수준
                    int index = linkedList.get(val);
//                    System.out.println(linkedList);
//                    System.out.println(Arrays.toString(origin));
                    linkedList.remove(val);
//                    System.out.println(linkedList);
                    origin[index] = i+1;
                    continue;
                }
            }


//            if(!linkedList.isEmpty()&&val<linkedList.size()){ // ll에서 뺴서쓰면 그만..
////                System.out.println("?");
//                int index = linkedList.get(val);
//                System.out.println("index = " + index);
//                linkedList.remove(val);
//                origin[index] = i;
//                continue;
//            }

//            System.out.println("val = " + maxExplore);
            // ll의 마지막인덱스에서부터 탐색한다..

            if(!linkedList.isEmpty()){
                val -= linkedList.size();
            }
            for(int j=maxExplore-1;j>=0;j--) {
                if(val==0 && origin[j]==0){
                    origin[j] = i+1;
                    break;
                }
                if(origin[j]==0) {
                    if(maxExplore>j){
                        linkedList.add(j);

                    }
                    val--;
                }
                maxExplore = j;
            }
        }


        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<n;i++){
            sb.append(origin[i]).append(" ");
        }

        System.out.println(sb);

    }
}