package com.FinanceManagement.ExpensesManagement.dto;

import com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes;
import lombok.Data;


@Data
public class ExpenseRequestDto {

    int amount;
    String expenseTypeId;

}
