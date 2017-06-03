package com.allstate.springboot.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private RestDocumentationResultHandler documentationHandler;

    @Before
    public void setUp() {
        this.documentationHandler = document("{method-name}", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.documentationHandler)
                .build();
    }

    @Test
    public void testGetPolicies() throws Exception {
         this.mockMvc.perform(get("/policies").accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andDo(this.documentationHandler.document(
                         responseFields(
                                 fieldWithPath("[].policyNumber").description("Policy number"),
                                 fieldWithPath("[].firstName").description("First Name"),
                                 fieldWithPath("[].lastName").description("Last Name"),
                                 fieldWithPath("[].age").description("Age"),
                                 fieldWithPath("[].premium").description("Premium")
                         )));
    }

    @Test
    public void testGetPolicy() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/policies/{policyNumber}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(this.documentationHandler.document(
                        pathParameters(parameterWithName("policyNumber").description("Policy Number")),
                        responseFields(
                                fieldWithPath("policyNumber").description("Policy number"),
                                fieldWithPath("firstName").description("First Name"),
                                fieldWithPath("lastName").description("Last Name"),
                                fieldWithPath("age").description("Age"),
                                fieldWithPath("premium").description("Premium")
                        )));
    }

    @Test
    public void testCreatePolicy() throws Exception {
        String requestBody = "{\"firstName\":\"Veeru\",\"lastName\":\"Suda\",\"age\":32}";

        this.mockMvc.perform(post("/policies").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andDo(this.documentationHandler.document(
                        requestFields(
                                fieldWithPath("firstName").description("First Name"),
                                fieldWithPath("lastName").description("Last Name"),
                                fieldWithPath("age").description("Age")),
                        responseFields(
                                fieldWithPath("policyNumber").description("Policy number"),
                                fieldWithPath("firstName").description("First Name"),
                                fieldWithPath("lastName").description("Last Name"),
                                fieldWithPath("age").description("Age"),
                                fieldWithPath("premium").description("Premium")
                        )));
    }


}
