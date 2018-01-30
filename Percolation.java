import java.util.Random;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private static boolean[] grid;
	private static WeightedQuickUnionUF percTracker, isFullTracker;
	private static int gridWidth;
	
	
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
		int node = convertXY(i,j);
		//open the space
		grid[node] = true;
		
		//open vertical
		try {
			if(grid[convertXY(i, j - 1)]) {
				percTracker.union(node, convertXY(i, j - 1));
				isFullTracker.union(node, convertXY(i, j - 1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			// If y-1 is out of bounds, union to arbitrary top
			percTracker.union(node, 0);
			isFullTracker.union(node, 0);
		}
		try {
			if((grid[convertXY(i, j + 1)])){
				percTracker.union(node, convertXY(i, j + 1));
				isFullTracker.union(node, convertXY(i, j + 1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			// If y+1 is out of bounds, union to arbitrary bottom.
			percTracker.union(node, grid.length - 1);
		}
		
		
		//open horizontal
		if(grid[convertXY(i - 1, j)]){
			percTracker.union(node, convertXY(i - 1, j));
			isFullTracker.union(node, convertXY(i - 1, j));
			}
		if((grid[convertXY(i + 1, j)])) {
			percTracker.union(node, convertXY(i + 1, j));
			isFullTracker.union(node, convertXY(i + 1, j));
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
		return (x)+  (y * Percolation.gridWidth);
				
	}
	
	public int[] convertIndex(int index) {
		int[] orderedPair = {index % gridWidth, index / gridWidth};
		return orderedPair;
	}
	
	
	public void printGrid() {
		for(int i = 1; i < grid.length-1; i++) {
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
		
		p.printGrid();
		System.out.println("Percolates: " + p.percolates());
		
	}

}
