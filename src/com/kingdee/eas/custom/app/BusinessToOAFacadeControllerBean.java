package com.kingdee.eas.custom.app;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import icepdf.ls;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;
import com.kingdee.eas.basedata.master.material.IMaterial;
import com.kingdee.eas.basedata.master.material.MaterialFactory;
import com.kingdee.eas.basedata.scm.sm.srm.SupplyInfoInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.custom.EAISynTemplate;
import com.kingdee.eas.custom.app.unit.SeeyonUtil;
import com.kingdee.eas.custom.app.unit.SupplyInfoLogUnit;
import com.kingdee.eas.tm.common.httpClient.HttpClientUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

import java.lang.String;

public class BusinessToOAFacadeControllerBean extends AbstractBusinessToOAFacadeControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.custom.app.BusinessToOAFacadeControllerBean");

	@Override
	protected void _updateMaterialInfo(Context ctx, String id)
			throws BOSException {
  		if(id !=null && !"".equals(id)){
  		 	List ll =new ArrayList();
 			if(id.contains("&")){
 				 String[] is = id.split("&");
  				 for(int j=0 ; j< is.length ;j++){
 					 if(is[j] !=null && !"".equals( is[j])){
 						 Map<String,String>  material = getMaterialInfoToOA(ctx,is[j]);
 						 if(material != null && material.size() > 0){
 							ll.add(material);
 						 }
 					 }
 				 }
 		 	 
 			}  else{
 				 Map<String,String>  material = getMaterialInfoToOA(ctx,id);
 				 if(material !=null && material.size() > 0){
 					 ll.add(material); 
 				 }
 			}
 			//requestOAInterface(ll);
 			 String msg ="";
	 		   String body = JSON.toJSONString(ll);
	 		  requestOAInterface(body);
	 		   if(body.length() <2000)
	 			  msg =  body;
	 		SupplyInfoLogUnit.insertLog(ctx, DateBaseProcessType.Update, DateBasetype.Material,"","","","msg�� "+msg);//��¼��־
  		}
	}
	
	private static void requestOAInterface(String json) {
		if(json !=null && !"".equals(json)){
		String token = SeeyonUtil.getToken();
		if(token !=null && !"".equals(token)){
			String url = SeeyonUtil.BASEHPATH+"/lolkk/thirdUrlController.do?method="+SeeyonUtil.UPDATEMATERIAL+"&token="+token;
			String result = SeeyonUtil.doPostJson(url, json);
			System.out.println("########### OA "+SeeyonUtil.UPDATEMATERIAL+" :"+result);
 		}
		}
	}
	
	public static void requestOAInterfaceNologin(String json) { 
	     String url = SeeyonUtil.BASEHPATH+SeeyonUtil.UPDATEMATERIAL;   
	     //String body = JSON.toJSONString(mp);
		 try {
			HttpClientUtil.doPost(url, json);
		} catch (EASBizException e) {
			e.printStackTrace();
		} catch (BOSException e) {
			e.printStackTrace();
		} 
	}
	
	private static void requestOAInterface(List ls) {

        // ��½ Url  
//        String loginUrl = "http://oa.meiweigroup.com:8001/seeyon/main.do?method=login";  
//        // ���½����ʵ� Url  
//        String dataUrl = "http://oa.meiweigroup.com:8001/seeyon/lolkk/thirdUrlController.do?method=syncSupplierMaterial";  
//        HttpClient httpClient = new HttpClient();  
//        // ģ���½����ʵ�ʷ�������Ҫ��ѡ�� Post �� Get ����ʽ  
//        PostMethod postMethod = new PostMethod(loginUrl);  
//        // ���õ�½ʱҪ�����Ϣ���û���������  
//        NameValuePair[] data = { new NameValuePair("login_username", "bd3"), new NameValuePair("login_password", "meiwei2020") };  
        
        // ��½ Url  
        String loginUrl = "http://106.14.41.41/seeyon/main.do?name=2";  
        // ���½����ʵ� Url  
        String dataUrl = "http://106.14.41.41/seeyon/lolkk/thirdUrlController.do?method=syncSupplierMaterial";  
        HttpClient httpClient = new HttpClient();  
        // ģ���½����ʵ�ʷ�������Ҫ��ѡ�� Post �� Get ����ʽ  
        PostMethod postMethod = new PostMethod(loginUrl);  
        // ���õ�½ʱҪ�����Ϣ���û���������  
        NameValuePair[] data = { new NameValuePair("login_username", "dengchangchi"), new NameValuePair("login_password", "Aa123456") };  
        
        postMethod.setRequestBody(data);  
        try {  
            // ���� HttpClient ���� Cookie,���������һ���Ĳ���  
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);  
            httpClient.executeMethod(postMethod);  
            // ��õ�½��� Cookie  
            Cookie[] cookies = httpClient.getState().getCookies();  
            StringBuffer tmpcookies = new StringBuffer();  
            for (Cookie c : cookies) {  
                tmpcookies.append(c.toString() + ";"); 
            }
            String body = JSON.toJSONString(ls);
            RequestEntity bbb = new StringRequestEntity (body ,"application/json" ,"UTF-8");
            PostMethod postMethod1 = new PostMethod(dataUrl);  
            // ÿ�η�������Ȩ����ַʱ�����ǰ��� cookie ��Ϊͨ��֤  
            postMethod1.setRequestHeader("cookie", tmpcookies.toString());   
            // ��ӡ���������ݣ�����һ���Ƿ�ɹ� 
            postMethod1.setRequestEntity(bbb);
            postMethod1.setRequestHeader("Content-Type","application/json");
            httpClient.executeMethod(postMethod1);  
            String text = postMethod1.getResponseBodyAsString();  
             System.out.println(text);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	
	
	private Map<String,String> getMaterialInfoToOA(Context ctx, String id){
		Map<String,String>  mp = new HashMap<String,String> ();
		if(id !=null && !"".equals(id)){
			String sql = "select fnumber,fname_l2 fname,cfpinpai pp ,cfhuohao hh ,fmodel gg,cfxinghao xx from t_bd_material where fid ='"+id+"'";
			try {
				IRowSet rs = DbUtil.executeQuery(ctx, sql.toString());
				  if(rs != null &&  rs.size() > 0){
					  while(rs.next()){
						  if(rs.getObject("fnumber") !=null && !"".equals(rs.getObject("fnumber").toString()))
							  mp.put("code", rs.getObject("fnumber").toString());
						  else
							  mp.put("code", "");
						  
						  if(rs.getObject("fname") !=null && !"".equals(rs.getObject("fname").toString()))
							  mp.put("name", rs.getObject("fname").toString());
						  else
							  mp.put("name", "");
						  
						  if(rs.getObject("pp") !=null && !"".equals(rs.getObject("pp").toString()))
							  mp.put("pp", rs.getObject("pp").toString());
						  else
							  mp.put("pp", "");
						  
						  if(rs.getObject("hh") !=null && !"".equals(rs.getObject("hh").toString()))
							  mp.put("hh", rs.getObject("hh").toString());
						  else
							  mp.put("hh", "");
						  
						  if(rs.getObject("gg") !=null && !"".equals(rs.getObject("gg").toString()))
							  mp.put("gg", rs.getObject("gg").toString());
						  else
							  mp.put("gg", "");
						  
						  if(rs.getObject("xx") !=null && !"".equals(rs.getObject("xx").toString()))
							  mp.put("xx", rs.getObject("xx").toString());
						  else
							  mp.put("xx", "");
					  }
				  }
			} catch (BOSException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mp;
	}

	@Override
	protected void _disableSupplyInfo(Context ctx, List ids)
			throws BOSException {
		if(ids !=null && ids.size() > 0){
			StringBuffer idstr = new StringBuffer("");
			Map sendMp = new HashMap();
			List ls = new ArrayList();
			for(int i = 0 ; i < ids.size() ; i++){
				if(ids.get(i)!=null && !"".equals(ids.get(0).toString())){
					idstr.append("'"+ids.get(i).toString()+"',");
					 Map mp = new HashMap();
					 mp.put("fid", ids.get(i).toString());
					 ls.add(mp);
				}
			}
	 		sendMp.put("count", ids.size());
	 		sendMp.put("fids", ls);
	 		
			if(idstr.length() > 1){
				idstr = new StringBuffer(idstr.substring(0, idstr.length()-1));
				
				//�����޸Ĺ����۸�
				String updateSQL1 ="/*dialect*/update t_sm_supplyinfo set  FUneffectualDate = sysdate where FID in ("+idstr+") ";
				DbUtil.execute(ctx, updateSQL1);
				
				//�޸��м��
				String updateSQL2 = "/*dialect*/update EAS_ORG_SupplyinfoMid set FEndDate = sysdate , FStatus = 0 , fSign = 0 where  SFID in ("+idstr+") ";
				EAISynTemplate.execute(ctx, "04",updateSQL2);
				
				//����֪ͨ��OA
				//SupplyInfoLogUnit.requestOAInterface(sendMp);
				
				SupplyInfoLogUnit.requestOAInterfaceNologin(sendMp);
				
			}
		}
		
	}

	@Override
	protected void _ableSupplyInfo(Context ctx, List ids) throws BOSException {
		if(ids !=null && ids.size() > 0){
			StringBuffer idstr = new StringBuffer("");
			Map sendMp = new HashMap();
			List ls = new ArrayList();
			for(int i = 0 ; i < ids.size() ; i++){
				if(ids.get(i)!=null && !"".equals(ids.get(0).toString())){
					idstr.append("'"+ids.get(i).toString()+"',");
					 Map mp = new HashMap();
					 mp.put("fid", ids.get(i).toString());
					 ls.add(mp);
				}
			}
	 		sendMp.put("count", ids.size());
	 		sendMp.put("fids", ls);
	 		
			if(idstr.length() > 1){
				idstr = new StringBuffer(idstr.substring(0, idstr.length()-1));
				
				//�����޸Ĺ����۸�
				String updateSQL1 ="/*dialect*/update t_sm_supplyinfo set  FUneffectualDate = to_timestamp('2039-12-30 23:23:23','yyyy-mm--dd hh24:mi:ss') where FID in ("+idstr+") ";
				DbUtil.execute(ctx, updateSQL1);
				
				//�޸��м��
				String updateSQL2 = "/*dialect*/update EAS_ORG_SupplyinfoMid set FEndDate = to_timestamp('2039-12-30 23:23:23','yyyy-mm--dd hh24:mi:ss') , FStatus = 1 , fSign = 0 where  SFID in ("+idstr+") ";
				EAISynTemplate.execute(ctx, "04",updateSQL2);
				
				//����֪ͨ��OA
//				SupplyInfoLogUnit.requestOAInterface(sendMp);
				
				SupplyInfoLogUnit.requestOAInterfaceNologin(sendMp);

				
			}
		}
	}

	
 
    
	
	
}