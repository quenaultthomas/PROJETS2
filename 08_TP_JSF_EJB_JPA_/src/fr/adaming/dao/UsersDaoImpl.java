package fr.adaming.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.User;

@Stateless
public class UsersDaoImpl implements IUsersDao {
	//
	@PersistenceContext(unitName="PU")
	EntityManager em;
	
	
	@Override
	public void AjouterDAO(User us) {
				
				//Users newUser = new Users(us.getNom(), us.getPrenom(), us.getMail(), us.getPassword());
				//em.persist(newUser);
				
				em.persist(us);
	}

	@Override
	public int MiseAJourDAO(User us, Agent agent) {
			System.out.println("Avant le try catch");
		try {
		String ReqMiseAJour = "SELECT u FROM User as u WHERE u.id_user=:id_u and u.agent.id_agent =:id_a";
		Query queryMAJ = em.createQuery(ReqMiseAJour);
		
		queryMAJ.setParameter("id_u", us.getId_user());
		queryMAJ.setParameter("id_a", agent.getId_agent());
		
		User mergeUser = (User) queryMAJ.getSingleResult();
				
		//Modification des parametres de la table 
		mergeUser.setNom(us.getNom());
		mergeUser.setDate(us.getDate());
			
	
		em.merge(mergeUser);
	
		System.out.println("dans le try");
		return 1;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int SupprimerDAO(User us, Agent agent) {
	
		
		try {
			String ReqMiseAJour = "SELECT u FROM User as u WHERE u.id_user=:id_u and u.agent.id_agent =:id_a";
			Query queryMAJ = em.createQuery(ReqMiseAJour);
			
			queryMAJ.setParameter("id_u", us.getId_user());
			queryMAJ.setParameter("id_a", agent.getId_agent());
			
			User delUser = (User) queryMAJ.getSingleResult();
		
		// Récupérer un user de l'id 6
		//User delUser = em.find(User.class, us.getId_user());
		
		// supprimer l'objet u
		em.remove(delUser);
		return 1;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public List<User> getAllUserDAO() {
				
		Query queryUserList = em.createNamedQuery("getAllUsers");
				
		@SuppressWarnings("unchecked")
		List<User> listeUser = queryUserList.getResultList();
		
		return listeUser;
		
	}

	@Override
	public User UsersGetByIdDAO(Long id_us, Agent agent) {
		
		try {
			
		
		String ReqMiseAJour = "SELECT u FROM User as u WHERE u.id_user=:id_u and u.agent.id_agent =:id_a";
		Query queryS = em.createQuery(ReqMiseAJour);
		
		queryS.setParameter("id_u", id_us);
		queryS.setParameter("id_a", agent.getId_agent());
		
				
		User searchUser = (User) queryS.getSingleResult();
		
		return searchUser;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> AllUsersGetByIdAgentDao(Long id_agent) {
				
		Query queryUsersByIdAgent = em.createNamedQuery("getAllUsersByIdAgent");
		queryUsersByIdAgent.setParameter("id",id_agent);
	
		
		@SuppressWarnings("unchecked")
		List<User> listeUsersByIdAgent = queryUsersByIdAgent.getResultList();
		return listeUsersByIdAgent;
	}
		
}
