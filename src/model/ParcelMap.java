package model;

import java.util.Map;
import java.util.HashMap;

public class ParcelMap {
    private Map<String, Parcel> parcelMap; // Assuming parcelMap stores parcels by their ID

    // Constructor
    public ParcelMap() {
        this.parcelMap = new HashMap<>();
    }

    // Method to add a parcel to the map
    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
    }

    // Method to find a parcel by its ID
    public Parcel findParcelByID(String parcelId) {
        return parcelMap.get(parcelId);
    }

    // Override the toString() method to return a meaningful string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Parcel parcel : parcelMap.values()) {
            sb.append(parcel.toString()).append("\n");  // Append string representation of each parcel
        }
        return sb.toString();
    }
}
