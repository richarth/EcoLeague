package uk.co.ecoleague.android;

public class RegisterResponse {
	private String status = "";
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	private String user_key = "";
}
