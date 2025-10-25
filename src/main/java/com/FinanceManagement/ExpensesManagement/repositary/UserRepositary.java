package com.FinanceManagement.ExpensesManagement.repositary;

import com.FinanceManagement.ExpensesManagement.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepositary extends JpaRepository<Users , String> {


    Optional<Users> findByEmailId(String emailId);
}
