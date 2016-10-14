package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.User;

@Local
public interface IUsersService {
	
	public void AjouterService(User us);
	
	public int MiseAJourService(User us, Agent agent);
	
	public int SupprimerService(User us, Agent agent);
	
	public List<User> getAllUserService();
	
	public User UsersGetByIdService(Long id_us, Agent agent);

	public List<User> AllUsersGetByIdAgentService(Long id_agent);

}
