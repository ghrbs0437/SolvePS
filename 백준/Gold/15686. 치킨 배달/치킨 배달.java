import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs =  br.readLine().split(" ");
        int mapSize = Integer.parseInt(strs[0]);
        int storeSize = Integer.parseInt(strs[1]);

        Map map = new Map(mapSize);
        for(int i=0; i<mapSize;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<mapSize;j++){
                map.setValue(i,j,Integer.parseInt(strs[j]));
            }
        }
        // max 치킨거리가 최소가 되도록 치킨집을 지워라...
        map.finalStoreCount = storeSize;
        map.getAnswer();
        System.out.println(map.answer);
    }


    public static class Map{

        int[][] map;
        // 맵에 존재하는 home 을 담고 있는 리스트
        ArrayList<Home> homeList = new ArrayList<>();
        // 맵에 존재하는 store 을 담고 있는 리스트
        ArrayList<Store> storeList = new ArrayList<>();
        // home에서 각 store까지의 거리를 담고있는 list map
        HashMap<Home,ArrayList<Integer>> distanceMap = new HashMap<>();

        // 최종적으로 남게되는 가게의 수
        int finalStoreCount;

        // 가게의 조합에 따른 인덱스 모음
        ArrayList<ArrayList<Integer>> storeCase = new ArrayList<>();

        int answer = Integer.MAX_VALUE;

        Map(int mapSize){
            map = new int[mapSize][mapSize];
        }

        public void getAnswer(){
            generateDistanceMap();
            getCombination(0,0,new boolean[storeList.size()]);

            tc : for(ArrayList<Integer> storesPerCase : storeCase) {
                int sumChickenDistance = 0;
                for (Home home : homeList) {
                    ArrayList<Integer> distanceList = distanceMap.get(home);
                    int homeMinChickenDistance = Integer.MAX_VALUE;
                    for(Integer storeIndex : storesPerCase){
                        int distance = distanceList.get(storeIndex);
                        if (homeMinChickenDistance > distance) {
                            homeMinChickenDistance = distance;
                        }
                    }
                    sumChickenDistance+=homeMinChickenDistance;
                    
                    if(sumChickenDistance>answer){
                        continue tc;
                    }
                }
                if (sumChickenDistance < answer) {
                    answer = sumChickenDistance;
                }
            }
        }


        public void setValue(int y, int x, int value){
            map[y][x] = value;
            if(value==2){ // 치킨집
                Store store = new Store(y,x);
                storeList.add(store);
            }
            if(value==1){
                Home home = new Home(y,x);
                homeList.add(home);
            }
        }


        private void getCombination(int selectCnt, int depth,boolean[] select){
            if(selectCnt == finalStoreCount) {
                ArrayList<Integer> alist = new ArrayList<>();
                for (int i = 0; i < select.length; i++) {
                    if (select[i]) {
                        alist.add(i);
                    }
                }
                storeCase.add(alist);
                return;
            }

            if(depth == select.length){
                return;
            }

            select[depth] = true;
            getCombination(selectCnt+1,depth+1,select);
            select[depth] = false;
            getCombination(selectCnt,depth+1,select);
        }


        private void generateDistanceMap(){
            for(int i=0;i<homeList.size();i++){
                Home home = homeList.get(i);
                for(int j=0;j<storeList.size();j++){
                    Store store = storeList.get(j);
                    int distance = getDistance(home,store);
                    if(distanceMap.get(home)==null){
                        distanceMap.put(home,new ArrayList<>());
                    }
                    distanceMap.get(home).add(distance);
                }
            }
        }

        private int getDistance(Home home, Store store){
            return Math.abs(home.y - store.y) +  Math.abs(home.x - store.x);
        }
    }




    public static class Store{
        int y;
        int x;
        Store(int y,int x){
            this.y = y;
            this.x = x;
        }
    }

    public static class Home{
        int y;
        int x;

        Home(int y,int x){
            this.y = y;
            this.x = x;
        }
    }

}