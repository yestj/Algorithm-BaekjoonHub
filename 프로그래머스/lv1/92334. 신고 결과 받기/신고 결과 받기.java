import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
		// 신고자(key), 신고대상(value)
		HashMap<String, ArrayList<String>> user_report_list = new HashMap<>();
		// 신고대상(key), 횟수(value)
		HashMap<String, Integer> reported_cnt = new HashMap<>();

		report: for (int i = 0; i < report.length; i++) {
			String[] each_report = report[i].split(" ");
			String reporter = each_report[0];
			String reported_person = each_report[1];
			ArrayList<String> list = new ArrayList<>();

			// 이미 신고리스트가 있는 사람이라면 해당리스트를 불러옴.
			if (user_report_list.containsKey(reporter)) {
				list = user_report_list.get(reporter);
			}

			// 1. report 배열을 돌며, 신고자가 기존에 신고대상을 신고한 적이 있는지 확인
			for (int j = 0; j < list.size(); j++) {
				// 2-1. 신고한 적이 있으면, continue
				if (reported_person.equals(list.get(j))) {
					continue report;
				}
			}

			// 2-2. 신고한 적이 없으면, report_list에 추가하고, 해당 신고대상의 신고횟수를 늘려줌
			list.add(reported_person);
			user_report_list.put(reporter, list);
			if (reported_cnt.containsKey(reported_person)) {
				int cnt = reported_cnt.get(reported_person);
				reported_cnt.put(reported_person, cnt + 1);
			} else {
				reported_cnt.put(reported_person, 1);
			}
		}

		// 3. id_list 배열을 돌며, 신고자가 신고한 신고대상의 최종 신고 횟수를 확인
		for (int i = 0; i < id_list.length; i++) {
			if (user_report_list.containsKey(id_list[i])) {
				ArrayList<String> list = user_report_list.get(id_list[i]);
				for (int j = 0; j < list.size(); j++) {
					// 4-1. k번 이상 신고 당한 사람이 있다면, answer에 해당 index 자리 +1
					if (reported_cnt.get(list.get(j)) >= k) {
						answer[i]++;
					}
				}
			}
		}

		return answer;
    }
}