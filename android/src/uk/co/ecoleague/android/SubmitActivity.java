package uk.co.ecoleague.android;

import uk.co.richarth.android.utils.JSONUtils;
import uk.co.richarth.android.utils.RequestUtils;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SubmitActivity extends Activity implements OnClickListener {
	private static final String PREFS_NAME = "EcoLeagueSettings";
	private RequestUtils requestUtils = null;
	private JSONUtils jsonUtils = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit);

		Button submit = (Button) findViewById(R.id.buttonSubmit);
		submit.setOnClickListener(this);
    }

	public void onClick(View v) {
		if (v == findViewById(R.id.buttonSubmit)) {
			
			FuelsUseage fuelUse = new FuelsUseage();
			
			EditText coalUse = (EditText) findViewById(R.id.editTextCoal);
			fuelUse.setCoal(coalUse.getText().toString());
			
			EditText oilUse = (EditText) findViewById(R.id.editTextOil);
			fuelUse.setOil(oilUse.getText().toString());
			
			EditText gasUse = (EditText) findViewById(R.id.editTextGas);
			fuelUse.setGas(gasUse.getText().toString());
			
			EditText electricUse = (EditText) findViewById(R.id.editTextElectrcity);
			fuelUse.setElectric(electricUse.getText().toString());
			
			CarsUseage carUse = new CarsUseage();
			
			EditText car1Use = (EditText) findViewById(R.id.editTextCar1);
			carUse.setFuel("");
			carUse.setOdometer(car1Use.getText().toString());
			
			UseageBody body = new UseageBody();
			
			body.setFuels(fuelUse);
			
			body.setCars(carUse);
			
			UseageData useData = new UseageData();
			
			EditText date = (EditText) findViewById(R.id.editTextDate);
			useData.setDate(date.getText().toString());
			
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

			useData.setUser_key(settings.getString("user_key", ""));
			
			useData.setBody(body);
			
			SubmitData data = new SubmitData();
			
			data.setCommand("submit");
			data.setArgs(useData);

			jsonUtils = new JSONUtils();
			String json = jsonUtils.getJSONString(data);
			Log.d("Eco", json);
			
			//new RegisterTask().execute();
		}
		
	}

	/*private class RegisterTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... params) {
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			
			UserData user = new UserData();
			user.setName(settings.getString("name", ""));
			user.setEmail(settings.getString("email", ""));
			user.setEmail(settings.getString("mobile", ""));
			GeoLocation location = new GeoLocation();
			location.setLat(lat);
			location.setLon(lon);
			user.setGeolocation(location);
			Fuel[] fuels = new Fuel[4];
			if (settings.getBoolean("hasGas", false)) {
				Gas gas = new Gas();
				
				FuelData data = new FuelData();
				data.setSupplier(settings.getString("gasSupplier", ""));
				data.setUnits(settings.getString("gasUnits", ""));
				
				gas.setGas(data);
				
				fuels[0] = gas;
			}
			if (settings.getBoolean("hasElectric", false)) {
				Electric electric = new Electric();
				
				FuelData data = new FuelData();
				data.setSupplier(settings.getString("electricSupplier", ""));
				data.setUnits("kWh");
				
				electric.setElectric(data);
				
				fuels[1] = electric;
			}
			if (settings.getBoolean("hasOil", false)) {
				Oil oil = new Oil();
				
				FuelData data = new FuelData();
				data.setUnits(settings.getString("oilUnits", ""));
				
				oil.setOil(data);
				
				fuels[2] = oil;
			}
			if (settings.getBoolean("hasCoal", false)) {
				Coal coal = new Coal();
				
				FuelData data = new FuelData();
				data.setUnits("kg");
				
				coal.setCoal(data);
				
				fuels[3] = coal;
			}
			user.setFuels(fuels);
			
			int carCount = settings.getInt("carCount", 0);
			Car[] cars = new Car[carCount];
			for (int i = 1; i <= carCount; i++) {
				Car car = new Car();
				switch (i) {
				case 1:
					car.setFuel(settings.getString("car1FuelType", ""));
					car.setSize(settings.getString("carSizeInput1", ""));
					break;
				case 2:
					car.setFuel(settings.getString("car2FuelType", ""));
					car.setSize(settings.getString("carSizeInput2", ""));
					break;
				case 3:
					car.setFuel(settings.getString("car3FuelType", ""));
					car.setSize(settings.getString("carSizeInput3", ""));
					break;
				case 4:
					car.setFuel(settings.getString("car4FuelType", ""));
					car.setSize(settings.getString("carSizeInput4", ""));
					break;
				case 5:
					car.setFuel(settings.getString("car5FuelType", ""));
					car.setSize(settings.getString("carSizeInput5", ""));
					break;
				}
				cars[i - 1] = car;
			}
			user.setCars(cars);
			
			RegisterData data = new RegisterData();
			data.setCommand("register");
			data.setArgs(user);

			json = jsonUtils.getJSONString(data);

			requestUtils = new RequestUtils();
			
			try {
				requestUtils
						.sendJson(getString(R.string.registerUrl), json);
			} catch (Throwable t) {
			}
			
			return null;
		}*/

}
