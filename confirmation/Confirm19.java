package confirmation;

public class Confirm19 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String title = "タイトル";
		String address = "shinya.sugiyama@test.com";
		String body = "こちらはメールのボディです。";
		email(title,address,body);
		
		System.out.println("");
		
		double triangleArea = calcTriangleArea(10.0, 5.0);
		System.out.println("三角形の面積：" + triangleArea + "平方cm");
		double circleArea = calcCircleArea(5.0);
		System.out.println("円の面積：" +  circleArea + "平方cm");	
		
	}
	
	 public static void email(String title, String address, String body) {
		 System.out.println(address + "にメール送信");
		 System.out.println("件名: " + title);
		 System.out.println("本文: " + body);
	 }
	 
	 
	 public static double calcTriangleArea(double bottom, double height) {
		 double area = (bottom * height) / 2;
		 return area;
	 }
	 
	 public static double calcCircleArea(double radius) {
		 double area = radius * radius * 3.14;
		 return area;
	 }
	 
}