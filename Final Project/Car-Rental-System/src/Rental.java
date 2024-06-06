public class Rental {
    private Vehicle vehicle; // The vehicle being rented
    private Customer customer; // The customer renting the vehicle
    private int rentalDays; // The number of days the vehicle is rented for

    // Constructor to initialize the rental object
    public Rental(Vehicle vehicle, Customer customer, int rentalDays) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    // Getter for the vehicle
    public Vehicle getVehicle() {
        return vehicle;
    }

    // Getter for the customer
    public Customer getCustomer() {
        return customer;
    }

    // Getter for the rental days
    public int getRentalDays() {
        return rentalDays;
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + vehicle.getId() +
                ", Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel() +
                ", Rented by: " + customer.getName();
    }
}
