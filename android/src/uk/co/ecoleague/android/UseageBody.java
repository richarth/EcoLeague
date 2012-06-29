package uk.co.ecoleague.android;

public class UseageBody {
	private FuelsUseage fuels = null;
	private CarsUseage cars = null;

	// private PublicTransportUseage = null;
	public FuelsUseage getFuels() {
		return fuels;
	}

	public void setFuels(FuelsUseage fuels) {
		this.fuels = fuels;
	}

	public CarsUseage getCars() {
		return cars;
	}

	public void setCars(CarsUseage cars) {
		this.cars = cars;
	}
}
