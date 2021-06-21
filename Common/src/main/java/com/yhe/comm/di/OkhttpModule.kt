package com.yhe.comm.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
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
                builder.addHeader("AUTHORIZATION", "Bearer 8rMHzP9QedDyHC6ASG24")
                return chain.proceed(builder.build())
            }
        }
    }

}