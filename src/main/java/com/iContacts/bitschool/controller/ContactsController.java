package com.iContacts.bitschool.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iContacts.bitschool.domain.Contacts;
import com.iContacts.bitschool.domain.Users;
import com.iContacts.bitschool.service.ContactsService;
import com.iContacts.bitschool.service.UsersService;

@Controller
@RequestMapping("/ajax")
public class ContactsController {

        public ContactsController() {
                System.out.println("ContactsController() start...");
        }
        
        @Inject
        UsersService usersService;
        @Inject
        ContactsService contactsService;
        
        // 주소록 등록
        @RequestMapping("/insertContact.contact")
        @ResponseBody
        public Object insertContacts(@ModelAttribute("contacts") Contacts contacts, Users users,
                        HttpSession session) throws Exception {
            System.out.println("InsertContacts controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            
            // 유저 생성 (테스트용)
    
            users.setId(107);
    		
    		// 세션에 유저 아이디 등록 (로그인)
    		session.setAttribute("users", users.getId());
    		
    		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
    		contacts.setUserId((Integer) session.getAttribute("users"));
    		
    		// 주소록 이름 널체크
    		if (contacts.getName().equals("")) {
				map.put("result", "nullname");
				return map;
			} 
    		
    		// 동일 이메일 체크
    		Contacts dbContacts = new Contacts();
    		dbContacts = (Contacts) contactsService.getContact(contacts);
    		if (dbContacts == null) {
    			// 주소록 등록
    			contactsService.insertContacts(contacts);
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
    		return map;
        }
        
     // 주소록 리스트 조회
        @RequestMapping("/selectContactList.contact")
        @ResponseBody
        public Object selectContactList(@ModelAttribute("contacts") Contacts contacts, Users users,
                        HttpSession session) throws Exception {
            System.out.println("SelectContactList controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            List<Contacts> contactList;
            // 유저 생성 (테스트용)

    		users.setId(107);
    
    		// 세션에 유저 아이디 등록 (로그인)
    		session.setAttribute("users", users.getId());
    	
    		contacts.setUserId((Integer) session.getAttribute("users"));
    		contactList = contactsService.getContactsList(contacts);
    		System.out.println("주소록 리스트 : " + contactList);
    		map.put("result", "success");
    		map.put("contactList", contactList);
    		
    		return map;
        }
        
        // 상세 주소 조회
        @RequestMapping("/selectContact.contact")
        @ResponseBody
        public Object selectContact(@ModelAttribute("contacts") Contacts contacts,
                        HttpSession session) throws Exception {
            System.out.println("SelectContact controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            
    		contacts = contactsService.getContact(contacts);
    		
    		map.put("result", "success");
    		map.put("contacts", contacts);
    		
    		return map;
        }
        
        // 상세 주소 업데이트
        @RequestMapping("/updateContact.contact")
        @ResponseBody
        public Object updateContact(@ModelAttribute("contacts") Contacts contacts, Users users,
                        HttpSession session) throws Exception {
            System.out.println("UpdateContact controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            
            users.setId(107);
    		// 세션에 유저 아이디 등록 (로그인)
    		session.setAttribute("users", users.getId());
    		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
    		contacts.setUserId((Integer) session.getAttribute("users"));
    		contactsService.updateContacts(contacts);
    		map.put("result", "success");
    		
    		return map;
        }
        
        // 상세 주소 삭제
        @RequestMapping("/deleteContact.contact")
        @ResponseBody
        public Object deleteContact(@ModelAttribute("contacts") Contacts contacts, Users users,
                        HttpSession session) throws Exception {
            System.out.println("DeleteContact controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            
            // 유저 정보 테스트용 세팅
            users.setId(107);
    		// 세션에 유저 아이디 등록 (로그인)
    		session.setAttribute("users", users.getId());
    		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
    		contacts.setUserId((Integer) session.getAttribute("users"));
    		
    		contactsService.deleteContacts(contacts);
    		map.put("result", "success");
    		
    		return map;
        }
        
}