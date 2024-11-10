package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Parcel;

public class FileUtils {

    // Method to read customer data
    public static List<Customer> readCustomerData(String fileName) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split by comma and trim spaces
                String[] data = line.split(",");
                if (data.length == 3) {
                    int seqNo = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String parcelId = data[2].trim();
                    customers.add(new Customer(seqNo, name, parcelId));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Method to read parcel data
    // In your FileUtils class
    public static List<Parcel> readParcelData(String filePath) {
        List<Parcel> parcels = new ArrayList<>();
        // Code to read from the file
        // Example of reading a parcel's data
        // Assuming the file contains these fields in order: id, daysInDepot, weight, dimensions, description, length, width, height, status
        String parcelId = "P001";  // Placeholder for actual file reading
        int daysInDepot = 5;
        double weight = 2.5;
        String dimensions = "20x15x10";
        String description = "Electronics";
        double length = 20.0;
        double width = 15.0;
        double height = 10.0;
        String status = "In Depot";

        // Create a new Parcel with all required parameters
        Parcel parcel = new Parcel(parcelId, daysInDepot, weight, dimensions, description, length, width, height, status);
        parcels.add(parcel);

        return parcels;
    }

}
