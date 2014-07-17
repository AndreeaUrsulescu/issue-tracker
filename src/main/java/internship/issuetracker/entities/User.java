package internship.issuetracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@NamedQueries({ @NamedQuery(name = User.FIND_ALL, query = "select a from User a order by a.id"),
			    @NamedQuery(name = User.FIND, query = "select a from User a where id = :id") })

@Entity
@Table(name = "users")
public class User {

	public static final String FIND_ALL = "User.findAll";
	public static final String FIND = "User.find";
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", nullable=false, unique=true, length=12)
    private String userName;

    @Column(name = "user_email", nullable=false)
    private String email;

    @Column(name = "user_password", nullable=false)
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
	// enforce lowercase letters
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
}
