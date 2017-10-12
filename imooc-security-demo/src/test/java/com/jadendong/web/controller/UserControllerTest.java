package com.jadendong.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenQuerySuccess() throws Exception {

		String string = mockMvc
				.perform(MockMvcRequestBuilders.get("/user").param("username", "jojo").param("age", "18")
						.param("ageTo", "60").param("xxx", "jojo").param("size", "15")
						// .param("page", "3")
						.param("sort", "age,desc").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)).andReturn().getResponse()
				.getContentAsString();

		System.out.println(string);

	}

	@Test
	public void wheenGetInfoSuccess() throws Exception {
		String string = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(string);

	}

	@Test
	public void wheenGetInfoFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

	}

	@Test
	public void whenCreateSuccess() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":" + date.getTime() + "}";

		String string = mockMvc
				.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(content))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(string);
	}

	@Test
	public void whenUpdateSuccess() throws Exception {
		Date date = new Date(
				LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		System.out.println(date.getTime());
		String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":" + date.getTime() + "}";

		String string = mockMvc
				.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(content))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(string);
	}

	@Test
	public void whenDeleteSuccess() throws UnsupportedEncodingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc
				.perform(fileUpload("/file").file(new MockMultipartFile("file", "test.txt", "multipart/form-data",
						"hello word".getBytes("UTF-8"))))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		System.out.println(result);
	}
}
