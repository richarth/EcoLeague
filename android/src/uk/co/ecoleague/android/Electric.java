package uk.co.ecoleague.android;

public class Electric extends Fuel {
	private FuelData fuelData = null;

	public FuelData getElectric() {
		return fuelData;
	}

	public void setElectric(FuelData fuelData) {
		this.fuelData = fuelData;
	}
}
