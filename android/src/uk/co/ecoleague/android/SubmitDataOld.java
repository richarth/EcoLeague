package uk.co.ecoleague.android;

import java.util.Date;

public class SubmitDataOld {
	private Date date;
	private int fuelsGas;
	private int fuelsElectricity;
	private int fuelsOils;
	private int fuelsCoal;
	private int car1Odometer;
	private int car2Odometer;
	private int car3Odometer;
	private int car4Odometer;
	private int publicTransportTrain;
	private int publicTransportBus;
	private int planeDomestic;
	private int planeInternetionalShort;
	private int planeInternetionalLong;
	
	SubmitDataOld(){
		//setting actual date
		setDate(new Date());
		
		//Loading last data from shared preferences
	}
	
	public boolean submitToServer(String UserKey){
		boolean result = false;
		//Calling server for saving data
		
		return result;
	}
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public int getFuelsGas(){
		return fuelsGas;
	}
	
	public void setFuelsGas(int gas){
		this.fuelsGas = gas;
	}

	public boolean isFuelsGas(){
		return false;// from shared preferences
	}
	
	public String getFuelsGasUnit(){
		return "";// from shared preferences
	}
	
	public int getFuelsElectricity(){
		return fuelsElectricity;
	}
	
	public void setFuelsElectricity(int electricity){
		this.fuelsElectricity = electricity;
	}
	
	public boolean isFuelsElectricity(){
		return false;//from shared preferences
	}
	
	public String getFuelsElectricityUnit(){
		return "";//from ...
	}
	
	public int getFuelsOils(){
		return fuelsOils;
	}
	
	public void setFuelsOils(int oils){
		this.fuelsOils = oils;
	}
	
	public boolean isFuelsOils(){
		return false;//...
	}
	
	public String getFuelsOilsUnit(){
		return "";//...
	}
	
	public int getFuelsCoal(){
		return fuelsCoal;
	}
	
	public void setFuelCoal(int coal){
		this.fuelsCoal = coal;
	}
	
	public int getCar1Odometer(){
		return car1Odometer;
	}
	
	public void setCar1Odometer(int odometer){
		this.car1Odometer = odometer;
	}
		
	public int getCar2Odometer(){
		return car2Odometer;
	}
	
	public void setCar2Odometer(int odometer){
		this.car2Odometer = odometer;
	}
	
	public int getCar3Odometer(){
		return car3Odometer;
	}
	
	public void setCar3Odometer(int odometer){
		this.car3Odometer = odometer;
	}
	
	public int getCar4Odometer(){
		return car4Odometer;
	}

	public void setCar4Odometer(int odometer){
		this.car4Odometer = odometer;
	}
	
	public int getPublicTransportTrain(){
		return publicTransportTrain;
	}
	
	public void setPublicTransportTrain(int train){
		this.publicTransportTrain = train;
	}
	
	public int getPublicTransportBus(){
		return publicTransportBus;
	}
	
	public void setPublicTransportBus(int bus){
		this.publicTransportBus = bus;
	}
	
	public int getPlaneDomestic(){
		return planeDomestic;
	}
	
	public void setPlaneDomestic(int plane){
		this.planeDomestic = plane;
	}
	
	public int getPlaneInternetionalShort(){
		return planeInternetionalShort;
	}
	
	public void setPlaneInternetionalShort(int plane){
		this.planeInternetionalShort = plane;
	}
	
	public int getPlaneInternetionalLong(){
		return planeInternetionalLong;
		}
	
	public void setPlaneInternetionalLong(int plane){
		this.planeInternetionalLong = plane;
	}
	
}
