package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int[][] percolation;
    private int numOfOpenSites;
    private int bound;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be bigger than 0.");
        }

        this.percolation = new int[N][];
        this.numOfOpenSites = 0;
        this.bound = N;
    }

    public void open(int row, int col) {
        if (isOutOfBound(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (!isOpen(row, col)) {
            this.percolation[row][col] = 1;
            this.numOfOpenSites += 1;
        }
    }

    public boolean isOpen(int row, int col) {
        if (isOutOfBound(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        return this.percolation[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (isOutOfBound(row, col)) {
            throw new IndexOutOfBoundsException();
        }


    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        for (int row = 0; row < bound; row++) {
            if ()
        }
    }

    private boolean isOutOfBound(int row, int col) {
        return (row < 0 || row > bound - 1) || (col < 0 || col > bound - 1);
    }
}
