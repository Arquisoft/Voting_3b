package hello;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static boolean mapear = false;

	public static void main(String[] args) {
		if (!mapear)
			SpringApplication.run(Application.class, args);
		else {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("voters");

			emf.close();
		}
	}
}
