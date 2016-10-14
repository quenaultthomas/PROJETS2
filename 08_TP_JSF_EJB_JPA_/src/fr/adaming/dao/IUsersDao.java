package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.User;


@Local
public interface IUsersDao {
	
	public void AjouterDAO(User us);
	
	public int MiseAJourDAO(User us, Agent agent);
	
	public int SupprimerDAO(User us, Agent agnet);
	
	public List<User> getAllUserDAO();
	
	public User UsersGetByIdDAO(Long id_us, Agent agent);	

	public List<User> AllUsersGetByIdAgentDao(Long id_agent);
	
	

}
