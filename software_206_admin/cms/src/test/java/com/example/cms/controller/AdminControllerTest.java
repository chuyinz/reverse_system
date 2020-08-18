package com.example.cms.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AdminController.class)
@WebAppConfiguration
public class AdminControllerTest {
    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;
    @MockBean
    private AdminController adminController;
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void login() throws Exception{
            String responseString =this.mockMvc.perform(post("/user/login").contentType("application/json;charset=UTF-8")
                    .param("account","1234")
                .param("password","123")).andExpect(status().isOk())
                .andDo(print()) .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("———返回的json=" + responseString);
    }


}
