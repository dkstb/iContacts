package com.iContacts.bitschool.service;

import com.iContacts.bitschool.domain.Contacts;

public interface ContactsService {
	public int insertContacts(Contacts contacts) throws Exception;
    public Contacts getContacts(Contacts contacts) throws Exception;
    public int updateContacts(Contacts contacts) throws Exception;
    public int deleteContacts(Contacts contacts) throws Exception;
    
    // 테스트용 전체 연락처 삭제
    public int deleteAllContacts() throws Exception;
}
