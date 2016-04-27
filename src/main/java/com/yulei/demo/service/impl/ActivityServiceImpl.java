package com.yulei.demo.service.impl;

import com.yulei.demo.model.Activity;
import com.yulei.demo.model.Attachment;
import com.yulei.demo.model.Notice;
import com.yulei.demo.repository.ActivityRepository;
import com.yulei.demo.repository.AttachmentRepository;
import com.yulei.demo.service.ActivityService;
import com.yulei.demo.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lei.yu on 2016/4/22.
 */
@Service
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private AttachmentRepository attachmentRepository;
    public Activity saveActivityWithAttachment(Activity activity, String shortId) {
        List<Attachment> list= attachmentRepository.findAllByShortId(shortId);
        StringBuffer sb = new StringBuffer();
        for(Attachment attachment:list){
            sb.append(attachment.getId());
            sb.append(";");
        }
        sb.deleteCharAt(sb.toString().length()-1);
        activity.setAttachmentId(sb.toString());
        activity = activityRepository.save(activity);
        attachmentService.updataNewsId(list,activity.getId(),2);
        return activity;
    }
}
