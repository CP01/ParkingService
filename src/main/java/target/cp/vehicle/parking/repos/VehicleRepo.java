package target.cp.vehicle.parking.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import target.cp.vehicle.parking.pojos.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

	Vehicle findByVehicleNumber(String vehicleNumber);

	Vehicle findByContactNumber(Long contactNumber);
}
