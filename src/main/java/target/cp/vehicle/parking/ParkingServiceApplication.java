package target.cp.vehicle.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingServiceApplication {

	public static void main(String[] args) {
		
		Admin admin = new Admin();
		for(int i=0;i<Admin.totalLevels;i++) {
			System.out.println("Level - " + admin.initializeLevel() + " is initialized.");
		}
		SpringApplication.run(ParkingServiceApplication.class, args);
	}

}
