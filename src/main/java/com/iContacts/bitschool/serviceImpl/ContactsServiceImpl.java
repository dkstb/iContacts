package com.iContacts.bitschool.serviceImpl;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iContacts.bitschool.dao.ContactsDao;
import com.iContacts.bitschool.domain.Contacts;
import com.iContacts.bitschool.domain.Users;
import com.iContacts.bitschool.service.ContactsService;

@Repository
public class ContactsServiceImpl extends SqlSessionDaoSupport implements
		ContactsService {

	@Inject
	ContactsDao contactsDao;
	
	public ContactsServiceImpl() {
		System.out.println("ContactsServiceImpl 생성...");
	}

	@Override
	public int insertContacts(Contacts contacts) throws Exception {
		return contactsDao.insertContacts(contacts);
	}

	@Override	// 주소록 리스트 가져오기
	public List<Contacts> getContactsList(Contacts contacts) throws Exception {
		return contactsDao.getContactsList(contacts);
	}
	
	@Override	// 상세 주소 조회
	public Contacts getContact(Contacts contacts) throws Exception {
		return contactsDao.getContact(contacts);
	}

	@Override
	public int updateContacts(Contacts contacts) throws Exception {
		return contactsDao.updateContacts(contacts);
	}

	@Override
	public int deleteContacts(Contacts contacts) throws Exception {
		return contactsDao.deleteContacts(contacts);
	}

	@Override
	public int deleteAllContacts(Users users) throws Exception {
		return contactsDao.deleteAllContacts(users);
	}

	@Override	// 테스트용 주소록 전체 삭제
	public int deleteAllContactsForTest() throws Exception {
		return contactsDao.deleteAllContactsForTest();
	}

	

}
