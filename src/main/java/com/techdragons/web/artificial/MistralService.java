package com.techdragons.web.artificial;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class MistralService {

    private final String mistralApiUrl;
    private final CloseableHttpAsyncClient httpclient;

    public MistralService(@Value("${mistral.api.url}")String mistralApiUrl) {
        this.mistralApiUrl = mistralApiUrl;
        this.httpclient = HttpAsyncClients.createDefault();
    }

    public CompletableFuture<String> sendAndReceiveMessageAsync(String prompt) {
        CompletableFuture<String> responseFuture = new CompletableFuture<>();
        this.httpclient.start();

        HttpPost request = new HttpPost(mistralApiUrl + "/api/generate");
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(new JSONObject().put("model", "mistral").put("prompt", prompt).toString(), "UTF-8"));

        httpclient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                try {
                    String responseString = EntityUtils.toString(result.getEntity());
                    responseFuture.complete(responseString);
                } catch (IOException e) {
                    responseFuture.completeExceptionally(e);
                }
            }

            @Override
            public void failed(Exception ex) {
                responseFuture.completeExceptionally(ex);
            }

            @Override
            public void cancelled() {
                responseFuture.cancel(true);
            }
        });

        return responseFuture;
    }
}

