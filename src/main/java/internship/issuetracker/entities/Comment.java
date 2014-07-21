package internship.issuetracker.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = Comment.FIND_ISSUE, query = "select a from Comment a where a.issue = :issue"),
	@NamedQuery(name = Comment.FIND_OWNER, query = "select a from Comment a where a.owner= :owner"),
	@NamedQuery(name = Comment.FIND_ID , query = "select a from Comment a where id = :id")
	})
@Entity
@Table(name = "Comments")
public class Comment implements Serializable {
	
	public static final String FIND_ISSUE = "Comment.findIssue";
	public static final String FIND_OWNER = "Comment.findOwner";
	public static final String FIND_ID = "Comment.findID";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "content")
	@Size(max = 150)	
	String content;
	

	@ManyToOne
	@JoinColumn(name = "id_owner", nullable = false)
	User owner;
	
	@ManyToOne
	@JoinColumn(name = "id_issue", nullable = false)
	Issue issue;
	

	@Column(name = "creation_date", nullable = false)
	Date creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
}
