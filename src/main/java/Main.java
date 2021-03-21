public class Main {
  private static final int pauseTime = 5000;

  public static void main(String[] args) {
    final Autoshop autoshop = new Autoshop();
    ThreadGroup threadGroup = new ThreadGroup("group");

    Thread thread1 = new Thread(threadGroup, autoshop::sellCar, "Покупатель1");
    thread1.start();
    Thread thread2 = new Thread(threadGroup, autoshop::sellCar, "Покупатель2");
    thread2.start();
    Thread thread3 = new Thread(threadGroup, autoshop::sellCar, "Покупатель3");
    thread3.start();

    Thread thread4 = new Thread(threadGroup, autoshop::releaseCar, "Toyota");
    thread4.start();
    while (autoshop.getNumberOfCarsSold() < autoshop.getMaxNumberOfCars()) {
      try {
        Thread.sleep(pauseTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    threadGroup.interrupt();
    System.out.println("Продано " + autoshop.getNumberOfCarsSold() + " машин.");
  }

}
