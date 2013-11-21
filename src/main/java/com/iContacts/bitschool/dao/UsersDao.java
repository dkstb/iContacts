package com.iContacts.bitschool.dao;

import com.iContacts.bitschool.domain.Users;

public interface UsersDao {
	public Integer insertUsers(Users users) throws Exception;
    public Users getUsers(Users users) throws Exception;
    public int updateUsers(Users users) throws Exception;
    public int deleteUsers(Users users) throws Exception;
    // 이메일로 체크
    public Users checkUsers(Users users) throws Exception;
    
    // 테스트용 전체 유저 삭제
    public int deleteAllUsers() throws Exception;
}
