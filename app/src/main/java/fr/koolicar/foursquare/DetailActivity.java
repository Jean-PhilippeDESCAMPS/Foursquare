package fr.koolicar.foursquare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.koolicar.koolicarapi.Location;
import fr.koolicar.koolicarapi.Venue;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("venue")) {
            Venue venue = extras.getParcelable("venue");
            TextView nameTextView = (TextView) findViewById(R.id.name_textview);
            if (venue.getName() != null) {
                nameTextView.setText(venue.getName());
            }
            Location location = venue.getLocation();
            TextView addressTextView = (TextView) findViewById(R.id.address_textview);
            if (location.getAddress() != null) {
                addressTextView.setText(location.getAddress());
            }
            TextView crossStreetTextView = (TextView) findViewById(R.id.crossStreet_textview);
            if (location.getCrossStreet() != null) {
                crossStreetTextView.setText(location.getCrossStreet());
            }
            TextView distanceTextView = (TextView) findViewById(R.id.distance_textview);
            if (location.getDistance() != null) {
                distanceTextView.setText(location.getDistance());
            }
            TextView cityTextView = (TextView) findViewById(R.id.city_textview);
            if (location.getCity() != null) {
                cityTextView.setText(location.getCity());
            }
            TextView countryTextView = (TextView) findViewById(R.id.country_textview);
            if (location.getCountry() != null) {
                countryTextView.setText(location.getCountry());
            }

        }
    }
}
