package app.doordash.demo.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Masoom on 4/16/17.
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException { //going to intercept the http call
        Request originalRequest = chain.request();

        Request newRequest = originalRequest.newBuilder() //once login is done you can add the interceptor here and add auth token
                .build();

        return chain.proceed(newRequest);
    }
}



