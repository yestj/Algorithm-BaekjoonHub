import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
큐를 이용하여 타자 순서와 주자를 관리하려 했으나,
아웃될 경우 순서를 유지하며 관리하기가 어려움
=> 배열/인덱싱 방식으로 접근 변경.
 */

public class Main {

    static int N;
    static int[][] score;

    // 선수 뽑기용 방문체크.
    static boolean[] visited = new boolean[9];

    // 선수 순서 넣어놓기
    static int[] order = new int[9];

    static int cnt; // 최대 점수.

    static boolean[] base; // 각 루에 선수가 있는지 저장.


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        score = new int[N][9];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < 9; c++) {
                score[r][c] = sc.nextInt();
            }
        }

        setOrder(0);
        System.out.println(cnt);

    }

    static void play() {

        int idx = 0;
        int player = order[idx]; // 현재 타자
        int tempCnt = 0; // 점수

        for(int i = 0; i < N; i++){
            base = new boolean[4]; // 주자 저장
            int out = 0; // 아웃 카운트
            while(out < 3){
                player = order[idx];
                int hit = score[i][player];
                switch(hit) {
                    case 0:
                        out++;
                        break;
                    // 1루타
                    case 1:
                        if(base[3]) {
                            tempCnt++;
                            base[3] = false;
                        }
                        if(base[2]) {
                            base[3] = true;
                            base[2] = false;
                        }
                        if(base[1]) {
                            base[2] = true;
                        }
                        base[1] = true;
                        break;
                    // 2루타
                    case 2:
                        if(base[3]) {
                            tempCnt++;
                            base[3] = false;
                        }
                        if(base[2]) {
                            tempCnt++;
                        }
                        if(base[1]) {
                            base[3] = true;
                            base[1] = false;
                        }
                        base[2] = true;
                        break;
                    // 3루타
                    case 3:
                        if(base[3]) {
                            tempCnt++;
                        }
                        if(base[2]) {
                            tempCnt++;
                            base[2] = false;
                        }
                        if(base[1]) {
                            tempCnt++;
                            base[1] = false;
                        }
                        base[3] = true;
                        break;
                    // 홈런
                    case 4:
                        if(base[3]) {
                            tempCnt++;
                            base[3] = false;
                        }
                        if(base[2]) {
                            tempCnt++;
                            base[2] = false;
                        }
                        if(base[1]) {
                            tempCnt++;
                            base[1] = false;
                        }
                        tempCnt++;
                        break;
                }
                idx++;
                if(idx == 9){
                    idx = 0;
                }
            }
        }
        cnt = Math.max(tempCnt, cnt);


    }

    // 순서를 정해주는 메소드. (4번째 자리는 1번 타자로 고정)
    static void setOrder(int cnt) {

        if (cnt == 9) {
            play();
            return;
        }
        if (cnt == 3) {
            order[cnt] = 0;
            setOrder(cnt + 1);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[cnt] = i;
                setOrder(cnt + 1);
                visited[i] = false;
            }
        }

    }
}