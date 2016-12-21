package fr.koolicar.koolicarapi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jean-philippedescamps on 20/12/2016.
 */

public class Venue implements Parcelable {

    String id;

    String name;

    Location location;

    public Venue(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Venue(Parcel source) {
        this.id = source.readString();
        this.name = source.readString();
        this.location = source.readParcelable(Location.class.getClassLoader());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeParcelable(location, flags);
    }

    public static final Parcelable.Creator<Venue> CREATOR
            = new Parcelable.Creator<Venue>() {

        @Override
        public Venue createFromParcel(Parcel source) {
            return new Venue(source);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[0];
        }
    };

}
