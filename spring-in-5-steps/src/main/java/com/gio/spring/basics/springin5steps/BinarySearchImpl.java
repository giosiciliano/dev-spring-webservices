package com.gio.spring.basics.springin5steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {
	
	@Autowired
	private SortAlgorithm sortAlgorithm;
	
	// Constructor injection
	/*
	public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
		super();
		this.sortAlgorithm = sortAlgorithm;
	}
	*/

	// Setter injection (not needed - since the attribute is autowired)
	/*
	public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
		this.sortAlgorithm = sortAlgorithm;
	}
	*/

	public int binarySearch(int[] numbers, int search) {
		
		/* sorting an array 
		 * using bubble sort or quick sort algorithm
		 * TIGHTLY coupled if logic is embedded or class called directly
		 * instead have the interface be set by a calling app w/ algorithm
		 * which will make this class LOOSELY coupled
		 */
		int[] sortedNumbers = sortAlgorithm.sort(numbers);
		System.out.println(sortAlgorithm);
		
		// search the array
		
		// return the result
		return 3;
	}


}
