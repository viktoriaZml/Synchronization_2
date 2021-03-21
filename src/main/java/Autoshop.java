import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Autoshop {
  private final int maxNumberOfCars = 10;
  private int numberOfCarsSold = 0;
  private final int realeaseTime = 500;
  private final int sellTime = 2000;
  // Производитель
  Manufacturer manufacturer = new Manufacturer(this);
  List<Car> cars = new ArrayList<>(maxNumberOfCars);

  public void releaseCar() {
    for (int i = 0; i < maxNumberOfCars; i++) {
      try {
        Thread.sleep(realeaseTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      manufacturer.releaseCar();
    }
  }

  public void sellCar() {
    while (numberOfCarsSold < maxNumberOfCars) {
      try {
        System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
        Car car = manufacturer.sellCar();
        if (car != null)
          numberOfCarsSold++;
        Thread.sleep(new Random().nextInt(sellTime));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  List<Car> getCars() {
    return cars;
  }

  public int getNumberOfCarsSold() {
    return numberOfCarsSold;
  }

  public int getMaxNumberOfCars() {
    return maxNumberOfCars;
  }
}
