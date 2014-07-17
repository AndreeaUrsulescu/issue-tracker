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
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = User.FIND_PASS, query = "select a from User a where user_name = :user_name AND user_password = :user_password"),
	@NamedQuery(name = User.FIND_NAME, query = "select a from User a where user_name = :user_name") })
@Entity
@Table(name = "Users")
public class User implements Serializable {

    public static final String FIND_NAME = "User.findName";
    public static final String FIND_PASS = "User.findPass";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    @Size(min = 2, max = 12)
    private String userName;

    @Column(name = "user_email", nullable = false)
    @Email
    private String email;

    @Column(name = "user_password", nullable = false)
    @Size(min = 5)
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
	// force lowercase
	this.userName = userName.toLowerCase();
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
