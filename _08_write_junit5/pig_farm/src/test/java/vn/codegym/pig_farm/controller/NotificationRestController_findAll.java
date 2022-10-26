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
public class NotificationRestController_findAll {
    @Autowired
    private MockMvc mockMvc;


    /**
     * This function used to test display all record with keyword = null
     * @author DatLT
     * @Time 09/09/2022
     *
     * @throws Exception
     */
    @Test
    public void findAll_keyword_7() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notification?keyword=" + null))
                .andDo(print())
                .andExpect(status().is(204));
    }


    /**
     * This function used to test display all record with keyword = "", has pagination
     * @author DatLT
     * @Time 09/09/2022
     *
     * @throws Exception
     */
    @Test
    public void findAll_keyword_8() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notification?keyword="))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(4))
                .andExpect(jsonPath("content[3].id").value(5))
                .andExpect(jsonPath("content[3].title").value("Cân nhắc giá thịt heo và thức ăn chăn nuôi"))
                .andExpect(jsonPath("content[3].content").value("Ngày 26/7/2022, Văn phòng Chính phủ có Công văn số 4679/VPCP-KTTH về giá thịt heo và nguyên liệu thức ăn chăn nuôi (TĂCN)...."))
                .andExpect(jsonPath("content[3].submittedDate").value("2022-09-08"))
                .andExpect(jsonPath("content[3].image").value("../img/ttsk4.jpg"))
                .andExpect(jsonPath("content[3].isDeleted").value(false));
    }


    /**
     * This function used to test display all record with keyword = "long"
     * @author DatLT
     * @Time 09/09/2022
     *
     * @throws Exception
     */
    @Test
    public void findAll_keyword_9() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notification?keyword=long"))
                .andDo(print())
                .andExpect(status().is(204));
    }


    /**
     * This function used to test display all record with keyword = "ngãi"
     * @author DatLT
     * @Time 09/09/2022
     *
     * @throws Exception
     */
    @Test
    public void findAll_keyword_10() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notification?keyword=ngãi"))
                .andDo(print())
                .andExpect(status().is(204));
    }

    /**
     * This function used to test display all record with keyword = "heo", has pagination
     * @author DatLT
     * @Time 09/09/2022
     *
     * @throws Exception
     */
    @Test
    public void findAll_keyword_11() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notification?keyword=heo"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(1))
                .andExpect(jsonPath("content[0].id").value(5))
                .andExpect(jsonPath("content[0].title").value("Cân nhắc giá thịt heo và thức ăn chăn nuôi"))
                .andExpect(jsonPath("content[0].content").value("Ngày 26/7/2022, Văn phòng Chính phủ có Công văn số 4679/VPCP-KTTH về giá thịt heo và nguyên liệu thức ăn chăn nuôi (TĂCN)...."))
                .andExpect(jsonPath("content[0].submittedDate").value("2022-09-08"))
                .andExpect(jsonPath("content[0].image").value("../img/ttsk4.jpg"))
                .andExpect(jsonPath("content[0].isDeleted").value(false));
    }
}
