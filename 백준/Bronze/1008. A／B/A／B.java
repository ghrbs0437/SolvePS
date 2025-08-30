
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        double d = Double.parseDouble(strs[0])/ Double.parseDouble(strs[1]);
        System.out.println(d);

    }
}