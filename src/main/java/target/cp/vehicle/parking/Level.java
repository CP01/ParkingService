package target.cp.vehicle.parking;

import java.util.LinkedHashMap;
import java.util.Map;

public class Level {

	Map<String, String> carSummary = new LinkedHashMap<>();
	Map<String, String> bikeSummary = new LinkedHashMap<>();
	private int carSlots;
	private int bikeSlots;
	private int carSlotsAvailability;
	private int bikeSlotsAvailability;
	
	public Map<String, String> getCarSummary() {
		return carSummary;
	}
	public Map<String, String> getBikeSummary() {
		return bikeSummary;
	}
	public int getCarSlots() {
		return carSlots;
	}
	public void setCarSlots(int carSlots) {
		this.carSlots = carSlots;
	}
	public int getBikeSlots() {
		return bikeSlots;
	}
	public void setBikeSlots(int bikeSlots) {
		this.bikeSlots = bikeSlots;
	}
	public int getCarSlotsAvailability() {
		return carSlotsAvailability;
	}
	public void setCarSlotsAvailability(int carSlotsAvailability) {
		this.carSlotsAvailability = carSlotsAvailability;
	}
	public int getBikeSlotsAvailability() {
		return bikeSlotsAvailability;
	}
	public void setBikeSlotsAvailability(int bikeSlotsAvailability) {
		this.bikeSlotsAvailability = bikeSlotsAvailability;
	}
}
