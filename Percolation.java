import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridsize;
    private int gridsquared;
    private WeightedQuickUnionUF WqfGrid;
    private WeightedQuickUnionUF WqfGrid1;

//    public QuickFindUF QfGrid;
//    public QuickFindUF QfGrid1;

    private int virtualTop;
    private int virtualBottom;
    private boolean[][] arr;
    private int opensites;

    public Percolation(int n) {
        gridsize = n;
        gridsquared = n * n;
        arr = new boolean[gridsize][gridsize];
        for (int i = 0; i < gridsize; i++)
            for (int j = 0; j < gridsize; j++) {
                arr[i][j] = false;
            }
        WqfGrid = new WeightedQuickUnionUF(gridsquared + 2);
        WqfGrid1 = new WeightedQuickUnionUF(gridsquared + 1);
        virtualTop = 0;
        virtualBottom = gridsquared + 1;
        opensites = 0;

//        QfGrid = new QuickFindUF(gridsquared + 2);
//        QfGrid1 = new QuickFindUF(gridsquared + 1);


    }

    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) {
            return;
        } else {
            arr[row][col] = true;
            int index = GetIndex(row, col);

            if (row == 0) {

                WqfGrid.union(index, virtualTop);
                WqfGrid1.union(index, virtualTop);

//                QfGrid.union(index, virtualTop);
//                QfGrid1.union(index, virtualTop);


            }
            if (row == gridsize - 1) {
                WqfGrid.union(index, virtualBottom);
//                QfGrid.union(index, virtualBottom);

            }
            if (row > 0 && isOpen(row - 1, col)) {
                WqfGrid.union(index, GetIndex(row - 1, col));
                WqfGrid1.union(index, GetIndex(row - 1, col));

//                QfGrid.union(index, GetIndex(row - 1, col));
//                QfGrid1.union(index, GetIndex(row - 1, col));

            }
            if (row < gridsize - 1 && isOpen(row + 1, col)) {
                WqfGrid.union(index, GetIndex(row + 1, col));
                WqfGrid1.union(index, GetIndex(row + 1, col));

//                QfGrid.union(index, GetIndex(row + 1, col));
//                QfGrid1.union(index, GetIndex(row + 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                WqfGrid.union(index, GetIndex(row, col - 1));
                WqfGrid1.union(index, GetIndex(row, col - 1));
//                QfGrid.union(index, GetIndex(row, col - 1));
//                QfGrid1.union(index, GetIndex(row, col - 1));
            }
            if (col < gridsize - 1 && isOpen(row, col + 1)) {
                WqfGrid.union(index, GetIndex(row, col + 1));
                WqfGrid1.union(index, GetIndex(row, col + 1));
//                QfGrid.union(index, GetIndex(row, col + 1));
//                QfGrid1.union(index, GetIndex(row, col + 1));
            }
        }
        opensites++;
        return;


    }


    private int GetIndex(int row, int col) {
        int index = 1;
        index += (row * gridsize);
        index += (col);
        return index;
    }


    private void validate(int row, int col) {
        if (row < 0 || row > gridsize - 1 || col < 0 || col > gridsize - 1)
            throw new IllegalArgumentException("Illegal row or col");
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return arr[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return WqfGrid1.find(GetIndex(row, col)) == WqfGrid1.find(virtualTop);
//        return QfGrid1.find(GetIndex(row, col)) == QfGrid1.find(virtualTop);
    }

    public int numberOfOpenSites() {
        return opensites;
    }

    public boolean percolates() {
        return WqfGrid.find(virtualBottom) == WqfGrid.find(virtualTop);
//        return QfGrid.find(virtualBottom) == QfGrid.find(virtualTop);
    }


    public static void main(String[] args) {
//        Percolation a = new Percolation(3);
//        a.open(0, 1);
//        a.open(1, 1);
//        a.open(2, 1);
//        StdOut.print(a.isFull(0, 1));
//        StdOut.print(a.isFull(1, 1));
//        StdOut.print(a.isFull(2, 1));
//        StdOut.print(a.percolates());
    }
}
