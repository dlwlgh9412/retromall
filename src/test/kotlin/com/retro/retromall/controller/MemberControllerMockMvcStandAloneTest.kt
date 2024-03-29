package com.retro.retromall.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.retro.retromall.member.controller.MemberController
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class MemberControllerMockMvcStandAloneTest {
    private var memberController: MemberController = mockk()

    private val mvc: MockMvc = MockMvcBuilders.standaloneSetup(memberController)
        .build()
    private lateinit var jacksonTester: JacksonTester<Any>


    @BeforeEach
    fun init() {
        JacksonTester.initFields(this, ObjectMapper())
    }

    //@Test
    /*fun canRetrieveByIdWhenExists() {
        //given
        val loginRequest = LoginRequest(oAuthType = OAuthType.KAKAO, authorizationCode = "Password", "")
        val tokenDto = TokenDto("Bearer", "access", "refresh")
        val loginResponse = LoginResponse("nickName", "profileImage",tokenDto)
        every { memberService.findMemberByOauth(loginRequest) } returns loginResponse
        every { memberController.login(loginRequest) } returns ResponseEntity.ok(loginResponse)

        //when
        val response = mvc.perform(
            post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(loginRequest).json)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn().response

        //then
        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(
            jacksonTester.write(loginResponse).json, response.contentAsString
        )
    }

    //@Test
    fun canRetrieveByIdWhenDoesNotExist() {
        //given
        var everyLoginRequest = LoginRequest(OAuthType.KAKAO, "Password","")
        every {
            memberService.findMemberByOauth(
                everyLoginRequest
            )
        }.throws(EntityNotFoundException("Entity Not Found"))

        //when
        val loginRequest = LoginRequest(OAuthType.KAKAO, "Password","")
        val response = mvc.perform(
            post("/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(loginRequest).json)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn().response

        //then
        assertEquals(response.status, HttpStatus.BAD_REQUEST.value())
        assertEquals(response.contentAsString, "")
    }*/
}