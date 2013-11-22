package com.iContacts.bitschool.dao;

import java.util.List;

import com.iContacts.bitschool.domain.Contacts;

public interface ContactsDao {
	public int insertContacts(Contacts contacts) throws Exception;						// 주소록 생성
    public List<Contacts> getContactsList(Contacts contacts) throws Exception;			// 주소록 리스트 가져오기
    public List<Contacts> getSearchContactsList(Contacts contacts) throws Exception;	// 주소록 검색 리스트 가져오기
    public Contacts getContact(Contacts contacts) throws Exception;						// 상세 주소 조회
    public int updateContacts(Contacts contacts) throws Exception;
    public int deleteContacts(Contacts contacts) throws Exception;
    public int deleteAllContacts(Contacts contacts) throws Exception;
    
    // 테스트용 전체 연락처 삭제
	public int deleteAllContactsForTest();
}
