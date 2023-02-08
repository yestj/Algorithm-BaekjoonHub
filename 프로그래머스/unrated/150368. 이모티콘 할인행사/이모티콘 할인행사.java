class Solution {
    private static int[] discount = {40, 30, 20, 10};
	private static int[] answer = {0, 0};
	private static int[] emoticonDiscount; // 각 이모티콘의 할인율을 저장할 배열 
	private static int N, M;
    
    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
		M = emoticons.length;
		emoticonDiscount = new int[M];
		dfs(0, users, emoticons);
        return answer;
    }
    
    private static void dfs(int depth, int[][] users, int[] emoticons) {
		if(depth == M) {
			int subscriber = 0;
			int sales = 0;
			for(int i = 0; i < N; i++) {
				int curSales = 0;
				for(int j = 0; j < M; j++) {
					if(users[i][0] <= emoticonDiscount[j]) {
						curSales += emoticons[j] * (100 - emoticonDiscount[j])/100;
					}
				}
				if(users[i][1] <= curSales) {
					subscriber++;
				} else {
					sales += curSales;
				}
			}
			// 모든 유저들의 계산을 마친 후, 최종 값을 answer와 비교 
			// 구독자수가 answer보다 많으면 현재 값으로 업데이트  
			if(subscriber > answer[0]) {
				answer[0] = subscriber;
				answer[1] = sales;
			// 구독자수가 같으나 판매액이 더 높으면 판매액만 업데이트 
			} else if (subscriber == answer[0] && sales > answer[1]) {
				answer[1] = sales;
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			emoticonDiscount[depth] = discount[i];
			dfs(depth + 1, users, emoticons);
		}
    }
}