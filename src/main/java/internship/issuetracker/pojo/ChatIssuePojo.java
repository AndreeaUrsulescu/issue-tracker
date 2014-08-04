package internship.issuetracker.pojo;

import java.util.Date;

public class  ChatIssuePojo  {

	private String owner;

	private String content;
	
	private String link;
	
	private Integer range;

	//private Date creationDate;
	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public ChatIssuePojo() {
		
	}
	
	public  ChatIssuePojo(String owner, String content, String link, Date creationDate, int range) {
		this.content = content;
//		this.creationDate = creationDate;
		this.range= range;
		this.owner = owner;
		this.link=link;
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

//	public Date getCreationDate() {
//		return creationDate;
//	}

//	public void setCreationDate(Date creationDate) {
//		this.creationDate = creationDate;
//	}


	public void setRange(int range) {
		this.range = range;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link=link;
	}
}
