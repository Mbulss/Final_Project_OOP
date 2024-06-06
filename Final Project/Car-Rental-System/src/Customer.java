public class Customer {
    private String customerId; // Unique identifier for the customer
    private String name; // Name of the customer
    private int rentalCount; // Number of rentals

    // Constructor to initialize the customer object
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.rentalCount = 0;
    }

    // Getter for customer ID
    public String getCustomerId() {
        return customerId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for rental count
    public int getRentalCount() {
        return rentalCount;
    }

    // Increment rental count
    public void incrementRentalCount() {
        rentalCount++;
    }

    // Check if customer is eligible for loyalty discount
    public boolean isEligibleForLoyaltyDiscount() {
        return rentalCount >= 3; // Example threshold
    }

    // Calculate discount percentage based on rental count
    public double getLoyaltyDiscountPercentage() {
        if (rentalCount >= 5) {
            return 0.30; // 30% discount for 5 or more rentals
        } else if (rentalCount >= 3) {
            return 0.20; // 20% discount for 3 or more rentals
        }
        return 0.0; // No discount for less than 3 rentals
    }

    // Method to update the customer's name
    public void setName(String name) {
        this.name = name;
    }
}
