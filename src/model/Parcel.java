package model;

public class Parcel {
    private String parcelId;
    private int daysInDepot;
    private double weight;
    private String dimensions;
    private String id;
    private double length;  // length of the parcel
    private double height;  // height of the parcel
    private double width;   // width of the parcel
    private String description;
    private String status;  // For example




    public Parcel(String id, int daysInDepot, double weight, String dimensions, String description,
                  double length, double width, double height, String status) {
        this.id = id;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.dimensions = dimensions;
        this.description = description;
        this.length = length;
        this.width = width;
        this.height = height;
        this.status = status;
    }
// Getter and setter methods

    // Override the toString() method to provide a meaningful string representation
    @Override
    public String toString() {
        return "Parcel ID: " + id + ", Description: " + description + ", Weight: " + weight
                + "kg, Dimensions: " + length + "x" + width + "x" + height + " cm";
    }


    public double calculateFee() {
        // Base rate per weight unit (for example, per kg)
        double baseRate = 10.0; // Example base rate per kg
        double weightFee = baseRate * weight;

        // Size-based fee (you can use the parcel's volume or size)
        double sizeFee = (length * width * height) / 1000.0; // Example: fee based on volume in cubic meters

        // Additional fee for each day the parcel has been in the depot
        double dailyFee = 5.0; // Example daily fee
        double daysFee = dailyFee * daysInDepot;

        // Calculate total fee
        double totalFee = weightFee + sizeFee + daysFee;

        // Optional: Apply discount or surcharge based on customer or parcel ID
        if (id.startsWith("C")) {  // Example: discount for parcel IDs starting with 'C'
            totalFee *= 0.9; // 10% discount
        }

        return totalFee;
    }

    public String getParcelId() {
        return parcelId;
    }
    public String getId() {
        return parcelId;
    }
    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public void setDaysInDepot(int daysInDepot) {
        this.daysInDepot = daysInDepot;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }


}
