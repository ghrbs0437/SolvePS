import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            String[] strs = br.readLine().split(" ");
 
            // 맵크기
            int N = Integer.parseInt(strs[0]);
            // 선택 벌통개수
            int M = Integer.parseInt(strs[1]);
 
            // 최대
            int C = Integer.parseInt(strs[2]);
 
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                strs = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(strs[j]);
                }
            }
            // 벌꿀집을 연속해서 선택하지 않을 경우가 있다...
            // 1. 구간을 뽑는다
            // 2. 구간에서 선택을 뽑는다
            // 3. 계산한다..
            int val = getAns(map, M, C);
            sb.append("#"+tc+" "+val +"\n");
        }
        System.out.println(sb);
    }
 
    static int ONE_LINE_MAX = 0;
    static int TWO_LINE_MAX = 0;
 
    public static int getAns(int[][] map, int selectHoneyCnt, int maxHoney) {
        int twoLineMax = 0;
        int oneLineMax = 0;
        if (map.length / 2 >= 2) {
            oneLineMax = getOneLineMax(map, selectHoneyCnt, maxHoney);
        }
        twoLineMax = getTwoLineMax(map, selectHoneyCnt, maxHoney);
        return Math.max(oneLineMax, twoLineMax);
    }
 
    public static int getTwoLineMax(int[][] map, int possibleSelectCnt, int maxHoney){
        ArrayList<Integer> valueList = new ArrayList<>();
 
        for(int i=0;i<map.length;i++){
            int max = 0;
            for(int j=0;j<map.length-possibleSelectCnt+1;j++){
                ArrayList<ArrayList<Integer>> container = new ArrayList<>();
                makeRangeSelection(container,new ArrayList<>(),j,maxHoney,possibleSelectCnt,0);
                selection :
                for (ArrayList<Integer> selects : container) {
                    int honey = 0;
                    int value = 0;
                    for (Integer index : selects) {
                        honey += map[i][index];
                        if (honey > maxHoney) {
                            continue selection;
                        }
                        value += map[i][index] * map[i][index];
                    }
                    if (max < value) {
                        max = value;
                    }
                }
            }
            valueList.add(max);
        }
        valueList.sort((a,b)->b-a);
        return valueList.get(0) + valueList.get(1);
    }
 
    public static int getOneLineMax(int[][] map, int possibleSelectCnt, int maxHoney) {
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            max = Math.max(max, getLineMax(map, possibleSelectCnt, maxHoney, i));
        }
        return max;
    }
 
    public static int getLineMax(int[][] map, int possibleSelectCnt, int maxHoney, int y) {
        int max = 0;
        for (int i = 0; i < map.length - possibleSelectCnt; i++) {
            ArrayList<ArrayList<Integer>> startContainer = new ArrayList<>();
            makeRangeSelection(startContainer, new ArrayList<>(), i, maxHoney, possibleSelectCnt, 0);
            for (int j = i + possibleSelectCnt; j < map.length - possibleSelectCnt+1; j++) {
                ArrayList<ArrayList<Integer>> endContainer = new ArrayList<>();
                makeRangeSelection(endContainer, new ArrayList<>(), j, maxHoney, possibleSelectCnt, 0);
                int val = getMaxFromSelection(map,y, startContainer,endContainer,maxHoney);
                if(val>max){
                    max = val;
                }
            }
        }
        return max;
    }
 
    public static int getMaxFromSelection(int[][] map, int y, ArrayList<ArrayList<Integer>> startContainer, ArrayList<ArrayList<Integer>> endContainer, int maxHoney) {
        int maxStart = 0;
        selection:
        for (ArrayList<Integer> startSel : startContainer) {
            int honey = 0;
            int value = 0;
            for (Integer index : startSel) {
                honey += map[y][index];
                if (honey > maxHoney) {
                    continue selection;
                }
                value += map[y][index] * map[y][index];
            }
            if (maxStart < value) {
                maxStart = value;
            }
        }
 
        int maxEnd = 0;
        selection2:
        for (ArrayList<Integer> endSel : endContainer) {
            int honey = 0;
            int value = 0;
            for (Integer index : endSel) {
                honey += map[y][index];
                if (honey > maxHoney) {
                    continue selection2;
                }
                value += map[y][index] * map[y][index];
            }
            if (maxEnd < value) {
                maxEnd = value;
            }
        }
 
        return maxStart + maxEnd;
    }
 
 
    public static void makeRangeSelection(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> select, int index, int maxHoney, int possibleSelectCnt, int selectCnt) {
        if (selectCnt == possibleSelectCnt) {
            if (select.isEmpty()) {
                return;
            }
            result.add(listCopy(select));
            return;
        }
        ArrayList<Integer> newList1 = listCopy(select);
        makeRangeSelection(result, newList1, index + 1, maxHoney, possibleSelectCnt, selectCnt + 1);
        ArrayList<Integer> newList2 = listCopy(select);
        newList2.add(index);
        makeRangeSelection(result, newList2, index + 1, maxHoney, possibleSelectCnt, selectCnt + 1);
 
 
    }
 
    private static ArrayList<Integer> listCopy(ArrayList<Integer> alist) {
        ArrayList<Integer> newList = new ArrayList<>();
        if (alist == null) {
            return newList;
        }
        for (Integer i : alist) {
            newList.add(i);
        }
        return newList;
    }
}