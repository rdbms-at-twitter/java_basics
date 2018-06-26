package confirmation;

public class Confirm22 {
	String name;
	int staffid;
	String email;

	public void sayhello() {
		System.out.println("Hello " + this.name);
	}
		
	public Confirm22(String name, int staffid, String email) {
		super();
		this.name = name;
		this.staffid = staffid;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Confirm22 sugiyama = new Confirm22("Shinya Sugiyama",12345,"shinya.sugiyama@test.com");
		//sugiyama.sayhello();
		//System.out.println(sugiyama.name);

		System.out.println("【社員情報】");
		System.out.println("名前： " + sugiyama.getName());
		System.out.println("社員番号： " + sugiyama.getStaffid());
		System.out.println("メールアドレス：　" + sugiyama.getEmail());
		
	}

}
