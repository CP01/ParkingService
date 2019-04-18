package target.cp.vehicle.parking.enums;

public enum SlotValues {

	C0_C0, // Both Slots Available
	C0_C1, // Upper Rack Occupied, Bottom Rack Vacant
	C1_C0, // Bottom Rack Occupied, Upper Rack Vacant
	C1_C1, // Both the Racks occupied
	
	B0_B0, // All Five Slots Available
	B0_B1, // Upper Rack has 1 Bike, Bottom Rack Vacant
	B0_B2,
	B0_B3,
	B1_B0,
	B1_B1,
	B1_B2,
	B1_B3,
	B2_B0,
	B2_B1,
	B2_B2,
	B2_B3; // All Five Slots Occupied

}

/*
 
	V_C0_C0, // Both Slots Available
	V_C0_C1, // Upper Rack Occupied, Bottom Rack Vacant
	V_C1_C0, // Bottom Rack Occupied, Upper Rack Vacant
	F_C1_C1, // Both the Racks occupied
	
	V_B0_B0, // All Five Slots Available
	V_B0_B1, // Upper Rack has 1 Bike, Bottom Rack Vacant
	V_B0_B2,
	V_B0_B3,
	V_B1_B0,
	V_B1_B1,
	V_B1_B2,
	V_B1_B3,
	V_B2_B0,
	V_B2_B1,
	V_B2_B2,
	F_B2_B3; // All Five Slots Occupied

 */
