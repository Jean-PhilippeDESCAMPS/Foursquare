package fr.koolicar.foursquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.koolicar.koolicarapi.FoursquareService;
import fr.koolicar.koolicarapi.Venue;

public class MainActivity extends AppCompatActivity implements FoursquareService.ServiceHandler, AdapterView.OnItemClickListener {

    private List<Venue> venues;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FoursquareService foursquareService = FoursquareService.newInstance(this, this);
        foursquareService.execute("");

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onRetrivedVenues(List<Venue> venues) {
        this.venues = venues;
        List<String> values = new ArrayList<String>();
        for (Venue venue : venues) {
            values.add(venue.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Venue venue = venues.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("venue", venue);
        startActivity(intent);
    }
}
