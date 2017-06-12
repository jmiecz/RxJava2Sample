package com.rxjavasample;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.TimeZone;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Josh on 6/12/2017.
 */

public class RetroService {
    private static OkHttpClient okHttpClient;
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <T> T createRetrofitService(Class<T> clazz, String baseUrl) {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(getCachedOkHttpClient());

        Retrofit retrofit = builder.build();

        return retrofit.create(clazz);
    }

    private static OkHttpClient getCachedOkHttpClient() {
        if (okHttpClient != null) {
            return okHttpClient;
        }

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());

                        requestBuilder.header("Content-Type", "application/json; charset=utf-8");

                        Request request = requestBuilder.build();

                        return chain.proceed(request);
                    }
                }).build();

        return okHttpClient;
    }
}
