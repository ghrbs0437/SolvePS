import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N];
        String[] strs = br.readLine().split(" ");

        for(int i=0;i<N;i++){
            map[i] = Integer.parseInt(strs[i]);
        }

        int[] lis = new int[N];

        PriorityQueue<Integer>[] pqs = new PriorityQueue[N]; // lis 각 차리에 올 수 있는 인덱스들
        for(int i=0;i<N;i++){
            pqs[i] = new PriorityQueue<>((a,b)->a-b);
        }

        int index = 0;
        lis[0] = map[0];
        pqs[0].add(0);


        for(int i=1;i<N;i++){
            int num = map[i];
            if(num > lis[index]){
                lis[++index] = num;
                pqs[index].add(i);
            }else{
                int min = 0;
                int max = index;
                int middle = (min+max)/2;
                while(true){
                    middle = (min + max)/2;
                    if(min == max){
                        if(middle>0&&num==lis[middle-1]){
                        }else{
                            lis[middle] = num;
                            pqs[middle].add(i);
                        }
                        break;
                    }
                    if(num<lis[middle]){
                        max = middle;
                    }else{
                        min = middle+1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();


        int maxIndex = pqs[index].poll();
        int maxV = map[maxIndex];
        ArrayList<Integer> answer = new ArrayList<>();
//        sb.append(map[maxIndex]+" ");
        answer.add(map[maxIndex]);
        for(int i=index-1;i>=0;i--){
            while(true){
                int num = pqs[i].poll(); // 여기서 오랫동안 보관된거일수록 값이 크다.
                if(map[num] < maxV){
                    answer.add(map[num]);
//                    sb.insert(0,map[num]+" ");
                    maxV = map[num];
                    break;
                }else{
                    continue;
                }
            }

        }

//        sb.insert(0,(index+1)+"\n");
        sb.append(index+1).append("\n");
        for(int i= answer.size()-1;i>=0;i--){
            sb.append(answer.get(i)).append(" ");
        }
        System.out.println(sb);


    }
}