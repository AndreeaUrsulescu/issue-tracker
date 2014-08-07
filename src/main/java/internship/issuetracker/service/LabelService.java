package internship.issuetracker.service;

import internship.issuetracker.entities.Label;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueLabelRepository;
import internship.issuetracker.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {

    private static final Logger LOG = Logger.getLogger(LabelService.class.getName());

    String labelString = "Label '";
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IssueLabelRepository issueLabelRepository;

    LabelPojo convertLabelEntityToPojoLabel(Label label) {
        return new LabelPojo(label.getId(), label.getLabelName());
    }

    public void convertPojoLabelToLabelEntity(LabelPojo pojo, Label entity) {
        entity.setLabelName(pojo.getLabelName());
    }

    public List<LabelPojo> getAllLabels() {
        List<Label> labels = labelRepository.findLabels();
        List<LabelPojo> pojoLabels = new ArrayList<LabelPojo>();

        if (labels.isEmpty()) {
            LOG.log(Level.INFO, "There are no labels");
        }
        for (Label label : labels) {
            pojoLabels.add(convertLabelEntityToPojoLabel(label));
        }
        return pojoLabels;
    }

    public boolean assignLabelToIssue(Long id, LabelPojo labelPojo) {
        Label label = labelRepository.findLabelByName(labelPojo.getLabelName());
        if (null != label) {
            boolean exists = issueLabelRepository.getLabelsForIssue(id).contains(label);
            if (!exists) {
                issueLabelRepository.addLabelForIssue(id, label.getLabelName());
                LOG.log(Level.INFO, labelString + labelPojo.getLabelName() + "' was assigned to issue " + id);
                return true;
            } else {
                LOG.log(Level.INFO, labelString + labelPojo.getLabelName() + "' is already assigned to issue " + id);
                return false;
            }
        } else {
            label = new Label();
            convertPojoLabelToLabelEntity(labelPojo, label);
            labelRepository.create(label);
            LOG.log(Level.INFO, labelString + labelPojo.getLabelName() + "' was created");
            issueLabelRepository.addLabelForIssue(id, label.getLabelName());
            LOG.log(Level.INFO, labelString + labelPojo.getLabelName() + "' was assigned to issue " + id);
        }
        return true;
    }

    public void removeLabelFromIssue(Long issueId, LabelPojo labelToRemove) {
        issueLabelRepository.removeLabelFromIssue(issueId, labelToRemove.getLabelName());
        LOG.log(Level.INFO, labelString + labelToRemove.getLabelName() + "' was removed from issue " + issueId);
    }
}
