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
            
            // 세션에서 로그인 한 유저 정보 가져오기
    		users = ((Users)session.getAttribute("users"));
    		System.out.println("세션에서 가져온 유저 정보 : " + users);
    		
    		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
    		contacts.setUserId(users.getId());
    		
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
            
            // 세션에서 로그인 한 유저 정보 가져오기
    		users = ((Users)session.getAttribute("users"));
    		if(users == null){
                map.put("result", "logout");
                System.out.println("SelectContactList Controller end...");
                return map;
    		}
    		contacts.setUserId(users.getId());
    		
    		contactList = contactsService.getContactsList(contacts);
    		
    		// 주소록 리스트 널체크
    		if (contactList.size()==0) {
				map.put("result", "noContacts");
				System.out.println("주소록 하나도 없다고 알림.");
				return map;
			}
    		
    		System.out.println("주소록 리스트 : " + contactList);
    		map.put("result", "success");
    		map.put("contactList", contactList);
    		
    		return map;
        }
        
        // 주소록 검색 조회
        @RequestMapping("/searchContactList.contact")
        @ResponseBody
        public Object searchContactList(@ModelAttribute("contacts") Contacts contacts, Users users,
                        HttpSession session) throws Exception {
            System.out.println("SearchContactList controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            List<Contacts> contactList;
            
            // 세션 유저 정보 체크
            Users sessionUser = new Users();
            sessionUser = (Users)session.getAttribute("users");
            contacts.setUserId(sessionUser.getId());
            
    		contactList = contactsService.getSearchContactsList(contacts);
    		
    		// 주소록 리스트 널체크
    		if (contactList.size()==0) {
				map.put("result", "noContacts");
				System.out.println("주소록 하나도 없다고 알림.");
				return map;
			}

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
            
            System.out.println("요청 들어온 contacs : " + contacts);
    		contacts = contactsService.getContact(contacts);
    		
    		// 상세주소 널체크
    		if (contacts == null) {
				map.put("result", "noContact");
				return map;
			}
    		
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
            
            // 세션에서 로그인 한 유저 정보 가져오기
    		users = ((Users)session.getAttribute("users"));
    		
    		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
    		contacts.setUserId(users.getId());
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
            
            // 세션에서 로그인 한 유저 정보 가져오기
    		users = ((Users)session.getAttribute("users"));
    		
    		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
    		contacts.setUserId(users.getId());
    		
    		contactsService.deleteContacts(contacts);
    		map.put("result", "success");
    		
    		return map;
        }
        
}
