package target.cp.vehicle.parking;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import target.cp.vehicle.parking.enums.OwnerType;
import target.cp.vehicle.parking.enums.RowNames;
import target.cp.vehicle.parking.enums.VehicleType;
import target.cp.vehicle.parking.pojos.Vehicle;
import target.cp.vehicle.parking.repos.VehicleRepo;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepo vehicleRepo;
	@Autowired
	private OwnerService ownerService; 
	
	public String getSlot(Vehicle vehicle, String ownerType) {
		boolean isElder = false;
		if(OwnerType.ROYAL.toString().equals(ownerType))
		{
			return getSlotForRoyal(vehicle);
		}
		else if(OwnerType.ELDER.toString().equals(ownerType)) {
			isElder = true;
		}
		Boolean slotFound = false;
		String slot = null;
		if(VehicleType.BIKE.name().equals(vehicle.getVehicleType())) {
			int curLevel = 0;
			for(Level level : Admin.levels) {
				if(level.getBikeSlotsAvailability() == 0) {
					if(curLevel == Admin.totalLevels-1) {
						//Try to occupy Car slots
					}
					else {
						curLevel++;
						continue;
					}
				}
				else {
					for(Map.Entry<String, String> entry : level.bikeSummary.entrySet()) {

						if(slotFound) {
							level.setBikeSlotsAvailability(level.getBikeSlotsAvailability()-1);
							break;
						}
						String value[] = entry.getValue().split(FrequentlyUsedConstants.UNDERSCORE);
						int bottomCount = Integer.parseInt(value[0].substring(1));
						int upperCount = Integer.parseInt(value[1].substring(1));
						if(bottomCount == FrequentlyUsedConstants.BOTTOM_RACK_BIKE_CAPACITY
								&& upperCount == FrequentlyUsedConstants.UPPER_RACK_BIKE_CAPACITY) {
							continue;
						}
						else {
							StringBuilder newValue = new StringBuilder("");
							if(!isElder && upperCount < FrequentlyUsedConstants.UPPER_RACK_BIKE_CAPACITY)
							{
								newValue.append(value[0]);
								newValue.append(FrequentlyUsedConstants.UNDERSCORE);
								newValue.append(FrequentlyUsedConstants.B);
								newValue.append(upperCount+1);
								vehicle.setIsUpperRack(true);
							}
							else if(bottomCount < FrequentlyUsedConstants.UPPER_RACK_BIKE_CAPACITY) {
								newValue.append(FrequentlyUsedConstants.B);
								newValue.append(bottomCount+1);
								newValue.append(FrequentlyUsedConstants.UNDERSCORE);
								newValue.append(value[1]);
								vehicle.setIsUpperRack(false);
							}
							else {
								continue;
							}
							level.bikeSummary.put(entry.getKey(), newValue.toString());
							slotFound = true;
							vehicle.setSlot(entry.getKey());
							vehicle.setLevel(curLevel);
							vehicle.setVehicleType(VehicleType.BIKE.name());
							System.out.println(level.bikeSummary);
						}
					}
				}
				if(slotFound) {
					break;
				}
				curLevel++;
			}
		}
		else if(VehicleType.CAR.name().equals(vehicle.getVehicleType())) {
			int curLevel = 0;
			for(Level level : Admin.levels) {
				if(level.getCarSlotsAvailability() == 0) {
					if(curLevel == Admin.totalLevels-1) {
						//Try to occupy Bike slots
					}
					else {
						curLevel++;
						continue;
					}
				}
				else {
					for(Map.Entry<String, String> entry : level.carSummary.entrySet()) {
						if(slotFound) {
							level.setCarSlotsAvailability(level.getCarSlotsAvailability()-1);
							break;
						}
						String value[] = entry.getValue().split(FrequentlyUsedConstants.UNDERSCORE);
						int bottomCount = Integer.parseInt(value[0].substring(1));
						int upperCount = Integer.parseInt(value[1].substring(1));
						if(bottomCount == FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK
								&& upperCount == FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK) {
							continue;
						}
						else {
							StringBuilder newValue = new StringBuilder("");
							if(!isElder && upperCount < FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK)
							{
								newValue.append(value[0]);
								newValue.append(FrequentlyUsedConstants.UNDERSCORE);
								newValue.append(FrequentlyUsedConstants.C);
								newValue.append(upperCount+1);
								vehicle.setIsUpperRack(true);
							}
							else if(bottomCount < FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK) {
								newValue.append(FrequentlyUsedConstants.C);
								newValue.append(bottomCount+1);
								newValue.append(FrequentlyUsedConstants.UNDERSCORE);
								newValue.append(value[1]);
								vehicle.setIsUpperRack(false);
							}
							else {
								continue;
							}
							level.carSummary.put(entry.getKey(), newValue.toString());
							slotFound = true;
							vehicle.setSlot(entry.getKey());
							vehicle.setLevel(curLevel);
							vehicle.setVehicleType(VehicleType.CAR.name());
							System.out.println(level.carSummary);
						}
					}
				}
				if(slotFound) {
					break;
				}
				curLevel++;
			}
		}

		if(slotFound) {
			if(vehicle.getIsRideShared() != null && vehicle.getIsRideShared()) {
				ownerService.addIncentivesForSharedRide(vehicle.getContactNumber());
			}
			vehicle.setInTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
			if(addVehicle(vehicle)) {
				slot = vehicle.getSlot();
			}
		}
		// If slot not available for elder person preference then automatically lowers the preference
		else if(isElder) {
			slot = getSlot(vehicle, OwnerType.NORMAL.toString());
		}
		
		return slot;
	}
	

	  
	  private String getSlotForRoyal(Vehicle vehicle) {

			Boolean slotFound = false;
			String slot = null;
			// For Royal Member, only upper bike slot will be provided.
			// As royal member required vacant adjacent slots, and only upper rack for bike having space for 3 bikes.
			// So we can allot middle position to Royal people
			if(VehicleType.BIKE.name().equals(vehicle.getVehicleType())) {
				int curLevel = 0;
				for(Level level : Admin.levels) {
					if(level.getBikeSlotsAvailability() == 0) {
						if(curLevel == Admin.totalLevels-1) {
							//Try to occupy Car slots
						}
						else {
							curLevel++;
							continue;
						}
					}
					else {
						for(Map.Entry<String, String> entry : level.bikeSummary.entrySet()) {

							if(slotFound) {
								level.setBikeSlotsAvailability(level.getBikeSlotsAvailability()-3);
								break;
							}
							String value[] = entry.getValue().split(FrequentlyUsedConstants.UNDERSCORE);
							int bottomCount = Integer.parseInt(value[0].substring(1));
							int upperCount = Integer.parseInt(value[1].substring(1));
							if(bottomCount == FrequentlyUsedConstants.BOTTOM_RACK_BIKE_CAPACITY
									&& upperCount == FrequentlyUsedConstants.UPPER_RACK_BIKE_CAPACITY) {
								continue;
							}
							else {
								StringBuilder newValue = new StringBuilder("");
								if(upperCount == 0)
								{
									newValue.append(value[0]);
									newValue.append(FrequentlyUsedConstants.UNDERSCORE);
									newValue.append(FrequentlyUsedConstants.B);
									newValue.append(upperCount+3);
									vehicle.setIsUpperRack(true);
								}
								else {
									continue;
								}
								level.bikeSummary.put(entry.getKey(), newValue.toString());
								slotFound = true;
								vehicle.setSlot(entry.getKey()+":ROYAL");
								vehicle.setLevel(curLevel);
								vehicle.setVehicleType(VehicleType.BIKE.name());
								System.out.println(level.bikeSummary);
							}
						}
					}
					if(slotFound) {
						break;
					}
					curLevel++;
				}
			}
			else if(VehicleType.CAR.name().equals(vehicle.getVehicleType())) {
				int curLevel = 0;
				for(Level level : Admin.levels) {
					if(level.getCarSlotsAvailability() == 0) {
						if(curLevel == Admin.totalLevels-1) {
							//Try to occupy Bike slots
						}
						else {
							curLevel++;
							continue;
						}
					}
					else {
						// First Looking for Bottom Slot
						String prevKey = null, prevPrevKey = null;
						for(Map.Entry<String, String> entry : level.carSummary.entrySet()) {
							if(slotFound) {
								level.setCarSlotsAvailability(level.getCarSlotsAvailability()-3);
								break;
							}
							String curKey = entry.getKey();
							// Can not adjust Royal Family these rows, due to Bike slots
							if(curKey.contains(RowNames.FORMAT.toString()) || curKey.contains(RowNames.NARRAT.toString())) {
								prevPrevKey = null;
								prevKey = null;
								continue;
							}
							// Continuous three slots should be in same row
							if(prevPrevKey != null) {
								if(!prevPrevKey.substring(0, prevPrevKey.indexOf("_")).equals(curKey.substring(0, prevPrevKey.indexOf("_")))) {
									prevPrevKey = null;
									prevKey = null;
								}
							}
							String value[] = entry.getValue().split(FrequentlyUsedConstants.UNDERSCORE);
							int bottomCount = Integer.parseInt(value[0].substring(1));
							int upperCount = Integer.parseInt(value[1].substring(1));
							if(bottomCount == FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK
									&& upperCount == FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK) {
								continue;
							}
							else {
								StringBuilder newValue = new StringBuilder("");
								if(bottomCount < FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK)
								{
									if(prevPrevKey == null) {
										prevPrevKey = entry.getKey();
										continue;
									}
									else if(prevKey == null) {
										prevKey = entry.getKey();
										continue;
									}
									newValue.append(FrequentlyUsedConstants.C);
									newValue.append(bottomCount+1);
									newValue.append(FrequentlyUsedConstants.UNDERSCORE);
									//newValue.append(value[1]);
								}
								else {
									prevPrevKey = null;
									prevKey = null;
									continue;
								}
								level.carSummary.put(entry.getKey(), newValue.toString()+value[1]);
								level.carSummary.put(prevKey, newValue.toString()+level.carSummary.get(prevKey).substring(3));
								level.carSummary.put(prevPrevKey, newValue.toString()+level.carSummary.get(prevKey).substring(3));
								slotFound = true;
								vehicle.setIsUpperRack(false);
								vehicle.setSlot(prevPrevKey + ":" + prevKey + ":" + entry.getKey());
								vehicle.setLevel(curLevel);
								vehicle.setVehicleType(VehicleType.CAR.name());
								System.out.println(level.carSummary);
							}
						}

						// If bottom slot not found then look for upper slot
						if(!slotFound) {
							prevKey = null;
							prevPrevKey = null;
							for(Map.Entry<String, String> entry : level.carSummary.entrySet()) {
								if(slotFound) {
									level.setCarSlotsAvailability(level.getCarSlotsAvailability()-1);
									break;
								}
								String curKey = entry.getKey();
								// Can not adjust Royal Family these rows, due to Bike slots
								if(curKey.contains(RowNames.FORMAT.toString()) || curKey.contains(RowNames.NARRAT.toString())) {
									prevPrevKey = null;
									prevKey = null;
									continue;
								}
								// Continuous three slots should be in same row
								if(prevPrevKey != null) {
									if(!prevPrevKey.substring(0, prevPrevKey.indexOf("_")).equals(curKey.substring(0, prevPrevKey.indexOf("_")))) {
										prevPrevKey = null;
										prevKey = null;
									}
								}
								String value[] = entry.getValue().split(FrequentlyUsedConstants.UNDERSCORE);
								int bottomCount = Integer.parseInt(value[0].substring(1));
								int upperCount = Integer.parseInt(value[1].substring(1));
								if(bottomCount == FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK
										&& upperCount == FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK) {
									continue;
								}
								else {
									StringBuilder newValue = new StringBuilder("");
									if(upperCount < FrequentlyUsedConstants.CAR_CAPACITY_PER_RACK)
									{
										if(prevPrevKey == null) {
											prevPrevKey = entry.getKey();
											continue;
										}
										else if(prevKey == null) {
											prevKey = entry.getKey();
											continue;
										}
										//newValue.append(value[0]);
										newValue.append(FrequentlyUsedConstants.UNDERSCORE);
										newValue.append(FrequentlyUsedConstants.C);
										newValue.append(upperCount + 1);
									}
									else {
										prevPrevKey = null;
										prevKey = null;
										continue;
									}
									level.carSummary.put(entry.getKey(), value[0] + newValue.toString());
									level.carSummary.put(prevKey, level.carSummary.get(prevKey).substring(0,2) + newValue.toString());
									level.carSummary.put(prevPrevKey, level.carSummary.get(prevKey).substring(0,2) + newValue.toString());
									slotFound = true;
									vehicle.setIsUpperRack(true);
									vehicle.setSlot(prevPrevKey + ":" + prevKey + ":" + entry.getKey());
									vehicle.setLevel(curLevel);
									vehicle.setVehicleType(VehicleType.CAR.name());
									System.out.println(level.carSummary);
								}
							}
						}

					}
					if(slotFound) {
						break;
					}
					curLevel++;
				}
			}

			if(slotFound) {
				if(vehicle.getIsRideShared() != null && vehicle.getIsRideShared()) {
					ownerService.addIncentivesForSharedRide(vehicle.getContactNumber());
				}
				vehicle.setInTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
				if(addVehicle(vehicle)) {
					slot = vehicle.getSlot();
				}
			}
			
			return slot;
		
	}



	public Boolean addVehicle(Vehicle vehicle) {
		  long newVechicleId = vehicleRepo.count()+1; // auto Id generation not working with H2 hence putting id manually
		  vehicle.setVehicleId(newVechicleId);
		  if(vehicleRepo.save(vehicle) != null) {
			  return true;
		  }
		  return false;
	  }
	  
	  public Vehicle findByVehicleNumber(String vehicleNumber) {
		  return vehicleRepo.findByVehicleNumber(vehicleNumber);
	  }
	  
	  public Vehicle findByContactNumber(Long contactNumber) {
		  return vehicleRepo.findByContactNumber(contactNumber);
	  }
	  
	  public Boolean removeVehicle(Vehicle vehicle) {
		  Level level = Admin.levels.get(vehicle.getLevel());
		  Boolean isSlotUpdated = false, isRoyal = false;
		  if(vehicle.getSlot().contains(":"))
		  {
			  isRoyal = true;
		  }
		  if(VehicleType.BIKE.name().equals(vehicle.getVehicleType())) {
			  String slot;
			  if(isRoyal) {
				  slot = vehicle.getSlot().substring(0, vehicle.getSlot().indexOf(":"));
			  }
			  else {
				  slot = vehicle.getSlot();
			  }
			  String slotStatus = level.bikeSummary.get(slot);
			  StringBuilder updatedSlot = new StringBuilder("");
			  String value[] = slotStatus.split(FrequentlyUsedConstants.UNDERSCORE);
			  if(vehicle.getIsUpperRack()) {
				  int upperCount = Integer.parseInt(value[1].substring(1));
				  updatedSlot.append(value[0]);
				  updatedSlot.append(FrequentlyUsedConstants.UNDERSCORE);
				  updatedSlot.append(FrequentlyUsedConstants.B);
				  if(isRoyal) {
					  updatedSlot.append(upperCount-3);
				  }
				  else {
					  updatedSlot.append(upperCount-1);
				  }
				  level.bikeSummary.put(slot, updatedSlot.toString());
			  }
			  else {
				  int bottomCount = Integer.parseInt(value[0].substring(1));
				  updatedSlot.append(FrequentlyUsedConstants.B);
				  updatedSlot.append(bottomCount-1);
				  updatedSlot.append(FrequentlyUsedConstants.UNDERSCORE);
				  updatedSlot.append(value[1]);
				  level.bikeSummary.put(slot, updatedSlot.toString());
			  }
			  if(isRoyal) {
				  level.setBikeSlotsAvailability(level.getBikeSlotsAvailability()+3);
			  }
			  else {
				  level.setBikeSlotsAvailability(level.getBikeSlotsAvailability()+1);
			  }
			  isSlotUpdated = true;
		  }
		  else if(VehicleType.CAR.name().equals(vehicle.getVehicleType())) {
			  String slotStatus, updatedSlot;
			  // ":" Used for delimiting Royal Family 3 slots, so handling that here
			  if(vehicle.getSlot().contains(":")) {
				  String royalSlots[] = vehicle.getSlot().split(":");
				  for(String slot : royalSlots) {
					  slotStatus = level.carSummary.get(slot);
					  updatedSlot = getUpdatedSlotAfterVehicleExit(slotStatus, vehicle);
					  level.carSummary.put(slot, updatedSlot);
				  }
				  level.setCarSlotsAvailability(level.getCarSlotsAvailability()+3);
			  }
			  // Normal Citizen Scenario
			  else {
				  slotStatus = level.carSummary.get(vehicle.getSlot());
				  updatedSlot = getUpdatedSlotAfterVehicleExit(slotStatus, vehicle);
				  level.setCarSlotsAvailability(level.getCarSlotsAvailability()+1);
				  level.carSummary.put(vehicle.getSlot(), updatedSlot);
			  }
			  
			  isSlotUpdated = true;
		  }
		  
		  try {
			  if(isSlotUpdated) {
				  vehicleRepo.delete(vehicle);
			  }
		  } catch(IllegalArgumentException ex) {
			  System.err.println("Exception in VehicleService.removeVehicle() method " + ex);
		  }
		  return isSlotUpdated;
	  }
	  
	  private String getUpdatedSlotAfterVehicleExit(String slotStatus, Vehicle vehicle) {
		  StringBuilder updatedSlot = new StringBuilder("");
		  String value[] = slotStatus.split(FrequentlyUsedConstants.UNDERSCORE);
		  if(vehicle.getIsUpperRack()) {
			  int upperCount = Integer.parseInt(value[1].substring(1));
			  updatedSlot.append(value[0]);
			  updatedSlot.append(FrequentlyUsedConstants.UNDERSCORE);
			  updatedSlot.append(FrequentlyUsedConstants.C);
			  updatedSlot.append(upperCount-1);

		  }
		  else {
			  int bottomCount = Integer.parseInt(value[0].substring(1));
			  updatedSlot.append(FrequentlyUsedConstants.C);
			  updatedSlot.append(bottomCount-1);
			  updatedSlot.append(FrequentlyUsedConstants.UNDERSCORE);
			  updatedSlot.append(value[1]);
		  }
		  return updatedSlot.toString();
	  }
	  
}
