package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IUsersDao;
import fr.adaming.model.Agent;
import fr.adaming.model.User;

@Stateful
public class UsersServiceImpl implements IUsersService {
	
	
	@EJB
	IUsersDao usersDao;

	
	@Override
	public void AjouterService(User us) {
		 usersDao.AjouterDAO(us);
	}

	@Override
	public int MiseAJourService(User us, Agent agent) {
		return usersDao.MiseAJourDAO(us, agent);
	}

	@Override
	public int SupprimerService(User us, Agent agent) {
		return usersDao.SupprimerDAO(us, agent);
	}

	@Override
	public List<User> getAllUserService() {
		return usersDao.getAllUserDAO();
	}

	@Override
	public User UsersGetByIdService(Long id_us, Agent agent) {
		return usersDao.UsersGetByIdDAO(id_us, agent);
	}
	
	@Override
	public List<User> AllUsersGetByIdAgentService(Long id_agent) {
		
		return usersDao.AllUsersGetByIdAgentDao(id_agent);
	}

}
