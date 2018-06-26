package confirmation;

public class Confirm03 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		System.out.println("Welcome to Quiz Room");
		System.out.println("Please input your name");
		String name = new java.util.Scanner(System.in).nextLine();
		System.out.println("Please Type Your Age");
		String ageString = new java.util.Scanner(System.in).nextLine();
		int age = Integer.parseInt(ageString);
		int fortune = new java.util.Random().nextInt(4);
		fortune++;
		System.out.println("Answer is there");
		System.out.println(age + "Age" + name + "-san, Your fortune is" + fortune + "...");
		System.out.println("1: Good Fortune 2: Middle Fortune 3: Lucky  4: Bad Fortune");
		
	}

}
