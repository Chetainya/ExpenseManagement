package com.FinanceManagement.ExpensesManagement.repositary;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTypesRepositary extends JpaRepository<com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes,String> {

}
