package uk.co.ecoleague.android;

import uk.co.richarth.android.utils.JSONUtils;
import uk.co.richarth.android.utils.RequestUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterActivity extends Activity implements OnClickListener {
	private static final String PREFS_NAME = "EcoLeagueSettings";
	private int carCount = 1;
	private double lat = 0d;
	private double lon = 0d;
	private String json = "";
	private RequestUtils requestUtils = null;
	private JSONUtils jsonUtils = null;
	
	public RegisterActivity() {
		jsonUtils = new JSONUtils();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		TextView policyLink = (TextView) findViewById(R.id.policyLink);
		Linkify.addLinks(policyLink, Linkify.ALL);
		Linkify.addLinks(policyLink, Linkify.WEB_URLS);

		Button submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(this);

		Button addCar = (Button) findViewById(R.id.addCar);
		addCar.setOnClickListener(this);

		Spinner gas_unit_spinner = (Spinner) findViewById(R.id.gasUnits);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> gas_unit_adapter = ArrayAdapter
				.createFromResource(this, R.array.gas_units_array,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		gas_unit_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		gas_unit_spinner.setAdapter(gas_unit_adapter);

		Spinner gas_supplier_spinner = (Spinner) findViewById(R.id.gasSupplier);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> gas_supplier_adapter = ArrayAdapter
				.createFromResource(this, R.array.gas_supplier_array,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		gas_supplier_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		gas_supplier_spinner.setAdapter(gas_supplier_adapter);

		Spinner electric_supplier_spinner = (Spinner) findViewById(R.id.electricSupplier);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> electric_supplier_adapter = ArrayAdapter
				.createFromResource(this, R.array.electric_supplier_array,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		electric_supplier_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		electric_supplier_spinner.setAdapter(electric_supplier_adapter);

		Spinner oil_unit_spinner = (Spinner) findViewById(R.id.oilUnits);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> oil_unit_adapter = ArrayAdapter
				.createFromResource(this, R.array.oil_units_array,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		oil_unit_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		oil_unit_spinner.setAdapter(oil_unit_adapter);

		Spinner car_1_fuel_type_spinner = (Spinner) findViewById(R.id.fuelTypeInput1);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_1_fuel_adapter = ArrayAdapter
				.createFromResource(this, R.array.fuel_types,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_1_fuel_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_1_fuel_type_spinner.setAdapter(car_1_fuel_adapter);

		Spinner car_1_size_spinner = (Spinner) findViewById(R.id.carSizeInput1);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_1_size_adapter = ArrayAdapter
				.createFromResource(this, R.array.car_sizes,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_1_size_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_1_size_spinner.setAdapter(car_1_size_adapter);

		Spinner car_2_fuel_type_spinner = (Spinner) findViewById(R.id.fuelTypeInput2);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_2_fuel_adapter = ArrayAdapter
				.createFromResource(this, R.array.fuel_types,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_2_fuel_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_2_fuel_type_spinner.setAdapter(car_2_fuel_adapter);

		Spinner car_2_size_spinner = (Spinner) findViewById(R.id.carSizeInput2);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_2_size_adapter = ArrayAdapter
				.createFromResource(this, R.array.car_sizes,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_2_size_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_2_size_spinner.setAdapter(car_2_size_adapter);

		Spinner car_3_fuel_type_spinner = (Spinner) findViewById(R.id.fuelTypeInput3);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_3_fuel_adapter = ArrayAdapter
				.createFromResource(this, R.array.fuel_types,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_3_fuel_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_3_fuel_type_spinner.setAdapter(car_3_fuel_adapter);

		Spinner car_3_size_spinner = (Spinner) findViewById(R.id.carSizeInput3);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_3_size_adapter = ArrayAdapter
				.createFromResource(this, R.array.car_sizes,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_3_size_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_3_size_spinner.setAdapter(car_3_size_adapter);

		Spinner car_4_fuel_type_spinner = (Spinner) findViewById(R.id.fuelTypeInput4);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_4_fuel_adapter = ArrayAdapter
				.createFromResource(this, R.array.fuel_types,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_4_fuel_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_4_fuel_type_spinner.setAdapter(car_4_fuel_adapter);

		Spinner car_4_size_spinner = (Spinner) findViewById(R.id.carSizeInput4);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> car_4_size_adapter = ArrayAdapter
				.createFromResource(this, R.array.car_sizes,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		car_4_size_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		car_4_size_spinner.setAdapter(car_4_size_adapter);
	}

	public void onStart() {
		super.onStart();

		getLocation();
	}

	private void getLocation() {
		new LocationTask().execute();
	}

	public void onClick(View arg0) {
		if (arg0 == findViewById(R.id.submit)) {
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();

			EditText nameInput = (EditText) findViewById(R.id.nameInput);
			editor.putString("name", nameInput.getText().toString());

			EditText emailInput = (EditText) findViewById(R.id.emailInput);
			editor.putString("email", emailInput.getText().toString());

			EditText mobileInput = (EditText) findViewById(R.id.mobileInput);
			editor.putString("mobile", mobileInput.getText().toString());

			CheckBox hasGas = (CheckBox) findViewById(R.id.gasCheckBox);
			editor.putBoolean("hasGas", hasGas.isChecked());

			Spinner gasUnit = (Spinner) findViewById(R.id.gasUnits);
			editor.putString("gasUnits", gasUnit.getSelectedItem().toString());

			Spinner gasSupplier = (Spinner) findViewById(R.id.gasSupplier);
			editor.putString("gasSupplier", gasSupplier.getSelectedItem().toString());

			CheckBox hasElectric = (CheckBox) findViewById(R.id.electricCheckBox);
			editor.putBoolean("hasElectric", hasElectric.isChecked());

			Spinner electricSupplier = (Spinner) findViewById(R.id.electricSupplier);
			editor.putString("electricSupplier", electricSupplier.getSelectedItem()
					.toString());

			CheckBox hasOil = (CheckBox) findViewById(R.id.oilCheckBox);
			editor.putBoolean("hasOil", hasOil.isChecked());

			Spinner oilUnit = (Spinner) findViewById(R.id.oilUnits);
			editor.putString("oilUnits", oilUnit.getSelectedItem().toString());

			CheckBox hasCoal = (CheckBox) findViewById(R.id.coalCheckBox);
			editor.putBoolean("hasCoal", hasCoal.isChecked());

			Spinner car1FuelType = (Spinner) findViewById(R.id.fuelTypeInput1);
			editor.putString("car1FuelType", car1FuelType.getSelectedItem().toString());

			Spinner car1Size = (Spinner) findViewById(R.id.carSizeInput1);
			editor.putString("car1Size", car1Size.getSelectedItem().toString());

			Spinner car2FuelType = (Spinner) findViewById(R.id.fuelTypeInput2);
			editor.putString("car2FuelType", car2FuelType.getSelectedItem().toString());

			Spinner car2Size = (Spinner) findViewById(R.id.carSizeInput2);
			editor.putString("car2Size", car2Size.getSelectedItem().toString());

			Spinner car3FuelType = (Spinner) findViewById(R.id.fuelTypeInput3);
			editor.putString("car3FuelType", car3FuelType.getSelectedItem().toString());

			Spinner car3Size = (Spinner) findViewById(R.id.carSizeInput3);
			editor.putString("car3Size", car3Size.getSelectedItem().toString());

			Spinner car4FuelType = (Spinner) findViewById(R.id.fuelTypeInput4);
			editor.putString("car4FuelType", car4FuelType.getSelectedItem().toString());

			Spinner car4Size = (Spinner) findViewById(R.id.carSizeInput4);
			editor.putString("car4Size", car4Size.getSelectedItem().toString());

			editor.putInt("carCount", carCount);

			editor.commit();
			
			new RegisterTask().execute();
		} else if (arg0 == findViewById(R.id.addCar)) {
			carCount++;

			switch (carCount) {
			case 2:
				TextView car2Label = (TextView) findViewById(R.id.car_label2);
				car2Label.setVisibility(View.VISIBLE);

				LinearLayout car2FuelContainer = (LinearLayout) findViewById(R.id.car_fuel_container2);
				car2FuelContainer.setVisibility(View.VISIBLE);

				LinearLayout car2SizeContainer = (LinearLayout) findViewById(R.id.car_size_container2);
				car2SizeContainer.setVisibility(View.VISIBLE);
				break;
			case 3:
				TextView car3Label = (TextView) findViewById(R.id.car_label3);
				car3Label.setVisibility(View.VISIBLE);

				LinearLayout car3FuelContainer = (LinearLayout) findViewById(R.id.car_fuel_container3);
				car3FuelContainer.setVisibility(View.VISIBLE);

				LinearLayout car3SizeContainer = (LinearLayout) findViewById(R.id.car_size_container3);
				car3SizeContainer.setVisibility(View.VISIBLE);
				break;
			case 4:
				TextView car4Label = (TextView) findViewById(R.id.car_label4);
				car4Label.setVisibility(View.VISIBLE);

				LinearLayout car4FuelContainer = (LinearLayout) findViewById(R.id.car_fuel_container4);
				car4FuelContainer.setVisibility(View.VISIBLE);

				LinearLayout car4SizeContainer = (LinearLayout) findViewById(R.id.car_size_container4);
				car4SizeContainer.setVisibility(View.VISIBLE);
				break;
			}
		}
	}

	private class RegisterTask extends AsyncTask<Void, Void, Void> {
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
		}

		protected void onPostExecute(Void param) {
			// the user was registered proceed to the submit screen
			if (requestUtils.getResponseCode() == 200) {
				RegisterResponse rsp = (RegisterResponse) jsonUtils.fromJSONString(requestUtils.getContent(), RegisterResponse.class);
				if (rsp.getStatus().equals("OK")) {
					SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();

					editor.putString("user_key", rsp.getUser_key());
					editor.commit();
					
					Intent intent = new Intent(RegisterActivity.this, SubmitActivity.class);
					startActivity(intent);
				}
			}
		}
	}
	
	private class LocationTask extends AsyncTask<Void, Void, Void> {
	    protected Void doInBackground(Void... params) {
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

			String locationProvider = LocationManager.GPS_PROVIDER;

			Location lastKnownLocation = locationManager
					.getLastKnownLocation(locationProvider);

			if (lastKnownLocation != null) {
				lat = lastKnownLocation.getLatitude();
				lon = lastKnownLocation.getLongitude();
			} else {
				locationProvider = LocationManager.NETWORK_PROVIDER;
				lastKnownLocation = locationManager
						.getLastKnownLocation(locationProvider);

				if (lastKnownLocation != null) {
					lat = lastKnownLocation.getLatitude();
					lon = lastKnownLocation.getLongitude();
				} else {
					locationProvider = LocationManager.PASSIVE_PROVIDER;

					lastKnownLocation = locationManager
							.getLastKnownLocation(locationProvider);

					if (lastKnownLocation != null) {
						lat = lastKnownLocation.getLatitude();
						lon = lastKnownLocation.getLongitude();
					}
				}
			}
			
			return null;
	    }
	    
	    protected void onPostExecute(Void param) {
	    }
	}
}