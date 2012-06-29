package uk.co.ecoleague.android;

public class UserData {
	private String name = "";
	private String email = "";
	private String mobile = "";
	private GeoLocation geolocation = null;
	private Fuel[] fuels = null;
	private Car[] cars = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public GeoLocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(GeoLocation location) {
		this.geolocation = location;
	}

	public Fuel[] getFuels() {
		return fuels;
	}

	public void setFuels(Fuel[] fuels) {
		this.fuels = fuels;
	}

	public Car[] getCars() {
		return cars;
	}

	public void setCars(Car[] cars) {
		this.cars = cars;
	}
}
