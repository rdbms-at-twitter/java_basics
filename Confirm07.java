package confirmation;

public class Confirm07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int weight;
		weight = 60;
		if (weight == 60) {
		System.out.println(weight);			//数値比較
		} else {
		System.out.println("Over Weight");
		}
		
		int kisuu = 3;
		if (kisuu %2 == 1) {				//奇数確認
		System.out.println("奇数です");
	    } else {
	    System.out.println("偶数です");	
	    }
		
		int age1 = 10;
		int age2 = 30;
		if (age1 * age2 * 2 > 60);
		System.out.println("OLD");

		String name = "山";
		if (name.equals("杉")) {				//文字列比較
		System.out.println("同じです"); 
		} else {
		System.out.println("違います");	
		}
		
		
		// If ELSEでの選択
		System.out.println("【メニュー】 1:検索 2:登録 3:削除 4:変更 >");
		int selected = new java.util.Scanner(System.in).nextInt(); //nextInt(数値)とnextLine(文字)
		
		if (selected == 1) {
			System.out.println("検索します");
		}
		
		else if (selected == 2) {
			System.out.println("登録します");
		}
		
		else if (selected == 3) {
			System.out.println("削除します");
		}
		
		else if (selected == 4) {
			System.out.println("変更");
		}
		
		else {
			System.out.println("Out of Scope");
		}
		
		
			
		//CASE文での選択肢

        System.out.print(" [メニュー]  1:検索 2:登録 3:削除 4:変更 > ");
        int selected2 = new java.util.Scanner(System.in).nextInt();
        
        switch (selected2) {
        case 1:
        	System.out.println("検索します");
        	break;
        case 2:
        	System.out.println("登録します");
        	break;
        case 3:
        	System.out.println("削除します");
        	break;
        case 4:
        	System.out.println("変更します");
        	break;
        }
		
		
		
		
	}

}