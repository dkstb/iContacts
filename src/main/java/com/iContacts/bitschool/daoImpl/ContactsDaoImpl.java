package com.iContacts.bitschool.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.iContacts.bitschool.dao.ContactsDao;
import com.iContacts.bitschool.domain.Contacts;

public class ContactsDaoImpl extends SqlSessionDaoSupport implements ContactsDao {
	
	public ContactsDaoImpl() {
		System.out.println("ContactsDaoImpl 생성...");
	}

	@Override
	public int insertContacts(Contacts contacts) throws Exception {
		return (Integer) getSqlSession().insert("com.iContacts.bitschool.dao.UsersDao.insertContacts", contacts);
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
		return getSqlSession().delete("com.iContacts.bitschool.dao.ContactsDao.deleteAllUsers");
	}

}
