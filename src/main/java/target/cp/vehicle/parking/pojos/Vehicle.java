package target.cp.vehicle.parking.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle implements Serializable {
	
	//@Column(name = "vehicleId", nullable = false, columnDefinition = "bigserial")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long vehicleId;
	String vehicleNumber;
	String vehicleType;
	int level;
	String slot;
	Boolean isUpperRack;
	Timestamp inTime;
	Timestamp outTime;
	Boolean isRideShared;
	Long contactNumber;
	
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public Boolean getIsUpperRack() {
		return isUpperRack;
	}
	public void setIsUpperRack(Boolean isUpperRack) {
		this.isUpperRack = isUpperRack;
	}
	public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}
	public Timestamp getOutTime() {
		return outTime;
	}
	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}
	public Boolean getIsRideShared() {
		return isRideShared;
	}
	public void setIsRideShared(Boolean isRideShared) {
		this.isRideShared = isRideShared;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
}
