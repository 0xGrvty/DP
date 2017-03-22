
import java.util.ArrayList;

public class DP {
	private ArrayList<ArrayList<City>> allPaths = new ArrayList<>();
	private ArrayList<ArrayList<City>> tempPaths = new ArrayList<>();


	public void calcCosts () {
		allPaths.add(new ArrayList<>());
		allPaths.add(new ArrayList<>());
		allPaths.add(new ArrayList<>());
		tempPaths.add(new ArrayList<>());
		tempPaths.add(new ArrayList<>());
		tempPaths.add(new ArrayList<>());

		Integer[][] OpCosts = {
				{8, 3, 10, 43, 15, 48, 5, 40, 20, 30, 28, 24},
				{18, 1, 35, 18, 10, 19, 18, 10, 8, 5, 8, 20},
				{40, 5, 8, 13, 21, 12, 4, 27, 25, 10, 5, 15}
		};

		Integer[][] RelocationCosts = {
				{0, 20, 15},
				{20, 0, 10},
				{15, 10, 0}
		};	

		int min, index = 0, cost;
		ArrayList<City> temp = new ArrayList<>();
		//		tempPaths = allPaths;
		for (int i = 11; i >= 0; i--) {
			tempPaths = (ArrayList<ArrayList<City>>) allPaths.clone();
			for (int j = 0; j < 3; j++) {
				System.out.println("Row: " + j);
				if (i == 11) {
					allPaths.get(0).add(new City("NY", OpCosts[0][i]));
					allPaths.get(1).add(new City("LA", OpCosts[1][i]));
					allPaths.get(2).add(new City("DEN", OpCosts[2][i]));
					System.out.println(allPaths.get(0));
					System.out.println(allPaths.get(1));
					System.out.println(allPaths.get(2));
					break;
				}

				min = Integer.MAX_VALUE;
				cost = 0;
				for (int k = 0; k < 3; k++) {
					System.out.println("City: " + k);
					System.out.println(OpCosts[j][i] + " " + RelocationCosts[j][k] + " " + pathSum(allPaths.get(k)));
					System.out.println(pathSum(allPaths.get(k)) + RelocationCosts[j][k] + OpCosts[j][i]);
					if ((pathSum(allPaths.get(k)) + RelocationCosts[j][k] + OpCosts[j][i]) < min) {
						min = pathSum(allPaths.get(k)) + RelocationCosts[j][k] + OpCosts[j][i];
						index = k;
						cost = OpCosts[j][i] + RelocationCosts[j][k];
						System.out.println("Index set to: " + index);				
					}
				}
				System.out.println("Min: " + min + "\n");

				temp = (ArrayList<City>) allPaths.get(index).clone();

				//				System.out.println(temp);
				System.out.println("Path adopted: " + allPaths.get(index));
				tempPaths.set(j, temp);

				if (j == 0) {
					tempPaths.get(j).add(new City("NY", cost));
				}
				else if (j == 1) {
					tempPaths.get(j).add(new City("LA", cost));
				}
				else if (j == 2) {
					tempPaths.get(j).add(new City("DEN", cost));
				}

//				System.out.println(pathSum(allPaths.get(j)) + "\n");
			}
			allPaths = (ArrayList<ArrayList<City>>) tempPaths.clone();
			
		}
	}

	public int pathSum(ArrayList<City> path) {
		int sum = 0;
		for (int i = 0; i < path.size(); i++) {
			sum += path.get(i).getCost();
		}
		return sum;
	}

	public static void main (String[] args) {
		DP dp = new DP();
		dp.calcCosts();
		int small = Integer.MAX_VALUE;
		int index = 0;
		ArrayList<City> temp = new ArrayList<>();
		
		for (int i = 0; i < dp.allPaths.size(); i++) {
			if (dp.pathSum(dp.allPaths.get(i)) < small) {
				small = dp.pathSum(dp.allPaths.get(i));
				index = i;
			}
		}
		System.out.println(small);
		for (int i = dp.allPaths.get(index).size() - 1; i >= 0; i--) {
			System.out.print(dp.allPaths.get(index).get(i).getName() + " ");
		}
	}
}



