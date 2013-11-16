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
                System.out.println("UsersController() 생성자 start...");
        }
        
        @Inject
        UsersService usersService;
        
        //아이디(이메일) 중복 체크
//        @RequestMapping("/checkOverlap.do")
//        @ResponseBody
//        public Object checkOverlap(@ModelAttribute("users") Users users) throws Exception {
//                System.out.println("checkOverlap controller start....");
//                
//                System.out.println("db전:"+users);
//                
//                HashMap<String, Object> map= new HashMap<String,Object>();
//                Users dbUsers = new Users();
//                dbUsers = (Users)usersService.getUsers(users);
//                System.out.println("db후:"+dbUsers);
//                
//                if(dbUsers == null) {
//                        map.put("result", "success");
//                } else {
//                        map.put("result", "fail");
//                }
//                
//                System.out.println("checkOverlap controller end....");
//                return map;
//        }
        
        //회원가입
        @RequestMapping("/insertUsers.contact")
        @ResponseBody
        public Object insertUsers(@ModelAttribute("users") Users users,
                        HttpSession session) throws Exception {
                System.out.println("InsertUssers controller start....");
                HashMap<String, Object> map= new HashMap<String,Object>();
                
                Users dbUsers = new Users();
                dbUsers = (Users)usersService.getUsers(users);
                System.out.println("db후:"+dbUsers);
                
                if(dbUsers == null) {
                        users.setId(usersService.insertUsers(users));
                        System.out.println("db 후 users:"+users);

                        map.put("result", "success");
                        
//                        // 세션에 유저의 이름과 이메일만 저장
//                        Users newUsers = new Users();
//                        newUsers.setName(users.getName());
//                        newUsers.setEmail(users.getEmail());
//                        session.setAttribute("users", newUsers);
//                        map.put("users", newUsers);
                        
                } else {
                        map.put("result", "fail");
                }
                return map;
                
        }
        
        // 페이스북 회원 가입
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
//                System.out.println("중복 체크:: "+dbUsers);
//                
//                if(dbUsers == null) {
//                        users.setUserNo(usersService.insertUsers(users));
//                        System.out.println("db 후 users:"+users);
//
//                        // 세션에 유저의 이름과 유저넘버만 저장
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
//                // 세션에 유저의 이름과 유저넘버만 저장
//                Users newUsers = new Users();
//                newUsers.setName(dbUsers.getName());
//                newUsers.setUserNo(dbUsers.getUserNo());
//                session.setAttribute("users", newUsers);
//                map.put("users", newUsers);
//                System.out.println("session users정보:"+session.getAttribute("users"));
//                
//                System.out.println("fbInsertUsers controller end....");
//                return map;
//        }
//        
//        //로그인(가이드 여부 체크 포함)
//        @RequestMapping("/loginUsers.do")
//        @ResponseBody
//        public Object loginUsers(@ModelAttribute("users") Users users ,
//                        HttpSession session) throws Exception {
//                
//                System.out.println("db전:"+users);
//                HashMap<String, Object> map= new HashMap<String,Object>();
//
//                Users dbUsers = new Users();
//                dbUsers = (Users)usersService.getUsers(users);
//                System.out.println("db후:"+dbUsers);
//                
//                if(dbUsers == null) {
//                        map.put("result", "noInformation");
//                        return map;
//                }
//                if(users.getEmailId().equals(dbUsers.getEmailId()) 
//                                && users.getPassword().equals(dbUsers.getPassword())) {
//                        if (dbUsers.getGState().equals("F")) {
//                                map.put("result", "success");
//                        } else {
//                                map.put("result", "guide");
//                        }
//                        // 세션에 유저의 이름과 유저넘버만 저장
//                        Users newUsers = new Users();
//                        newUsers.setName(dbUsers.getName());
//                        newUsers.setUserNo(dbUsers.getUserNo());
//                        session.setAttribute("users", newUsers);
//                        map.put("users", newUsers);
//                        
//                        System.out.println("session users정보:"+session.getAttribute("users"));
//                } else {
//                        map.put("result", "fail");
//                }
//                return map;
//        }
//        
//        //로그아웃
//        @RequestMapping("/logoutUsers.do")
//        @ResponseBody
//        public Object logoutUsers(HttpSession session){
//                System.out.println("logoutUssers controller start....");
//        
//                HashMap<String, Object> map= new HashMap<String,Object>();
//                session.removeAttribute("users");
//                map.put("result", "success");
//                
//                System.out.println("session users정보:"+session.getAttribute("users"));
//                System.out.println("logoutUssers controller end....");
//                        
//                return map;
//        }
//        
//        // 내정보 보기
//        @RequestMapping("/seeUsersInfo.do")
//        @ResponseBody
//        public Object seeUsersInfo(Users users, HttpSession session) throws Exception {
//                System.out.println("SeeUsersInfo Controller start...");
//                
//                //session userNo 가져오기
//                users = (Users)session.getAttribute("users");
//                
//                System.out.println("session users 정보: "+users);
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
//        // 내정보 수정
//        @RequestMapping("/updateUsers.do")
//        @ResponseBody
//        public Object updateUsers(@ModelAttribute("users") Users users, HttpSession session) throws Exception {
//                System.out.println("UpdateUsers Controller start...");
//                HashMap<String, Object> map = new HashMap<String, Object>();
//
//                System.out.println("파라미터 유저 정보 :: "+users);
//                
//                // db 유저 정보 가져옴.
//                Users sUsers = new Users();
//                sUsers = (Users)session.getAttribute("users");        //session 정보에서 userNo 받음.
//                users.setUserNo(sUsers.getUserNo());
//
//                Users dbUsers = usersService.getUsers(sUsers);
//                System.out.println("DB User 정보:: "+dbUsers);
//                
//                users.setEmailId(dbUsers.getEmailId());
//                users.setName(dbUsers.getName());
//                if(users.getProfileImage()==""){
//                        users.setProfileImage(dbUsers.getProfileImage());
//                }
//                
//                System.out.println("update할 값:"+users);        
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
//        //회원탈퇴
//        @RequestMapping("/deleteUsers.do")
//        @ResponseBody
//        public Object deleteUsers(HttpSession session, Users users, Guide guide) 
//                        throws Exception {
//                System.out.println("DeleteUsers Controller start...");
//                
//                //session userNo 가져오기
//                users = (Users)session.getAttribute("users");
//                System.out.println("session users 정보: "+users);
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
//        //페이지 변경시, login/guide 여부 체크
//        @RequestMapping("/checkAccount.do")
//        @ResponseBody
//        public Object checkAccount(HttpSession session, Users users) throws Exception {
//                System.out.println("<<<<<checkAccount Controller start...");
//
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        System.out.println("session users 정보: "+session.getAttribute("users"));
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
