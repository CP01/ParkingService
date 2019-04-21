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
}
