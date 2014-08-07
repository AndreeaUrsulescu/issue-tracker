package internship.issuetracker.entities;

import internship.issuetracker.utils.EncryptData;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@NamedQueries({ @NamedQuery(name = ResetPassword.FIND_KEYHASH, query = "select a from ResetPassword a where keyHash = :keyHash"),
        @NamedQuery(name = ResetPassword.FIND_USER, query = "select a from ResetPassword a where owner = :owner") })
@Entity
@Table(name = "ResetPasswords")
public class ResetPassword implements Serializable {

    public static final String FIND_KEYHASH = "ResetPassword.findKeyHash";
    public static final String FIND_USER = "ResetPassword.findUser";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "keyHash", nullable = false, unique = true)
    private String keyHash;

    @OneToOne
    @JoinColumn(name = "id_owner", nullable = false)
    private User owner;

    public ResetPassword() {
    }

    public ResetPassword(User user) {
        this.owner = user;
        this.keyHash = EncryptData.sha256(owner.getEmail() + owner.getPassword() + owner.getUserName());

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

    public void setKeyHash(String keypass) {
        this.keyHash = keypass;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(keyHash).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResetPassword) {
            ResetPassword resetPassword = (ResetPassword) obj;
            return new EqualsBuilder().append(this.keyHash, resetPassword.keyHash).isEquals();
        }
        return false;
    }
}
