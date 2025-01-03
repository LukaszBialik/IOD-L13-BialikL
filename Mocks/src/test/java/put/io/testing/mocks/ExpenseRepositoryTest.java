package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;



public class ExpenseRepositoryTest {

    @Test
    void loadExpenses() {
        MyDatabase myDatabase = new MyDatabase();
        ExpenseRepository repository = new ExpenseRepository(myDatabase);
        repository.loadExpenses();
        assertTrue(repository.getExpenses().isEmpty());
    }


    @Test
    void loadExpenses2() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository repository = new ExpenseRepository(mockDatabase);
        repository.loadExpenses();
        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();
        assertTrue(repository.getExpenses().isEmpty());
    }

    @Test
    void saveExpenses1() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository expenseRepository = new ExpenseRepository(mockDatabase);

        expenseRepository.addExpense(new Expense());
        expenseRepository.saveExpenses();
        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).persist(any(Expense.class));
        inOrder.verify(mockDatabase).close();
        verify(mockDatabase,atLeastOnce()).persist(any(Expense.class));

        assertEquals(1,expenseRepository.getExpenses().size());
    }


@Test
void saveExpenses2() {
    IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
    when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());

    ExpenseRepository expenseRepository = new ExpenseRepository(mockDatabase);

    expenseRepository.addExpense(new Expense());
    expenseRepository.addExpense(new Expense());
    expenseRepository.addExpense(new Expense());
    expenseRepository.addExpense(new Expense());
    expenseRepository.addExpense(new Expense());

    expenseRepository.saveExpenses();
    verify(mockDatabase,times(5)).persist(any(Expense.class));
}
}

