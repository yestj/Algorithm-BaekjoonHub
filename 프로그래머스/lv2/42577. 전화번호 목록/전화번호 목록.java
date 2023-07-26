import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        HashSet<String> checkBook = new HashSet<>();
        for (int i = 0; i < phone_book.length; i++) {
            checkBook.add(phone_book[i]);
        }
        
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 1; j < phone_book[i].length(); j++) {
                String tmp = phone_book[i].substring(0, j);
                if (checkBook.contains(tmp)) return false;
            }
        }
        
        return true;
    }
}