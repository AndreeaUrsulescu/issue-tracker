package internship.issuetracker.service;

import internship.issuetracker.entities.Label;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueLabelRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
	
	private static final Logger log = Logger.getLogger(LabelService.class
			.getName());
	
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
		
		if (Labels.size() == 0)
			log.log(Level.INFO, "There are no labels");
		
		for (Label label : Labels) {
			PojoLabels.add(convertLabelEntityToPojoLabel(label));
		}
		return PojoLabels;
	}

	public boolean assignLabelToIssue(Long id, LabelPojo labelPojo) {
		Label label = labelRepository.findLabelByName(labelPojo.getLabelName());
		if (null != label) {
			boolean exists = issueLabelRepository.getLabelsForIssue(id)
					.contains(label);
			if (!exists) {
				issueLabelRepository.addLabelForIssue(id, label.getLabelName());
				log.log(Level.INFO, "Label '" + labelPojo.getLabelName() + "' was assigned to issue " + id);
				return true;
			} else {
				log.log(Level.INFO, "Label '" + labelPojo.getLabelName() + "' is already assigned to issue " + id);
				return false;
			}
		} else {
			label = new Label();
			convertPojoLabelToLabelEntity(labelPojo, label);
			labelRepository.create(label);
			log.log(Level.INFO, "Label '" + labelPojo.getLabelName() + "' was created");
			issueLabelRepository.addLabelForIssue(id, label.getLabelName());
			log.log(Level.INFO, "Label '" + labelPojo.getLabelName() + "' was assigned to issue " + id);
		}
		return true;
	}
	
	public void removeLabelFromIssue(Long issueId, LabelPojo labelToRemove) {
		issueLabelRepository.removeLabelFromIssue(issueId, labelToRemove.getLabelName());
		log.log(Level.INFO, "Label '" + labelToRemove.getLabelName() + "' was removed from issue " + issueId);
	}
}
