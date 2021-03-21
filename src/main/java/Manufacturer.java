import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Manufacturer {
  private final Autoshop autoshop;
  private Lock lock;
  private Condition condition;

  public Manufacturer(Autoshop autoshop) {
    this.autoshop = autoshop;
    lock = new ReentrantLock(true);
    condition = lock.newCondition();
  }

  public void releaseCar() {
    lock.lock();
    try {
      autoshop.getCars().add(new Car());
      System.out.printf("Производитель %s выпустил 1 авто\n", Thread.currentThread().getName());
      condition.signal();
    } finally {
      lock.unlock();
    }
  }

  public Car sellCar() {
    lock.lock();
    try {
      while (autoshop.getCars().size() == 0) {
        System.out.println("Машин нет");
        condition.await();
      }
      System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " ушел без покупки");
      return null;
    } finally {
      lock.unlock();
    }
    return autoshop.getCars().remove(0);
  }
}
