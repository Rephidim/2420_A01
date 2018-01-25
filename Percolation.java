import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * TODO side-to-side open() works to open individual sits in 
 * grid of specified size, plus extra entry at start + end.
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
		//TODO open site (i,j) if it is not open already
		if (a[xyConv(i,j)] == 0) {
			a[xyConv(i,j)] = 1;
		}
		if (a[xyConv(i,j)] == 1 && a[xyConv(i-1,j)] == 1) {
			uf.union(xyConv(i,j), xyConv(i-1,j));
		}
		if (a[xyConv(i,j)] == 1 && a[xyConv(i+1,j)] == 1) {
			uf.union(xyConv(i,j), xyConv(i+1,j));
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
		//TODO is site (i) full?
		if (uf.connected(xyConv(i,j),0)) {
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
	
	/**
	 * For testing purposes only.
	 * @param args
	 */
	public static void main(String[] args) {
		Percolation p = new Percolation(3);
	}
}
