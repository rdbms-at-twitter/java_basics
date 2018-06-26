package confirmation;

public class Confirm16 {
	
	public static int[] makeArray(int size) {    //int配列を作成して戻るメソッド
		int[] newArray = new int[size];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = i;
		}
		return newArray;						//配列を戻す{0,1,2}
	}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int[] array = makeArray(3);				//3つのArrayを定義→makeArrayに渡して、0,1,2を取得(length=3)
		for (int i : array) {
		System.out.println(i);					//arrayの全要素を出力 0,1,2を出力
		}
	}
}
