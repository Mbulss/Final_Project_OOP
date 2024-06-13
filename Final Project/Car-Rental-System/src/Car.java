//Car: A class representing a regular car with basic attributes like carId, brand, model, basePricePerDay, and isAvailable.
public class Car implements Vehicle {
    // Attributes for the Car class
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    // Constructor to initialize a Car object
    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true; // Cars are available by default when created
    }

    // Getter method to get the car's ID
    @Override
    public String getId() {
        return carId;
    }

    // Getter method to get the car's brand
    @Override
    public String getBrand() {
        return brand;
    }

    // Getter method to get the car's model
    @Override
    public String getModel() {
        return model;
    }

    // Method to calculate the rental price based on the number of rental days
    @Override
    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    // Method to check if the car is available for rent
    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    // Method to mark the car as rented (unavailable)
    @Override
    public void     rent() {
        isAvailable = false;
    }

    // Method to mark the car as returned (available)
    @Override
    public void returnVehicle() {
        isAvailable = true;
    }
}

