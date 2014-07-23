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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = Comment.FIND_COMMENTS_BY_ISSUE, query = "select a from Comment a where a.issue = :issue order by a.creationDate DESC,a.id DESC"),
	@NamedQuery(name = Comment.FIND_COMMENTS_BY_OWNER, query = "select a from Comment a where a.owner= :owner order by a.creationDate DESC,a.id DESC")
	})
@Entity
@Table(name = "Comments")
public class Comment implements Serializable {
	
	public static final String FIND_COMMENTS_BY_ISSUE = "Comment.findCommentsByIssue";
	public static final String FIND_COMMENTS_BY_OWNER = "Comment.findCommentsByOwner";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "content")
	@Size(max = 500,min=1)	
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "id_owner", nullable = false)
	private User owner;
	
	@ManyToOne
	@JoinColumn(name = "id_issue", nullable = false)
	private Issue issue;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	
	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(content).append(owner)
				.append(issue).append(creationDate).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Comment) {
			if (this == obj) {
				Comment comment = (Comment) obj;
				return new EqualsBuilder().append(this.content, comment.content)
						.append(this.creationDate, comment.creationDate)
						.append(this.owner, comment.owner)
						.append(this.issue, comment.issue).isEquals();
			}
		}
		return false;
	}
}
