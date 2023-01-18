class Solution {
    public int solution(int n, int k) {
        int answer = 0;
		// 변환한 진수값을 저장 
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			sb.append(n%k);
			n /= k;
		}
		String convertValue = sb.reverse().toString();
		
		int ed = 0;
		for(int st = 0; st < convertValue.length() -1; st = ed) {
			for(ed = st + 1; ed < convertValue.length() && convertValue.charAt(ed) != '0'; ed++);
			if(isPrime(Long.parseLong(convertValue.substring(st, ed)))) {
				answer++;
				
			}
		}
		return answer;
    }
    
    public boolean isPrime(long n) {
		if(n <= 1) return false;
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) return false;
		}
		return true;
	}


}