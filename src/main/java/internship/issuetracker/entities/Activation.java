package internship.issuetracker.entities;

import internship.issuetracker.utils.EncryptData;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

@SuppressWarnings("serial")
@NamedQueries({
@NamedQuery(name = Activation.FIND_KEYHASH, query = "select a from Activation a where keyHash = :keyHash")})
@Entity
@Table(name = "Activations")
public class Activation implements Serializable {
	
	public static final String FIND_KEYHASH="Activation.findKeyHash";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "keyHash", nullable = false, unique = true)
	private String keyHash;

	@Column(name = "user_name", nullable = false, unique = true)
	@Size(min = 5, max = 12)
	@Pattern(regexp = "^[a-zA-Z]{5,12}$")
	private String userName;

	@Column(name = "user_email", nullable = false)
	@Email
	private String email;

	@Column(name = "user_password", nullable = false)
	@Size(min = 5)
	private String password;
	
	public Activation()
	{
	}
	
	public Activation(User user)
	{
		this.email=user.getEmail();
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.EncryptPasswordAndKeyHash();
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyHash() {
		return keyHash;
	}
	
	public void setKeyHash(String keyHash) {
		this.keyHash = keyHash;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userName).append(email)
				.append(password).toHashCode();
	}
	
	@Override
	public boolean equals(Object object){
		if( object instanceof Activation){
			Activation activation = (Activation) object;
			return new EqualsBuilder().append(activation.email, this.email)
									  .append(activation.keyHash, this.keyHash)
									  .append(activation.password, this.password)
									  .append(activation.userName, this.userName)
									  .isEquals();
		}
		return false;
	}
		
	public void EncryptPasswordAndKeyHash()
	{
		this.setKeyHash(EncryptData.sha256(this.getUserName()));
		this.setPassword(EncryptData.sha256(password));
	}
	
	public User getUserFromActivation() {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUserName(userName);
		return user;
	}
	
	
}
