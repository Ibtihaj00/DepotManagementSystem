package controller;

import model.Customer;
import model.Parcel;
import model.QueueOfCustomers;
import model.ParcelMap;
import utils.FileUtils;
import utils.Log;
import java.util.List;

public class Manager {
    private QueueOfCustomers customerQueue = new QueueOfCustomers();
    private ParcelMap parcelMap = new ParcelMap();
    private Log log = Log.getInstance();

    // Constructor
    public Manager() {
        initializeData("data/customers.txt", "data/parcels.txt");
    }

    // This method is responsible for loading data into the customerQueue and parcelMap
    public void initializeData(String customerFilePath, String parcelFilePath) {
        List<Customer> customers = FileUtils.readCustomerData(customerFilePath);
        List<Parcel> parcels = FileUtils.readParcelData(parcelFilePath);

        // Add customers to the customerQueue
        for (Customer customer : customers) {
            customerQueue.addCustomer(customer);
        }

        // Add parcels to the parcelMap
        for (Parcel parcel : parcels) {
            parcelMap.addParcel(parcel);
        }

        log.addEvent("Data initialized from files: " + customerFilePath + " and " + parcelFilePath);
    }

    // Process the next customer in the queue
    public void processNextCustomer() {
        Customer customer = customerQueue.removeCustomer();
        if (customer != null) {
            Parcel parcel = parcelMap.findParcelByID(customer.getParcelId());
            if (parcel != null) {
                Worker worker = new Worker();
                worker.processCustomer(customer, parcel);
                log.addEvent("Processed customer: " + customer.getName() + " for parcel " + parcel.getId());
            } else {
                log.addEvent("Parcel not found for customer: " + customer.getName());
            }
        } else {
            log.addEvent("No customers in queue.");
        }
    }

    // Method to get the displayable customer queue
    public String getCustomerQueueDisplay() {
        return customerQueue.toString();
    }

    // Method to get the displayable parcel list
    public String getParcelListDisplay() {
        return parcelMap.toString();
    }

    // Method to calculate the fee based on parcel details
    public double calculateFee(Customer customer, Parcel parcel) {
        double fee = 0.0;
        if (parcel != null) {
            // Example Fee Calculation Logic
            double weightFee = parcel.getWeight() * 1.5; // Fee per kg
            double sizeFee = parcel.getLength() * parcel.getWidth() * parcel.getHeight() * 0.02; // Fee based on volume
            double daysFee = parcel.getDaysInDepot() * 0.5; // Fee per day in depot

            fee = weightFee + sizeFee + daysFee;

            // Example discount logic for specific customer types or parcel IDs
            if (customer.getParcelId().startsWith("C")) {
                fee *= 0.9; // 10% discount for "C" type parcels
            }
        }
        return fee;
    }

    // Method to calculate and display the fee for the next customer in the queue
    public String calculateAndDisplayFee() {
        Customer customer = customerQueue.peekCustomer(); // Peek at the next customer without removing
        if (customer != null) {
            Parcel parcel = parcelMap.findParcelByID(customer.getParcelId());
            if (parcel != null) {
                double fee = calculateFee(customer, parcel);
                return "Fee for " + customer.getName() + ": " + fee;
            } else {
             //   return "Parcel not found for customer: " + customer.getName();
                double fee = calculateFee(customer, parcel);

                return "Fee for " + customer.getName() + ": $10.0"  ;

            }
        }
        return "No customers in queue.";
    }
}
