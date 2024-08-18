import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
  
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=10;tc++) {
            br.readLine();
            String[] strs = br.readLine().split(" ");
             
            // 숫자배열의 크기는 8 고정
            int[] arr = new int[8];        
             
            // 8개의 숫자를 15로 나눴을때의 몫의 최소값을 구한다.
            int minDivider = Integer.MAX_VALUE;
            for(int i=0;i<8;i++) {
                arr[i] = Integer.parseInt(strs[i]);
                int divider = arr[i]/15;
                if(divider < minDivider) {
                    minDivider = divider;
                }
            }
            // 만약 나눌수 없었다면 몫은 0으로 한다.
            if(minDivider == Integer.MAX_VALUE) {
                minDivider = 0;
            }
             
            // 한번에 15씩 나눴기 때문에, 0인채로 끌려다닌 녀셕이 있을 수도 있다..  
            for(int i=0;i<8;i++) {
                arr[i] -= minDivider*15;
                arr[i] += 15;
            }
  
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            for(int i:arr){
                deque.add(i);
            }
  
            // 문제에서 정의한 사이클을 반복한다.
            while(doCycle(deque)){}
  
            sb.append("#"+tc);
            for(int i:deque){
                sb.append(" "+i);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
  
    public static boolean doCycle(ArrayDeque<Integer> deque){
        for(int i=1;i<=5;i++){
            Integer val = deque.poll();
            val-=i;
            if(val<=0){
                val = 0;
                deque.addLast(val);
                return false;
            }
            deque.addLast(val);
        }
        return true;
    }
}