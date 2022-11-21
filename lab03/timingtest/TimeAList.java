package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int n = 1000; n < 1000000; n *= 2) {
            Ns.addLast(n);
            Stopwatch sw = new Stopwatch();
            int count = addData(n);
            times.addLast(sw.elapsedTime());
            opCounts.addLast(count);
        }

        printTimingTable(Ns, times, opCounts);
    }

    public static int addData(int n) {
        AList<Integer> lst = new AList<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += 1;
            lst.addLast(i);
        }
        return count;
    }
}
