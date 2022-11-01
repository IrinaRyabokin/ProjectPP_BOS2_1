import java.util.concurrent.CountDownLatch;

public class SumThread implements Runnable{
    CountDownLatch synch;
    long mas[];
    int pos1, pos2;

    SumThread(long m[], int p1, int p2, CountDownLatch s)
    {
        mas = m;
        pos1 = p1;
        pos2 = p2;
        synch = s;
    }

    public void run()
    {
        mas[pos1] = mas[pos1]+mas[pos2];
        synch.countDown();
    }
}
