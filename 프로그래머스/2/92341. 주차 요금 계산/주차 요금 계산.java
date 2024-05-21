import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        HashMap<String,String> carMap = new HashMap<>(); // carNumber , time
        HashMap<String,Integer> feeMap = new HashMap<>(); // carNumber , answer
        HashMap<String,Integer> carTimeMap = new HashMap<>(); // carNumber , runningTime
        for(String record: records){
            String[] command = record.split(" ");
            String eventTime = command[0];
            String carNumber = command[1];
            String eventType = command[2];
            if(eventType.equals("IN")){
                carMap.put(carNumber,eventTime);
            }else{
                if(!carMap.containsKey(carNumber)){
                    continue;
                }
                String inTime = carMap.get(carNumber);
                int runningTime = getTimeGap(inTime,eventTime);
                if(carTimeMap.get(carNumber)==null){
                    carTimeMap.put(carNumber,runningTime);
                }else{
                    carTimeMap.put(carNumber, carTimeMap.get(carNumber) + runningTime);
                }
                carMap.remove(carNumber);
            }
        }
        
        Set<String> remains = carMap.keySet();
        for(String carNumber : remains){
            String inTime = carMap.get(carNumber);
            int runningTime = getTimeGap(inTime,"23:59");
            if(carTimeMap.get(carNumber)==null){
                carTimeMap.put(carNumber,runningTime);
            }else{
                carTimeMap.put(carNumber, carTimeMap.get(carNumber) + runningTime);
            }
            // carMap.remove(carNumber);
        }
        
        Set<String> keys = carTimeMap.keySet();
        for(String carNumber : keys){
            int runningTime = carTimeMap.get(carNumber);
            if(runningTime - defaultTime<=0){ 
                feeMap.put(carNumber,defaultFee);
            }else{
                int overTime = runningTime - defaultTime;
                int overUnit = overTime / unitTime;
                if(overTime%unitTime!=0){
                    overUnit++;
                }
                feeMap.put(carNumber, defaultFee + overUnit*unitFee);
            }
        }
        
        
        
//         for(String record: records){
//             String[] command = record.split(" ");
//             String eventTime = command[0];
//             String carNumber = command[1];
//             String eventType = command[2];
//             if(eventType.equals("IN")){
//                 carMap.put(carNumber,eventTime);
//             }
            
//             if(eventType.equals("OUT")){
//                 if(!carMap.containsKey(carNumber)){
//                     continue;
//                 }
//                 String inTime = carMap.get(carNumber);
//                 int runningTime = getTimeGap(inTime,eventTime);

//                 //  정산
//                 //  1. 기본요금대상자
//                 carMap.remove(carNumber);
//                 if(runningTime - defaultTime<=0){ 
//                     if(feeMap.get(carNumber)==null){
//                         feeMap.put(carNumber,defaultFee);
//                     }else{
//                         feeMap.put(carNumber, feeMap.get(carNumber) +defaultFee);
//                     }
//                 }
//                 //  2. 추가요금 대상자
//                 else{
//                     int overTime = runningTime - defaultTime;
//                     int overUnit = overTime / unitTime;
//                     if(feeMap.get(carNumber)==null){
//                         feeMap.put(carNumber, defaultFee + overUnit*unitFee);
//                     }else{
//                         int temp = feeMap.get(carNumber);
//                         feeMap.put(carNumber, feeMap.get(carNumber) + defaultFee + (overUnit*unitFee));
//                     }
//                 }
//             }
//         }
        
//         // 남아있는거 처리
//         Set<String> keys = carMap.keySet();
//         for(String carNumber : keys){
//             String inTime = carMap.get(carNumber);
//             int runningTime = getTimeGap(inTime,"23:59");
//                 //  정산
//                 //  1. 기본요금대상자
//                 if(runningTime - defaultTime<=0){ 
//                     if(feeMap.get(carNumber)==null){
//                         feeMap.put(carNumber,defaultFee);
//                     }else{
//                         feeMap.put(carNumber, feeMap.get(carNumber) +defaultFee);
//                     }
//                 }
//                 //  2. 추가요금 대상자
//                 else{
//                     int overTime = runningTime - defaultTime;
//                     int overUnit = overTime / unitTime;
//                     if(feeMap.get(carNumber)==null){
//                         feeMap.put(carNumber, defaultFee + overUnit*unitFee);
//                     }else{
//                         feeMap.put(carNumber, feeMap.get(carNumber) + defaultFee + overUnit*unitFee);
//                     }
//                 }
//         }
    
        
        ArrayList<String> alist = new ArrayList<>();
        Set<String> feeKeys= feeMap.keySet();
        for(String key : feeKeys){
            alist.add(key);
        }
        alist.sort((a,b)->a.compareTo(b));
        int size = feeMap.size();
        int[] answer = new int[size];
        for(int i=0;i<size;i++){
             String car =alist.get(i);
             answer[i] = feeMap.get(car);
        }
        
        return answer;
    }
    
    public int getTimeGap(String small,String big){
        int hour = Integer.valueOf(big.substring(0,2)) - Integer.valueOf(small.substring(0,2));
        int minute = Integer.valueOf(big.substring(3,5)) - Integer.valueOf(small.substring(3,5));
        if( minute < 0){
            hour--;
            minute += 60;
        }
        return hour*60 + minute;
    }
}

