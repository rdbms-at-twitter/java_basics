package confirmation;

public class Confirmation06 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		for (int i = 1; i < 10; i++) {      //外部ループ1～9
			for (int j = 1; j < 10; j++) {  //内部ループは1～9
				System.out.print(i * j);    //計算 printlnだと文字毎の改行になってしまうのでprint
				System.out.print(" ");	    //空白 printlnだと文字毎の改行になってしまうのでprint
			}
			System.out.println("");         //改行
		}

	}

}
