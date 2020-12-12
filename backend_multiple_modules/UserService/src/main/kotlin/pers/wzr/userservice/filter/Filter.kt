package pers.wzr.userservice.filter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.HandlerFilterFunction
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.commons.jwt.JwtUtils
import pers.wzr.commons.response.Response
import pers.wzr.commons.response.ResponseCode
import pers.wzr.commons.verification.code.VerificationCodeUtils
import pers.wzr.userservice.dao.Repository
import pers.wzr.userservice.entity.UserBuilder
import reactor.core.publisher.Mono

//@Component
class Filter:HandlerFilterFunction<ServerResponse,ServerResponse> {

//    @Autowired
    lateinit var repository:Repository

    override fun filter(request: ServerRequest, next: HandlerFunction<ServerResponse>): Mono<ServerResponse> {
//        val username = request.queryParam("username").get()
//        val password = request.queryParam("password").get()
//        val code = request.queryParam("code").get()
//        if(VerificationCodeUtils.verify(code))
//        {
//            val user = UserBuilder().username(username).password(password).build()
//            val matcher = ExampleMatcher.matching()
//                    .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
//                    .withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact())
//                    .withIgnorePaths("id","nickname","email")
//            return repository.findOne(Example.of(user,matcher)).flatMap { t->
//                Response.build(ResponseCode.USER_SIGN_IN_SUCCESS)
//            }.switchIfEmpty(
//                Response.build(ResponseCode.USER_SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR)
//            )
//        }
//        return Response.build(ResponseCode.USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR)

        val session = request.session()
        return session.flatMap { t->
            val username = request.queryParam("username")
            if(!username.isPresent)
            {
                return@flatMap ServerResponse.ok().body(Mono.just("Please input usernmae"),String::class.java)
            }
            val password = request.queryParam("password")
            if(!password.isPresent)
            {
                return@flatMap ServerResponse.ok().body(Mono.just("Please input password"),String::class.java)
            }
            if(username.get() == "11" && password.get() == "22")
            {
                val token = JwtUtils.generateJWT(username.get(),password.get())
                t.attributes["token"] = token
                println("create token:$token")
                return@flatMap next.handle(request)
            }
            ServerResponse.ok().body(Mono.just("username or password error"),String::class.java)
        }
    }
}