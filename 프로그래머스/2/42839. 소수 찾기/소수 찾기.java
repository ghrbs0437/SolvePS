import java.util.*;

class Solution {
    // static int answer = 0;
    static HashSet<Integer> hset = new HashSet<>();
    public int solution(String numbers) {
        
        search(numbers,new boolean[numbers.length()],"");
        return hset.size();
    }
    
    // visited를 인덱스로하면 안되고 값으로 해야할듯..
    public void search(String str , boolean[] visited, String makingString){
        
        if(isPrimeNumber(makingString)){
            hset.add(Integer.parseInt(makingString));
        }
        
        if(makingString.length()==str.length()){
            return;
        }
        
                
        for(int i=0;i<str.length();i++){
            if(visited[i]){
                continue;
            }
            if(makingString.length()==0 && str.charAt(i)=='0'){
                continue;
            }
            visited[i] = true;
            search(str, visited,makingString+str.charAt(i));
            visited[i] = false;
        }
    }
    
    
    
    public boolean isPrimeNumber(String s){
        if(s.length()==0){
            return false;
        }
        
        int number = Integer.parseInt(s);
        
        if(number == 1){
            return false;
        }
        if(number ==2 || number==3){
            return true;
        }
        
        int keyVal = 1;
        for(int i=1;i<number;i++){
            if(i * i > number){
                break;
            }
            keyVal = i;
            
        }
        for(int i=2;i<=keyVal;i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}