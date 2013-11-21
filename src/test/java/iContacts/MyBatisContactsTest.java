package iContacts;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.iContacts.bitschool.domain.Contacts;
import com.iContacts.bitschool.domain.Users;
import com.iContacts.bitschool.service.ContactsService;
import com.iContacts.bitschool.service.UsersService;

public class MyBatisContactsTest {

	@Test	// 주소록 생성 테스트
	public void insertContactsTest() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		ContactsService contactsService = (ContactsService)context.getBean("contactsServiceImpl");
		UsersService usersService = (UsersService)context.getBean("usersServiceImpl");

		Contacts contacts = contactsInit(contactsService, usersService);	// 주소록 테스트 초기화

		assertNotNull(contactsService.insertContacts(contacts));
	}

	@Test	// 주소록 조회 테스트
	public void getContactsListTest() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		ContactsService contactsService = (ContactsService)context.getBean("contactsServiceImpl");
		UsersService usersService = (UsersService)context.getBean("usersServiceImpl");
		
		Contacts contacts = contactsInit(contactsService, usersService);	// 주소록 테스트 초기화
		
		contactsService.insertContacts(contacts);	// 주소록 디비에 입력
		
		assertNotNull(contactsService.getContactsList(contacts));	// 리스트 불러와서 널 아닌 것 테스트
	}
	
	@Test 	// 상세 주소 조회 테스트
	public void getContactTest() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		ContactsService contactsService = (ContactsService)context.getBean("contactsServiceImpl");
		UsersService usersService = (UsersService)context.getBean("usersServiceImpl");
		
		Contacts contacts = contactsInit(contactsService, usersService);	// 주소록 테스트 초기화
		contactsService.insertContacts(contacts);	// 주소록 디비에 입력
		assertNotNull(contactsService.getContact(contacts));	// 상세 주소 조회
	}
	
	@Test	// 상세 주소 업데이트 테스트
	public void updateContact() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		ContactsService contactsService = (ContactsService)context.getBean("contactsServiceImpl");
		UsersService usersService = (UsersService)context.getBean("usersServiceImpl");
		
		Contacts contacts = contactsInit(contactsService, usersService);	// 주소록 테스트 초기화
		
		contactsService.insertContacts(contacts);
		
		// 주소록 정보 수정
		contacts.setEmail("kodongju@naver.com");
		contacts.setPhoneNum("010-1111-1111");
		
		// 업데이트 실행
		assertNotNull(contactsService.updateContacts(contacts));
	}
	
	@Test
	public void deleteContact() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		ContactsService contactsService = (ContactsService)context.getBean("contactsServiceImpl");
		UsersService usersService = (UsersService)context.getBean("usersServiceImpl");
		
		Contacts contacts = contactsInit(contactsService, usersService);	// 주소록 테스트 초기화
		
		contactsService.insertContacts(contacts);
		
		contactsService.deleteContacts(contacts);
		
		assertNull(contactsService.getContact(contacts));
	}
	
	private Contacts contactsInit(ContactsService contactsService,
			UsersService usersService) throws Exception {
		// 테스트 초기화
		contactsService.deleteAllContactsForTest();	// 모든 주소록 삭제
		usersService.deleteAllUsers();			// 모든 유저 삭제

		// 유저 생성
		Users users = new Users();
		users.setEmail("win@hanmail.net");
		users.setPassword("1234");
		users.setName("김위나");
		usersService.insertUsers(users);
		
		// 주소록 생성
		Contacts contacts = new Contacts();
		contacts.setUserId(users.getId());
		contacts.setName("고동주");
		contacts.setPhoneNum("010-8523-7340");
		contacts.setEmail("godongsori@gmail.com");
		contacts.setWork("비트컴퓨터");
		contacts.setHomeAddr("서울시 강북구 송천동 423-5 석빌라 101호");
		contacts.setMemo("미니 프로젝트 조장");
		return contacts;
	}

}
