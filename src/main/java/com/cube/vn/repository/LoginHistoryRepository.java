package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.LoginHistory;
import com.cube.vn.dao.LoginHistoryPK;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, LoginHistoryPK> {
	@Query("SELECT lh FROM LoginHistory lh WHERE user_id=(:userId)")
	List<LoginHistory> getLoginTimeOfUser(@Param("userId") String userId);
}
