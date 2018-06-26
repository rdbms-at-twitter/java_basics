package confirmation;

public class CalcLogic {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//Calc.javaと連動して動きます
		
		int a = 10;
		int b = 2;
		
		//外部クラスCalc内部のメソッドを呼び出して処理
		int total = Calc.plus(a,b);
		int delta = Calc.minus(a,b);
		System.out.println("Plus Result: " + total +  "\n" + "Minus Result: " + delta);

		System.out.println("");
		int[] heights = {172, 149, 152, 191, 155};
		java.util.Arrays.sort(heights);  //Javaの標準ソート機能
		for (int h : heights) {          //拡張FOR
		System.out.println(h);
	}

}
}