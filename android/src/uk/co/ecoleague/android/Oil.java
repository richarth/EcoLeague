package uk.co.ecoleague.android;

public class Oil extends Fuel {
	private FuelData fuelData = null;

	public FuelData getOil() {
		return fuelData;
	}

	public void setOil(FuelData fuelData) {
		this.fuelData = fuelData;
	}
}
