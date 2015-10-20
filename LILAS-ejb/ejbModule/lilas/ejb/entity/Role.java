package lilas.ejb.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Entity
@Table(name="ROLES")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_ROLE")
	private Long numRole;
	@Column(name="NAME_ROLE")
	private String name;
	@Column(name="PATH_ROLE")
	private String urlRole;
	@Column(name="ICON_ROLE")
	private String icon;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrlRole() {
		return urlRole;
	}
	public void setUrlRole(String urlRole) {
		this.urlRole = urlRole;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Acces> listAcces;
	public Set<Acces> getListAcces() {
		return listAcces;
	}
	public void setListAcces(Set<Acces> listAcces) {
		this.listAcces = listAcces;
	}

	private static final long serialVersionUID = 1L;

	public Role() {
		super();
	}   
	public Long getNumRole() {
		return this.numRole;
	}

	public void setNumRole(Long numRole) {
		this.numRole = numRole;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
