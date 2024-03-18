package com.techdragons.web.artificial;


import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenaiService {

    private final OkHttpClient client;
    private final String apiKey;

    @Autowired
    public OpenaiService(@Value("${openai.api.key}") String apiKey,OkHttpClient client) {
        this.client = client;
        this.apiKey = apiKey;
    }
    public String sendMessage(String text, String context) throws IOException {
        JSONObject body = new JSONObject();
        body.put("model", "gpt-3.5-turbo-0125");
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", context));
        messages.put(new JSONObject().put("role", "user").put("content", text));
        body.put("messages", messages);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(RequestBody.create(body.toString(), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + this.apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray choices = jsonResponse.getJSONArray("choices");
            if (choices.length() > 0) {
                String content = choices.getJSONObject(0).getJSONObject("message").getString("content");
                return content;
            }
            return "";
        }
    }
}
