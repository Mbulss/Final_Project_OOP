public class SUV extends Car {

    // Constructor for the SUV class
    // Takes carId, brand, model, and basePricePerDay as parameters
    public SUV(String carId, String brand, String model, double basePricePerDay) {
        // Calls the constructor of the superclass (Car) with the provided parameters
        super(carId, brand, model, basePricePerDay);
    }
}
