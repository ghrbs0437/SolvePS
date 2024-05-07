import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashSet<Integer> hmap = new HashSet<>();
        for(int num:nums){
            hmap.add(num);
        }
        if(hmap.size()>nums.length/2){
            return nums.length/2;
        }else{
            return hmap.size();
        }
    }
}