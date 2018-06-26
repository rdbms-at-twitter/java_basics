package confirmation;

public class Confirm21 {
	String name;
	int staffid;
	String email;

	public void sayhello() {
		System.out.println("Hello " + this.name);
	}
		
	public Confirm21(String name, int staffid, String email) {
		super();
		this.name = name;
		this.staffid = staffid;
		this.email = email;
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Confirm21 sugiyama = new Confirm21("Shinya Sugiyama",12345,"shinya.sugiyama@test.com");
		sugiyama.sayhello();
		//System.out.println(sugiyama.name);

	}

}
