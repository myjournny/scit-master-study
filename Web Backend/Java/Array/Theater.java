package chapter07.array1;

import java.util.Scanner;

public class Theater {

	public static void main(String[] args) {
		Theater_Method tm = new Theater_Method();
		Scanner scan = new Scanner(System.in);
		int num = 0, cnt = 0;
		
		while (true) {
			tm.printMenu();
			num = scan.nextInt();
			switch (num) {
				case 1:
					tm.seatInfo();
					break;
				case 2:
					cnt=tm.inputCnt();
					tm.reservationSeats(cnt);
					break;
				case 0:
					tm.printText(1);
					return;
				default:
					tm.printText(2);
					break;
			}
		}

	}

}
