package internship.issuetracker.comparators;

import internship.issuetracker.entities.Issue;

import java.util.Comparator;

public class UpdateDateComparator implements Comparator<Issue> {
 @Override
 public int compare(Issue i1, Issue i2) {
  
  if(i1.getUpdateDate().before(i2.getUpdateDate()))
   return -1;
  
  if(i1.getUpdateDate().after(i2.getUpdateDate()))
   return 1;
  
  return 0;
 }

}
