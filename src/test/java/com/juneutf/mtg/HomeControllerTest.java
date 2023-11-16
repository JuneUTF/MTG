package com.juneutf.mtg;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.juneutf.mtg.config.vender.CreateToken;
import com.juneutf.mtg.config.vender.EmailService;
import com.juneutf.mtg.controller.HomeController;
import com.juneutf.mtg.service.ActionService;
import com.juneutf.mtg.service.PlanService;
import com.juneutf.mtg.service.SetPasswordService;

/**
 * HomeController の JUnit テスト
 */
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
	@InjectMocks
    private HomeController homeController;

    @Mock
    private SetPasswordService setPasswordService;

    @Mock
    private CreateToken createToken;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PlanService planService;

    @Mock
    private ActionService actionService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;


    @Autowired
    private MockMvc mockMvc;

    /**
     * getIndex メソッドのテスト
     */
    @Test
    public void testGetIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    /**
     * getLogin メソッドのテスト
     */
    @Test
    public void testGetLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * getchangerPass メソッドのテスト
     */
    @Test
    public void testGetchangerPass() throws Exception {
        mockMvc.perform(get("/password"))
                .andExpect(status().isOk())
                .andExpect(view().name("getPassword"));
    }

    // 他の HomeController のメソッドのテストを記述してください

    /**
     * setKanryo メソッドのテスト
     */
    @Test
    public void testGetIndexAuth() {
    	// モックAuthentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(authentication.getName()).thenReturn("anonymousUser");
    	// モックModel
    	Model model = Mockito.mock(Model.class);
        //getIndex呼び出す
        String result = homeController.getIndex(model);
        // 結果検証
        assertEquals("index", result);
        Mockito.verify(model, Mockito.times(1)).addAttribute("job", Mockito.any());
    }
}
