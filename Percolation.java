import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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
//		if (a[xyConv(i+1,j)] == 1) {
//			uf.union(a[xyConv(i,j)],a[xyConv(i+1,j)]);
//		}
//		if (a[xyConv(i-1,j)] == 1) {
//			uf.union(a[xyConv(i,j)],a[xyConv(i-1,j)]);
//		}
//		if (a[xyConv(i,j+1)] == 1) {
//			uf.union(a[xyConv(i,j)],a[xyConv(i,j+1)]);
//		}
//		if (a[xyConv(i,j-1)] == 1) {
//			uf.union(a[xyConv(i,j)],a[xyConv(i,j-1)]);
//		}
	}
	
	/**
	 * Check if value at b[i] is true
	 * @param i
	 * @return x
	 */
	public boolean isOpen(int i, int j) {
		if (a[xyConv(i,j)] == 1) {
			return true;
		} else return false;
	}
	
	public boolean isFull(int i, int j) {
		//TODO is site (i) full?
		if (uf.connected(a[xyConv(i,j)],a[0])) {
			return true;
		}
		return false;
	}
	
	public boolean percolates() {
		return uf.connected(0, a.length - 1);
	}
	
	private int xyConv(int i, int j) {
		int x = (i+1) + (this.w * j);
		return x;
	}
	
	/**
	 * For testing purposes only.
	 * @param args
	 */
	public static void main(String[] args) {
		Percolation p = new Percolation(3);
		p.open(1, 1);
		p.open(0, 0);
		p.open(2, 2);
		for (int i = 1; i < 4; i++) {
			System.out.print(p.a[i]);
		}
		System.out.println();
		for (int i = 4; i < 7; i++) {
			System.out.print(p.a[i]);
		}
		System.out.println();
		for (int i = 7; i < 10; i++) {
			System.out.print(p.a[i]);
		}
		System.out.println();
		System.out.println("isOpen(0,0) " + p.isOpen(0, 0));
		System.out.println("isOpen(1,0) " + p.isOpen(1, 0));
		System.out.println("isOpen(2,0) " + p.isOpen(2, 0));
		System.out.println("isOpen(0,1) " + p.isOpen(0, 1));
		System.out.println("isOpen(1,1) " + p.isOpen(1, 1));
		System.out.println("isOpen(2,1) " + p.isOpen(2,1));
		System.out.println("isOpen(0,2) " + p.isOpen(0, 2));
		System.out.println("isOpen(1,2) " + p.isOpen(1, 2));
		System.out.println("isOpen(2,2) " + p.isOpen(2, 2));
	}
}
