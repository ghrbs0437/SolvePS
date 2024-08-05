import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split("-");
        StringBuilder sb = new StringBuilder();

        for(String s : strs){
            sb.append(s.charAt(0));
        }
        System.out.println(sb);

    }
}