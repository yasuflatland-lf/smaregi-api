package jp.creollc.smaregi;

import jp.creollc.smaregi.constants.SmaregiConstants;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SmaregiIO {
    /**
     * Constructor
     *
     * @param contactId   Contact ID
     * @param accessToken Access Token
     */
    public SmaregiIO(String contactId, String accessToken) {
        _contactId = contactId;
        _accessToken = accessToken;
    }

    /**
     * POST Method
     *
     * @param url      URI of ClientBase REST API.
     * @param procName ClientBase API's procName.
     * @param params   JSON Strings to be stored in the payload of ClientBase API.
     * @return Response JSON string from ClientBase API
     * @throws IOException
     */
    public String post(String url, String procName, String params) throws IOException {
        Charset charset = StandardCharsets.UTF_8;

        try (CloseableHttpClient client = HttpClients.custom().build()) {
            List<NameValuePair> formparams = new ArrayList<>();

            // Generate params
            formparams.add(new BasicNameValuePair(SmaregiConstants.PROC_NAME, procName));
            formparams.add(new BasicNameValuePair(SmaregiConstants.PARAMS, params));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            // Create a request
            HttpUriRequest request =
                RequestBuilder.post()
                    .setUri(url)
                    .setHeader(SmaregiConstants.X_CONTACT_ID, _contactId)
                    .setHeader(SmaregiConstants.X_ACCESS_TOKEN, _accessToken)
                    .setHeader(SmaregiConstants.CONTENT_TYPE, SmaregiConstants.DEFAULT_REQUEST_CONTENT_TYPE)
                    .setEntity(entity)
                    .build();

            // Send Request
            try (CloseableHttpResponse response = client.execute(request)) {
                int status = response.getStatusLine().getStatusCode();

                _success = (status == HttpStatus.SC_OK) ? true : false;

                String rawJson = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                return StringEscapeUtils.unescapeJava(rawJson);
            }
        }
    }

    /**
     * Recent Response status
     *
     * @return true if the response code is 200 or false
     */
    public boolean isSuccessful() {
        return _success;
    }

    protected boolean _success = true;
    protected String _contactId;
    protected String _accessToken;
}
