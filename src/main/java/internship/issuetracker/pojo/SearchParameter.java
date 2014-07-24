package internship.issuetracker.pojo;

import internship.issuetracker.enums.State;


public class SearchParameter {

	private String searchCriteria;
	private String input;
	private int pageNumber;
	private State state;
	private String sortCriteria ;
	private String sortType ;
	
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
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
