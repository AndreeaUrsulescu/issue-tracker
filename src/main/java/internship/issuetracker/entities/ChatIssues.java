package internship.issuetracker.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@NamedQueries({
		@NamedQuery(name = ChatIssues.GET_ALL_MESSAGES, query = "select a from ChatIssues a order by a.creationDate DESC")})

@Entity
@Table(name = "ChatIssues")
public class ChatIssues implements Serializable {
	

	public static final String GET_ALL_MESSAGES = "ChatIssues.findAllMessages";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "content")
	@Size(max = 700, min = 1)
	private String content;
	
	@Column(name = "link")
	@Size(max = 200, min = 0)
	private String link;

	@Column(name = "range")
	private int range;
	
	@Column(name = "owner")
	private String owner;

	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getLink() {
		return link;
	}

	public String getOwner() {
		return owner;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public int getRange() {
		return range;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setRange(int range) {
		this.range = range;
	}

}