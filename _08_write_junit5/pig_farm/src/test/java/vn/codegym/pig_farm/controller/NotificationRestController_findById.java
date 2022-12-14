package vn.codegym.pig_farm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationRestController_findById {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findById_id_1() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/notification/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void findById_id_2() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/notification/{id}", "''"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void findById_id_3() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/notification/{id}", 10))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void findById_id_4() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/notification/{id}", 5))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id").value(5))
                .andExpect(jsonPath("title").value("C??n nh???c gi?? th???t heo v?? th???c ??n ch??n nu??i"))
                .andExpect(jsonPath("content").value("Ng??y 26/7/2022, V??n ph??ng Ch??nh ph??? c?? C??ng v??n s??? 4679/VPCP-KTTH v??? gi?? th???t heo v?? nguy??n li???u th???c ??n ch??n nu??i (T??CN)...."))
                .andExpect(jsonPath("submittedDate").value("2022-09-08"))
                .andExpect(jsonPath("image").value("../img/ttsk4.jpg"))
                .andExpect(jsonPath("isDeleted").value(false));
    }
}
