import java.util.*;


class Solution {
    public String solution(String[] participant, String[] completion) {
        int size = participant.length;
        Arrays.sort(participant);
        Arrays.sort(completion);
        for(int i=0;i<size-1;i++){
            if(!participant[i].equals(completion[i])){
                return participant[i];
            }
        }
        return participant[size-1];
    }
}