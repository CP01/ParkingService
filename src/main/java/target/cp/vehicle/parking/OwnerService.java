package target.cp.vehicle.parking;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import target.cp.vehicle.parking.pojos.Owner;
import target.cp.vehicle.parking.repos.OwnerRepo;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerRepo ownerRepo;
	
	public Owner findOwner(Owner owner) {
		Optional<Owner> foundOwner = ownerRepo.findById(owner.getContactNumber());
		if(foundOwner.isPresent()) {
			return frequentUser(foundOwner.get());
		}
		return addOwner(owner);
	}
	
	public Owner findOwner(Long contactNumber) {
		Owner owner = null;
		Optional<Owner> foundOwner = ownerRepo.findById(contactNumber);
		if(foundOwner.isPresent()) {
			owner = frequentUser(foundOwner.get());
		}
		return owner;
	}
	
	public Owner addOwner(Owner owner) {
		  long newOwnerId = ownerRepo.count()+1; // auto Id generation not working with H2 hence putting id manually
		  owner.setId(newOwnerId);
		return ownerRepo.save(owner);
	}
	
	public Owner updateOwner(Owner owner) {
		  long newOwnerId = ownerRepo.count()+1; // auto Id generation not working with H2 hence putting id manually
		  owner.setId(newOwnerId);
		return ownerRepo.save(owner);
	}
	
	public Owner frequentUser(Owner owner) {
		owner.setParkCount(owner.getParkCount()+1);
		if(owner.getParkCount()%3==0) {
			owner.setPoints(owner.getPoints()+Admin.FREQUENCY_INCENTIVES);
		}
		return updateOwner(owner);
	}
	
	public Owner addIncentivesForSharedRide(Long contactNumber) {
		Optional<Owner> foundOwner = ownerRepo.findById(contactNumber);
		Owner owner = null;
		if(foundOwner.isPresent()) {
			owner = foundOwner.get();
			owner.setPoints(owner.getPoints()+Admin.SHARING_INCENTIVES);
		}
		if(owner != null) {
			updateOwner(owner);
		}
		return owner;
	}
}
