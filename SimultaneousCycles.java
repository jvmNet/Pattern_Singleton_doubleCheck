package multithreading;

public class SimultaneousCycles {
    private static String messageFirstCycle = null;
    private static long sizeSleepFirstCycle = 0;

    public static void setMessageFirstCycle(String messageFirstCycle) {
        SimultaneousCycles.messageFirstCycle = messageFirstCycle;
    }

    public static void setSizeSleepFirstCycle(long sizeSleepFirstCycle) {
        SimultaneousCycles.sizeSleepFirstCycle = sizeSleepFirstCycle;
    }

    public static String getMessageFirstCycle() {
        return messageFirstCycle;
    }

    public static long getSizeSleepFirstCycle() {
        return sizeSleepFirstCycle;
    }

    public static void main(String[] args) {

        SecondCycle secondCycle = new SecondCycle("Cycle - 2", 500);
        Thread thread = new Thread(secondCycle);
        thread.start();

        setMessageFirstCycle("Cycle - 1");
        setSizeSleepFirstCycle(500);
        for(int i = 0; i < 10; i++) {
            System.out.println(getMessageFirstCycle());
            try{
                Thread.sleep(getSizeSleepFirstCycle());
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static class SecondCycle implements Runnable {
        private String messageSecondCycle = null;
        private long sizeSleepSecondCycle = 0;

        public SecondCycle(String messageSecondCycle, long sizeSleepSecondCycle) {
            this.messageSecondCycle = messageSecondCycle;
            this.sizeSleepSecondCycle = sizeSleepSecondCycle;
        }

        @Override
        public void run() {
            for(int i = 0; i < 10; i++) {
                System.out.println(messageSecondCycle);
                try{
                Thread.sleep(sizeSleepSecondCycle);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
