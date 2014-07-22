package internship.issuetracker.pojo;

import java.util.Date;

public class CommentPojo {

	private String owner;

	private String content;

	private Date creationDate;

	private Long issueId;
	
	public CommentPojo(String owner, String content, Date creationDate, Long issueId) {
		this.content = content;
		this.creationDate = creationDate;
		this.issueId = issueId;
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}
}
