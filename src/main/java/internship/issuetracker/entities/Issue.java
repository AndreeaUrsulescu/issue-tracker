package internship.issuetracker.entities;

import internship.issuetracker.enums.State;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = Issue.FIND_TITLE, query = "select a from Issue a where title = :title"),
	@NamedQuery(name = Issue.FIND_DATE, query = "select a from Issue a where a.updateDate= :updateDate") })
@Entity
@Table(name = "Issues")
public class Issue implements Serializable {

    public static final String FIND_TITLE = "Issue.findTitle";
    public static final String FIND_DATE = "Issue.findDate";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_owner", nullable = false)
    User owner;
    
    @Column(name = "title", nullable = false)
    @Size(min = 5, max = 50)
    String title;
    
    @Column(name="content")
    @Size(max=150)
    String content;
    
	@Column(name = "update_date", nullable = false)
	Date updateDate;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(name = "state", nullable = false)
    State state;
   
	public Issue() {
	state = State.New;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public User getOwner() {
	return owner;
    }

    public void setOwner(User owner) {
	this.owner = owner;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContent() {
	return content;
    }
    
    public void setContent(String content) {
	this.content = content;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public State getState() {
	return state;
    }

    public void setState(State state) {
	this.state = state;
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(title).append(owner)
		.append(updateDate).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Issue) {
	    if (this == obj) {
		Issue issue = (Issue) obj;
		return new EqualsBuilder().append(this.title, issue.title)
			.append(this.updateDate, issue.updateDate)
			.append(this.owner, issue.owner)
			.append(this.state, issue.state).isEquals();
	    }
	}
	return false;
    }
}