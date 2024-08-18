import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.StandardSocketOptions;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<String> answerCase = new ArrayList<>();
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            answerCase = new ArrayList<>();
            flag = false;
            String str = br.readLine();

            int[] overIndex = new int[str.length()];
            int[] alphabet = new int[26];
            for (int i = 0; i < str.length(); i++) {
                overIndex[i] = str.charAt(i) - 'A';
                alphabet[str.charAt(i) - 'A']++;
            }
            getAns(overIndex, alphabet, str, new StringBuilder(), 0);
//            System.out.println(Arrays.toString(overIndex));
            if(answerCase.size()>=2){
                System.out.println(answerCase.get(1));
            }else{
                System.out.println(str);
            }
//            System.out.println();
        }

        // 사전순으로 뒤에있을만한 것들은 배제하면서 진행하자..

    }


    //넣을떄 사전순으로 가장 앞에있는 순으로 넣어야해..
    public static void getAns(int[] overIndex, int[] useAlphabet, String str, StringBuilder building, int depth) {
        // 만들고있는 문자열이 유효하지 않으면 더 탐색하지 않는다..
        if(flag){
            return;
        }
        if(!checkAfterDictionary(str,building)){
//            System.out.println(str+"||"+building);
            return;
        }

        if (building.length() == str.length()) {
            answerCase.add(building.toString());
            if(answerCase.size()>=2){
                flag = true;
            }
            return;
        }
//        System.out.println(building);

        for (int i = 0; i < useAlphabet.length; i++) { // 원문자열 이상
            if (useAlphabet[i] <= 0) {
                continue;
            }
            useAlphabet[i]--;
            building.append((char) (i + 'A'));
            getAns(overIndex, useAlphabet, str, building, depth + 1);
            building.deleteCharAt(building.length() - 1);
            useAlphabet[i]++;
        }
    }

    public static boolean checkAfterDictionary(String str, StringBuilder sb){
        for(int i=0;i<sb.length();i++){
            if(str.charAt(i) < sb.charAt(i)){
                return true;
            }
            if(str.charAt(i) > sb.charAt(i)){
                return false;
            }
        }
        return true;
    }
}