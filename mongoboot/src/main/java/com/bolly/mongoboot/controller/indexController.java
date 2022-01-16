package com.bolly.mongoboot.controller;

import com.bolly.mongoboot.dao.BdConsultChatDao;
import com.bolly.mongoboot.doc.BdConsultChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

    @Autowired
    private BdConsultChatDao bdConsultChatDao;

    @RequestMapping(value = "/getMsgUid", method = RequestMethod.GET)
    public BdConsultChat search(String msgUid) {
        return bdConsultChatDao.getByMsgUID(msgUid);
    }
}
