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
                .andExpect(jsonPath("title").value("Cân nhắc giá thịt heo và thức ăn chăn nuôi"))
                .andExpect(jsonPath("content").value("Ngày 26/7/2022, Văn phòng Chính phủ có Công văn số 4679/VPCP-KTTH về giá thịt heo và nguyên liệu thức ăn chăn nuôi (TĂCN)...."))
                .andExpect(jsonPath("submittedDate").value("2022-09-08"))
                .andExpect(jsonPath("image").value("../img/ttsk4.jpg"))
                .andExpect(jsonPath("isDeleted").value(false));
    }
}
