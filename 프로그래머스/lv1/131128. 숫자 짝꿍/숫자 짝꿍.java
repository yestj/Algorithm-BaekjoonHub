class Solution {
    public String solution(String X, String Y) {

		// 0~9까지 숫자가 몇 번 나왔는지 카운트 할 배열 생성
		int[] arrX = new int[10];
		int[] arrY = new int[10];

		cntNum(X, arrX);
		cntNum(Y, arrY);

		// 답을 더할 StringBuilder
		StringBuilder answer = new StringBuilder();
		// 배열을 9에서 부터 돌며, 두 배열 모두 cnt된 숫자가 0 이상일 경우, 그 중 최솟값 횟수만큼 해당 idx를 sb에 더해줌
		for (int i = 9; i >= 0; i--) {
			if (arrX[i] > 0 && arrY[i] > 0) {
				int minCnt = Math.min(arrX[i], arrY[i]);
				for (int j = 0; j < minCnt; j++) {
					answer.append(i);
				}
			}
		}

		// 답 출력
		if (answer.toString().equals(""))
			return "-1";
		else if (answer.toString().startsWith("0"))
			return "0";
		else
			return answer.toString();
	}

	// 주어진 문자열에서 0~9까지 숫자가 몇 번 나왔는지 카운트
	private void cntNum(String str, int[] arr) {
		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - '0';
			arr[idx]++;
		}
	}
}