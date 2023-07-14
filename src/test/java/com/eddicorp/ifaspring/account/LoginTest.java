package com.eddicorp.ifaspring.account;

import com.eddicorp.ifaspring.account.controller.form.LoginResForm;
import com.eddicorp.ifaspring.account.controller.form.SignUpReqForm;
import com.eddicorp.ifaspring.account.entity.Account;
import com.eddicorp.ifaspring.account.repository.AccountRepository;
import com.eddicorp.ifaspring.account.repository.UserTokenRepository;
import com.eddicorp.ifaspring.account.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginTest {

    @Mock
    private AccountRepository mockAccountRepository;
    @InjectMocks
    private UserTokenRepository mockUserTokenRepository;

    @InjectMocks
    private AccountServiceImpl mockAccountService;

    @BeforeEach
    public void setup () throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Mocking init")
    void test () {
        System.out.println("Mocking ready!");
    }
    @Test
    @DisplayName("회원가입을 진행합니다.")
    public void 회원가입테스트() {
        final SignUpReqForm reqForm = new SignUpReqForm("email", "password", "nickName");
        final Account account = new Account("email", "password", "nickName");

        when(mockAccountRepository.save(account))
                .thenReturn(new Account("email", "password", "nickName"));

        final AccountServiceImpl sut = new AccountServiceImpl(mockAccountRepository, mockUserTokenRepository);
        final LoginResForm actual = sut.signUp(reqForm);

        assertEquals(actual.getNickName(), account.getNickName());
    }


}
