package confirmation;
import java.util.Scanner;

public class Confirm15 {
	/*
	 * BMIを計算するプログラム
	 * BMI = 体重/(身長 * 身長)
	 * 体重(kg), 身長(m)
	 * 予めデータを設定した場合の計算
	 * 
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		double weight,height,bmi;
		weight = 58;
		height = 1.68;
		bmi = weight /(height * height);
		System.out.println("BMI: " + bmi);
		
	
	/*
	 * 
	 * データをキーボードから入力する場合
	 * import java.util.Scanner;を設定しておく。
	 * 
	 */
	
	Scanner stdIn = new Scanner(System.in);
	System.out.println("体重を入力してください(kg): ");
	weight = stdIn.nextDouble();
	System.out.println("身長を入力してください(cm): ");
	height = stdIn.nextDouble();
	height /= 100;
	
	bmi = weight / (height * height);
	System.out.println("BMI: " + bmi + "です。");
	}

}
