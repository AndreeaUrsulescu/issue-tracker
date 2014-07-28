package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueRepository;

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
		List<Label> Labels= finaAllLabels();
		List<LabelPojo> PojoLabels=new ArrayList<LabelPojo>();
		for(Label l:Labels)
		{
			PojoLabels.add(convertLabelEntityToPojoLabel(l));
		}
		return PojoLabels;
	}

	
	public void assignLabelToIssue(Long id,LabelPojo labelPojo)
	{
		Issue issue=issueRepository.findIssue(id);
		Label label=findLabelByName(labelPojo.getLabelName());
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
