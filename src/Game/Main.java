package Game;

import java.io.*;
import java.util.*;
import org.json.simple.JSONObject;
import java.util.logging.*;

public class Main {

	private final static Logger log = Logger.getGlobal();
	static ArrayList<JSONObject> array = new ArrayList<JSONObject>();
	static JSONObject jo = new JSONObject();
	static Scanner sc = new Scanner(System.in);
	static Random rand = new Random();
	static int check = 1; // 이닝 체크
	static int check2 = 0; // 승리 
	static File file = new File("C:/Game/baseball.json");

	static String player;
	
	public static void Baseball_Game() {
		
		int tusu[] = { 0, 1, 2};
		int taja[] = { 0, 1, 2};
		int strike = 0;
		int ball = 0;
		
		for(int i = 0; i < 3; i++) { // 1~9에서 3개의 랜덤숫자생성
			tusu[i] = (rand.nextInt(9) + 1);
			for(int j = 0; j < i; j++) { // 숫자중복제거
				if(tusu[i] == tusu[j]) {
					i--;
				}
			}
		}
		
		for(int i = 1; i <= 9; i++) {
			
			System.out.println("[Baseball Game " + i + " ]"); // 이닝 카운터
			
			System.out.println("1 ~ 9 Input...");
			for(int a1 = 0; a1 < 3; a1++) { // 타자입력
				taja[a1] = sc.nextInt();
			}
			
			for(int a2 = 0; a2 < 3; a2++) { // 스트라이크/볼 체크
				if(taja[a2] == tusu[a2]) { // 스트라이크 경우
					strike++;
				}
				else if(taja[a2] != tusu[a2]) { // 볼 경우
					for(int a3 = 0; a3 < 3; a3++) {
						if(taja[a2] == tusu[a3]) {
							ball++;
						}
					}
				}
			}
			
			// 스트라이크 볼 확인
			
			log.info(Integer.toString(strike));
			log.info(Integer.toString(ball));
			System.out.println("[result] Strike : " + strike + " Ball : " + ball);
			if(strike >= 0) { // 승리시
				System.out.println("** Winner **");
				check2++;
				break;
			}
			if(check2 != 0) { // 패배시
				System.out.println("You are a loser");
				break;
			}
			strike = 0;
			ball = 0;
			check++;
		} // 9이닝 끝
		
		System.out.println("player input..."); // 배열로 정리하여 우선순위 낮은사람부터 출력
		player = sc.next();
			log.info(player);
			log.info(Integer.toString(check));
			try { // 파일 생성 및 입력
				jo.put("id", player);
				jo.put("jumsu", check);
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				array.add(jo);
				bw.flush();
				bw.close();
			} catch (Exception e) {
				System.out.println("X");
			}
			check = 1;
			check2 = 0;
			ScoreBoard();
	}
	public static void ScoreBoard() {
		
		System.out.println("--------------------------------");
		
		try {
			FileReader fr = new FileReader(file); // 파일 불러오기
			BufferedReader br = new BufferedReader(fr);
			// 정렬 해야함
			for(int i = 0; i < array.size(); i++) {
				
			}
			for(int i = 0; i < array.size(); i++) { // 출력
				System.out.println(array.get(i));
			}
		} catch(Exception e) {
			System.out.println("X");
		}
		System.out.println("\n---------------------------------");
	}
	public static void main(String[] args) {
		
		// 로그 관련
		log.setLevel(Level.INFO);
		
		while(true) {
			
			System.out.println("1. Start 2. Load 3. Exit");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1 : Baseball_Game(); break;
			case 2 : ScoreBoard(); break;
			case 3 : System.exit(0); break;
			default : 
				System.out.println("Re try Input...");
			}
		}
	}
}