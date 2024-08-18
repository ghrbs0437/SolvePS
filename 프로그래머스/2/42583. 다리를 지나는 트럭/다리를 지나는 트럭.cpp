#include <string>
#include <vector>
#include <iostream>


using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer=0;
    int Possible=0;
    int point=0;
    int num=0;
    int sum=0;

    vector<int> in_time;

    //다리위에 올라갈때마다 +1, 기다려야할때마다 bridgh_length
    for(int i=1;;i++){
        
        if(sum+truck_weights[num]<=weight){//다리위의 무게가 감당가능하면
            Possible=1;
        }else{
            Possible=0;
        }
        if(num>truck_weights.size()){
            Possible=0;
        }
        
        /*종료조건*/
        if(point==truck_weights.size()){
            answer=i;
            break;
        }
        
        
        if(Possible){
            sum+=truck_weights[num];
            num++;
            in_time.push_back(i);
            //cout<<num-1<<"번째가 담긴 시간"<<i<<endl;
        }else{           //불가능하면 기다리기.
            if(i-in_time.front()>=bridge_length){
                sum-=truck_weights[point];
                point++;
                in_time.erase(in_time.begin());
                //cout<<"건넌시점"<<i<<endl;
                i--;
            }
        }
        
      
    }
    return answer;
}