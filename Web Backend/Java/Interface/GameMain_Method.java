package practice_answer;

import java.util.Random;
import java.util.Scanner;

public class GameMain_Method {

	//메인 메뉴를 출력해주는 메서드
	public void printMenu() {
		System.out.println("=== ABCDE_rpg ===");
		System.out.println("1. 캐릭터 생성");
		System.out.println("2. 캐릭터 정보");
		System.out.println("3. 전직하기");
		System.out.println("4. 기본 공격");
		System.out.println("5. 스킬 사용");
		System.out.println("0. 종료");
		System.out.println("=================");
		System.out.print("번호를 선택하세요 : ");
	}
	
	//출력 문구만 모아 놓은 메서드
	public void printText(int num) {
		switch (num) {
			case 1 -> System.out.println("게임을 종료합니다.");
			case 2 -> System.out.println("번호를 잘못 입력하셨습니다.");
			case 3 -> System.out.println("캐릭터를 생성하세요.");
			case 4 -> System.out.println("스킬을 배우지 않았습니다.");
		}
	}
	
	//캐릭터를 생성하는 메서드
	//스탯 힘, 민첩, 지능을 랜덤한 숫자로 스탯의 총 합이 15이상일 경우에만 부여한다.
	//사용자의 선택에 따라 저장 or 스탯 재부여 한다.
	public Novice createCharacter() {
		Novice novice = null;
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();
		System.out.println("캐릭터를 생성합니다.");
		System.out.print("사용하실 아이디를 입력해 주세요 : ");
		String id = scan.next();
		int str = 0, dex = 0, intel = 0, sum = 0;
		
		String text = "";
		while(true) {
			System.out.println("스탯을 부여합니다. ");
			
			while(true) {
				str = ran.nextInt(10);
				dex = ran.nextInt(10);
				intel = ran.nextInt(10);
				sum = str + dex + intel;
				//스탯의 총 합이 15이상일 때까지 각 스탯에 랜덤한 숫자 부여
				if(sum >= 15)
					break;
			}
			
			System.out.printf("부여된 스탯정보: 힘[%d], 민첩[%d], 지능[%d]\n", str, dex, intel);
			System.out.print("스탯을 다시 받으시겠습니까? (y/n) : ");
			text = scan.next();
			
			if(text.equals("n"))
				break;
		}
		System.out.println("현재 정보로 저장합니다.");
		novice = new Novice(id, str, dex, intel);
		System.out.println();
		return novice;
	}
	
	//직업에 따라 인스턴스를 생성하는 메서드
	//선택한 직업에 따라 해당 클래스로 인스턴스를 생성하고, 문구를 출력한다.
	public Novice jobSelect(int num, Novice novice) {
		Novice character = null;
		switch (num) {
			case 1 -> {
				character = new Knight(novice.getName(), novice.getStrength(), 
						novice.getDexterity(), novice.getIntelligence());
				System.out.println("기사로 전직합니다.");				
			}
			case 2 -> {
				character = new Thief(novice.getName(), novice.getStrength(), 
						novice.getDexterity(), novice.getIntelligence());
				System.out.println("도적으로 전직합니다.");
			}
			case 3 -> {
				character = new Magician(novice.getName(), novice.getStrength(), 
						novice.getDexterity(), novice.getIntelligence());
				System.out.println("마법사로 전직합니다.");				
			}
		}
		
		return character;
	}

	//전직을 위해 번호를 입력받는 메서드
	public int numberInput() {
		Scanner scan = new Scanner(System.in);
		int num = 0;
		while(num > 3 || num < 1) {
			System.out.println("=== 직업 종류 ===");
			System.out.println("1. 기사");
			System.out.println("2. 도적");
			System.out.println("3. 마법사");
			System.out.print("전직할 직업의 번호를 선택하세요 : ");
			num = scan.nextInt();
		}
		return num;
	}
	
	//생성된 캐릭터의 정보를 보여 주는 메서드
	//생성된 인스턴스에 따라 직업표시가 달라진다.
	public void characterInfo(Novice novice) {
		String job = "";
		
		if(novice instanceof Knight)
			job = "기사";
		else if(novice instanceof Thief)
			job = "도적";
		else if(novice instanceof Magician)
			job = "마법사";
		else
			job = "평민";
		
		String str = String.format("[ID: %s(%s), stat: 힘(%d), 민첩(%d), 지능(%d)]"
				, novice.getName(), job, novice.getStrength()
				, novice.getDexterity(), novice.getIntelligence());
		System.out.println(str);
	}

	public Skill skill(Novice novice) {
		Skill skill = null;
		if (novice instanceof Knight knight)
			skill = knight;
		else if	(novice instanceof Thief thief)
			skill = thief;
		else if	(novice instanceof Magician magician)
			skill = magician;
		
		return skill;
	}
}
