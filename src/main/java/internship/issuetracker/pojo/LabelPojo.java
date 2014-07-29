package internship.issuetracker.pojo;

public class LabelPojo {
	
	String labelName;
	
	public LabelPojo(){
	    
	}
	
	public LabelPojo(String labelName) {
	    this.labelName = labelName;
	}

	public String getLabelName() {
		return labelName;
	}
	
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
