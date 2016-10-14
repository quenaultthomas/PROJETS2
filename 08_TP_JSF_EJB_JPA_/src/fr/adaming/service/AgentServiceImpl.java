package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAgentDao;
import fr.adaming.model.Agent;

@Stateful
public class AgentServiceImpl implements IAgentService {
	
	@EJB
	IAgentDao agentDao;
	
	@Override
	public List<Agent> isExistService(String login, String password) {
		return agentDao.isExistDao(login, password);
	}

}
