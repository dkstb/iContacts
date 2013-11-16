package com.iContacts.bitschool.dao;

import com.iContacts.bitschool.domain.Users;

public interface UsersDao {
	public Integer insertUsers(Users users) throws Exception;
    public Users getUsers(Users users) throws Exception;
    public int updateUsers(Users users) throws Exception;
    public int deleteUsers(Users users) throws Exception;
    // 이메일 아이디로만 유저 체크
    public Users checkUsers(Users users) throws Exception;
}
