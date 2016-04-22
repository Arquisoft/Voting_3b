package DBManagement;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import hello.UserNotFoundException;
import hello.UserInfo;

public class VoterService implements CrudRepository<UserInfo, Serializable>{
	
	public UserInfo findByEmailAndPassword(String email, String password) throws UserNotFoundException{
		return new GetVP().findByEmailAndPassword(email, password);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Serializable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UserInfo arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends UserInfo> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Serializable arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<UserInfo> findAll(Iterable<Serializable> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo findOne(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserInfo> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserInfo> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


}
