package dto;

public class User {

	private int Idx;
	private String Userid;
	private String Name;
	
	public User() {

	}

	public User(int idx, String userid, String name) {
		super();
		Idx = idx;
		Userid = userid;
		Name = name;
	}

	public int getIdx() {
		return Idx;
	}

	public void setIdx(int idx) {
		Idx = idx;
	}

	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "User [Idx=" + Idx + ", Userid=" + Userid + ", Name=" + Name + "]";
	}

	
	
}
