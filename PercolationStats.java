import java.util.Arrays;

import java.util.HashMap;
import java.util.Random;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats {
	
	Percolation p;
	
	Random rand = new Random();	
	
	int expNum;
	int openCounter;
	int[] openPerPerc;
	
	
	public PercolationStats(int N, int T){
		
		p = new Percolation(N);
		openPerPerc = new int[T];
		expNum = T;
		
		//perform T experiments opening a space at random until the system percolates
		for (int i = 0; i < T; i++) {
			openCounter = 0;
			
			
			while(!p.percolates()) {
				
				int randomX = rand.nextInt(N);
				int randomY = rand.nextInt(N);
				
				//if the chosen index is already open choose a different one.
				if(p.isOpen(randomX, randomY)) {
					i--;
				}else {
					//open the index
					p.open(randomX, randomY);
					openCounter++;
				}
			
			openPerPerc[i] = openCounter;
				
			}	
		}
	}
	
	public double mean() {
		double sum = 0;
		for(int n : openPerPerc) {
			sum += n;
		}
		
		return sum / openPerPerc.length;
	}
	
	public double stdDev() {
		double sumOfDevs = 0;
		
		for(int n : openPerPerc) {
			sumOfDevs += ((n - mean()) * (n - mean()));
		}
		
		return Math.sqrt(sumOfDevs / (expNum -1));
	}
	
	public double confidenceLow() {
		return mean() - ((1.96 * stdDev()) / Math.sqrt(expNum));
	}
	
	public double confidenceHigh() {
		return mean() + ((1.96 * stdDev()) / Math.sqrt(expNum));
	}





}