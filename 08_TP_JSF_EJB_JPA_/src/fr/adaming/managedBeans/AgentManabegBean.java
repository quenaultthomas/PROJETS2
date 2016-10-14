package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Agent;
import fr.adaming.model.User;
import fr.adaming.service.IAgentService;
import fr.adaming.service.IUsersService;
//
@ManagedBean(name="agentMB")
@RequestScoped
public class AgentManabegBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mail;
	private String mdp;
	private Agent agent;
	
	private List<User> listeUsers ;
	
	HttpSession session;
	
	 @EJB
	 IAgentService agentservice;
	 @EJB
	 IUsersService userService;
	
	/**
	 * 
	 */
	public AgentManabegBean() {
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}
	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public String isExist(){
		List<Agent> listeAgent = agentservice.isExistService(mail, mdp);
		
		if (listeAgent.size()==1){
			agent = listeAgent.get(0);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agent", agent);
			listeUsers = userService.AllUsersGetByIdAgentService(agent.getId_agent());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeUsers", listeUsers);
			
			return "succes";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le login ou le mot de passe est incorrect !!"));
			
			return "fail";
		}
	}
	
		public String deconnecter(){
		
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext() 
				.getSession(true)).invalidate();
		return "deco";

}
}
