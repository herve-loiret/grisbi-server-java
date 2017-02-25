package grisbiweb.server.config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import grisbiweb.server.controller.AccountController;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@Import({ WebMvcConfiguration.class })
public class CrossDomainFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrossDomainFilter crossDomainFilter;

    @Test
    @SneakyThrows
    public void should_filter_be_called() {
        mockMvc.perform(get("/accounts/"));
        verify(crossDomainFilter, times(1)).doFilter(any(), any(), any());
    }

}
