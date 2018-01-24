import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[] a;
	private static WeightedQuickUnionUF uf;
	
	/**
	 * Constructor - creates a new boolean array of length N + 2, sets all values to false, then sets
	 * values at b[0] and b[N-1] to true.
	 * @param N
	 */
	public Percolation(int N) {
		this.a = new int[N + 2];
		Arrays.fill(a, 0);
		a[0] = 1;
		a[a.length -1] = 1;
		uf = new WeightedQuickUnionUF(a.length);
	}
	
	public void open(int i) {
		//TODO open site (row i) if it is not open already
		if (this.a[i] != 1) {
			this.a[i] = 1;
		}
		if (this.a[i] == 1 && this.a[i-1] == 1) {
			uf.union(i,(i-1));
		}
		if (this.a[i] == 1 && this.a[i+1] == 1) {
			uf.union(i, (i+1));
		}
	}
	
	/**
	 * Check if value at b[i] is true
	 * @param i
	 * @return x
	 */
	public boolean isOpen(int i) {
		boolean x;
		if (this.a[i] == 1) {
			x = true;
		} else x = false;
		return x;
	}
	
	public boolean isFull(int i) {
		boolean x = false;
		//TODO is site (i) full?
		
		return x;
	}
	
	public boolean percolates() {
		//TODO does the system percolate?
		return uf.connected(0, a.length - 1);
	}
	
	/**
	 * For testing purposes only.
	 * @param args
	 */
	public static void main(String[] args) {
		Percolation p = new Percolation(4);
		System.out.println(p.percolates()); // Should be false
		p.open(1);
		p.open(2);
		System.out.println(p.percolates()); // Should be false
		p.open(3);
		p.open(4);
		System.out.println(p.percolates()); // Should be true
	}
}
