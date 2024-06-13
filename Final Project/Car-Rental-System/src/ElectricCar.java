//A subclass of Car representing electric cars.
public class ElectricCar extends Car {

    // Constructor for the ElectricCar class
    // Takes carId, brand, model, and basePricePerDay as parameters
    public ElectricCar(String carId, String brand, String model, double basePricePerDay) {
        // Calls the constructor of the superclass (Car) with the provided parameters
        super(carId, brand, model, basePricePerDay);
    }
}