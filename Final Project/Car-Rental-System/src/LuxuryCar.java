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

//LuxuryCar: A subclass of Car that adds the luxuryTax attribute and overrides the calculatePrice() method to include a luxury tax.

//How its works
//For luxury vehicles, the system adds the luxury tax to the base rental price before applying the loyalty discount (if applicable).


//Luxury tax is added to the rental cost of luxury vehicles, making the rental cost higher than that of regular vehicles.
//The LuxuryCar class overrides the calculatePrice(int rentalDays) method from the Car class to include the luxury tax in the rental price.
//When calculating the rental price, this method first calls the calculatePrice method from the Car class to get the base price, then adds the specified luxury tax percentage.
//For example, if the luxury tax is 15%, the total rental price will be the base price plus 15% of that base price.