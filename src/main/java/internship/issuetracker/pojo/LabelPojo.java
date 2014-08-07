package internship.issuetracker.pojo;

public class LabelPojo {

    Long id;
    private String labelName;

    public LabelPojo() {

    }

    public LabelPojo(Long id, String labelName) {
        this.labelName = labelName;
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
