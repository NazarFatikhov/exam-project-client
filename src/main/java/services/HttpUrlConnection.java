package services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;

public class HttpUrlConnection {

    public String sendGet(String url) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        if(response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
            String content = EntityUtils.toString(response.getEntity());
            return content;
        }
        throw new ConnectException("Bad response. Status code: " + response.getStatusLine().getStatusCode());
    }

    public String sendPut(String url, String body) throws IOException {
        StringEntity entity = new StringEntity(body,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut(url);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
            String content = EntityUtils.toString(response.getEntity());
            return content;
        }
        throw new ConnectException("Bad response. Status code: " + response.getStatusLine().getStatusCode());
    }

    public void sentDelete(String url) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(url);
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK){
            throw new ConnectException("Bad response. Status code: " + response.getStatusLine().getStatusCode());
        }
    }
}
