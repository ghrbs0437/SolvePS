import java.util.*;


class Solution {
    public boolean solution(String s) {
        int length = s.length();
        Stack<Character> st = new Stack<>();
        for(int i=0;i<length;i++){
            char cur = s.charAt(i);
            if(cur=='{' ||cur=='('){
                st.add(cur);
            }else if(cur==')'){
                if(st.empty() || st.peek()!='('){
                    return false;
                }else{
                    st.pop();
                }
            }else if(cur=='}'){
                 if(st.empty() ||st.peek()!='{'){
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