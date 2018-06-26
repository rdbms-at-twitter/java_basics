package confirmation;

public class Confirm13 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        //addメソッドをMainで呼び出し。
		add(100,20);
		add(200,50);
		
		Yobidashi("Yobidashiファンクションをここで呼び出しています。");
				
	}
	
	//Mainから以下のaddメソッドを呼び出し
	public static void add(int x, int y) {
		int ans = x + y;
		System.out.println(x + "+" + y  + "=" + ans);
		
	}
	
	
	public static void Yobidashi(String name) {
		System.out.println(name + "をMainファンクションから呼び出しています。");
		
	}
	

}
