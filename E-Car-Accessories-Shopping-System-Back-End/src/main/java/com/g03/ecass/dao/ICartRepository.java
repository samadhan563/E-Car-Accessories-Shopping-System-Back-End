package com.g03.ecass.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.g03.ecass.pojos.entity.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer> {
	@Query("select c from Cart c where c.userId=:uId")
	List<Cart> getCartByuserId(@Param("uId")int uId);
	
	@Query("select SUM(c.finalPrice) from Cart c where c.userId=:uId")
	Double getCartTotalAmt(@Param("uId") int uId);
	
	@Query("select SUM(c.productPrice) from Cart c where c.userId=:uId")
	Double getCartTotalSAmt(@Param("uId") int uId);
	
	String deleteByUserId(int userId);
	
	List<Cart> findByUserId(int userId);
	}
