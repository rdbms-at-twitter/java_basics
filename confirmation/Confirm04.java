package confirmation;

public class Confirm04 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		boolean tenki = true;
		if (tenki == true) {
			System.out.println("Washing Machine");
			System.out.println("Go for a wolk");
		} else
			System.out.println("Watch DVD with Makito");	
		
		
		System.out.println("Let check your fortune!!");
		int fortune = new java.util.Random().nextInt(5) + 1;
		switch (fortune) {
		case 1:
		case 2:
			System.out.println("Grate");
		break;
		
		case 3:
			System.out.println("General");
			break;
		case 4:
		case 5:
			System.out.println("No so bad....");
		
		}
				
	}

}
