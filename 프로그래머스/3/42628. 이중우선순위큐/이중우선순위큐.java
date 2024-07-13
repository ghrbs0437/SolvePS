import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        ArrayList<Integer> alist = new ArrayList<>();
        
        for(String oper : operations){
            String[] comm = oper.split(" ");
            
            if(comm[0].equals("I")){
                alist.add(Integer.valueOf(comm[1]));
                Collections.sort(alist);
            }else{
                if(alist.size()==0){
                    continue;
                }
                
                if(comm[1].equals("-1")){
                    alist.remove(0);
                }else{
                    alist.remove(alist.size()-1);
                }
            }
        }
        if(alist.size()==0){
           return answer;
        }
        answer[1] = alist.get(0);
        answer[0] = alist.get(alist.size()-1);
        
        return answer;
    }
}