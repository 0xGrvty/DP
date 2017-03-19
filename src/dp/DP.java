package dp;

import java.util.ArrayList;
import dp.City;

public class DP {
	private static City NY;
	private static City LA;
	private static City DEN;
	private static ArrayList<City> cities;
	private static ArrayList<String> plan;
	private static ArrayList<Integer> costs;
	private static Integer planCost;

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

		cities = new ArrayList<City>();
		cities.add(NY);
		cities.add(LA);
		cities.add(DEN);

		plan = new ArrayList<String>(); // Final output path of the plan
		costs = new ArrayList<Integer>(); // List of all possible costs
		planCost = 0; // The current plan's cost.
	}

	public static void main (String[] args) {
		DP dp = new DP();

		for (int i = 0; i < DP.cities.size(); i++) {
			DP.planCost = 0; // Reset the plan cost per city
			for (int j = 11; j >= 0; j--) { // Starting from end of plan
				Integer min = cities.get(i).getOpCosts()[j]; // Find min value (optimal for that path)
				for (int k = 0; k < 3; k++) {
					if (j - 1 < 0) { // If we get to month 0 (out of bounds)
						break;
					}
					else {
						// Find min
						if (DP.planCost + (cities.get(i).getOpCosts()[j - 1] + cities.get(i).getReloCosts()[k]) < min) {
							min = (cities.get(i).getOpCosts()[j - 1] + cities.get(i).getReloCosts()[k]);
						}
					}
				}
				
				if (j == 11) {
					DP.planCost += cities.get(i).getOpCosts()[j];
					DP.planCost += min;
					DP.plan.add(cities.get(i).getCityName());
					continue;  // Only want to add the last month once
				}
				else {
					DP.planCost += min;
					DP.plan.add(cities.get(i).getCityName());
				}
			}
			costs.add(planCost);
		}
		
		// sysout the list of costs.
		for (int i = 0 ; i < cities.size(); i++) {
			System.out.println(cities.get(i).getCityName() + ": " + costs.get(i));
		}

	}

}
