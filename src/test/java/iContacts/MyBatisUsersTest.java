package iContacts;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.iContacts.bitschool.domain.Users;
import com.iContacts.bitschool.service.UsersService;

public class MyBatisUsersTest {

	@Test
	public void test() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/mybatis/mybatis-context.xml");
		UsersService usersService = (UsersService) context
				.getBean("usersServiceImpl");

		// 1. UsersService.insertUsers Test

		// Test Users instance »ý¼º
		Users users = new Users();
		users.setEmail("win@hanmail.net");
		users.setPassword("1234");
		users.setName("±èÀ§³ª");

		assertNotNull(usersService.insertUsers(users));
	}

}
