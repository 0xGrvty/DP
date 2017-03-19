package dp;

import java.awt.List;
import java.util.ArrayList;
import dp.City;

public class DP {
	private static City NY;
	private static City LA;
	private static City DEN;
	private static ArrayList<String> plan;
	
	public DP() {
		
		// Since we don't need these arrays after we use them
		// to initialize the cities, these are temporary when we call
		// the DP constructor
		Integer[] opCostNY = {
				8, 3, 10, 43,
				15, 48, 5, 40,
				20, 30, 28, 24
		};
		Integer[] reloCostNY = { 0, 20, 15 };
		
		Integer[] opCostLA = {
				18, 1, 35, 18,
				10, 19, 18, 10,
				8, 5, 8, 20
		};
		Integer[] reloCostLA = { 20, 0, 10 };
		
		Integer[] opCostDEN = {
				40, 5, 8, 13,
				21, 12, 4, 27,
				25, 10, 5, 15
		};
		Integer[] reloCostDEN = { 15, 10, 0 };
		
		// Initialize the cities, then we are done with the constructor
		NY = new City(opCostNY, reloCostNY, "NY");
		LA = new City(opCostLA, reloCostLA, "LA");
		DEN = new City(opCostDEN, reloCostDEN, "DEN");
		plan = new ArrayList<String>(); // Final output path of the plan
	}
	
	public static void main (String[] args) {
		DP dp = new DP();
	}

}
