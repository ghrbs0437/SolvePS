import java.util.*;

class Solution {
    public boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        int length = s.length();
        for(int i=0;i<length;i++){
            char cur = s.charAt(i);
            if(cur=='('){
                st.push(cur);
            }else if(cur==')'){
                if(st.empty() || st.peek()!='('){
                    return false;
                }else{
                    st.pop();
                }
            }
        }  
        if(st.empty()){
            return true;
        }
        return false;
    }
}