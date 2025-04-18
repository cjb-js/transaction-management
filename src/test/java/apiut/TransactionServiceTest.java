package apiut;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cjbbank.transaction.TransactionServiceApplication;
import com.cjbbank.transaction.api.model.PagedData;
import com.cjbbank.transaction.api.model.TransactionDTO;
import com.cjbbank.transaction.exception.GeneralException;
import com.cjbbank.transaction.service.TransactionService;

@SpringBootTest(classes = { TransactionServiceApplication.class })
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createTransaction_shouldReturnOK() throws Exception {
        // 准备测试数据
        String testTransactionName = "12345";
        BigDecimal testTransactionAmt = BigDecimal.valueOf(1.0);

        // 执行测试方法
        TransactionDTO body = new TransactionDTO();
        body.setName(testTransactionName);
        body.setAmount(testTransactionAmt);

        TransactionDTO result = transactionService.createTransaction(body);

        // 验证结果
        assertNotNull(result);
        assertNotNull(result.getId());

        assertEquals(testTransactionName, result.getName());
        assertEquals(testTransactionAmt, result.getAmount());

    }

    @Test
    void getAllTransaction_shouldOK() throws Exception {
        // 准备测试数据
        transactionService.clearAllTransactions();

        int cnt = 10;
        int i = 0;
        while (i++ < cnt) {
            TransactionDTO body = new TransactionDTO();
            body.setName("tx" + i);
            body.setAmount(BigDecimal.valueOf(i));

            transactionService.createTransaction(body);
        }

        PagedData result = transactionService.getAllTransactions(1, cnt + 1);

        // transactionService.deleteTransactionById(result.getId());

        assertNotNull(result);

        assertEquals(result.getCount(), cnt);

    }

    @Test
    void deleteTransaction_shouldOK() throws Exception {
        // 准备测试数据
        String testTransactionName = "12345";
        BigDecimal testTransactionAmt = BigDecimal.valueOf(1.0);

        // 执行测试方法
        TransactionDTO body = new TransactionDTO();
        body.setName(testTransactionName);
        body.setAmount(testTransactionAmt);

        TransactionDTO result = transactionService.createTransaction(body);
        assertNotNull(result);

        assertThatCode(() -> transactionService.deleteTransactionById(result.getId())).doesNotThrowAnyException();

    }

    @Test
    void deleteTransaction_shouldThrowException() throws Exception {
        // 准备测试数据
        String txId = "12345-not-exist-id";

        // 断言会抛出特定异常
        GeneralException exception = assertThrows(GeneralException.class, // 预期的异常类型
                () -> transactionService.deleteTransactionById(txId) // 执行的方法
        );

        // 可选：进一步验证异常信息
        assertEquals(String.format("transaction not found with id {0}"), exception.getMessage());

    }

    @Test
    void updateTransaction_shouldThrowException() throws Exception {
        // 准备测试数据
        String txId = "12345-not-exist-id";
        String testTransactionName = "12345";
        BigDecimal testTransactionAmt = BigDecimal.valueOf(1.0);

        // 执行测试方法
        TransactionDTO body = new TransactionDTO();
        body.setName(testTransactionName);
        body.setAmount(testTransactionAmt);

        // 断言会抛出特定异常
        GeneralException exception = assertThrows(GeneralException.class, // 预期的异常类型
                () -> transactionService.updateTransactionById(txId, body) // 执行的方法
        );

        // 可选：进一步验证异常信息
        assertEquals(String.format("transaction not found with id {0}"), exception.getMessage());

    }

    @Test
    void updateTransaction_shouldOK() throws Exception {
        // 准备测试数据
        String testTransactionName = "12345";
        BigDecimal testTransactionAmt = BigDecimal.valueOf(1.0);

        // 执行测试方法
        TransactionDTO body = new TransactionDTO();
        body.setName(testTransactionName);
        body.setAmount(testTransactionAmt);

        TransactionDTO createResult = transactionService.createTransaction(body);

        assertNotNull(createResult);

        String newTransactionName = "newName111";
        body.setName(newTransactionName);

        TransactionDTO updateResult = transactionService.updateTransactionById(createResult.getId(), body);

        // 可选：进一步验证异常信息
        assertNotNull(updateResult);

        assertEquals(newTransactionName, updateResult.getName());

    }

}
