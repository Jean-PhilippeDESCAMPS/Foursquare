package fr.koolicar.koolicarapi;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean-philippedescamps on 20/12/2016.
 */

public class FoursquareService extends Service {

    private static final String TAG = "FoursquareService";
    static FoursquareService foursquareService;

    static String CLIENT_ID = "LT0D0D2BSZ40MGND1OPVKYZP0QXLNWCSZPEPLDWPJ51XNDQM";
    static String CLIENT_SECRET = "TMRJXJSIEEIBL42LL0AH30B4DS3EWVPU0HKBVQKMTIYZOQPZ";
    static String FOURSQUARE_ENDPOINT = "https://api.foursquare.com/v2/venues/search";

    public interface ServiceHandler {
        void onRetrivedVenues(List<Venue> venues);
    }

    public ServiceHandler serviceHandler;

    public FoursquareService(Context context, ServiceHandler serviceHandler) {
        this.context = context;
        this.serviceHandler = serviceHandler;
    }

    public static FoursquareService newInstance(Context context, ServiceHandler serviceHandler) {
        if (foursquareService == null) {
            foursquareService = new FoursquareService(context, serviceHandler);
        }
        return foursquareService;
    }

    @Override
    protected List doInBackground(String... params) {
        try {
            String path = FOURSQUARE_ENDPOINT + "?client_id=" + CLIENT_ID +
                    "&client_secret=" + CLIENT_SECRET + "&ll=45.5333759,-73.62132600000001&v=" + Utils.now("yyyyMMdd");
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String jsonString = stringBuilder.toString();
            Log.e(TAG, "Response from url " + url.toString() + " : " + jsonString);
            if (jsonString != null) {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONObject responseObject = jsonObject.getJSONObject("response");
                JSONArray venuesArray =  responseObject.getJSONArray("venues");
                Gson gson = new Gson();
                List<Venue> venues = new ArrayList<Venue>(venuesArray.length());
                for (int i = 0; i < venuesArray.length(); i++) {
                    JSONObject venueObject = venuesArray.getJSONObject(i);
                    Venue venue = gson.fromJson(venueObject.toString(), Venue.class);
                    venues.add(venue);
                }

                return venues;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
        if (list != null) {
            if (serviceHandler != null) {
                serviceHandler.onRetrivedVenues(list);
            }
        }
    }
}
