package pers.wzr.filter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.HandlerFilterFunction
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import pers.wzr.dao.UserRepository
import pers.wzr.entity.UserBuilder
import pers.wzr.response.Response
import pers.wzr.response.ResponseCode
import pers.wzr.util.VerificationCodeUtils
import reactor.core.publisher.Mono

@Component
class UserFilter:HandlerFilterFunction<ServerResponse,ServerResponse> {

    @Autowired
    lateinit var repository:UserRepository

    override fun filter(request: ServerRequest, next: HandlerFunction<ServerResponse>): Mono<ServerResponse> {
        val username = request.queryParam("username").get()
        val password = request.queryParam("password").get()
        val code = request.queryParam("code").get()
        if(VerificationCodeUtils.verify(code))
        {
            val user = UserBuilder().username(username).password(password).build()
            val matcher = ExampleMatcher.matching()
                    .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
                    .withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact())
                    .withIgnorePaths("id","nickname","email")
            return repository.findOne(Example.of(user,matcher)).flatMap { t->
                Response().code(ResponseCode.USER_SIGN_IN_SUCCESS).build()
            }.switchIfEmpty(
                    Response().code(ResponseCode.USER_SIGN_IN_FAILED_USERNAME_OR_PASSWORD_ERROR).build()
            )
        }
        return Response().code(ResponseCode.USER_SIGN_IN_FAILED_VERIFICATION_CODE_ERROR).build()
    }
}