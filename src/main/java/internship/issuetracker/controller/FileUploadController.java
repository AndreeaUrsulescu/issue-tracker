package internship.issuetracker.controller;

import internship.issuetracker.pojo.AttachmentPojo;
import internship.issuetracker.service.AttachmentService;
import internship.issuetracker.service.IssueService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
/* @RequestMapping("/issues/issue/{id}") */
public class FileUploadController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private IssueService issueService;

    @RequestMapping(value = "/issues/issue/{id}/upload", method = RequestMethod.POST)
    public @ResponseBody
    LinkedList<AttachmentPojo> upload(@PathVariable Long id, MultipartHttpServletRequest request, HttpServletResponse response) {

        LinkedList<AttachmentPojo> files = new LinkedList<AttachmentPojo>();
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;

        while (itr.hasNext()) {

            mpf = request.getFile(itr.next());
            attachmentService.uploadFile(id, mpf);
        }

        files.addAll(attachmentService.convertToPojo(id));
        return files;
    }

    @RequestMapping(value = "/issues/issue/{id}/remove/{attachmentId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> removeAttachment(@PathVariable Long id, @PathVariable Long attachmentId) {
        Map<String, Object> map = new HashMap<String, Object>();

        attachmentService.removeAttachment(attachmentId, id);
        map.put("result", issueService.getIssue(id).getAttachments().size());
        map.put("attachments", attachmentService.convertToPojo(id));
        return map;
    }
}
