package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class LabelService {
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	LabelPojo convertLabelEntityToPojoLabel(Label label)
	{
		LabelPojo pojo=new LabelPojo();
		pojo.setLabelName(label.getLabelName());
		return pojo;
	}
	
	public void convertPojoLabelToLabelEntity(LabelPojo pojo,Label entity)
	{
		entity.setLabelName(pojo.getLabelName());
	}
	
	List<LabelPojo> getAllLabels()
	{
		List<Label> Labels= labelRepository.findLabels();
		List<LabelPojo> PojoLabels=new ArrayList<LabelPojo>();
		for(Label label:Labels)
		{
			PojoLabels.add(convertLabelEntityToPojoLabel(label));
		}
		return PojoLabels;
	}

	
	public void assignLabelToIssue(Long id,LabelPojo labelPojo)
	{
		Issue issue=issueRepository.findIssue(id);
		Label label=labelRepository.findLabelByName(labelPojo.getLabelName());
		if(null!=label)
		{
			label.getIssues().add(issue);
			labelRepository.update(label);
		}
		else
		{
			label=new Label();
			convertPojoLabelToLabelEntity(labelPojo,label);
			label.getIssues().add(issue);
			labelRepository.create(label);
			
		}
	}
}
