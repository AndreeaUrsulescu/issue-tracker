package internship.issuetracker.controller;

import internship.issuetracker.entities.Attachment;
import internship.issuetracker.service.AttachmentService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/issues/issue/{id}/download")
public class FileDownloadController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{attachmentId}")
    public void doDownload(HttpServletRequest request,
            HttpServletResponse response, @PathVariable Long id, @PathVariable Long attachmentId) throws IOException {
     
		Attachment attachment = attachmentService.getAttachment(attachmentId);
		
		response.setContentType(attachment.getContentType());
		String headerKey = "Content-Disposition";
	    String headerValue = String.format("attachment; filename=\"%s\"",
	                attachment.getFilename());
	    response.setHeader(headerKey, headerValue);
	    
	    OutputStream out = response.getOutputStream();
	    InputStream is = new ByteArrayInputStream(attachment.getContent());
	    IOUtils.copy(is, out);
        out.flush();
        out.close();
        is.close();	
	}
}
