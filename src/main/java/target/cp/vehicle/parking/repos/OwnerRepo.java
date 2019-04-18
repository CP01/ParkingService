package target.cp.vehicle.parking.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import target.cp.vehicle.parking.pojos.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long> {

}
