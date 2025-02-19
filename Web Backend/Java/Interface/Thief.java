package practice_answer;

//도적
public class Thief extends Novice implements Skill{

	//명시적 생성자
	//Thief형 인스턴스 생성시 Novice의 명시적 생성자를 호출하여 각각의 초기값을 가지는 인스턴스 생성
	public Thief(String name, int strength, int dexterity, int intelligence) {
		super(name, strength, dexterity, intelligence);
	}
	
	//상위 클래스 Novice의 attack()메서드를 재정의(오버라이드)
	@Override
	public void attack() {
		System.err.println("치명적인 공격");
	}

	//interface Skill의 메서드 skill()를 구현한다. (오버라이드) 
	@Override
	public void skill() {
		System.err.println("중독");
	}
	
}
