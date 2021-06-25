package com.yhe.comm.di

import com.google.gson.JsonObject
import com.yhe.comm.log.LogUtils
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private val DEBUG = true;
private val TIME_OUT = 3000

@Module
class OkhttpModule {

    // 这里需要注意的是，Dagger的@Singleton不同于java的单例，它的生命周期是依附于@component的。
    // 如果是同一个@Module,即使@Providers被标记为@Singleton，对于不同的@component，也会生成不同的@Singleton依赖
    // 所以，如果想要确保OkHttpClient是全局唯一的，则应使用实现一个全局的@Component: ApplicationComponent
    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient{
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            //.followRedirects(false)
            //.followSslRedirects(false)
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(getCommonIntercepter())

        if (DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logger)
        }

        /*Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 5555));
        builder.proxy(proxy);*/

        return builder.build()
    }

    private fun getCommonIntercepter(): Interceptor {
        return object : Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val builder: Request.Builder = request.newBuilder()
                //TODO builder.addHeader()
                //builder.addHeader("AUTHORIZATION", "Bearer 8rMHzP9QedDyHC6ASG24")
                builder.addHeader("x-tn-api_key", "d0c38f98-342c-4bf8-a21d-d101f7fecf61")
                builder.addHeader("x-tn-search-region", "KR")
                //builder.addHeader("Accept-Encoding", "gzip") // OKHttp会自动解/压缩gzip，不要在请求头中添加Accept-Encoding，否则Okhttp会认为调用者需要自己处理gzip，而不再在内部自动解析gzip
                builder.addHeader("Content-Type", "application/json")
                //builder.addHeader("accept-encoding", "gzip")
                builder.addHeader("x-tn-api_signature", generateSignature("d0c38f98-342c-4bf8-a21d-d101f7fecf61", "458b10af-bb07-4485-8bc2-e5436bbd992b", System.currentTimeMillis(), 0))
                var response = chain.proceed(builder.build())
                var json = JsonObject()
                json.addProperty("body", response.body()!!.string())
                LogUtils.i("HAHA", "${json.toString()}")
                var  newRes = response
                    .newBuilder()
                    .body(ResponseBody.create(response.body()!!.contentType(), json.toString()))
                    .build()
                return newRes
            }
        }
    }

    private val MD5_ALGORITHM = "MD5"
    private val HEX = 16
    private val DELIMITER = ":"

    fun generateSignature(
        applicationId: String,
        applicationSecret: String,
        timestamp: Long,
        delta: Long
    ): String? {
        var timestamp = timestamp
        return try {
            timestamp = (timestamp + delta) / 1000 //in seconds
            val input: String =
                applicationId + DELIMITER + timestamp + DELIMITER.toString() + applicationSecret
            val digest: MessageDigest = MessageDigest.getInstance(MD5_ALGORITHM)
            digest.update(input.toByteArray(Charset.defaultCharset()), 0, input.length)
            val mdbytes: ByteArray = digest.digest()
            val hexString = StringBuilder()
            for (mdbyte in mdbytes) {
                val hex = Integer.toHexString(0xff and mdbyte.toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            applicationId + DELIMITER + timestamp + DELIMITER.toString() + hexString.toString()
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
    }

}