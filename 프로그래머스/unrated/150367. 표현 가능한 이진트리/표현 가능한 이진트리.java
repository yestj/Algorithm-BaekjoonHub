class Solution {
    public int[] solution(long[] numbers) {
        
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String bNum = Long.toBinaryString(numbers[i]);
            // 포화 이진트리로 만들어 줌
            // 이진수의 길이를 재고, 2^h - 1이 커지는 시점의 h를 구함
            int h = 0;
            while ((int)Math.pow(2, h) - 1 < bNum.length()) {
                h++;
            }
            // 포화 이진트리의 크기가 될 때까지 왼쪽에 0을 붙여줌
            while ((int)Math.pow(2, h) - 1 != bNum.length()) {
                bNum = "0" + bNum;
            }
            // root자리가 0이라면 이진트리가 될 수 없음. 재귀로 돌면서 루트자리 확인.
            if(checkRoot(bNum)) {
                answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    public boolean checkRoot(String number) {
        int midIdx = (number.length() - 1) / 2;
        char root = number.charAt(midIdx);
        String left = number.substring(0, midIdx);
        String right = number.substring(midIdx + 1, number.length());
        
        // 루트가 0인데 양 옆에 1이 있을 경우 빠져 나옴
        if(root == '0' && 
            (left.charAt((left.length() - 1 ) / 2 ) == '1' ||
             right.charAt((right.length() - 1 ) / 2) == '1')) {
            return false;
        }
        
        // 자식노드가 있는 루트노드가 더 있다면 탐색하러 감 (왼쪽부터 하고 오른쪽으로 감)
        if(left.length() >= 3) {
            if(!checkRoot(left)) return false;
            if(!checkRoot(right)) return false;
            return true;
        }
        
        return true;
    }
        
}