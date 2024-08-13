package timingtest;
import deque.LinkedListDeque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeLinkedListDeque {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        //array list for 'Ns'
        AList<Integer> Ns = new AList<>();
        int i = 1000;
        while (i <= 128000) {
            Ns.addLast(i);
            i *= 2;
        }

        //array list for 'opCounts'
        AList<Integer> opCounts = new AList<>();
        for (int j = 0; j < Ns.size(); j++) {
            opCounts.addLast(10000);
        }

        //array list for 'times'
        AList<Double> times = new AList<>();
        for (int j = 0; j < Ns.size(); j++) {
            LinkedListDeque<Integer> list = new LinkedListDeque<>();
            int x = 1;
            while (x <= Ns.get(j)) {
                list.addLast(x);
                x++;
            }
            int k = 1;
            Stopwatch sw = new Stopwatch();
            while (k <= opCounts.get(j)) {
                list.getLast();
                k++;
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
