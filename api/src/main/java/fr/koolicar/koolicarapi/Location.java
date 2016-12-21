package fr.koolicar.koolicarapi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jean-philippedescamps on 20/12/2016.
 */

public class Location implements Parcelable {

    String address;

    String crossStreet;

    String distance;

    String city;

    String state;

    String country;

    public Location(String address, String crossStreet, String distance, String city, String state, String country) {
        this.address = address;
        this.crossStreet = crossStreet;
        this.distance = distance;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Location(Parcel source) {
        this.address = source.readString();
        this.crossStreet = source.readString();
        this.distance = source.readString();
        this.city = source.readString();
        this.state = source.readString();
        this.country = source.readString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", crossStreet='" + crossStreet + '\'' +
                ", distance='" + distance + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(crossStreet);
        dest.writeString(distance);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
    }

    public static final Parcelable.Creator<Location> CREATOR
            = new Parcelable.Creator<Location>() {

        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[0];
        }
    };
}
