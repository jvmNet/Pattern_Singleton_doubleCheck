package multithreading;

public class SingletonExample {
    private static Singleton example = null;
    private static final int SIZE_THREAD = 1000;
    private static Singleton[] array = null;
    private static Thread[] threads = null;

    public static Singleton getExample() {return example;}

    public static Singleton[] getArray(){
        return array;
    }

    public static Thread[] getThreads() {return threads;}

    public static void main(String[] args) throws InterruptedException {

        multiThread();

        System.out.println(Singleton.getCounter());
        
        singleThread();

        System.out.println(Singleton.getCounter());

    }

    public static void singleThread() {

        array = new Singleton[SIZE_THREAD];
        for(int i = 0; i < array.length; i++) {
            array[i] = Singleton.getSingleton();
        }
    }

    public static void multiThread() throws InterruptedException {

        threads = new Thread[SIZE_THREAD];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ThreadRun());
            threads[i].start();
        }
        for(int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

    }

    public static void printArray(Object[] array) {
        for(Object mass: array) System.out.println(mass);

        System.out.println(array.length);
    }
}

class ThreadRun implements Runnable {

    @Override
    public void run() {
        Singleton.getSingleton();
    }
}

class Singleton {
    private static final Object sync = new Object();
    private static int counter = 0;
    private static volatile Singleton instance = null;

    private Singleton() {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static Singleton getSingleton() {
        if(instance == null) {
            synchronized (sync) {
                if(instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
