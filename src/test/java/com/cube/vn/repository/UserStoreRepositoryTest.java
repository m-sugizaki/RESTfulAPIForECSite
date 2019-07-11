package com.cube.vn.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.UserStore;
import com.cube.vn.dao.UserStorePK;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserStoreRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private UserStoreRepository userStoreRepository;

	@Test
    public void testFindByUserIdAndPass_HasData() {
		UserStorePK pk = new UserStorePK();
		UserStore userStore = new UserStore();
		List<UserStore> listUserStoreFoundByUserAndPass = null;
		
		pk.setUserId("testuser8");
		userStore.setPrimaryKey(pk);
		userStore.setPassword("123456");
		
		this.testEntityManager.persist(userStore);
		this.testEntityManager.flush();
		
		listUserStoreFoundByUserAndPass = this.userStoreRepository.findByUserIdAndPass(pk.getUserId(), userStore.getPassword());
		
		Assert.assertTrue(listUserStoreFoundByUserAndPass != null && listUserStoreFoundByUserAndPass.size() == 1);
		Assert.assertEquals(userStore.getPrimaryKey().getUserId(), listUserStoreFoundByUserAndPass.get(0).getPrimaryKey().getUserId());
		Assert.assertEquals(userStore.getPassword(), listUserStoreFoundByUserAndPass.get(0).getPassword());
	}
	
	@Test
    public void testFindByUserIdAndPass_NoData() {
		List<UserStore> listUserStoreFoundByUserAndPass = null;

		listUserStoreFoundByUserAndPass = this.userStoreRepository.findByUserIdAndPass("testuser8", "123456");
		
		Assert.assertTrue(listUserStoreFoundByUserAndPass != null && listUserStoreFoundByUserAndPass.size() == 0);
	}
}
