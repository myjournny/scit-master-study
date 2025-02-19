package chapter13.collection;

import java.util.Scanner;

public class User_main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// User_method에 정의된 변수 및 메서드를 사용하기위한 인스턴스 생성
		User_method um = new User_method();
		int num = 0;
		
		while (true) {
			// 메뉴를 출력하는 메서드 호출
			um.printMenu();
			num = scan.nextInt();
			
			switch (num) {
				case 1: 
					// 유저 등록하는 메서드 호출
					um.createUser();		break;
				case 2: 
					// 유저 조회하는 메서드 호출
					um.selectUser();		break;
				case 3: 
					// 유저 전체 조회하는 메서드 호출
					um.selectALL();			break;
				case 0: 
					// 프로그램 종료
					System.exit(0);
			}
		}
	}

}
