package pers.wzr.response

class ResponseBuilder {
    private var response = Response()

    fun data(t:Any): ResponseBuilder
    {
        response.data = t
        return this
    }
    fun code(t:Int): ResponseBuilder
    {
        response.code = t
        return this
    }
    fun message(t:String): ResponseBuilder
    {
        response.message = t
        return this
    }
    fun build() = response
}