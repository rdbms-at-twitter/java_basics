package confirmation;

public class Confirm09 {

	public static void main(String[] args) {
		
		//　基本的なFOR LOOP
		int[] score = {10,20,30,40,50,60};    //メモリー上に配列を作成→変数Score作成→先頭要素のアドレスが代入される。
		for (int i = 0;  i < score.length;  i++) {	
			System.out.println(score[i]);	  //scoreの中に入っているアドレス番地を取り出し配列を見つける。見つけた配列の先頭要素からn個後ろを読み書き。

		}
		{
		int sum = score[0]+score[1]+score[2]+score[3]+score[4]+score[5];
		int avg = sum/score.length;
		System.out.println("Total:" + sum);
		System.out.println("Average:" + avg);
		System.out.println("");
	    }
		
		String s = "上記はFOR LOOPでARRAYの数を確認して計算している";
		System.out.println("文字の長さを以下で確認可能");
		System.out.println(s.length());			//文字の長さの時は（）を付ける
		System.out.println("");
		
		
		//拡張FOR LOOP --- for(型 変数名:配列変数名)
		//PHPでいう、ダブルアローのような感じかな?
		int[] score_ex = {10,20,30,40,50,60};
		for (int value : score_ex) {
			System.out.println(value);
		}
		
		//二次元配列の例
		int [][] scores_dimention = new int [2][3];
		scores_dimention[0][0] = 40;
		scores_dimention[0][1] = 50;
		scores_dimention[0][2] = 60;
		scores_dimention[1][0] = 70;
		scores_dimention[1][1] = 80;
		scores_dimention[1][2] = 90;
		System.out.println("二次元配列からデータ取り出し");
		System.out.println(scores_dimention[0][1]);
		System.out.println(scores_dimention[1][1]);
		
	}

}
