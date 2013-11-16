package com.iContacts.bitschool.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iContacts.bitschool.dao.UsersDao;
import com.iContacts.bitschool.domain.Users;

@Repository
public class UsersDaoImpl extends SqlSessionDaoSupport implements UsersDao {
        
        public UsersDaoImpl() {
                System.out.println("UserDaoImpl start...");
        }

        @Override
        public Integer insertUsers(Users users) throws Exception {
                return (Integer) getSqlSession().insert("com.iContacts.bitschool.dao.UsersDao.insertUsers", users);
        }

        @Override
        public Users getUsers(Users users) throws Exception {
                return (Users)getSqlSession().selectOne("com.iContacts.bitschool.dao.UsersDao.getUsers", users);
        }

        @Override
        public int updateUsers(Users users) throws Exception {
                return getSqlSession().update("com.iContacts.bitschool.dao.UsersDao.updateUsers", users);
        }

        @Override
        public int deleteUsers(Users users) throws Exception {
                return getSqlSession().delete("com.iContacts.bitschool.dao.UsersDao.deleteUsers", users);
        }

        
        // 이메일 아이디로만 유저 체크
        @Override
        public Users checkUsers(Users users) throws Exception {
                return (Users)getSqlSession().selectOne("Users.checkUsers", users);
        }
}
