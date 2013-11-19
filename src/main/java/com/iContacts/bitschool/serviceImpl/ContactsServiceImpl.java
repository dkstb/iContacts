package com.iContacts.bitschool.serviceImpl;

import javax.inject.Inject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iContacts.bitschool.dao.ContactsDao;
import com.iContacts.bitschool.domain.Contacts;
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

	@Override
	public Contacts getContacts(Contacts contacts) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateContacts(Contacts contacts) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteContacts(Contacts contacts) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAllContacts() throws Exception {
		return contactsDao.deleteAllContacts();
	}

}
