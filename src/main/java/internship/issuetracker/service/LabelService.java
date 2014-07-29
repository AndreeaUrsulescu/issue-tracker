package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    @Autowired
    LabelRepository labelRepository;

    @Autowired
    IssueRepository issueRepository;

    LabelPojo convertLabelEntityToPojoLabel(Label label) {
	LabelPojo pojo = new LabelPojo(label.getLabelName());
	return pojo;
    }

    public void convertPojoLabelToLabelEntity(LabelPojo pojo, Label entity) {
	entity.setLabelName(pojo.getLabelName());
    }

    public List<LabelPojo> getAllLabels() {
	List<Label> Labels = labelRepository.findLabels();
	List<LabelPojo> PojoLabels = new ArrayList<LabelPojo>();
	for (Label label : Labels) {
	    PojoLabels.add(convertLabelEntityToPojoLabel(label));
	}
	return PojoLabels;
    }

    public boolean assignLabelToIssue(Long id, LabelPojo labelPojo) {
	Issue issue = issueRepository.findIssue(id);
	Label label = labelRepository.findLabelByName(labelPojo.getLabelName());
	if (null != label) {
	    //there seems to be a problem with contains
	    //boolean exists = issue.getLabels().contains(label);
	    boolean exists = false;
	    for (Label lbl : issue.getLabels()) {
		if(lbl.getLabelName().equals(label.getLabelName())){
		    exists = true;
		}
	    }
	    System.out.println(exists);
	    if (!exists) {
		label.getIssues().add(issue);
		labelRepository.update(label);
		return true;
	    } else {
		return false;
	    }
	} else {
	    label = new Label();
	    convertPojoLabelToLabelEntity(labelPojo, label);
	    labelRepository.create(label);
	    label.getIssues().add(issue);
	    labelRepository.update(label);
	}
	return true;
    }
}
