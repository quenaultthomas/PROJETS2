package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Map<String , Object> localites;
	private Locale local= FacesContext.getCurrentInstance().getViewRoot().getLocale();
	private String localCode = local.toString();
	private int menuSelect;
	
	// Empty Constructor
	public LanguageBean() {
		super();
	}
	
	// Static Constructor
	static{
		localites=new HashMap<String, Object>();
		localites.put("francais", Locale.FRANCE);
		localites.put("anglais", Locale.US);
	}
	
	// Getters and Setters

	public Map<String , Object> getLocalites() {
		return localites;
	}

	public static void setLocalites(Map<String , Object> localitess) {
		localites = localitess;
	}
	
	public Locale getLocal() {
		return local;
	}

	public void setLocal(Locale local) {
		this.local = local;
	}
	
	public void changerLoc(ValueChangeEvent event){
		String nouvelleLocale = (String) event.getNewValue();
		for (Map.Entry<String, Object> entry:localites.entrySet()){
			if (nouvelleLocale.equals(entry.getValue().toString())){
				
				FacesContext context =FacesContext.getCurrentInstance();
				
				UIViewRoot page = context.getViewRoot();
				
				page.setLocale((Locale) entry.getValue());
				
				this.setLocalCode(entry.getValue().toString());
				
				this.local = FacesContext.getCurrentInstance().getViewRoot().getLocale();
				
				
				
				
			}
		}
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	public int getMenuSelect() {
		return menuSelect;
	}

	public void setMenuSelect(int menuSelect) {
		this.menuSelect = menuSelect;
	}


}
