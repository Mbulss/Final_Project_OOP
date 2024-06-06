public interface Vehicle {
    // Get the vehicle ID
    String getId();

    // Get the vehicle brand
    String getBrand();

    // Get the vehicle model
    String getModel();

    // Calculate the rental price based on the number of days
    double calculatePrice(int rentalDays);

    // Check if the vehicle is available for rent
    boolean isAvailable();

    // Mark the vehicle as rented
    void rent();

    // Mark the vehicle as returned
    void returnVehicle();
}
