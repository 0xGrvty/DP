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
	private static Integer n = 11;
	private static Integer OverallCost = 0;
	private static Integer StationaryTime = 0;
	private static Integer currBestDifference = 0;

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
		int hi = recurse(NY, 0);
		System.out.println(hi);

		/*

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

		*/

	}

	public static int recurse(City state, int current) {
		int CurrentCost = state.getOpCosts()[current];
		StationaryTime++;
		System.out.println(CurrentCost);
		if (current == n) {
			OverallCost += CurrentCost;
			return OverallCost;
		} 


		int NYcost = cities.get(0).getOpCosts()[current + 1] + state.getReloCosts()[0];
		int LAcost = cities.get(1).getOpCosts()[current + 1] + state.getReloCosts()[1];
		int DENcost = cities.get(2).getOpCosts()[current + 1] + state.getReloCosts()[2];


		if (state.getCityName().equals("NY")) {
			if (NYcost < LAcost && NYcost < DENcost) {
				recurse(NY, current + 1);
			} else if (LAcost < DENcost) {
				System.out.println("switch LA");
				recurse(LA, current + 1);
				CurrentCost += state.getReloCosts()[1];
			} else {
				checkValidity(NY, DEN, current, 0, 0);
				System.out.println("switch DEN");
				recurse(DEN, current + 1);
				CurrentCost += state.getReloCosts()[2];
			}
		} else if (state.getCityName().equals("LA")) {
			if (LAcost < NYcost && LAcost < DENcost) {
				recurse(LA, current + 1);
			} else if (NYcost < DENcost) {
				System.out.println("switch NY");
				recurse(NY, current + 1);
				CurrentCost += state.getReloCosts()[0];
			} else {
				System.out.println("switchDEN");
				recurse(DEN, current + 1);
				CurrentCost += state.getReloCosts()[2];
			}
		} else {
			if (DENcost < NYcost && DENcost < LAcost) {
				recurse(DEN, current + 1);
			} else if (NYcost < LAcost) {
				System.out.println("switch NY");
				recurse(NY, current + 1);
				CurrentCost += state.getReloCosts()[0];
			} else {
				System.out.println("switch LA");
				recurse(LA, current + 1);
				CurrentCost += state.getReloCosts()[1];
			}
		}
		
		OverallCost += CurrentCost;
		return OverallCost;
	}

	public static void checkValidity(City from, City to, int current, int movingCost, int stationary) {
		int currBestDifference = from.getOpCosts()[current];
		int currentPlaceholder = to.getOpCosts()[current];
		System.out.println("yo");
		System.out.println(currBestDifference);
		System.out.println(currentPlaceholder);
		while (current > 0) {
			int fromCost = from.getOpCosts()[current];
			int toCost = to.getOpCosts()[current];
			if (toCost < fromCost) {
				if (currBestDifference > fromCost - toCost) {
					currBestDifference = fromCost - toCost;
					currentPlaceholder = current - 1;
				}
			}
			current--;
		}
		System.out.println(currentPlaceholder);
		System.out.println(currBestDifference);
	}

}
