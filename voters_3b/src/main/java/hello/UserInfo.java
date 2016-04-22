package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TUsers")

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserInfo() {
	}

	private static final Logger log = LoggerFactory.getLogger(UserInfo.class);

	@Id@GeneratedValue
	private Long id;
	private String name;
	private String NIF;
	private String email;
	private Long pollingStationCode;
	private String password;

	public Long getPollingStationCode() {
		return pollingStationCode;
	}

	public void setPollingStationCode(Long pollingStationCode) {
		this.pollingStationCode = pollingStationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfo(String email, String password, String name, String NIF, Long pollingStationCode, Long id) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.NIF = NIF;
		this.pollingStationCode = pollingStationCode;
		this.id = id;
	}

	public UserInfo(UserInfo ui) {
		new UserInfo(ui.email,ui.password,
				ui.name,ui.NIF,
				ui.pollingStationCode,ui.id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNIF() {
		return NIF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NIF == null) ? 0 : NIF.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (NIF == null) {
			if (other.NIF != null)
				return false;
		} else if (!NIF.equals(other.NIF))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", NIF=" + NIF + ", email=" + email + ", codigoMesa="
				+ pollingStationCode + ", contraseña=" + password + "]";
	}

	public Long getId() {
		return id;
	}

}