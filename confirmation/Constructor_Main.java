package confirmation;

public class Constructor_Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		//コンストラクタが呼ばれている為、new宣言したと同時に値が自動的に設定される。
		Constructor_Sub hero = new Constructor_Sub("マキト");
		//Constructor_Sub hero = new Constructor_Sub();   //メソッドに引数が無い場合は、こちらでhに代入すればOK
		
		System.out.print(hero.name);
		System.out.println("");
		System.out.print(hero.hp);
		
		System.out.println("\n=====================");
		
		Constructor_Sub hero2 = new Constructor_Sub();
		System.out.print(hero2.name);
		System.out.println("");
		System.out.print(hero2.hp);
		
	}

}
