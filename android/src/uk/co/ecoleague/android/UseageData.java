package uk.co.ecoleague.android;

public class UseageData {
	private String user_key = "";
	private String date = "";
	private UseageBody body = null;

	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UseageBody getBody() {
		return body;
	}

	public void setBody(UseageBody body) {
		this.body = body;
	}
}
