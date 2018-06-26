package confirmation;

public class Confirm01 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int a;
		a = 100;
		a++;
		System.out.println(a);
		
		++a;
		System.out.println(a);
		
		int b = 10;
		int c = 10;
		System.out.println(++b + 50);  //61 ++を他の演算子と扱う時は注意
		System.out.println(c++ + 50);  //60
		
		String d = "今年は" + 20180610; //自動型変換
		System.out.println(d);
		
		String e = "スギヤマ";
        System.out.print("My Name is"); //改行しません。
        System.out.print(e);
        System.out.print("です");
        
        int f = 5;
        int g = 3;
        int h = Math.max(f, g);         //比較して大きい数値を選択
        System.out.println("比較実験:" + 
        a + "と" + b + "とで大きい方は。。。" + h);      
        
        String age = "46";
        int i = Integer.parseInt(age);  //文字列を数値に変換
        System.out.println("あなたは来年、" + (i + 1) + "歳になりますね。");
        
        int j = new java.util.Random().nextInt(100);   //上限を100とした乱数発行例
        System.out.println("乱数を発行します：" + j + "が発行されました。");
        
        
        
	}

}
