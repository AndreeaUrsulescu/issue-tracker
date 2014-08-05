package internship.issuetracker.entities;

import internship.issuetracker.enums.State;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@NamedQueries({
		@NamedQuery(name = Issue.FIND_BY_ID , query = "select a from Issue a where id = :id"),
	@NamedQuery(name = Issue.FIND_ALL, query = "select a from Issue a order by a.lastDate DESC,a.id DESC") })
@Entity
@Table(name = "Issues")
public class Issue implements Serializable {

	public static final String FIND_BY_ID = "Issue.findByID";
	public static final String FIND_ALL = "Issue.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_owner", nullable = false)
	private User owner;

	@Column(name = "title", nullable = false)
	@Size(min = 5, max = 50)
	private String title;

	@Column(name = "content")
	@Lob
	private String content;
	
	

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date updateDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_date", nullable = false)
	private Date lastDate;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "state", nullable = false)
	private State state;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="issue")
	@OrderBy("creationDate DESC, id DESC")
	private List<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name = "id_assignee", nullable = true)
	private User assignee;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "issue_id", referencedColumnName = "id")
	private Set<Attachment> attachments = new HashSet<Attachment>();
	
	public Issue() {
		state = State.New;
		updateDate=new Date();
		lastDate=new Date();
	
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

	public void setOwner(User user) {
		this.owner = user;
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

	public void setState(State state) {
		this.state = state;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public Date getLastDate(){
		return lastDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public State getState() {
		return state;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(title).append(owner)
				.append(updateDate).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Issue) {
				Issue issue = (Issue) obj;
				return new EqualsBuilder().append(this.title, issue.title)
						.append(this.updateDate, issue.updateDate)
						.append(this.owner, issue.owner)
						.append(this.state, issue.state).isEquals();
			}
		return false;
	}
}
