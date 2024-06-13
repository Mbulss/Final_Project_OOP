/*
* Customer: Stores customer information such as ID, name, and rental count.
* Methods to increment rental count and check eligibility for loyalty discounts.
* */
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

/*
* Loyalty Discounts:
how it works
* When a customer rents a vehicle, the system checks if the customer is eligible for a loyalty discount and calculates the appropriate discount.
Loyalty discounts are provided to customers who frequently rent vehicles. This discount aims to incentivize customers to continue using the rental service.
*
In the code, the Customer class has the method isEligibleForLoyaltyDiscount() which checks if the customer qualifies for a discount based on the number of rentals they have completed.
If a customer has completed at least 3 rentals, they qualify for a discount.
*
The method getLoyaltyDiscountPercentage() returns the discount percentage based on the rental count. \
For example, customers who have completed 5 or more rentals will receive a 30% discount, while those with 3 or more rentals will receive a 20% discount.
*
In the rentVehicleMenu(Scanner scanner) method of the CarRentalSystem class,
* this discount is calculated and applied to the total rental price.*/