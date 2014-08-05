package internship.issuetracker.controller;

import internship.issuetracker.pojo.AttachmentPojo;
import internship.issuetracker.service.AttachmentService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/issues/issue/{id}")
public class FileUploadController {

	@Autowired
	private AttachmentService attachmentService;
	
	LinkedList<AttachmentPojo> files = new LinkedList<AttachmentPojo>();
    AttachmentPojo attachmentPojo = null;
    
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody LinkedList<AttachmentPojo> upload(@PathVariable Long id, MultipartHttpServletRequest request, HttpServletResponse response) {
 
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             
             /*System.out.println(mpf.getOriginalFilename() +" uploaded! "+ files.size());*/
 
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 5)
                 files.pop();
 
	             //2.3 create new fileMeta
	             attachmentPojo = new AttachmentPojo();
	             attachmentPojo.setFilename(mpf.getOriginalFilename());
	             attachmentPojo.setFileType(mpf.getContentType());
	             attachmentPojo.setIssueId(id);
	 
	             try {
	            	 attachmentPojo.setContent(mpf.getBytes());
	 
	                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
	                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("D:/temp/files/"+mpf.getOriginalFilename()));
	            	 Long attachmentId = attachmentService.uploadFile(id, mpf);
	            	 attachmentPojo.setId(attachmentId);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	             //2.4 add to files
	             files.add(attachmentPojo);
             
             /*System.out.println(attachmentPojo);*/
         }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }
}
