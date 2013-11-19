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

	@Test
	public void insertContactsTest() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		ContactsService contactsService = (ContactsService)context.getBean("contactsServiceImpl");
		UsersService usersService = (UsersService)context.getBean("usersServiceImpl");

		// 유저 생성
		contactsService.deleteAllContacts();	// 모든 주소록 삭제
		usersService.deleteAllUsers();			// 모든 유저 삭제
		
		Users users = new Users();
		users.setEmail("win@hanmail.net");
		users.setPassword("1234");
		users.setName("김위나");
		usersService.insertUsers(users);
		
		System.out.println("users.getId() : " + users.getId());

		// 주소록 생성
		Contacts contacts = new Contacts();
		contacts.setUserId(users.getId());
		contacts.setName("고동주");
		contacts.setPhoneNum("010-8523-7340");
		contacts.setEmail("godongsori@gmail.com");
		contacts.setWork("비트컴퓨터");
		contacts.setHomeAddr("서울시 강북구 송천동 423-5 석빌라 101호");
		contacts.setMemo("미니 프로젝트 조장");

		assertNotNull(contactsService.insertContacts(contacts));
	}
	
//	@Test
	public void loginTest() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		UsersService usersService = (UsersService) context.getBean("usersServiceImpl");
		
		usersService.deleteAllUsers();		// 전체 유저 삭제
		
		// Test Users instance 생성
		Users users = new Users();
		users.setEmail("win@hanmail.net");
		users.setPassword("1234");
		users.setName("김위나");
		
		usersService.insertUsers(users);	// 회원가입
		
		// 로그인 성공 유저 생성
		Users loginSuccessUsers = new Users();
		loginSuccessUsers.setEmail("win@hanmail.net");
		loginSuccessUsers.setPassword("1234");
		loginSuccessUsers.setName("김위나");
		
		// 로그인 실패 유저 생성
		Users loginFailUsers = new Users();
		loginFailUsers.setEmail("win@hanmail.net");
		loginFailUsers.setPassword("1111");
		loginFailUsers.setName("김위나");
		
		// 디비 유저 확인
		Users dbSuccessUser = new Users();
		Users dbFailUser = new Users();
		dbSuccessUser = usersService.getUsers(loginSuccessUsers);
		dbFailUser = usersService.getUsers(loginSuccessUsers);
		
		assertEquals(loginSuccessUsers.getEmail(), dbSuccessUser.getEmail());
		assertEquals(loginSuccessUsers.getPassword(), dbSuccessUser.getPassword());
		assertNotSame(loginFailUsers.getEmail(), dbFailUser.getEmail());
		assertNotSame(loginFailUsers.getEmail(), dbFailUser.getPassword());
	}
}
