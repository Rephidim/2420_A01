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
			uf.union(a[i],a[i-1]);
		}
		if (this.a[i] == 1 && this.a[i+1] == 1) {
			uf.union(a[i], a[i-1]);
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
		return uf.connected(a[0], a[a.length-1]);
	}
	
	private void getList() {
		System.out.println(Arrays.toString(this.a));
	}
	
	/**
	 * For testing purposes only.
	 * @param args
	 */
	public static void main(String[] args) {
		Percolation p = new Percolation(4);
		System.out.println("Percolation Array");
		p.getList();
		System.out.println(uf.count());
		System.out.println("is 0 connected to 5 ... should be false");
		System.out.println(uf.connected(p.a[0], p.a[5]));
		//TODO returns true
	}
}
