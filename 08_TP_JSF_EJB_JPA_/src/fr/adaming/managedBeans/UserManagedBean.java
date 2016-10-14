package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Agent;
import fr.adaming.model.User;
import fr.adaming.service.IUsersService;

@ManagedBean(name = "userMB")
@SessionScoped
public class UserManagedBean implements Serializable {
//
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User users;
	
	private Agent agent;
	HttpSession session;

	@EJB
	IUsersService userService;

	@PostConstruct
	private void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(false);

		agent = (Agent) session.getAttribute("agent");
	}

	/**
	 * 
	 */
	public UserManagedBean() {
		this.users = new User();
	}

	/**
	 * @return the user
	 */
	public User getUsers() {
		return users;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUsers(User users) {
		this.users = users;
	}

	public String ajouter() {
//
		users.setAgent(agent);

		userService.AjouterService(users);

		List<User> listeUsers = new ArrayList<User>();
		listeUsers = userService.AllUsersGetByIdAgentService(agent.getId_agent());

		session.setAttribute("listeUsers", listeUsers);
		return "afficher";
	}

	public String rechercher() {

		users.setAgent(agent);
		System.out.println("l'agent esgt : " + agent);
		
		users = userService.UsersGetByIdService(users.getId_user(),agent);
		
		System.out.println("Le user est egale à " + users);
		if (users != null) {
		
		System.out.println("le users est : " + users);
		
		List<User> listeUsers = new ArrayList<User>();
		listeUsers = userService.AllUsersGetByIdAgentService(agent.getId_agent());

		session.setAttribute("listeUsers", listeUsers);

		return "#";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Aucun utilisateur n'appartient à cet Agent !!"));
		return "#";
		}

	}

	public String supprimer() {
		users.setAgent(agent);
		userService.SupprimerService(users, agent);
		List<User> listeUsers = new ArrayList<User>();
		listeUsers = userService.AllUsersGetByIdAgentService(agent.getId_agent());
		session.setAttribute("listeUsers", listeUsers);
		return "afficher";
	
	}

	public String modifier() {

		users.setAgent(agent);
		int verif = userService.MiseAJourService(users, agent);
		
		System.out.println( "verif = " + verif);
		
		if (verif == 1) {
			List<User> listeUsers = new ArrayList<User>();
			listeUsers = userService.AllUsersGetByIdAgentService(agent.getId_agent());
			session.setAttribute("listeUsers", listeUsers);
			return "afficher";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Aucun utilisateur n'appartient à cet Agent !!"));
			return "#";
		}
	}

}
