package com.bankapp.bankappapi.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;

@Component
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	User save(User user);

	User findByMobileNumberAndPasswordAndStatus(String MobileNumber, String Password, String status);

	User findByMobileNumberAndPasswordAndUser(String MobileNumber, String Password, String type);

	User findByMobileNumberAndPassword(String MobileNumber, String Password);

	@Transactional
	@Modifying
	@Query("update banking u set u.amount = :amount where u.mobileNumber=:MobileNumber")
	void changeAmount(@Param("amount") int amount, @Param("MobileNumber") String MobileNumber);

	User findByMobileNumber(String mobileNumber);

	User findByAccountNumber(int accountNumber);

	Transaction save(Transaction transaction);

	@Modifying
	@Query("select u from com.bankapp.bankappapi.model.Transaction u where u.mobileNumber=:mobilenumber")
	List<Transaction> findbymobileNo(@Param("mobilenumber") String mobilenumber);

}