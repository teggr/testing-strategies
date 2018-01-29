package com.robintegg.blog.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.robintegg.blog.domain.Outlook;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.resources.WeatherResource;
import com.robintegg.blog.services.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherResource.class)
public class WeatherResourceTest {

	@MockBean
	private WeatherService mockWeatherService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGetAWeatherSummary() throws Exception {

		// given
		when(mockWeatherService.getSummary()).thenReturn(new WeatherSummary(5, 41, Outlook.RAINY));

		// when
		ResultActions resultActions = this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("celcius").value(5))
				.andExpect(jsonPath("fahrenheit").value(41)).andExpect(jsonPath("outlook").value("RAINY"));

	}

}
