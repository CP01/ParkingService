package target.cp.vehicle.parking;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import target.cp.vehicle.parking.pojos.Owner;
import target.cp.vehicle.parking.pojos.Vehicle;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vechileService;
	@Autowired
	private HomeController homeController;
	@Autowired
	private OwnerService ownerService;
	
	public List<Level> displayCompleteSummary() {
		return null;
	}
	
	@RequestMapping("/vehicleIn")
	public ModelAndView getSlotForIncomingVehicle(Vehicle vehicle, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/home");
		mv.addObject("levels", Admin.levels);
		homeController.initializeHomePage(mv);
		if(vehicle.getIsRideShared() == null)
		{
			vehicle.setIsRideShared(false);
		}
		Owner owner = ownerService.findOwner(vehicle.getContactNumber());
		String generatedSlot = vechileService.getSlot(vehicle, owner.getOwnerType());
		if(generatedSlot!=null && !generatedSlot.isEmpty()) {
			req.setAttribute("slot", generatedSlot);
		}
		else {
			req.setAttribute("slot", "Not Available. Please try later");
		}
		return mv;
	}
	
	 @RequestMapping("/vehicleOut/{vehicleNumber}")
	public boolean vehicleExit(@PathVariable String vehicleNumber) {
		Vehicle vehicle = vechileService.findByVehicleNumber(vehicleNumber);
		return vechileService.removeVehicle(vehicle);
	}
}
