package target.cp.vehicle.parking;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("home")
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("levels", Admin.levels);
		initializeHomePage(mv);
		return mv;
	}
	
	@RequestMapping("redirect")
	public void redirect(String contactNumber, HttpServletResponse res, HttpServletRequest req) {
		String conNum = req.getParameter("conNum");
		try {
			res.sendRedirect("owner/"+conNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initializeHomePage(ModelAndView mv) {
		int bikeVacantSpots = 0;
		int carVacantSpots = 0;
		for(Level level : Admin.levels) {
			bikeVacantSpots += level.getBikeSlotsAvailability();
			carVacantSpots += level.getCarSlotsAvailability();
		}
		mv.addObject("levelCount",Admin.levels.size());
		mv.addObject("bikeVacantSpots", bikeVacantSpots);
		mv.addObject("carVacantSpots", carVacantSpots);
	}
}
