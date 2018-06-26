package confirmation;
import java.util.Scanner;

public class Confirm17 {
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
		
		if(bmi < 18.5) {
			System.out.println("Skinny");
		} else if (bmi < 25) {
			System.out.println("Standard");
		} else {
			System.out.println("Fat");
		}
	
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

	if(bmi < 18.5) {
		System.out.println("やせてる");
	} else if (bmi < 25) {
		System.out.println("標準");
	} else {
		System.out.println("太り気味かな...");
	}
	
	
	System.out.println("");
	System.out.println("========以下、LOOP処理のメモ==========");
	
	System.out.println("");
	for(int i = 0; i < 5; i++) {
	System.out.println(i);
	}
	
	System.out.println("");
	for(int i = 5; i > 0; i--) {
	System.out.println(i);
	}
	
	}
}
