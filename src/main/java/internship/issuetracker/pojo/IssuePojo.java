package internship.issuetracker.pojo;

import internship.issuetracker.enums.State;

import java.util.Date;

public class IssuePojo {
	
	private Long id;

	private String owner;

	private String title;
	
	private String content;
	
	private Date updateDate;
	
	private State state;
	
	public IssuePojo(Long id, String owner, String title, String content, Date updateDate, State state) {
		this.id = id;
		this.content = content;
		this.owner = owner;
		this.state = state;
		this.title = title;
		this.updateDate = updateDate;
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
}
