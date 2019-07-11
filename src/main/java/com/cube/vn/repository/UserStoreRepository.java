package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.UserStore;
import com.cube.vn.dao.UserStorePK;

@Repository
public interface UserStoreRepository extends JpaRepository<UserStore, UserStorePK> {
	@Query("SELECT us FROM UserStore us WHERE user_id=(:userId) AND password= (:pass)")
	List<UserStore> findByUserIdAndPass(@Param("userId") String userId, @Param("pass") String pass);
}
