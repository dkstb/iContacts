package com.iContacts.bitschool.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iContacts.bitschool.domain.Users;
import com.iContacts.bitschool.service.UsersService;

@Controller
@RequestMapping("/ajax")
public class UsersController {

        public UsersController() {
                System.out.println("UsersController() start...");
        }
        
        @Inject
        UsersService usersService;
        
	// 로그인 체크
	@RequestMapping("/checkUsers.contact")
	@ResponseBody
	public Object checkUsers(HttpSession session, Users users) throws Exception {
		System.out.println("<<<<<checkUsers Controller start...");

		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println("session users 정보 : "
				+ session.getAttribute("users"));
		if (session.getAttribute("users") == null) {
			System.out.println("3");
			System.out.println("<<<<<checkUsers Controller end...");
			map.put("result", "logout");
			return map;
		}
		users = (Users) session.getAttribute("users");
		users = (Users) usersService.getUsers(users);
		if (users == null) {
			System.out.println("2");
			System.out.println("<<<<<checkUsers Controller end...");
			map.put("result", "fail");
			return map;
		}

		map.put("result", "login");
		map.put("users", users);
		System.out.println("1");
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
}
