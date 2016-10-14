package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;

@Stateless
public class AgentDaoImpl implements IAgentDao {

	@PersistenceContext(unitName="PU")
	EntityManager em;
	
	
	@Override
	public List<Agent> isExistDao(String login, String password) {
		
		String ReqIsExist = "SELECT u FROM Agent as u WHERE u.login=:login and u.password=:password";
		Query queryIsExist = em.createQuery(ReqIsExist);
		
		queryIsExist.setParameter("login",login);
		queryIsExist.setParameter("password",password);
		
		@SuppressWarnings("unchecked")
		List<Agent> listeAgent = queryIsExist.getResultList();
		
		return listeAgent;
	}

}
