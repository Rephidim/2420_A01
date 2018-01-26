import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * TODO open() works to open individual sites in 
 * grid.
 * Side-to-side filling works properly.
 * Top-Bottom filling not yet implemented.
 */

public class Percolation {
	private int[] a;
	private static WeightedQuickUnionUF uf;
	private int w;
	
	/**
	 * Constructor - creates a new boolean array of length N + 2, sets all values to false, then sets
	 * values at b[0] and b[N-1] to true.
	 * @param N
	 */
	public Percolation(int N) {
		this.a = new int[N*N + 2];
		this.w = N;
		Arrays.fill(a, 0);
		a[0] = 1;
		a[a.length -1] = 1;
		uf = new WeightedQuickUnionUF(a.length);
	}
	
	public void open(int i, int j) {
		int p = xyConv(i,j);
		if (a[p] == 0) {
			a[p] = 1;
		}
		if (j == 0 && a[p] == 1) {
			uf.union(0, p);
		}
		connectH(i, j);
		connectV(i, j);
		try {
			if (j == this.w-1 && a[p] == 1) {
				if (isFull(i-1,j) || isFull(i+1,j) || isFull(i,j-1)) {
					uf.union(p,a.length-1);
				}
			}
		} catch (IndexOutOfBoundsException e) {}
	}

	/**
	 * @param i
	 * @param j
	 */
	private void connectV(int i, int j) {
		int p = xyConv(i,j);
		try {
			if (a[p] == 1 && a[xyConv(i,j+1)] == 1) {
				uf.union(p,xyConv(i,j+1));
			}
		} catch (IndexOutOfBoundsException e) {}
		try {
			if (a[p] == 1 && a[xyConv(i,j-1)] == 1) {
				uf.union(p,xyConv(i,j-1));
			}
		} catch (IndexOutOfBoundsException e) {}
	}

	/**
	 * @param i
	 * @param j
	 */
	private void connectH(int i, int j) {
		int p = a[xyConv(i,j)];
		if (p == 1 && a[xyConv(i-1,j)] == 1) {
			uf.union(xyConv(i,j),xyConv(i-1,j));
		}	
		if (p == 1 && a[xyConv(i+1,j)] == 1) {
			uf.union(xyConv(i,j),xyConv(i+1,j));
		}
	}
	
	/**
	 * Check if value at b[i] is true
	 * @param i
	 * @return x
	 */
	public boolean isOpen(int i, int j) {
		if (a[xyConv(i,j)] == 1) {
			return true;
		} return false;
	}
	
	public boolean isFull(int i, int j) {
		//TODO is site (i,j) full?
		if (uf.connected(0,xyConv(i,j))) {
			return true;
		}
		return false;
	}
	
	public boolean percolates() {
		return uf.connected(0, a.length - 1);
	}
	
	private int xyConv(int i, int j) {
		return (i+1) + (this.w * j);
	}
}
