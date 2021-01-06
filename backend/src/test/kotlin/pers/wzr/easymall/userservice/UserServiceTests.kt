package pers.wzr.easymall.userservice

import cn.hutool.http.ContentType
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import pers.wzr.easymall.entity.entity.User

@SpringBootTest
class UserServiceTests {
    private val client = WebClient.create("http://localhost:8080/user/")
    @Test
    fun checkUsernameExists()
    {
        println(client.get().uri("check/username/3434").retrieve().bodyToMono(String::class.java).block())
    }

    @Test
    fun signIn()
    {
        println(
            client.post().uri("sign/in").accept(MediaType.APPLICATION_JSON).header("Content-Type",ContentType.JSON.toString())
                .bodyValue("{\"username\":\"3434\",\"password\":\"ccccc\",\"code\":\"11\"}").retrieve().bodyToMono(String::class.java)
                .block()
        )
    }

    @Test
    fun signUp()
    {
        println(
            client.post().uri("sign/up").accept(MediaType.APPLICATION_JSON).header("Content-Type",ContentType.JSON.toString())
                .bodyValue("{\"username\":\"3434\",\"password\":\"ccccc\",\"code\":\"3\"}").retrieve().bodyToMono(String::class.java)
                .block()
        )
    }

    @Test
    fun update()
    {
        println(
//            client.put().uri("update").bodyValue(UserBuilder().email("serewr").username("3434").password("ccccc").build()).retrieve()
//                .bodyToMono(String::class.java).block()
        )
    }

    @Test
    fun delete()
    {
        println(client.delete().uri("delete/83f539d0-27ca-4692-a4c9-a4b177105c37").retrieve().bodyToMono(String::class.java).block())
    }

    @Test
    fun getAll()
    {
        val code = client.get().uri("get").retrieve().bodyToMono(String::class.java).block()
        client.get().uri("data/$code").retrieve().bodyToFlux(User::class.java).subscribe{
            println(it)
        }
    }

    @Test
    fun getById()
    {
        println(client.get().uri("get/83f539d0-27ca-4692-a4c9-a4b177105c37").retrieve().bodyToMono(String::class.java).block())
    }

}