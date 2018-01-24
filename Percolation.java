import java.util.Arrays;
import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {
	private int[] a;
	
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
	}
	
	public void open(int i) {
		//TODO open site (row i) if it is not open already
		if (this.a[i] != 1) {
			this.a[i] = 1;
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
		boolean x = false;
		//TODO does the system percolate?
		QuickUnionUF uf = new QuickUnionUF(a.length);
		uf.connected(a[0], a[a.length-1]);
		return x;
	}
	
	private void getList() {
		System.out.println(Arrays.toString(this.a));
	}
	
	/**
	 * For testing purposes only. Will delete before deployment.
	 * @param args
	 */
	public static void main(String[] args) {
		Percolation p = new Percolation(4);
		p.getList();
		System.out.println(p.isOpen(1));
		p.open(1);
		System.out.println("opening 1.1");
		p.getList();
		System.out.println(p.isOpen(1));
		System.out.println("Percolates?");
		System.out.println(p.percolates());
		System.out.println("set all values in p[] to 1...");
		System.out.println("...");
		p.open(2);
		p.open(3);
		p.open(4);
		p.getList();
		System.out.println(p.percolates());
	}
}
