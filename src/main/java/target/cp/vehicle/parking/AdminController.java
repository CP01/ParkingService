package target.cp.vehicle.parking;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class AdminController {

	@RequestMapping("a_d_m_i_n_001")
	public ModelAndView adminConsole() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin");
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
