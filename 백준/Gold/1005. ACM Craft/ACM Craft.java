import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static class Building{
        public int number;
        public int remaining;
        public ArrayList<Integer> nexts = new ArrayList<>();

        public Building(int number, int remaining){
            this.number = number;
            this.remaining = remaining;
        }

        public void add(Integer next){
            nexts.add(next);
        }

        @Override
        public String toString() {
            return "Building{" +
                    "number=" + number +
                    ", remaining=" + remaining +
                    ", nexts=" + nexts +
                    '}';
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int test_case = Integer.parseInt(br.readLine());
        tk: for(int i=0;i<test_case;i++){
            String[] strs = br.readLine().split(" ");
            int buildingCnt = Integer.parseInt(strs[0]);
            int roleCnt = Integer.parseInt(strs[1]);
            strs = br.readLine().split(" ");
            int[] needTime = new int[buildingCnt+1];
            needTime[0] = -1;
            for(int j=0;j<strs.length;j++){
                needTime[j+1] = Integer.parseInt(strs[j]);
            }

            Building[] buildings = new Building[buildingCnt+1];
            int[] indegree = new int[buildingCnt+1];
            indegree[0] = -1;
            for(int j=0;j<roleCnt;j++){
                strs = br.readLine().split(" ");
                int start = Integer.parseInt(strs[0]);
                int end = Integer.parseInt(strs[1]);
                if(buildings[start] == null){
                    buildings[start] = new Building(start,needTime[start]);
                }
                buildings[start].add(end);
                indegree[end]++;
            }
            int W = Integer.parseInt(br.readLine());

            for(int j=1;j<=buildingCnt;j++){
                if(buildings[j]==null){
                    buildings[j] = new Building(j,needTime[j]);
                }
            }

            // 입력이 완료됨..



            // 그래프들이 존재한다.. 현재 진입차수가 0 이면 작업 착수가 가능하다..
            // 진입차수가 0인 정점들의 소요시간의 최소치만큼, 진입차수가 0인 모든 정점들의 남은시간을 감소시킨다.
            // 완료된 작업이 완료되면, 해당 정점에서 연결된 그래프들을 확인한다.. 이 정점의 진입차수가 0이라면, 테스크에 넣는다.
            // 건물 W가 지어질때 까지 반복한다..
//            ArrayList<Integer> proceeding = new ArrayList<>();
//            for(int j=1;j<=buildingCnt;j++){
//                if(indegree[j]==0){
//                    proceeding.add(j);
//                }
//            }
//            while(true){
//
//
//                continue tk;
//            }

            PriorityQueue<Building> pq = new PriorityQueue<>((a,b)->{
                return a.remaining - b.remaining;
            });
            ArrayList<Building> proceeding = new ArrayList<>();
            for(int j=1;j<=buildingCnt;j++) {
                if(indegree[j]==0){
                    proceeding.add(buildings[j]);
                    pq.add(buildings[j]);
                }
            }
            int answer = 0;
            while(true){
                Building minB = pq.poll();

                if(minB.number == W){
                    System.out.println( (answer+minB.remaining));
                    continue tk;
                }
                int tasktime = minB.remaining;
                answer +=tasktime;
                for(Building building : proceeding){
                    building.remaining = building.remaining - tasktime;
                }
                //TODO proceeding에서, remaining이 0이하면 삭제해야함..
                ArrayList<Integer> nexts = minB.nexts;
                for(Integer index : nexts){
                    if(--indegree[index]==0){
                        pq.add(buildings[index]);
                        proceeding.add(buildings[index]);
                    }
                }
            }
        }




















//        int test_case = Integer.parseInt(br.readLine());
//        tk: for(int i=0;i<test_case;i++){
//            String[] strs = br.readLine().split(" ");
//            int buildingCnt = Integer.parseInt(strs[0]);
//            int roleCnt = Integer.parseInt(strs[1]);
//            strs = br.readLine().split(" ");
//            int[] needTime = new int[buildingCnt+1];
//            needTime[0] = -1;
//            for(int j=0;j<strs.length;j++){
//                needTime[j+1] = Integer.parseInt(strs[j]);
//            }
//
//            HashMap<Integer,ArrayList<Integer>> hmap = new HashMap<>();
//            int[] indegree = new int[buildingCnt+1];
//            indegree[0] = -1;
//            for(int j=0;j<roleCnt;j++){
//                strs = br.readLine().split(" ");
//                int start = Integer.parseInt(strs[0]);
//                int end = Integer.parseInt(strs[1]);
//                if(hmap.get(start)==null){
//                    hmap.put(start,new ArrayList<>());
//                }
//                hmap.get(start).add(end);
//                indegree[end]--;
//            }
//            // 입력처리 완료
//
//
//
//            // 그래프들이 존재한다.. 현재 진입차수가 0 이면 작업 착수가 가능하다..
//            // 진입차수가 0인 정점들의 소요시간의 최소치만큼, 진입차수가 0인 모든 정점들의 남은시간을 감소시킨다.
//            // 완료된 작업이 완료되면, 해당 정점에서 연결된 그래프들을 확인한다.. 이 정점의 진입차수가 0이라면, 테스크에 넣는다.
//            // 건물 W가 지어질때 까지 반복한다..
//
//            ArrayList<Integer> proceeding = new ArrayList<>();
//            for(int j=1;j<=buildingCnt;j++){
//                if(indegree[j]==0){
//                    proceeding.add(j);
//                }
//            }
//
//            while(true){
//
//
//                continue tk;
//            }
//
//        }

    }



}