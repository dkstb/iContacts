package com.iContacts.bitschool.controller;

import java.util.HashMap;

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
public class UsersController {

        public UsersController() {
                System.out.println("UsersController() start...");
        }
        
        @Inject
        UsersService usersService;
        @Inject
        ContactsService contactsService;
        
	// 로그인 체크
	@RequestMapping("/checkUsers.contact")
	@ResponseBody
	public Object checkUsers(HttpSession session, Users users) throws Exception {
		System.out.println("<<<<<checkUsers Controller start...");

		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println("session users 정보 : "
				+ session.getAttribute("users"));
		if (session.getAttribute("users") == null) {
			System.out.println("<<<<<checkUsers Controller end...");
			map.put("result", "logout");
			return map;
		}
		users = (Users) session.getAttribute("users");
		users = (Users) usersService.getUsers(users);
		if (users == null) {
			
			System.out.println("<<<<<checkUsers Controller end...");
			map.put("result", "fail");
			return map;
		}

		map.put("result", "login");
		map.put("users", users);
		
		System.out.println("<<<<<checkUsers Controller end...");
		return map;
	}
        
	// 회원 가입
	@RequestMapping("/insertUsers.contact")
	@ResponseBody
	public Object insertUsers(@ModelAttribute("users") Users users,
			HttpSession session) throws Exception {
		System.out.println("InsertUssers controller start....");
		HashMap<String, Object> map = new HashMap<String, Object>();

		Users dbUsers = new Users();
		dbUsers = (Users) usersService.getUsers(users);
		System.out.println("db 후:" + dbUsers);

		if (dbUsers == null) {
			users.setId(usersService.insertUsers(users));
			System.out.println("db 후 users:" + users);

			map.put("result", "success");

			// 로그인 상태 만들기 (세션 유지)
			Users sessionUsers = new Users();
			sessionUsers.setId(users.getId());
			sessionUsers.setName(users.getName());
			sessionUsers.setEmail(users.getEmail());
			session.setAttribute("users", sessionUsers);
			map.put("users", sessionUsers);
			
		} else {
			map.put("result", "fail");
		}
		return map;
	}
        
    //로그인 컨트롤러
    @RequestMapping("/loginUsers.contact")
    @ResponseBody
    public Object loginUsers(@ModelAttribute("users") Users users ,
                HttpSession session) throws Exception {
            
        System.out.println("db 전:"+users);
        HashMap<String, Object> map= new HashMap<String,Object>();

        Users dbUsers = new Users();
        dbUsers = (Users)usersService.getUsers(users);
        System.out.println("db 후:"+dbUsers);
        
        if(dbUsers == null) {
                map.put("result", "noInformation");
                return map;
        }
        if(users.getEmail().equals(dbUsers.getEmail()) 
                        && users.getPassword().equals(dbUsers.getPassword())) {
        	map.put("result", "success");
        	// 로그인 상태 만들기 (세션 유지)
			Users sessionUsers = new Users();
			sessionUsers.setId(dbUsers.getId());
			sessionUsers.setName(dbUsers.getName());
			sessionUsers.setEmail(dbUsers.getEmail());
			session.setAttribute("users", sessionUsers);
			map.put("users", sessionUsers);
            
            System.out.println("session users 정보 : "+session.getAttribute("users"));
        } else {
        	map.put("result", "fail");
        }
        return map;
    }
        
        //로그아웃 컨트롤러
        @RequestMapping("/logout.contact")
        @ResponseBody
        public Object logoutUsers(HttpSession session){
                System.out.println("logoutUssers controller start....");
        
                HashMap<String, Object> map= new HashMap<String,Object>();
                session.removeAttribute("users");
                map.put("result", "success");
                
                System.out.println("session users 정보:"+session.getAttribute("users"));
                System.out.println("logoutUssers controller end....");
                        
                return map;
        }
       
        // 내 정보 보기
        @RequestMapping("/selectUsers.contact")
        @ResponseBody
        public Object seeUsersInfo(Users users, HttpSession session) throws Exception {
                System.out.println("SeeUsersInfo Controller start...");
                
                //session 이메일 정보
                users = (Users)session.getAttribute("users");
                
                System.out.println("session users 정보 : "+users);
                
                HashMap<String, Object> map = new HashMap<String, Object>();
                
                if(users == null){
                        map.put("result", "logout");
                        System.out.println("SeeUsersInfo Controller end...");
                        return map;
                }
                
                users = (Users)usersService.getUsers(users);
                map.put("result", "success");
                map.put("users", users);
                
                System.out.println("SeeUsersInfo Controller end...");
                return map;
        }
       
        
        // 내정보 업데이트 
        @RequestMapping("/updateUser.contact")
        @ResponseBody
        public Object updateUsers(@ModelAttribute("users") Users users, HttpSession session) throws Exception {
                System.out.println("UpdateUsers Controller start...");
                HashMap<String, Object> map = new HashMap<String, Object>();

                System.out.println(" 내정보 : "+users);
                
                if(usersService.updateUsers(users)==1){
                        map.put("result", "success");
                        System.out.println("db after");
                }else{
                        map.put("result", "fail");
                }
                                        
                System.out.println("UpdateUsers Controller end...");
                return map;
        }
        
        
     //   User 삭제
    @RequestMapping("/deleteUser.contact")
    @ResponseBody
    public Object deleteUser(Contacts contacts, Users users,
                    HttpSession session) throws Exception {
        System.out.println("DeleteContact controller start....");
        HashMap<String, Object> map= new HashMap<String,Object>();
        
        System.out.println("주소록 : " + contacts);
        
        // 세션에서 로그인 한 유저 정보 가져오기
		users = ((Users)session.getAttribute("users"));
		
		// 파라미터로 받은 주소록에 세션에 있는 유저 아이디 세팅
		contacts.setUserId(users.getId());
		
		contactsService.deleteAllContacts(contacts);   		
		usersService.deleteUsers(users);
		map.put("result", "success");
		
		//로그아웃
		session.removeAttribute("users");
		
		return map;
    }
        
        
        
        
        
        
        //ȸ��Ż��
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
}
