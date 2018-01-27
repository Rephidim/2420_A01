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
		grid[convertXY(i, j)] = true;
		
		if(j == 0) {
			percTracker.union(0,convertXY(i,j));
			isFullTracker.union(0,convertXY(i,j));
		}
		
		if(isOpen(i - 1, j)) {
			percTracker.union(convertXY(i -1, j), convertXY(i, j));
			if(isFull(i - 1, j)) {
				isFullTracker.union(convertXY(i -1, j), convertXY(i, j));
			}
		}
		if(isOpen(i + 1, j)) {
			percTracker.union(convertXY(i + 1, j), convertXY(i, j));
			if(isFull(i + 1, j)) {
				isFullTracker.union(convertXY(i + 1, j), convertXY(i, j));
			}
		}
		try {
			if(isOpen(i, j - 1)) {
				percTracker.union(convertXY(i, j - 1), convertXY(i, j));
				if(isFull(i, j - 1)) {
					isFullTracker.union(convertXY(i, j - 1), convertXY(i, j));
				}
			}
			
			if(isOpen(i, j + 1)) {
				percTracker.union(convertXY(i, j + 1), convertXY(i, j));
				if(isFull(i, j + 1)) {
					isFullTracker.union(convertXY(i, j + 1), convertXY(i, j));
				}
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			
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
		return (x+1)+  (y * this.gridWidth);
				
	}
	
	public int[] convertIndex(int index) {
		int[] orderedPair = {index % gridWidth, index / gridWidth};
		return orderedPair;
	}
	
	
	public void printGrid() {
		for(int i =0; i < grid.length; i++) {
			if(grid[i]) {
				if(isFull(convertIndex(i)[0], convertIndex(i)[1]));
			}
			if(i%gridWidth == 0) {
				System.out.println();
			}
		}
	}
	
	public static void main(String [] args) {
		
		Percolation p = new Percolation(10);
		
		Random rand = new Random();	
		
		for (int i = 0; i < 51; i++) {
			
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
		
		
		p.printGrid();
		
	}

}




