package internship.issuetracker.pojo;

import internship.issuetracker.enums.State;

public class MultipleSearchParameter {

	private String title;
	private String content;
	private State state;
	private String creator;
	private String assignee;
	private Long label;
	
	private int pageNumber;
	private String sortCriteria ;
	private String sortType ;
	
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
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Long getLabel() {
		return label;
	}
	public void setLabel(Long label) {
		this.label = label;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getSortCriteria() {
		return sortCriteria;
	}
	public void setSortCriteria(String sortCriteria) {
		this.sortCriteria = sortCriteria;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
}
