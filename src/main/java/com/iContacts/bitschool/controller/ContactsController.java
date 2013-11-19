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
        public Object insertContacts(@ModelAttribute("contacts") Contacts contacts,
                        HttpSession session) throws Exception {
            System.out.println("InsertContacts controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            
            // 유저 생성 (테스트용)
    		Users users = new Users();
    		users.setEmail("win@hanmail.net");
    		users.setPassword("1234");
    		users.setName("김위나");
    		usersService.insertUsers(users);
    		
    		// 세션에 유저 아이디 등록 (로그인)
    		session.setAttribute("users", users.getId());
    		
    		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
    		contacts.setUserId((Integer) session.getAttribute("users"));
    		
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
        
        @RequestMapping("/selectContactList.contact")
        @ResponseBody
        public Object selectContacts(@ModelAttribute("contacts") Contacts contacts,
                        HttpSession session) throws Exception {
            System.out.println("SelectContacts controller start....");
            HashMap<String, Object> map= new HashMap<String,Object>();
            List<Contacts> contactList;
            // 유저 생성 (테스트용)
    		Users users = new Users();
    		users.setId(93);
    
    		// 세션에 유저 아이디 등록 (로그인)
    		session.setAttribute("users", users.getId());
    	
    		contacts.setUserId((Integer) session.getAttribute("users"));
    		contactList = contactsService.getContactsList(contacts);
    		System.out.println("주소록 리스트 : " + contactList);
    		map.put("result", "success");
    		map.put("contacts", contactList);
    		
    		return map;
  
        }
        
        // ���̽��� ȸ�� ����
//        @RequestMapping("/fbInsertUsers.do")
//        @ResponseBody
//        public Object fbInsertUsers(@ModelAttribute("users") Users users ,
//                        HttpSession session) throws Exception {
//                System.out.println("fbInsertUsers controller start....");
//                HashMap<String, Object> map= new HashMap<String,Object>();
//                
//                session.removeAttribute("users");
//                users.setProfileImage("aaa.png");
//                
//                Users dbUsers = new Users();
//                dbUsers = (Users)usersService.checkUsers(users);
//                System.out.println("�ߺ� üũ:: "+dbUsers);
//                
//                if(dbUsers == null) {
//                        users.setUserNo(usersService.insertUsers(users));
//                        System.out.println("db �� users:"+users);
//
//                        // ���ǿ� ������ �̸��� ����ѹ��� ����
//                        Users newUsers = new Users();
//                        newUsers.setName(users.getName());
//                        newUsers.setUserNo(users.getUserNo());
//                        session.setAttribute("users", newUsers);
//                        map.put("users", newUsers);
//                        map.put("result", "success");
//                        
//                        System.out.println("fbInsertUsers controller end....");
//                        return map;
//                }else if(dbUsers.getGState().equals("F")) {
//                        map.put("result", "login");
//                }else if(dbUsers.getGState().equals("T")){
//                        map.put("result", "guide");
//                }else{
//                        map.put("result", "fail");
//                }
//                // ���ǿ� ������ �̸��� ����ѹ��� ����
//                Users newUsers = new Users();
//                newUsers.setName(dbUsers.getName());
//                newUsers.setUserNo(dbUsers.getUserNo());
//                session.setAttribute("users", newUsers);
//                map.put("users", newUsers);
//                System.out.println("session users����:"+session.getAttribute("users"));
//                
//                System.out.println("fbInsertUsers controller end....");
//                return map;
//        }
//        
        //로그인 컨트롤러
//        @RequestMapping("/loginUsers.do")
//        @ResponseBody
//        public Object loginUsers(@ModelAttribute("users") Users users ,
//                        HttpSession session) throws Exception {
//                
//                System.out.println("db 전:"+users);
//                HashMap<String, Object> map= new HashMap<String,Object>();
//
//                Users dbUsers = new Users();
//                dbUsers = (Users)usersService.getUsers(users);
//                System.out.println("db 후:"+dbUsers);
//                
//                if(dbUsers == null) {
//                        map.put("result", "noInformation");
//                        return map;
//                }
//                if(users.getEmail().equals(dbUsers.getEmail()) 
//                                && users.getPassword().equals(dbUsers.getPassword())) {
//                	map.put("result", "success");
//                	
//                	// 세션에 메일 주소만 유지
//                    Users sessionUser = new Users();
//                    sessionUser.setEmail(dbUsers.getEmail());
//                    session.setAttribute("users", sessionUser);
//                    map.put("users", sessionUser);
//                    
//                    System.out.println("session users 정보 : "+session.getAttribute("users"));
//                } else {
//                	map.put("result", "fail");
//                }
//                return map;
//        }
        
//        //�α׾ƿ�
//        @RequestMapping("/logoutUsers.do")
//        @ResponseBody
//        public Object logoutUsers(HttpSession session){
//                System.out.println("logoutUssers controller start....");
//        
//                HashMap<String, Object> map= new HashMap<String,Object>();
//                session.removeAttribute("users");
//                map.put("result", "success");
//                
//                System.out.println("session users����:"+session.getAttribute("users"));
//                System.out.println("logoutUssers controller end....");
//                        
//                return map;
//        }
//        
//        // ������ ����
//        @RequestMapping("/seeUsersInfo.do")
//        @ResponseBody
//        public Object seeUsersInfo(Users users, HttpSession session) throws Exception {
//                System.out.println("SeeUsersInfo Controller start...");
//                
//                //session userNo ��������
//                users = (Users)session.getAttribute("users");
//                
//                System.out.println("session users ����: "+users);
//                
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                
//                if(users == null){
//                        map.put("result", "logout");
//                        System.out.println("SeeUsersInfo Controller end...");
//                        return map;
//                }
//                
//                users = (Users)usersService.getUsers(users);
//                map.put("result", "success");
//                map.put("users", users);
//                
//                System.out.println("SeeUsersInfo Controller end...");
//                return map;
//        }
//        
//        // ������ ����
//        @RequestMapping("/updateUsers.do")
//        @ResponseBody
//        public Object updateUsers(@ModelAttribute("users") Users users, HttpSession session) throws Exception {
//                System.out.println("UpdateUsers Controller start...");
//                HashMap<String, Object> map = new HashMap<String, Object>();
//
//                System.out.println("�Ķ���� ���� ���� :: "+users);
//                
//                // db ���� ���� ������.
//                Users sUsers = new Users();
//                sUsers = (Users)session.getAttribute("users");        //session �������� userNo ����.
//                users.setUserNo(sUsers.getUserNo());
//
//                Users dbUsers = usersService.getUsers(sUsers);
//                System.out.println("DB User ����:: "+dbUsers);
//                
//                users.setEmailId(dbUsers.getEmailId());
//                users.setName(dbUsers.getName());
//                if(users.getProfileImage()==""){
//                        users.setProfileImage(dbUsers.getProfileImage());
//                }
//                
//                System.out.println("update�� ��:"+users);        
//
//                if(usersService.updateUsers(users)==1){
//                        map.put("result", "success");
//                }else{
//                        map.put("result", "fail");
//                }
//                                        
//                System.out.println("UpdateUsers Controller end...");
//                return map;
//        }
//        
//        //ȸ��Ż��
//        @RequestMapping("/deleteUsers.do")
//        @ResponseBody
//        public Object deleteUsers(HttpSession session, Users users, Guide guide) 
//                        throws Exception {
//                System.out.println("DeleteUsers Controller start...");
//                
//                //session userNo ��������
//                users = (Users)session.getAttribute("users");
//                System.out.println("session users ����: "+users);
//
//                
//                users = (Users)usersService.getUsers(users);
//                guide.setUserNo(users.getUserNo());
//                
//                joinGuideService.deleteGuide(guide);
//                usersService.deleteUsers(users);
//
//                session.removeAttribute("users");
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                map.put("result", "success");
//                
//                System.out.println("DeleteUsers Controller end...");
//                return map;
//        }
//        
//        //������ �����, login/guide ���� üũ
//        @RequestMapping("/checkAccount.do")
//        @ResponseBody
//        public Object checkAccount(HttpSession session, Users users) throws Exception {
//                System.out.println("<<<<<checkAccount Controller start...");
//
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        System.out.println("session users ����: "+session.getAttribute("users"));
//        if(session.getAttribute("users") == null){
//                System.out.println("<<<<<checkAccount Controller end...");
//                map.put("result", "logout");
//                return map;
//        }
//        users = (Users)session.getAttribute("users");
//        users = (Users)usersService.getUsers(users);
//        if(users == null){
//                System.out.println("<<<<<checkAccount Controller end...");
//                map.put("result", "fail");
//                return map;
//        }
//        if(users.getGState().equals("F")){
//            map.put("result", "login");
//        }else{
//            map.put("result", "guide");
//        }
//        map.put("users", users);
//
//        System.out.println("<<<<<checkAccount Controller end...");
//        return map;
//        }
}
