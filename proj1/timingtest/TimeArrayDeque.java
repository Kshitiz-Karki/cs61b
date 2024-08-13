package timingtest;
import deque.ArrayDeque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeArrayDeque {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        //array list for 'Ns'
        AList<Integer> Ns = new AList<>();
        int i = 1000;
        while (i <= 128000) {
            Ns.addLast(i);
            i *= 2;
        }

        //array list for 'times'
        AList<Double> times = new AList<>();
        //array list for 'opCounts'
//        AList<Integer> opCounts = new AList<>();
        for (int j = 0; j < Ns.size(); j++) {
            ArrayDeque<Integer> arrElems = new ArrayDeque<>();
            int k = 1;
            Stopwatch sw = new Stopwatch();
            while (k <= Ns.get(j)) {
                arrElems.addLast(k);
                k++;
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        printTimingTable(Ns, times, Ns);
    }
}
