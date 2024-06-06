public class LuxuryCar extends Car {
    private double luxuryTax; // Additional attribute for luxury tax

    // Constructor for the LuxuryCar class
    // Takes carId, brand, model, basePricePerDay, and luxuryTax as parameters
    public LuxuryCar(String carId, String brand, String model, double basePricePerDay, double luxuryTax) {
        // Calls the constructor of the superclass (Car) with the provided parameters
        super(carId, brand, model, basePricePerDay);
        this.luxuryTax = luxuryTax; // Initializes the luxury tax
    }

    // Override the calculatePrice method to include luxury tax in the price calculation
    @Override
    public double calculatePrice(int rentalDays) {
        double basePrice = super.calculatePrice(rentalDays); // Calculate base price using superclass method
        return basePrice + (basePrice * luxuryTax); // Add luxury tax to the base price
    }

    // Getter method for luxury tax
    public double getLuxuryTax() {
        return luxuryTax;
    }
}