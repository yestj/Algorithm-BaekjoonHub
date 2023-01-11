import java.util.ArrayList;

class Solution {
    
    // 주차 시간을 저장할 클래스
    static class ParkingTime {
        int hour; // 입차 시
        int mins; // 입차 분
        int totalTime; // 누적시간
        
        ParkingTime(int hour, int mins, int totalTime) {
            this.hour = hour;
            this.mins = mins;
            this.totalTime = totalTime;
        }
    }
    
    /**
    * @Param fees 요금관련 정보를 담고 있는 배열(0: 기본시간 1: 기본요금 2: 단위시간 3: 단위요금)
    * @Param records "시각 차량번호 내역"을 담고 있는 문자열 배열
    */
    public int[] solution(int[] fees, String[] records) {
        
        ParkingTime[] car = new ParkingTime[10000]; // 주차시간을 저장할 배열 생성, 각 인덱스는 차량번호 의미.

		// records를 돌면서 car의 값 갱신해줌.
		for (int i = 0; i < records.length; i++) {
			String temp = records[i];
			String[] recordSplited = temp.split(" ");
			String[] time = recordSplited[0].split(":");
			int hour = Integer.parseInt(time[0]);
			int mins = Integer.parseInt(time[1]);
			int carNum = Integer.parseInt(recordSplited[1]);
			String currStatus = recordSplited[2];

			if (currStatus.equals("IN")) {
				int totalTime = 0;
				if (car[carNum] != null) {
					totalTime = car[carNum].totalTime;
				}
				car[carNum] = new ParkingTime(hour, mins, totalTime);
			} else {
				int inHour = car[carNum].hour;
				int inMins = car[carNum].mins;
				int totalTime = car[carNum].totalTime;
				
				totalTime += (hour - inHour) * 60 + (mins - inMins);
				car[carNum] = new ParkingTime(-1, -1, totalTime); // 초기화.
			}
		}

		ArrayList<Integer> parkingFee = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			if (car[i] != null) {
				// 토탈시간 정산(out없는 애들)
				if (car[i].hour >= 0) {
					int inHour = car[i].hour;
					int inMins = car[i].mins;
					int totalTime = car[i].totalTime;
					totalTime += (23 - inHour) * 60 + (59 - inMins);
					car[i] = new ParkingTime(0, 0, totalTime); // 초기화.
				}
				// 금액 정산
				if (car[i].totalTime <= fees[0]) {
					parkingFee.add(fees[1]);
				} else {
					int addTime = car[i].totalTime - fees[0];
					int unitTime = (int) Math.ceil(((double)addTime / fees[2]));
					parkingFee.add(fees[1] + unitTime * fees[3]);
				}

			}
		}

		int[] answer = new int[parkingFee.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = parkingFee.get(i);
		}
		return answer;
    }
    
    
    
}