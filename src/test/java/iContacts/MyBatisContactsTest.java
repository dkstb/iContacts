package iContacts;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.iContacts.bitschool.domain.Users;
import com.iContacts.bitschool.service.UsersService;

public class MyBatisContactsTest {

	@Test
	public void insertUsersTest() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		UsersService usersService = (UsersService) context.getBean("usersServiceImpl");

		// 1. UsersService.insertUsers Test
		usersService.deleteAllUsers();
		
		// Test Users instance 생성자
		Users users = new Users();
		users.setEmail("win@hanmail.net");
		users.setPassword("1234");
		users.setName("김위나");

		assertNotNull(usersService.insertUsers(users));
	}
	
	@Test
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
