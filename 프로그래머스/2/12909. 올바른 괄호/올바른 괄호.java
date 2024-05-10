import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        
        if(s.charAt(0)==')'){
            return false;
        }
        if(s.charAt(s.length()-1)=='('){
            return false;
        }
        if(s.length()%2!=0){
            return false;
        }
        stack.push(s.charAt(0));
      
        System.out.println(stack.peek());
        for(int i=1;i<s.length();i++){
            if(stack.empty()){
                if(s.charAt(i)==')'){
                    return false;
                }else{
                    stack.push(s.charAt(i));
                }
            }else{
                if(s.charAt(i)==')'){
                    stack.pop();
                }else{
                    stack.push(s.charAt(i));
                }
                
            }
            
        }
        if(stack.empty()){
            return true;
        }else{
            return false;
        }
                
    }
}