package confirmation;

public class Confirm08 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// String input = new java.util.Scanner (System.in).nextLine() //1行の文字列入力を受け入れ 
		// String input = new java.util.Scanner (System.in).nextInt()  //1つの整数入力を受け入れ

		System.out.print("【Game of numbers】 ");
		int ans = new java.util.Random().nextInt(10);             //Generate Random number from 0 to 9
		for (int i = 0; i < 5 ; i++) {
			System.out.println("Please type number from 0 to 9");
			int num = new java.util.Scanner(System.in).nextInt(); //Accept input from Keyboard
			if (ans == num) {
				System.out.println("JackPot!!");
				break;
			}else {
				System.out.println("Not Hit JackPot");
			}
		}	
		System.out.println("End of the Game, Please Try Again");
	}

}
