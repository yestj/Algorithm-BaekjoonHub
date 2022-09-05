import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int[] nums = new int[10001];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			nums[Integer.parseInt(br.readLine())]++;
		}
	
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < 10001; i++) {
			while(nums[i] > 0 ){ 
				sb.append(i).append("\n");
				nums[i]--;
			}
		}
		
		System.out.println(sb);
	}

}