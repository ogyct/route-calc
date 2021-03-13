package com.augy.routecalc;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RouteCalcApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void czeItaTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/routing/CZE/ITA"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.route[0])").value("CZE"))
                .andExpect(jsonPath("$.route[1])").value("AUT"))
                .andExpect(jsonPath("$.route[2])").value("ITA"))
        ;

    }

    @Test
    @SneakyThrows
    void rusEspTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/routing/RUS/ESP"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.route[0])").value("RUS"))
                .andExpect(jsonPath("$.route[1])").value("POL"))
                .andExpect(jsonPath("$.route[2])").value("DEU"))
                .andExpect(jsonPath("$.route[3])").value("FRA"))
                .andExpect(jsonPath("$.route[4])").value("ESP"))
        ;

    }

    @Test
    @SneakyThrows
    void noPathtest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/routing/ABW/ESP"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
        ;

    }

}
