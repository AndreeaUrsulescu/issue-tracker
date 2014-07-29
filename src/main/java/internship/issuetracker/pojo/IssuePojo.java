package internship.issuetracker.pojo;

import internship.issuetracker.enums.State;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class IssuePojo {
	
	private Long id;

	private String owner;

	private String title;
	
	private String content;
	
	private Date updateDate;
	
	private State state;
	
	private List<CommentPojo> comments;
	
	private List<LabelPojo> labels;

	public IssuePojo(Long id, String owner, String title, String content, Date updateDate, State state) {
		this.id = id;
		this.content = content;
		this.owner = owner;
		this.state = state;
		this.title = title;
		this.updateDate = updateDate;
	}
	
	public IssuePojo(Long id, String owner, String title, String content, Date updateDate, State state, List<CommentPojo> comments, List<LabelPojo> labels) {
		this.id = id;
		this.content = content;
		this.owner = owner;
		this.state = state;
		this.title = title;
		this.updateDate = updateDate;
		this.comments = comments;
		this.labels = labels;
	}
	
	public List<LabelPojo> getLabels() {
	    return labels;
	}

	public void setLabels(List<LabelPojo> labels) {
	    this.labels = labels;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<CommentPojo> getComments() {
		return comments;
	}

	public void setComments(List<CommentPojo> comments) {
		this.comments = comments;
	}
}
