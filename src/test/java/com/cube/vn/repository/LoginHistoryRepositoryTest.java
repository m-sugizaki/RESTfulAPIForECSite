package com.cube.vn.repository;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.LoginHistory;
import com.cube.vn.dao.LoginHistoryPK;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoginHistoryRepositoryTest {
	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;

	@Test
    public void testGetLoginTimeOfUser_HasData() {
		LoginHistoryPK pk = new LoginHistoryPK();
		LoginHistory loginHistory = new LoginHistory();
		List<LoginHistory> listLoginTimeOfUser = null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		pk.setUserId("testuser");
		pk.setLoginDt(TestUtil.getDate(2019, 5, 13, 14, 23, 0));
		loginHistory.setPrimaryKey(pk);
		this.testEntityManager.persist(loginHistory);
		this.testEntityManager.flush();
		this.loginHistoryRepository.save(loginHistory);
		listLoginTimeOfUser = this.loginHistoryRepository.getLoginTimeOfUser(pk.getUserId());

		Assert.assertTrue(listLoginTimeOfUser != null && listLoginTimeOfUser.size() == 1);
		Assert.assertEquals(loginHistory.getPrimaryKey().getUserId(), listLoginTimeOfUser.get(0).getPrimaryKey().getUserId());
		Assert.assertTrue(dateformat.format(loginHistory.getPrimaryKey().getLoginDt()).equals(
				dateformat.format(listLoginTimeOfUser.get(0).getPrimaryKey().getLoginDt())));
	}
	
	@Test
    public void testGetLoginTimeOfUser_NoData() {
		List<LoginHistory> listLoginTimeOfUser = null;
		
		listLoginTimeOfUser = this.loginHistoryRepository.getLoginTimeOfUser("testuser");

		Assert.assertTrue(listLoginTimeOfUser != null && listLoginTimeOfUser.size() == 0);
	}
}
