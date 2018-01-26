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
		if (a[xyConv(i,j)] == 0) {
			a[xyConv(i,j)] = 1;
		}
		if (j == 0 && a[xyConv(i,j)] == 1) {
			uf.union(0, xyConv(i,j));
		}
		if (a[xyConv(i,j)] == 1 && a[xyConv(i-1,j)] == 1) {
			uf.union(xyConv(i,j),xyConv(i-1,j));
		}	
		if (a[xyConv(i,j)] == 1 && a[xyConv(i+1,j)] == 1) {
			uf.union(xyConv(i,j),xyConv(i+1,j));
		}
		try {
			if (a[xyConv(i,j)] == 1 && a[xyConv(i,j+1)] == 1) {
				uf.union(xyConv(i,j),xyConv(i,j+1));
			}
		} catch (IndexOutOfBoundsException e) {}
		try {
			if (a[xyConv(i,j)] == 1 && a[xyConv(i,j-1)] == 1) {
				uf.union(xyConv(i,j),xyConv(i,j-1));
			}
		} catch (IndexOutOfBoundsException e) {}
		try {
			if (j == this.w-1 && a[xyConv(i,j)] == 1) {
				uf.union(xyConv(i,j),a.length-1);
			}
		} catch (IndexOutOfBoundsException e) {}
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
	
	/**
	 * For testing purposes only.
	 * @param args
	 */
//	public static void main(String[] args) {
//		Percolation p = new Percolation(3);
//		p.open(1, 1);
//		System.out.println(Arrays.toString(p.a));
//		System.out.println("IsOpen");
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 0),p.isOpen(1, 0),p.isOpen(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 1),p.isOpen(1, 1),p.isOpen(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 2),p.isOpen(1, 2),p.isOpen(2, 2));
//		System.out.println("Is Full");
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 0),p.isFull(1, 0),p.isFull(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 1),p.isFull(1, 1),p.isFull(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 2),p.isFull(1, 2),p.isFull(2, 2));
//		System.out.println("Percolates? " + p.percolates());
//		p.open(1, 0);
//		System.out.println(Arrays.toString(p.a));
//		System.out.println("IsOpen");
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 0),p.isOpen(1, 0),p.isOpen(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 1),p.isOpen(1, 1),p.isOpen(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 2),p.isOpen(1, 2),p.isOpen(2, 2));
//		System.out.println("Is Full");
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 0),p.isFull(1, 0),p.isFull(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 1),p.isFull(1, 1),p.isFull(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 2),p.isFull(1, 2),p.isFull(2, 2));
//		System.out.println("Percolates? " + p.percolates());
//		p.open(0, 1);
//		System.out.println(Arrays.toString(p.a));
//		System.out.println("IsOpen");
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 0),p.isOpen(1, 0),p.isOpen(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 1),p.isOpen(1, 1),p.isOpen(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 2),p.isOpen(1, 2),p.isOpen(2, 2));
//		System.out.println("Is Full");
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 0),p.isFull(1, 0),p.isFull(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 1),p.isFull(1, 1),p.isFull(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 2),p.isFull(1, 2),p.isFull(2, 2));
//		System.out.println("Percolates? " + p.percolates());
//		p.open(2, 1);
//		System.out.println(Arrays.toString(p.a));
//		System.out.println("IsOpen");
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 0),p.isOpen(1, 0),p.isOpen(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 1),p.isOpen(1, 1),p.isOpen(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 2),p.isOpen(1, 2),p.isOpen(2, 2));
//		System.out.println("Is Full");
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 0),p.isFull(1, 0),p.isFull(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 1),p.isFull(1, 1),p.isFull(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 2),p.isFull(1, 2),p.isFull(2, 2));
//		System.out.println("Percolates? " + p.percolates());
//		p.open(1, 2);
//		System.out.println(Arrays.toString(p.a));
//		System.out.println("IsOpen");
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 0),p.isOpen(1, 0),p.isOpen(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 1),p.isOpen(1, 1),p.isOpen(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isOpen(0, 2),p.isOpen(1, 2),p.isOpen(2, 2));
//		System.out.println("Is Full");
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 0),p.isFull(1, 0),p.isFull(2, 0));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 1),p.isFull(1, 1),p.isFull(2, 1));
//		System.out.printf("%b,%b,%b\n",p.isFull(0, 2),p.isFull(1, 2),p.isFull(2, 2));
//		System.out.println("Percolates? " + p.percolates());
//	}
}
