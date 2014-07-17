package internship.issuetracker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

@NamedQueries({
	@NamedQuery(name = User.FIND_ALL, query = "select a from User a order by a.id"),
	@NamedQuery(name = User.FIND, query = "select a from User a where id = :id") })
@SuppressWarnings("serial")
@Entity
@Table(name = "Users")
public class User implements Serializable {

    public static final String FIND_ALL = "User.findAll";
    public static final String FIND = "User.find";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true, length = 12)
    @Min(5)
    @Max(12)
    private String userName;

    @Column(name = "user_email", nullable = false)
    @Email
    private String email;

    @Column(name = "user_password", nullable = false)
    @Min(5)
    private String password;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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
    public boolean equals(Object obj) {
	if (obj instanceof User) {
	    if (this == obj) {
		User user = (User) obj;
		return new EqualsBuilder().append(this.email, user.email)
			.append(this.userName, user.userName)
			.append(this.password, user.password).isEquals();
	    }
	}
	return false;
    }

}
