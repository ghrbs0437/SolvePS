#include <vector>
#include <map>
using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    int count=nums.size()/2;
    map<int,int> map1;
    for(int i=0;i<nums.size();i++){
        if(map1.find(nums[i])!=map1.end()){     // map에 존재하면..(중복건이면)
        }else{
            map1.insert({nums[i],1});
            answer++;
        }
    }
 
    if(answer>=count){
        return count;
    }
    return map1.size();
    
    
    return answer;
}