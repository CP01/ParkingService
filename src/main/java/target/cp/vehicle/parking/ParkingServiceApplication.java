package target.cp.vehicle.parking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingServiceApplication {

	private static Logger logger = LogManager.getLogger("ParkingServiceApplication.class");
	
	public static void main(String[] args) {
		
		logger.error("This is an error message");
		logger.fatal("This is an fatal message");
		
		Admin admin = new Admin();
		for(int i=0;i<Admin.totalLevels;i++) {
			System.out.println("Level - " + admin.initializeLevel() + " is initialized.");
		}
		SpringApplication.run(ParkingServiceApplication.class, args);
	}

}
