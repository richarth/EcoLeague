package uk.co.ecoleague.android;

public class Gas extends Fuel {
	private FuelData fuelData = null;

	public FuelData getGas() {
		return fuelData;
	}

	public void setGas(FuelData fuelData) {
		this.fuelData = fuelData;
	}
}
