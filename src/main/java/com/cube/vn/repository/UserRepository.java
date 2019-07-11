package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.User;
import com.cube.vn.dao.UserPK;

@Repository
public interface UserRepository extends JpaRepository<User, UserPK> {
	@Query("SELECT u FROM User u WHERE user_id=(:userId)")
	User findByUserId(@Param("userId") String userId);
	
	@Query("SELECT u FROM User u WHERE user_id IN :userIdLst")
	List<User> getUserInfoByUserIdList(@Param("userIdLst") List<String> userIdLst);
}
