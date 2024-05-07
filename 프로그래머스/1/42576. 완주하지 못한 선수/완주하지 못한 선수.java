import java.util.*;


class Solution {
    public String solution(String[] participant, String[] completion) {
        // sol 1 : 정렬후 다른원소가 나올경우 해당원소 return   (nlogn)
        
//         int size = participant.length;
//         Arrays.sort(participant);
//         Arrays.sort(completion);
//         for(int i=0;i<size-1;i++){
//             if(!participant[i].equals(completion[i])){
//                 return participant[i];
//             }
//         }
        
        
//         return participant[size-1];
        
        
        // sol 2 : 각 배열마다 원소요소의 개수 count, 요소개수가 다르다면 해당요소 return (n)
        int size = participant.length;
        
        HashMap<String,Integer> hmap = new HashMap<>();
        
        for(String s : participant){
            if(hmap.containsKey(s)){
                hmap.put(s,hmap.get(s)+1);    
            }else{
                hmap.put(s,1);
            }
            
        }
        for(String s : completion){
            hmap.put(s,hmap.get(s)-1);
        }
        
        return hmap.entrySet().stream().filter(a->a.getValue()==1).findAny().get().getKey();
    }
}