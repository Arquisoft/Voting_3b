package DBManagement;

import javax.persistence.*;

import hello.UserNotFoundException;
import hello.UserInfo;

public class GetVP implements GetVoter {

	@Override
	public UserInfo findByEmailAndPassword(String email, String password) throws UserNotFoundException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("voters");
		EntityManager em = emf.createEntityManager();
		UserInfo ui;
		try {
			ui = em.createNamedQuery("UserInfo.findByEmailAndPassword", UserInfo.class).setParameter(1, email)
					.setParameter(2, password).getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return ui;
	}

}
