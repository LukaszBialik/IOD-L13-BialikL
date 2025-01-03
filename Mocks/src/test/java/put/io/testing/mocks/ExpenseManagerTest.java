package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

import static org.mockito.ArgumentMatchers.*;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    @Test
    void calculateTotal() {
        ExpenseRepository repository = mock(ExpenseRepository.class);
        FancyService mockFancyService = mock(FancyService.class);
        List<Expense> expensesList = new ArrayList<>();

        for (int i=0; i<3; i++){
            Expense expenses = new Expense();
            expenses.setTitle("t");
            expenses.setCategory("c");
            expenses.setAmount(5);
            expensesList.add(expenses);
        }

        when(repository.getExpenses()).thenReturn(expensesList);

        ExpenseManager manager = new ExpenseManager(repository, mockFancyService);
        assertEquals(15, manager.calculateTotal());
    }

    @Test
    void calculateTotalCat() {
        ExpenseRepository repository = mock(ExpenseRepository.class);
        FancyService mockFancyService = mock(FancyService.class);
        List<Expense> expensesList = new ArrayList<>();

        for (int i=0; i<3; i++){
            Expense expanses = new Expense();
            expanses.setTitle("t");
            expanses.setCategory("c");
            expanses.setAmount(5);
            expensesList.add(expanses);
        }
        when(repository.getExpensesByCategory("Home")).thenReturn(expensesList);
        when(repository.getExpensesByCategory("Car")).thenReturn(expensesList);
        when(repository.getExpensesByCategory(and(not(eq("Home")), not(eq("Car"))))).thenReturn(Collections.emptyList());
        ExpenseManager manager = new ExpenseManager(repository, mockFancyService);
        assertEquals(15, manager.calculateTotalForCategory("Home"));
        assertEquals(15, manager.calculateTotalForCategory("Car"));
        assertEquals(0, manager.calculateTotalForCategory("Other"));
    }
    @Test
    void calculateTotalInDollarsTest() throws ConnectException {
        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        FancyService mockFancyService = mock(FancyService.class);
        when(mockFancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenReturn((double) 0);

        ExpenseManager manager = new ExpenseManager(mockExpenseRepository, mockFancyService);
        assertEquals(0, manager.calculateTotalInDollars());
    }
}

