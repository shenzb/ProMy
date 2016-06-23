package util;

import com.google.common.base.Joiner;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final String METHOD_POST = "POST";
    private static HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
    private static HttpClient fast_client = new HttpClient(new MultiThreadedHttpConnectionManager());
    private static HttpClient get_client = new HttpClient(new MultiThreadedHttpConnectionManager());
    static {
        client.getHttpConnectionManager().getParams().setConnectionTimeout(8000);
        client.getHttpConnectionManager().getParams().setSoTimeout(15000);
        client.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler() {

            public boolean retryMethod(HttpMethod method, IOException exception, int executionCount) {
                return executionCount < 2;
            }
        });

        /**
         * 用于访问webservice
         */
        client.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0");

        fast_client.getHttpConnectionManager().getParams().setConnectionTimeout(500);
        fast_client.getHttpConnectionManager().getParams().setSoTimeout(2000);

        get_client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);
        get_client.getHttpConnectionManager().getParams().setSoTimeout(10000);
        get_client.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0");


    }

    public static String get(String url) {
        logger.info("http  get url is " + url);
        GetMethod method = new GetMethod(url);
        method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        method.setRequestHeader("Cookie", "");
        method.setFollowRedirects(true);
        try {
            int code = get_client.executeMethod(method);
            if (code == 200) {
                long contentLength = method.getResponseContentLength();
                if (contentLength == -1)
                    return method.getResponseBodyAsString(Integer.MAX_VALUE);
                else
                    return method.getResponseBodyAsString();
            } else {
                logger.warn("get notify  url:{} return {}", url, code);
            }
        } catch (Exception e) {
            logger.error("push notify fail: url=" + url, e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String get(String url, Cookie[] cookies) {
        GetMethod method = new GetMethod(url);
        method.setFollowRedirects(true);
        try {
            String cookieString = converCookiesToString(cookies);
            method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
            method.setRequestHeader("Cookie", cookieString);
            int code = client.executeMethod(method);
            if (code == 200) {
                long contentLength = method.getResponseContentLength();
                if (contentLength == -1)
                    return method.getResponseBodyAsString(Integer.MAX_VALUE);
                else
                    return method.getResponseBodyAsString();
            } else {
                logger.warn("get url:{} return {}", url, code);
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url, e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    private static String converCookiesToString(Cookie[] cookies) {
        StringBuilder buff = new StringBuilder();
        if(ArrayUtils.isNotEmpty(cookies)) {
            for(Cookie cookie : cookies) {
                buff.append(";");
                buff.append(cookie.getName());
                buff.append("=");
                buff.append(cookie.getValue());
            }
        }
        if(buff.length()>0) {
            buff.delete(0, 1);
        }
        return buff.toString();
    }

    public enum ClientType {
        FAST, NORMAL
    }

    public static String postJson(String url, String body, ClientType clientType) {
        PostMethod method = new PostMethod(url);
        try {
            method.setRequestEntity(new StringRequestEntity(body, "application/json", "UTF-8"));

            int code = 0;
            switch (clientType) {
            case FAST:
                code = fast_client.executeMethod(method);
                break;
            case NORMAL:
                code = client.executeMethod(method);
                break;
            default:
                logger.error("error ClientType:" + clientType.name());
                return null;
            }
            if (code == 200) {
                long contentLength = method.getResponseContentLength();
                if (contentLength == -1)
                    return method.getResponseBodyAsString(Integer.MAX_VALUE);
                else
                    return method.getResponseBodyAsString();
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body + ", ClientType=" + clientType.name(), e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String post(String url, Map<String, String> params, String body, ClientType clientType) {
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "UTF-8"));

            if (params != null) {
                for (String name : params.keySet()) {
                    method.addParameter(name, params.get(name));
                }
            }

            int code = 0;

            switch (clientType) {
            case FAST:
                code = fast_client.executeMethod(method);
                break;
            case NORMAL:
                code = client.executeMethod(method);
                break;
            default:
                logger.error("error ClientType:" + clientType.name());
                return null;
            }

            /** 这块别删了，调试有用
            logger.debug("HTTP headers");
            for (Header header : method.getRequestHeaders()) {
                logger.debug("{}: {}", header.getName(), header.getValue());
            }
            logger.info("ResponseHeaders:{}", JSON.toJSONString(method.getResponseHeaders()));
            */

            if (code == 200) {
                long contentLength = method.getResponseContentLength();
                if (contentLength == -1)
                    return method.getResponseBodyAsString(Integer.MAX_VALUE);
                else
                    return method.getResponseBodyAsString();
            } else {
                logger.error("http code={}", code);
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body + ", params=" + params + ", ClientType="
                    + clientType.name(), e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String post(String url, String body) {
        return post(url, null, body, ClientType.NORMAL);
    }

    public static String getFromInputStream(String url) {
        GetMethod method = new GetMethod(url);
        method.setFollowRedirects(true);
        try {

            int code = client.executeMethod(method);
            if (code == 200) {
                InputStream in = method.getResponseBodyAsStream();
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                BufferedReader buffRead = new BufferedReader(isr);
                StringBuffer inputLine = new StringBuffer();
                String temp = null;
                while ((temp = buffRead.readLine()) != null) {
                    inputLine.append(temp);
                }
                return inputLine.toString();
            } else {
                logger.warn("get url:{} return {}", url, code);
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url, e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String postForm(String url, String body) {
        PostMethod method = new PostMethod(url);

        try {
            method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "UTF-8"));

            int code = client.executeMethod(method);
            logger.info("voucher response code:" + code);
            if (code == 200) {
                InputStream in = method.getResponseBodyAsStream();
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                BufferedReader buffRead = new BufferedReader(isr);
                StringBuffer inputLine = new StringBuffer();
                String temp = null;
                while ((temp = buffRead.readLine()) != null) {
                    inputLine.append(temp);
                }
                return inputLine.toString();
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body, e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String sslPost(String requestUrl, Map<String, String> paramMap) {
        String responseContent = null;
        HttpsURLConnection httpsURLConnection = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            SSLContext context = SSLContext.getInstance("SSL");
            context.init(null,new TrustManager[] {new TrustAnyTrustManager()}, new SecureRandom());

            URL url = new URL(requestUrl);

            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod(METHOD_POST);
            httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new TrustAnyHostnameVerifier());
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.connect();

//            String params = printMap(paramMap,"&");
            String params = "";
            if (paramMap != null) {
                Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");
                params = mapJoiner.join(paramMap);
            }
            byte[] b = params.getBytes();

            httpsURLConnection.getOutputStream().write(b, 0, b.length);
            httpsURLConnection.getOutputStream().flush();
            httpsURLConnection.getOutputStream().close();


            is = httpsURLConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, HTTP.UTF_8));

            String tempLine = null;
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");

            while ((tempLine = br.readLine()) != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
            }
            responseContent = tempStr.toString();
        } catch (Exception e) {
            logger.error("doHttpsClientPost exception",e);
        } finally {
            if (httpsURLConnection != null){
                httpsURLConnection.disconnect();
                httpsURLConnection = null;
            }

            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                logger.error("close error", e);
            }
        }

        return responseContent;

    }

    public static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


    public static class TrustAnyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String s, SSLSession sslSession) {
            return false;
        }
    }





    /**
     * 生成指定的query编码的URL
     * @param urlPrefix
     * @param queryParam
     * @param queryCharset
     * @return
     */
    public static String buildUrl(String urlPrefix, Map<String, String> queryParam, String queryCharset) {
        StringBuilder url = new StringBuilder(urlPrefix);
        logger.info("http url is " + urlPrefix + " ,param is " + queryParam + " ,charset is " + queryCharset);
        if (queryParam.size() > 0) {
            url.append("?");
            for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                String encodedStr;
                try {
                    encodedStr = URLEncoder.encode(entry.getValue(), queryCharset);
                } catch (UnsupportedEncodingException e) {
                    logger.error("in buildUrl(), encode {} to {} fail!", entry.getValue(), queryCharset);
                    return null;
                }
                url.append(entry.getKey()).append("=").append(encodedStr).append("&");
            }
            url.deleteCharAt(url.length() - 1);
        }
        return url.toString();
    }


}
