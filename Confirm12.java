package confirmation;

public class Confirm12 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		System.out.print("【Game of numbers】 ");
		int[] numbers = {3,4,9};
		for (int n : numbers) {
			System.out.println("Please type single number from 0 to 9");
			int input = new java.util.Scanner(System.in).nextInt();  //Accept input from Keyboard
			if (n == input) {
				System.out.println("JackPot!!");
				break;
			}else {
				System.out.println("Not Hit JackPot");
			}
		}	
		System.out.println("End of the Game, Please Try Again");
		
	}

}
