package target.cp.vehicle.parking;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import target.cp.vehicle.parking.pojos.Owner;
import target.cp.vehicle.parking.pojos.Vehicle;

@RestController
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	@Autowired
	private VehicleService vehicleService;

	
	@RequestMapping("/addOwner")
	public ModelAndView addOwner(Owner owner) {
		ModelAndView mv = new ModelAndView();
		Owner ownerFromDB = ownerService.findOwner(owner);
		mv.setViewName("/vehicle");
		mv.addObject("contactNumber", ownerFromDB.getContactNumber());
		mv.addObject("firstName", ownerFromDB.getFirstName());
		return mv;
	}
	
	@RequestMapping("/owner/{contactNumber}")
	public ModelAndView getOwner(@PathVariable String contactNumber, HttpServletRequest req) {
		Long conNum = Long.valueOf(contactNumber);
		ModelAndView mv = new ModelAndView();
		Owner owner = ownerService.findOwner(conNum);
		
		// Existing Customer
		if(owner != null) {
			Vehicle vehicle = vehicleService.findByContactNumber(conNum);
			// Vehicle in parking, needs to exit
			if(vehicle != null) {
				mv.setViewName("/home");
				mv.addObject("levels", Admin.levels);
				if(vehicleService.removeVehicle(vehicle))
					req.setAttribute("exitingOwner", owner.getFirstName());
				else
					req.setAttribute("failed", owner.getFirstName());
			}
			// New vehicle entering
			else {
				mv.setViewName("/vehicle");
				mv.addObject("contactNumber", owner.getContactNumber());
				mv.addObject("firstName", owner.getFirstName());
			}
		}
		// New Customer
		else {
			mv.setViewName("/owner");
			mv.addObject("contactNumer", conNum);
		}
		return mv;
	}
}
