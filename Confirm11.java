package confirmation;

public class Confirm11 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		int[] points = new int[4];				//4個のINTを格納　メモ：アレイだけど0,1,2,3の箱に入る。
		double[] weights = new double[5];		//5個のDOUBLEを格納
		boolean[] answers = new boolean[3];		//3個のBooleanを格納
		String[] names = new String[3];			//3個のStringを格納
		//ここまでは、アレイの定義例で以下では利用していない。
		
		System.out.println(""); //改行
		
		int[] moneyList = {121900,75823,4800000};    	//メモリー上に配列を作成→変数Score作成→先頭要素のアドレスが代入される。
		for (int i = 0;  i < moneyList.length;  i++) {	
			System.out.println(moneyList[i]);	  	//scoreの中に入っているアドレス番地を取り出し配列を見つける。見つけた配列の先頭要素からn個後ろを読み書き。
		}
		
		System.out.println(""); //改行
		
		//拡張FOR LOOP --- for(型 変数名:配列変数名)
		//PHPでいう、ダブルアローのような感じかな?
		int[] moneyList_ex = {121900,75823,4800000};
		for (int value : moneyList_ex) {
			System.out.println(value);
		}
		
		
	}

}
