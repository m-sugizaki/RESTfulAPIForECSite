package com.cube.vn.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.User;
import com.cube.vn.dao.UserPK;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindByUserId_HasData() {
		User user = new User();
		UserPK userPK = new UserPK();

		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);

		this.testEntityManager.persist(user);
		this.testEntityManager.flush();
		
		User user2 = this.userRepository.findByUserId(userPK.getUserId());

		Assert.assertTrue(user2 != null);
		Assert.assertEquals(user2.getPrimaryKey().getUserId(), user.getPrimaryKey().getUserId());
		Assert.assertEquals(user2.getAddress1(), user.getAddress1());
		Assert.assertEquals(user2.getAddress2(), user.getAddress2());
		Assert.assertEquals(user2.getBirthday(), user.getBirthday());
		Assert.assertEquals(user2.getEmail(), user.getEmail());
		Assert.assertEquals(user2.getMemberRank(), user.getMemberRank());
		Assert.assertEquals(user2.getName(), user.getName());
		Assert.assertEquals(user2.getNickname(), user.getNickname());
		Assert.assertEquals(user2.getPhoneNumber(), user.getPhoneNumber());
		Assert.assertEquals(user2.getPostalCode(), user.getPostalCode());
	}

	@Test
	public void testGetUserInfoByUserIdList_HasData() {
		User user = new User();
		UserPK userPK = new UserPK();
		User user2 = new User();
		UserPK userPK2 = new UserPK();
		List<User> userList = null;
		List<String> userIdList = new ArrayList<String>();

		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		userPK2.setUserId("testuser2");
		user2.setAddress1("都道府県、市町村、丁目番地");
		user2.setAddress2("ビル、マンション");
		user2.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user2.setEmail("testuser@gmail.com");
		user2.setMemberRank("一般");
		user2.setName("Test Test");
		user2.setNickname("Test");
		user2.setPhoneNumber("11111-1111-1111");
		user2.setPostalCode("111-1111");
		user2.setPrimaryKey(userPK2);

		this.testEntityManager.persist(user);
		this.testEntityManager.persist(user2);
		this.testEntityManager.flush();
		
		userIdList.add(userPK.getUserId());
		userIdList.add(userPK2.getUserId());
		
		userList = this.userRepository.getUserInfoByUserIdList(userIdList);

		Assert.assertTrue(userList != null && userList.size() == 2);
		Assert.assertTrue(userList.contains(user));
		Assert.assertTrue(userList.contains(user2));
	}

	@Test
	public void testFindByUserId_NoData() {
		Assert.assertTrue(this.userRepository.findByUserId("testuser4") == null);
	}

	@Test
	public void testGetUserInfoByUserIdList_NoData() {
		List<String> userIdList = new ArrayList<String>();
		List<User> userList = null;

		userIdList.add("testuser2");
		userIdList.add("testuser3");
		
		userList = this.userRepository.getUserInfoByUserIdList(userIdList);
		
		Assert.assertTrue(userList != null && userList.size( )== 0);
	}
}
