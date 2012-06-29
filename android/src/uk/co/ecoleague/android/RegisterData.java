package uk.co.ecoleague.android;

public class RegisterData {
	private String command = "register";
	private UserData args = null;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public UserData getArgs() {
		return args;
	}

	public void setArgs(UserData args) {
		this.args = args;
	}
}
