package target.cp.vehicle.parking;

import java.util.ArrayList;
import java.util.List;

import target.cp.vehicle.parking.enums.ColumnNames;
import target.cp.vehicle.parking.enums.RowNames;
import target.cp.vehicle.parking.enums.SlotValues;

public class Admin {
	
	static List<Level> levels = new ArrayList<>();
	
	// Need to initialize these values via constructor
	final static int totalLevels = 1;
	final static int FREQUENCY_INCENTIVES = 20;
	final static int SHARING_INCENTIVES = 25;
	final int BIKE_SLOTS = 6;
	final int CAR_SLOTS = 14;
	
	public int initializeLevel() {
		Level level = new Level();
		level.setBikeSlots(BIKE_SLOTS);
		level.setCarSlots(CAR_SLOTS);
		level.setBikeSlotsAvailability(5 * BIKE_SLOTS);
		level.setCarSlotsAvailability(2 * CAR_SLOTS);
		
		for(RowNames row : RowNames.values()) {
			for(ColumnNames col : ColumnNames.values()) {
				if((row.equals(RowNames.FORMAT) || row.equals(RowNames.NARRAT)) && 
						(col.equals(ColumnNames._ABLE) || col.equals(ColumnNames._IVE) || col.equals(ColumnNames._ING))) {
					level.bikeSummary.put(row.name()+col.name(), SlotValues.B0_B0.name());
				}
				else {
					level.carSummary.put(row.name()+col.name(), SlotValues.C0_C0.name());
				}
			}
		}
		System.out.println(level.carSummary);
		System.out.println(level.bikeSummary);
		levels.add(level);
		return levels.size()-1;
	}

}