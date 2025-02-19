package chapter13.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class User_method {
	Scanner scan = new Scanner(System.in);
	// 유저등록 리스트
	List<User> userList = new ArrayList<>();

	public void printMenu() {
		// 메뉴를 출력하는 코드 작성.
		String menu= """
		== 유저관리 프로그램 ==
		  1. 유저정보 등록
		  2. 유저정보 조회
		  3. 전체정보 조회
		  0. 종      료
		=================
		-> 메뉴의 번호 : """;
		System.out.println(menu);
	}

	public void createUser() {
		/*
			유저정보를 등록하는 코드 작성.
			1. 아이디, 비밀번호, 이름, 핸드폰, 주소를 입력받는다.
			2. User 인스턴스 생성한다.
			3. 유저등록 리스트에 같은 아이디가 존재하는지 확인 후 존재하지 않으면 등록
		 */
		String id, pw, name, phone, address;
		System.out.print("ID: ");
		id=scan.nextLine();
		System.out.print("PW: ");
		pw=scan.nextLine();
		System.out.print("이름: ");
		name=scan.nextLine();
		System.out.print("핸드폰: ");
		phone=scan.nextLine();
		System.out.print("주소: ");
		address=scan.nextLine();
		
		boolean overlap=false;
		for(User user : userList) {
			if(user.getId().equals(id)) {
				System.out.println("이미 존재하는 ID입니다.");
				overlap = true;
				break;
			}
		}
		if (!overlap)
			userList.add(new User(id, pw, name, phone, address));
	}

	public void selectUser() {
		/*
			유저정보를 조회하는 코드 작성.
			1. 유저등록 리스트에 유저정보가 존재하는지 확인
			2. 조회할 아이디, 확인용 비밀번호 입력 받는다.
			3. 조회할 아이디, 확인용 비밀번호가 유저등록 리스트에 저장된 비밀번호와 일치하면 유저정보 출력.
		 */
		String id, pw, name, phone, address;
	
		System.out.println("----------------");
		System.out.println("유저정보를 조회합니다.");
		System.out.println("-> 조회할 아이디 : ");
		id=scan.nextLine();
		System.out.println("-> 비밀번호 확인 : ");
		pw=scan.nextLine();
		
		boolean overlap = false;
		for(User user : userList) {
			if(user.getId().equals(id) && user.getPw().equals(pw)) {
				System.out.println(user);
				overlap = true;
				break;
			}
		}
		if(!overlap)
			System.out.println("일치하는 유저정보가 없습니다.");
		System.out.println();
	}
	class NameComparator implements Comparator<User> {
	    @Override
	    public int compare(User u1, User u2) {
	        return u1.getName().compareTo(u2.getName());
	    }
	}

	public void selectALL() {
		/*
			유저 전체정보를 조회하는 코드 작성.
			1. 유저등록 리스트에 유저정보가 존재하는지 확인
			2. 전체정보를 출력
			   (단, '이름순'으로 정렬하여 출력할 것)
		 */
		if (userList.isEmpty()) {  // 유저 리스트가 비어있는지 확인
			System.out.println("등록된 유저가 없습니다.");
		    return;  // 유저가 없으면 메서드를 종료
		};
		
		/*Collections.sort(userList, new Comparator<User>() {
	        @Override
	        public int compare(User u1, User u2) {
	            return u1.getName().compareTo(u2.getName());  // 이름순으로 비교
	        }
	    });*/
		
		Collections.sort(userList, new NameComparator());
		
		for (User user : userList) {
			System.out.println(user);
		}
		
		
	}

}
