package confirmation;

/*
 *   2で割っても3で割っても割り切れる数
 *   2で割り切れる、もしくは3で割り切れる数
 * 
 */

public class Confirm18 {

	public static void main(String[] args) {
	System.out.println("=============== AND ===================");
	int i = 1;
	while(i < 50) {
		if ((i % 2 == 0) && (i % 3 == 0)){   //2で割ってあまりが0で且つ3で割っても0の数
			System.out.println(i);
	}
		i++;  //インクリメント演算子
	}
	
	System.out.println("================ OR ===================");
	
	
	int x = 1;
	while(x < 50) {
		if ((x % 2 == 0 ) || (x % 3 == 0)) {  //2もしくは3で割り切れる数
			System.out.println(x);
		}
		x++;
	}
	
}	
}
