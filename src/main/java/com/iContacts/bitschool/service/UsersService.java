package com.iContacts.bitschool.service;

import com.iContacts.bitschool.domain.Users;

public interface UsersService {
    public Integer insertUsers(Users users) throws Exception;
    public Users getUsers(Users users) throws Exception;
    public int updateUsers(Users users) throws Exception;
    public int deleteUsers(Users users) throws Exception;
    // �̸��� ���̵�θ� ���� üũ
    public Users checkUsers(Users users) throws Exception;
}
