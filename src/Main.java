public class Main {
    public static void main(String[] args) throws InterruptedException {

        // процесс -
        // поток (в рамках процесса потоков может быть несколько)
        // thread - поток (нити)
        // [+]
        // [-]
        // [+][-][+][-][+][-]......
        MyThread thread1 = new MyThread("+");  // 1 thread
        thread1.start();
        MyThread thread2 = new MyThread("-"); // 2 thread
        thread2.start();
        Thread.sleep(500);
        thread1.flag = false;
        thread1.join(); // ждет завершение потока
        test("1 Stopped!");
    }

    public static final Object KEY = new Object();
    public static void test(String message) {
      //  synchronized(KEY) {
            try {
                System.out.print("[");
                Thread.sleep(1000);
                System.out.print(message);
                Thread.sleep(1000);
                System.out.print("]");
          //      KEY.notify(); // возобновить работу потока наход в режиме ожидания
          //      KEY.wait(); // поток находится в режиме ожидания
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
      //  }
    }
}
class MyThread extends Thread {
    private String mess;
    public boolean flag = true;
    MyThread(String m) {
        mess = m;
    }
    @Override
    public void run() {
        while(flag) {
            Main.test(this.mess);
        }
    }
}