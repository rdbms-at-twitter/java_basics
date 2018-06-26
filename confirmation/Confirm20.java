package confirmation;

public class Confirm20 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int month = 6;
		switch(month) {
		case 1:
			System.out.println("January");
			break;
		case 2:
			System.out.println("February");
			break;
		case 3:
			System.out.println("March");
			break;
		case 4:
			System.out.println("April");
			break;
		case 5:
			System.out.println("May");
			break;
		case 6:
			System.out.println("June");
			break;
		case 7:
			System.out.println("July");
			break;
		case 8:
			System.out.println("August");
			break;
		case 9:
			System.out.println("September");
		    break;
		case 10:
			System.out.println("October");
			break;
		case 11:
			System.out.println("Nobember");
			break;
		case 12:
			System.out.println("December");
			break;
		default:
			break;
		}
		
		System.out.println("");
		
		String[] month_array = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		for (int i = 0; i < 12; i ++ ) {
			System.out.println(month_array[i]);
		}
		
		
		
	}
}