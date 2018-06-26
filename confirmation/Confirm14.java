package confirmation;

public class Confirm14 {

		public static int add(int x, int y) {
		int ans = x + y;
		return ans;
		}
		
		public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int ans = add(100,10);
		System.out.println("100 + 10 = " + ans);
		System.out.println("");
		int[] array = {1,2,3};
		printArray(array);
		}
		
		
		public static void printArray(int[] array) {
		for (int element : array) //拡張FORでELEMENTにデータを代入して終わるまでLOOP
				System.out.println(element);
		}
		
		
		

}
