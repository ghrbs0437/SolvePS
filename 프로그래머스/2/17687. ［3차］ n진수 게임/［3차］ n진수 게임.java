import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        // 1. 몇번째를 출력해야하는지 구한다
        // 2. 순서대로 돌면서 해당하면 그 내용을 출력한다..
        
        // n = 진법
        // t = 구해야하는 숫자의 개수
        // m = 인원
        // p = 튜브의 순서
        //1 -> 튜브의 순서 + m x 만큼
        
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i=0;i<t;i++){
            queue.add(p);
            p = m + p;
        }
        System.out.println(queue);
        
        int counter = 1;
        int cur = 0 ;
        int number = 0;
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int next = queue.poll();
            // next는 뽑아야하는 순번
            // count는 현재 순번 카운터
            // number는 현재 숫자
            for(int i = number;;i++){
                String nextStr = devide(n,i);
                boolean flag = false;
                for(int j = 0;j<nextStr.length();j++){
                    if(counter == next){
                        sb.append(nextStr.charAt(j));
                        flag = true;
                    }
                    counter++;
                }
                number++;
                if(flag){
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public String changeToAF(int i){
        if(i>=10){
            return String.valueOf((char)(i+55));
        }
        return String.valueOf(i);
    }
    
    // n은 진법 , number는 실제숫자
    public String devide(int n, int number){
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        while(true){
            int temp = number / n;
            if(temp==0){
                stack.add(changeToAF(number%n));
                break;
            }else{
                stack.add(changeToAF(number%n));
                number = number / n;
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}