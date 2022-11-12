package com.demo.demokslm.controller;

import com.demo.demokslm.dao.UserDao;
import com.demo.demokslm.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
class UserControllerTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findUser() throws Exception{
        assert (userDao.selectById(999)==null);
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);

        userDao.insert(user);
        assert (userDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/user/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        userDao.deleteById(999);
        assert (userDao.selectById(999)==null);
    }

    @Test
    void addUser() throws Exception{
        assert (userDao.selectById(999)==null);
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user").content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert (userDao.selectById(999)!=null);
        userDao.deleteById(999);
        assert (userDao.selectById(999)==null);
    }

    @Test
    void adjustUser() throws Exception{
        assert (userDao.selectById(999)==null);
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);
        userDao.insert(user);
        assert (userDao.selectById(999)!=null);
        assert(userDao.selectById(999).getUserCaseId().equals("gxl999"));
        assert(userDao.selectById(999).getUserName().equals("RainOutSide"));
        user.setUserCaseId("gxl999888");
        user.setUserName("RainingOutSide");

        String responseString = mockMvc.perform(MockMvcRequestBuilders.put("/user").content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(userDao.selectById(999).getUserCaseId().equals("gxl999888"));
        assert(userDao.selectById(999).getUserName().equals("RainingOutSide"));
        userDao.deleteById(999);
        assert (userDao.selectById(999)==null);
    }

    @Test
    void deleteUserById() throws Exception{
        assert (userDao.selectById(999)==null);
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);
        userDao.insert(user);
        assert (userDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/user/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(userDao.selectById(999) == null);
    }
}