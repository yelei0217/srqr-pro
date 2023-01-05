package com.kingdee.eas.custom.app.unit;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @Author lei.ye
 * @DATE 2022/11/17.
 */
public class SeeyonUtil {
    
	 // oa �û�����
	public static String USERNAME ="dengchangchi";	   
	// oa AppID
	 public static String APPID ="4033a5f1-5659-4264-bba1-ce601dbba56a";
	// ����OA��ַ 
    public static String BASEHPATH ="http://oa.meiweigroup.com:8001/seeyon/lolkk/thirdUrlController.do?method=";
    //����OA��ַ 
//    public static String BASEHPATH ="http://106.14.41.41/seeyon/lolkk/thirdUrlController.do?method=";

	// oa �����޸�
    public static String UPDATEMATERIAL ="syncSupplierMaterial";
    
	// oa ��Ӧ�̻�����Ϣ
    public static String UPDATESUPPLIERINFO ="syncSupplierInfo";
    
	public static String getToken(){
		String token = "";
		String url = BASEHPATH+"/rest/token/csrest/"+APPID+"?loginName="+USERNAME;
		String jsonStr =  doGet(url);
 		System.out.println(jsonStr);
		if(jsonStr !=null && !"".equals(jsonStr) ) {
			 JsonObject object = new JsonParser().parse(jsonStr).getAsJsonObject();
			  if(object.get("id")!=null && !"".equals(object.get("id").toString())) {
				  token = object.get("id").getAsString();
			  }
		}
		return token;
	}
	
    private static String doGet(String url, Map<String, String> param) {
        // ����Httpclient����
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // ����uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // ����http GET����
            HttpGet httpGet = new HttpGet(uri);
            // ִ������
            response = httpclient.execute(httpGet);
            // �жϷ���״̬�Ƿ�Ϊ200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    private static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // ����Httpclient����
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // ����Http Post����
            HttpPost httpPost = new HttpPost(url);
            // ���������б�
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // ģ���
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // ִ��http����
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        // ����Httpclient����
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // ����Http Post����
            HttpPost httpPost = new HttpPost(url);
            // ������������
            StringEntity entity = new StringEntity(json,"utf-8"); 
            httpPost.setEntity(entity);
            // ִ��http����
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
   
}