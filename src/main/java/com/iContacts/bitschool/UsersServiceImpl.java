package com.iContacts.bitschool;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.iContacts.bitschool.dao.UsersDao;
import com.iContacts.bitschool.domain.Users;
import com.iContacts.bitschool.service.UsersService;

@Repository
public class UsersServiceImpl implements UsersService {
        
        @Inject
        UsersDao usersDao;
        
        public UsersServiceImpl() {
                // TODO Auto-generated constructor stub
        }

        @Override
        public Integer insertUsers(Users users) throws Exception {
                return usersDao.insertUsers(users);
        }

        @Override
        public Users getUsers(Users users) throws Exception {
                return (Users)usersDao.getUsers(users);
        }

        @Override
        public int updateUsers(Users users) throws Exception {
                // TODO Auto-generated method stub
                return usersDao.updateUsers(users);
        }

        @Override
        public int deleteUsers(Users users) throws Exception {
                // TODO Auto-generated method stub
                return usersDao.deleteUsers(users);
        }


        // 이메일 아이디로만 유저 체크
        @Override
        public Users checkUsers(Users users) throws Exception {
                return (Users)usersDao.checkUsers(users);
        }
}