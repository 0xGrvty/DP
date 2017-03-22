package src;

public class City {
	private int cost;
	private String name;
	
	public City(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}
}