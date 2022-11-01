import java.util.concurrent.CountDownLatch;

class Main {
    public static void main(String[] args) {
        int size = 1024;
        long SumMas = 0;

        long[] mas = new long[size];

        for (int i = 0; i < size; i++)
            mas[i] = i;

        for (int i = 0; i < size; i++)
            SumMas = SumMas + mas[i];

        System.out.println("Сума в однопоточному режимі:");
        System.out.println(SumMas);

        System.out.println("Сума в багатопоточному режимі:");
        System.out.println(findArraySum(mas));
    }

    public static long findArraySum(long[] mas) {
        int size = mas.length;

        do
        {
            CountDownLatch synch = new CountDownLatch(size/2);
            for (int i = 0; i < size / 2; i++) {
                int end = size - 1 - i;
                Thread thread = new Thread(new SumThread(mas, i, end, synch));
                thread.start();
            }

            try {
                synch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            size = size / 2 + size % 2;
        } while (size > 1);

        return mas[0];
    }
}