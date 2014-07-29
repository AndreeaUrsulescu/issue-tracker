package internship.issuetracker.service;

import internship.issuetracker.entities.Label;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueLabelRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueLabelRepository issueLabelRepository;

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
	Label label = labelRepository.findLabelByName(labelPojo.getLabelName());
	if (null != label) {
	    boolean exists = issueLabelRepository.getLabelsForIssue(id).contains(label);
	    if (!exists) {
		issueLabelRepository.addLabelForIssue(id, label.getLabelName());
		return true;
	    } else {
		return false;
	    }
	} else {
	    label = new Label();
	    convertPojoLabelToLabelEntity(labelPojo, label);
	    labelRepository.create(label);
	    issueLabelRepository.addLabelForIssue(id, label.getLabelName());
	}
	return true;
    }
}
