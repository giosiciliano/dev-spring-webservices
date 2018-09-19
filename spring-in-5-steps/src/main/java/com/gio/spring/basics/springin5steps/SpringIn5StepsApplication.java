package com.gio.spring.basics.springin5steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// spring boot app automatically scans the packages/sub-packages for beans
@SpringBootApplication
public class SpringIn5StepsApplication {
	
	/*
	 * USING SPRING
	 * what are the beans? using @Component
	 * - BinarySearchImpl, BubbleSortAlgorithm, QuickSortAlgorithm
	 * 
	 * what are the dependencies? using @Autowired
	 * - SortAlgorithm
	 * 
	 * where to search for beans? using @SpringBootApplication 
	 * - com.gio.spring.basics.springin5steps 
	 * - no need to add ComponentScan because beans are in this package/sub-package
	 */
	public static void main(String[] args) {
		
		// LOOSELY coupled in PURE JAVA
		//BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSortAlgorithm());
		
		// LOOSELY coupled in SPRING
		// Get bean from Application Context
		ApplicationContext appContext =
				SpringApplication.run(SpringIn5StepsApplication.class, args);
		BinarySearchImpl binarySearch = appContext.getBean(BinarySearchImpl.class);
		
		int result = binarySearch.binarySearch(new int[] {12, 4, 5}, 3);
		System.out.println(result);

	}
}
