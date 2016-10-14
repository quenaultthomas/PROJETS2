package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;

@Local
public interface IAgentService {

	
	public List<Agent> isExistService(String login, String password);
}
