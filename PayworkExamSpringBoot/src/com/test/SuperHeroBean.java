package com.test;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class SuperHeroBean implements Serializable {
	
	private String Name;
	
	private String Pseudo;
	
	private String Publisher;
	
	private String skills;

	private String allies;
	
	private String dateApp;
	
	public SuperHeroBean(){
		Name = "";
		Pseudo = "";
		Publisher = "";
		skills = "";
		allies = "";
		dateApp = "";
	}
	
	public void setSuperHeroBean(SuperHeroBean sHBRem){
		this.Name = sHBRem.getName();
		this.Pseudo = sHBRem.getPseudo();
		this.Publisher = sHBRem.getPublisher();
		this.dateApp = sHBRem.getDateApp();
		this.skills = sHBRem.getSkills();
		this.allies = sHBRem.getAllies();
	}
	
	
	@Override
	public boolean equals(Object object){
		if (object == null){return false;}
		else if (!(object instanceof SuperHeroBean)){return false;}
		else{
			SuperHeroBean remoteSHB = (SuperHeroBean)object;
			return (remoteSHB.getName().equalsIgnoreCase(this.Name));
		}
	}
	
	
	
	public String getName() {
		return Name;
	}
	
	//@XmlElement
	public void setName(String name) {
		Name = name;
	}

	public String getPseudo() {
		return Pseudo;
	}
	
	//@XmlElement
	public void setPseudo(String pseudo) {
		Pseudo = pseudo;
	}

	public String getPublisher() {
		return Publisher;
	}
	
	//@XmlElement
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public String getSkills() {
		return skills;
	}
	
	//@XmlElement
	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getAllies() {
		return allies;
	}
	
	//@XmlElement
	public void setAllies(String allies) {
		this.allies = allies;
	}

	public String getDateApp() {
		return dateApp;
	}
	
	//@XmlElement
	public void setDateApp(String dateApp) {
		this.dateApp = dateApp;
	}
	
	
	
	
	

}
