import java.util.*;

public class Main {

    // 최장 증가 수열이나, 길이를 구하는 문제이기 때문에 출력값이 틀리더라도 최대한 많은 숫자가 들어갈 수 있도록 내용물을 셋팅
    // 1. 가장 끝에 있는 숫자와 비교해 크면 수열에 붙여줌
    // 2. 적을 경우 이분 탐색을 통해 적당한 위치를 찾아 숫자를 대치

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] seq = new int[N];
        int[] LIS = new int[N];

        for (int i = 0; i < N; i++) {
            seq[i] = sc.nextInt();
        }

        // 초기화
        LIS[0] = seq[0];
        int LISLen = 1;

        for (int i = 1; i < N; i++) {

            int next = seq[i];

            if (LIS[LISLen - 1] < next) LIS[LISLen++] = next;
            else {
                int st = 0;
                int ed = LISLen;

                while (st < ed) {
                    int mid = (st + ed) / 2;

                    if(LIS[mid] < next) st = mid + 1;
                    else ed = mid;
                }

                LIS[st] = next;
            }
        }

        System.out.println(LISLen);
    }

}