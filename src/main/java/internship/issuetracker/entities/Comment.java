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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@Entity
@Table(name = "Comments")
public class Comment implements Serializable {
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
	
	@Temporal(TemporalType.DATE)
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
