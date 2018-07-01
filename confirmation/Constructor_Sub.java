package confirmation;

public class Constructor_Sub {

	int hp;
	String name;
	
	//コンストラクタ：メソッド名とクラス名が完全一致　＆　メソッドの宣言に戻り値が記述されていない。
	Constructor_Sub(String name) {
		this.hp = 1000;     //こちらのコンストラクタは常に同じ値
		this.name = name;   //こちらの名前は、CALLしたときに指定
	}
	
	//以下オーバーロードで同じ名前で因数が異なるメソッドを処理
	Constructor_Sub() {     
		this.hp = 1000;
		this.name = "ダミーデータ";
	}
		
}
