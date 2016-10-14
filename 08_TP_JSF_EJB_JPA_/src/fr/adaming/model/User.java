package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="users")

@NamedQueries({
	@NamedQuery(name="getAllUsers", query="SELECT u FROM User as u"),
	@NamedQuery(name="getAllUsersByIdAgent", query="SELECT u FROM User as u WHERE u.agent.id_agent =:id"),
	})
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_u")
	private Long id_user;
	
	
	private String nom;
	
	@Column(name="dn")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="agent_id", referencedColumnName="id_a")
	private Agent agent;
	
	/**
	 * 
	 */
	public User() {
		super();
	}
	
	/**
	 * @param nom
	 * @param daten
	 */
	public User(String nom, Date date) {
		super();
		this.nom = nom;
		this.date = date;
	}
	
	/**
	 * @param id_users
	 * @param nom
	 * @param daten
	 */
	public User(Long id_user, String nom, Date date) {
		super();
		this.id_user = id_user;
		this.nom = nom;
		this.date = date;
	}
	/**
	 * @return the id_users
	 */
	public Long getId_user() {
		return id_user;
	}
	/**
	 * @param id_users the id_users to set
	 */
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param daten the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Users [id_user=" + id_user + ", nom=" + nom + ", date=" + date + "]";
	}
	
	

}
