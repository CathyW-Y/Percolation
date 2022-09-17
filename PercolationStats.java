import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] numberOfBoxTillPercolation;

    private int gridsize;
    private int trials;


    public PercolationStats(int n, int trials) {
        validate(n, trials);
        gridsize = n;
        numberOfBoxTillPercolation = new double[trials];
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            Percolation a = new Percolation(gridsize);
            int opensites = 0;
            while (!a.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                if (!a.isOpen(row, col)) {
                    a.open(row, col);
                    opensites++;
                }
            }
            double frac = (double) opensites / (n * n);
            numberOfBoxTillPercolation[i] = frac;

        }
    }

    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Illegal input for n or T.");
    }

    public double mean() {
        return StdStats.mean(numberOfBoxTillPercolation);


    }

    public double stddev() {
        return StdStats.stddev(numberOfBoxTillPercolation);

    }

    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));

    }

    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));

    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
//        Stopwatch ab = new Stopwatch();
        PercolationStats perc = new PercolationStats(n, trials);
//        double time = ab.elapsedTime();
//        StdOut.println(time);
        StdOut.println("mean                    =" + perc.mean());
        StdOut.println("stddev                  =" + perc.stddev());
        StdOut.println("95% confidence interval =" + "[" + perc.confidenceLow() + ", " + perc.confidenceHigh() + "]");

    }
}
