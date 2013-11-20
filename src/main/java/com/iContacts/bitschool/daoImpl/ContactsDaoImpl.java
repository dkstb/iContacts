package com.iContacts.bitschool.daoImpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iContacts.bitschool.dao.ContactsDao;
import com.iContacts.bitschool.domain.Contacts;
import com.iContacts.bitschool.domain.Users;

@Repository
public class ContactsDaoImpl extends SqlSessionDaoSupport implements ContactsDao {
	
	public ContactsDaoImpl() {
		System.out.println("ContactsDaoImpl 생성...");
	}

	@Override
	public int insertContacts(Contacts contacts) throws Exception {
		return (Integer) getSqlSession().insert("com.iContacts.bitschool.dao.ContactsDao.insertContacts", contacts);
	}

	@SuppressWarnings("unchecked")
	@Override	// 주소록 리스트 가져오기
	public List<Contacts> getContactsList(Contacts contacts) throws Exception {
		return (List<Contacts>)getSqlSession().selectList("com.iContacts.bitschool.dao.ContactsDao.getContactsList", contacts);
	}
	
	@Override	// 상세 주소 조회
	public Contacts getContact(Contacts contacts) throws Exception {
		return (Contacts)getSqlSession().selectOne("com.iContacts.bitschool.dao.ContactsDao.getContact", contacts);
	}

	@Override
	public int updateContacts(Contacts contacts) throws Exception {
		return (Integer)getSqlSession().update("com.iContacts.bitschool.dao.ContactsDao.updateContacts", contacts);
	}

	@Override
	public int deleteContacts(Contacts contacts) throws Exception {
		return (Integer)getSqlSession().delete("com.iContacts.bitschool.dao.ContactsDao.deleteContacts", contacts);
	}

	@Override
	public int deleteAllContacts(Users users) throws Exception {
		return getSqlSession().delete("com.iContacts.bitschool.dao.ContactsDao.deleteAllContacts");
	}

}
