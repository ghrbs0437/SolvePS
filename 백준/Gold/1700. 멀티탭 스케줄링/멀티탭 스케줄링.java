import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]); // 멀티탭 구멍의 개수
        int K = Integer.parseInt(strs[1]); // 전기용품 총 사용횟수, 전기용품의 종류

        strs = br.readLine().split(" ");

        int[] arr = new int[K];
        PriorityQueue<Integer>[] lists = new PriorityQueue[K];

        for(int i=0;i<K;i++){
            lists[i] = new PriorityQueue<>();
        }


        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(strs[i]) - 1;
            lists[num].add(i);
            arr[i] = num;
        }

        HashSet<Integer> using = new HashSet<>();
        int answer = 0;

        for(int i=0;i<K;i++){

            int num = arr[i];
            if(using.contains(num)){
                lists[num].poll();
                continue;
            }
            if(N>0){
                using.add(num);
                lists[num].poll();
                N--;
            }else{
                answer++;
                int lastIdx = 0;
                Integer temp = -1;
                // 사용중인것 중에서, 가장 나중에 사용할걸 뺴면 되는거야.
                // 그런데.. 아예 안사용할수도 있어.

                for(Integer use : using){
                    Integer index = lists[use].peek(); // 현재 사용중인거 리스트를 확인해서..
                    if(index == null){
                        // 아예 안사용한다면 이거 뺴야해.
                        temp = use;
                        break;
                    }
                    if(index > lastIdx){ // 사용중인거중에서 마지막 인덱스가 가장 큰거를 쓰면 되는거야.
                        lastIdx = index;
                        temp = use;
                    }
                }
                lists[num].poll(); //
                using.remove(temp);
                using.add(num);
            }
        }
        System.out.println(answer);

    }
}