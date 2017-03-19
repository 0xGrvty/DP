package dp;

import java.util.ArrayList;

public class City {
	private Integer[] opCosts;
	private String cityName;
	private Integer[] reloCosts;
	
	public City(Integer[] opCosts, Integer[] reloCosts, String cityName) {
		this.opCosts = opCosts;
		this.cityName = cityName;
		this.reloCosts = reloCosts;
	}

	public Integer[] getOpCosts() {
		return opCosts;
	}
	public String getCityName() {
		return cityName;
	}
	public Integer[] getReloCosts() {
		return reloCosts;
	}
}
