import java.util.Arrays;
import java.util.Random;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private static boolean[] grid;
	private static WeightedQuickUnionUF percTracker, isFullTracker;
	static int gridWidth;
	
	
	public Percolation(int n) {
		//track grid width
		gridWidth = n;
		
		//create a grid of n by n size and a virtual top and bottom site
		grid = new boolean[(n * n) + 2];
	
		//create union object to track isfull and percolates
		percTracker = new WeightedQuickUnionUF((n * n) + 2);
		isFullTracker = new WeightedQuickUnionUF((n * n) + 1);
		
		//open the top site
		grid[0] = true;
	
	}
	
	
	public void open (int i, int j) {
		//open the space
		grid[convertXY(i, j)] = true;
		
		//if we are on the top row connect to the top and fill

		
		
		//open vertical
		try {
			if(grid[convertXY(i, j - 1)]) {
				percTracker.union(convertXY(i, j), convertXY(i, j - 1));
				isFullTracker.union(convertXY(i, j), convertXY(i, j - 1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			percTracker.union(convertXY(i, j), 0);
			isFullTracker.union(convertXY(i, j), 0);
		}
		try {
			if((grid[convertXY(i, j + 1)])){
				percTracker.union(convertXY(i, j), convertXY(i, j + 1));
				isFullTracker.union(convertXY(i, j), convertXY(i, j + 1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			percTracker.union(convertXY(i, j), grid.length - 1);
		}
		
		
		//open horizontal
		if(grid[convertXY(i - 1, j)] && convertXY(i, j) % gridWidth == 1){
			percTracker.union(convertXY(i, j), convertXY(i - 1, j));
			isFullTracker.union(convertXY(i, j), convertXY(i - 1, j));
			}
		if((grid[convertXY(i + 1, j)]) && convertXY(i, j) % gridWidth == 1) {
			percTracker.union(convertXY(i, j), convertXY(i + 1, j));
			isFullTracker.union(convertXY(i, j), convertXY(i + 1, j));
		}
		
		
		
	}
	
	public boolean isOpen(int i, int j) {
		return grid[convertXY(i, j)];
	}
	
	public boolean isFull(int i, int j) {
		return isFullTracker.connected(0, convertXY(i, j));
	}
	
	public boolean percolates() {
		return percTracker.connected(0, (grid.length - 1));
	}
	
	
	public int convertXY(int x, int y) {
		return (x)+  (y * this.gridWidth);
				
	}
	
	public int[] convertIndex(int index) {
		int[] orderedPair = {index % gridWidth, index / gridWidth};
		return orderedPair;
	}
	
	
	public void printGrid() {
		for(int i = 0; i < grid.length; i++) {
			if(grid[i]) {
				
				if(isFull(convertIndex(i)[0], convertIndex(i)[1])) {
					System.out.print("~ ");
				}else {
					System.out.print("O ");
				}
				
				
			}else {
				System.out.print("X ");
			}
			if(i % gridWidth == 0) {
				System.out.println();
			}
		}
	}
	
	public static void main(String [] args) {
		
		Percolation p = new Percolation(10);
		
		Random rand = new Random();	
		
		for (int i = 0; i <= 52; i++) {
			
			int randomX = rand.nextInt(gridWidth);
			int randomY = rand.nextInt(gridWidth);
			
			//if the chosen index is already open choose a different one.
			if(grid[p.convertXY(randomX, randomY)]) {
				i--;
			}else {
				//open the index
				p.open(randomX, randomY);
					}	
		}
		
		
//		PercolationStats ps = new PercolationStats(200, 100);
//		
//		System.out.println("mean: " + ps.mean());
//		System.out.println("stdDev: " + ps.stdDev());
//		System.out.println("low: " + ps.confidenceLow());
//		System.out.println("high: " + ps.confidenceHigh());
		
		
		p.printGrid();
		
	}

}




