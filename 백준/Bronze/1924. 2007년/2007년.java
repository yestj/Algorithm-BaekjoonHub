import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String[] weekDay = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

		int month = sc.nextInt();
		int day = sc.nextInt();

		int daySum = 0;
		for (int i = 0; i < month - 1; i++) {
			daySum += monthDays[i];
		}
		daySum += day;

		System.out.println(weekDay[daySum % 7]);

	}

}