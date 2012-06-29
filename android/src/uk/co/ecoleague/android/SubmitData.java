package uk.co.ecoleague.android;

public class SubmitData {
	private String command = "register";
	private UseageData args = null;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public UseageData getArgs() {
		return args;
	}

	public void setArgs(UseageData args) {
		this.args = args;
	}
}
