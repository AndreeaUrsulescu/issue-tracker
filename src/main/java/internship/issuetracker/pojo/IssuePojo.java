package internship.issuetracker.pojo;

import internship.issuetracker.enums.State;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class IssuePojo {
	
	private Long id;

	private String owner;

	private String title;
	
	private String content;
	
	private Date updateDate;
	
	private Date lastDate;
	
	private State state;
	
	private String update="";
	private String assignee;
	
	private List<CommentPojo> comments;

	private List<LabelPojo> labels;

	public IssuePojo(Long id, String owner, String title, String content, Date updateDate, Date lastDate, State state) {
		this.id = id;
		this.content = content;
		this.owner = owner;
		this.state = state;
		this.title = title;
		this.updateDate = updateDate;
		this.lastDate=lastDate;
		this.update=getUpdate();
	}
	
	public IssuePojo(Long id, String owner, String title, String content, Date updateDate, Date lastDate, State state, List<CommentPojo> comments, List<LabelPojo> labels) {
		this.id = id;
		this.content = content;
		this.owner = owner;
		this.state = state;
		this.title = title;
		this.updateDate = updateDate;
		this.comments = comments;
		this.labels = labels;
		this.lastDate=lastDate;
		this.update=getUpdate();
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
	
	public String getUpdate(){
		Date o= new Date();
		Date now=new Timestamp(o.getTime());
		long dif=((now.getTime()-lastDate.getTime())/1000)/60;
		
		if(dif<=1) update="about a minute";
		else if((dif>1)&&(dif<60)) update=dif+" minutes";
		else{ 
			dif/=60;
			if((dif>=1)&&(dif<2)) update="one hour";
			else if((dif>=2)&&(dif<24)) update=dif+" hours";
			else{
				dif/=24;
				if((dif>=1)&&(dif<2)) update="one day";
				else if((dif>=2)&&(dif<7)) update=dif+" days";
				else{
					dif/=7;
					if((dif>1)&&(dif<2)) update="one week";
					else update=dif+" weeks";
				}
			}
		}
		return update;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
}
