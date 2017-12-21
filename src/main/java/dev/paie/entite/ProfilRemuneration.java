package dev.paie.entite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String code;
	
	@ManyToMany
	@JoinTable(name="PrRem_Coti_non_im",
	joinColumns= @JoinColumn(name="ID_PRO", referencedColumnName="ID"),
	inverseJoinColumns= @JoinColumn(name="ID_COT", referencedColumnName="ID")
	)
	private List<Cotisation> cotisationsNonImposables;
	
	@ManyToMany
	@JoinTable(name="PrRem_Coti_im",    
	joinColumns= @JoinColumn(name="ID_PRO", referencedColumnName="ID"),
	inverseJoinColumns= @JoinColumn(name="ID_COT", referencedColumnName="ID")
	)
	private List<Cotisation> cotisationsImposables;
	
	@ManyToMany
	@JoinTable(name="Pro_Avan",
	joinColumns= @JoinColumn(name="ID_PRO", referencedColumnName="ID"),
	inverseJoinColumns= @JoinColumn(name="ID_AVAN", referencedColumnName="ID")
	)
	private List<Avantage> avantages;
	
	@OneToMany(mappedBy = "Pro_Re_ID")
	private Set<RemunerationEmploye> remunerationEmployes;
	
	public ProfilRemuneration() {
		remunerationEmployes = new HashSet<RemunerationEmploye>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

}
