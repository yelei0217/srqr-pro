package com.kingdee.eas.basedata.scm.sm.srm.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.scm.sm.srm.SuplierPriceAllocMessageInfo;
import com.kingdee.eas.basedata.scm.sm.srm.SupplyInfoCollection;
import com.kingdee.eas.basedata.scm.sm.srm.SupplyInfoFactory;
import com.kingdee.eas.basedata.scm.sm.srm.SupplyInfoInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.custom.app.DateBaseProcessType;
import com.kingdee.eas.custom.app.DateBasetype;
import com.kingdee.eas.custom.app.unit.SeeyonUtil;
import com.kingdee.eas.custom.app.unit.SupplyInfoChange;
import com.kingdee.eas.custom.app.unit.SupplyInfoLogUnit;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class SupplyInfoControllerBeanEx extends SupplyInfoControllerBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4152534103537584823L;

	@Override
	protected SuplierPriceAllocMessageInfo _assign(Context ctx,List supplyInfoIds, List purchaseOrgs) throws BOSException,EASBizException {
		 System.out.println("_assign");
		 SuplierPriceAllocMessageInfo messInfo =  super._assign(ctx, supplyInfoIds, purchaseOrgs);
		 List<String> setCompanyList = getCompanyList(ctx);
		 if(setCompanyList !=null && purchaseOrgs !=null && purchaseOrgs.size() > 0 && setCompanyList.size() > 0){
			 List<String>  chooseListIds = new ArrayList<String>();
			 for(int j = 0 ;j < purchaseOrgs.size() ; j++){
				 PurchaseOrgUnitInfo org = (PurchaseOrgUnitInfo) purchaseOrgs.get(j);
				 chooseListIds.add(org.getId().toString());
			 }
			 chooseListIds.retainAll(setCompanyList); 
			 if(chooseListIds.size() >0){
				 Set idset = new HashSet();
			 
				 Set orgset = new HashSet(); 
				for(int i = 0 ; i < chooseListIds.size() ; i++){	 
					orgset.add(chooseListIds.get(i)); 
				}
				
				for(int i = 0 ; i < supplyInfoIds.size() ; i++){	 
					idset.add(supplyInfoIds.get(i)); 
				} 
				doSendSuppInfoToOA(ctx,idset,orgset,"assign");
			 }
		 }
 		return messInfo;
	} 
	 

	//����׼  list��editҳ�� 
	@Override
	protected void _unAudit(Context ctx, IObjectPK pk) throws BOSException,
			EASBizException {
		// TODO Auto-generated method stub
		super._unAudit(ctx, pk);
	}
	
	//��׼���� list��editҳ�� 
	@Override
	public void _audit(Context ctx, IObjectPK pk) throws BOSException,
			EASBizException {
 		super._audit(ctx, pk);
 		// ��׼�����ж�fid�Ƿ����м����������޸ģ��������������
 		Set idset = new HashSet();
 		idset.add(pk);
 		List<String>  chooseListIds = getCompanyList(ctx);
		Set orgs = new HashSet(); 
		for(int i = 0 ; i < chooseListIds.size() ; i++){	 
			orgs.add(chooseListIds.get(i)); 
		}
		doSendSuppInfoToOA(ctx,idset,orgs,"audit");
	}
	


	@Override
	protected void _batchAudit(Context ctx, String[] pks)
			throws BOSException, EASBizException {
		// TODO Auto-generated method stub
		super._batchAudit(ctx, pks);
		Set idset = new HashSet();
		for(String pk:pks){
			idset.add(pk);
		}
		List<String>  chooseListIds = getCompanyList(ctx);
		Set orgs = new HashSet(); 
		for(int i = 0 ; i < chooseListIds.size() ; i++){	 
			orgs.add(chooseListIds.get(i)); 
		}
		doSendSuppInfoToOA(ctx,idset,orgs,"audit");
	}

	private void doSendSuppInfoToOA(Context ctx,Set idset, Set orgs,String oper){ 
		 FilterInfo filter = new FilterInfo();
		 if("audit".equals(oper)){
			 filter.getFilterItems().add(new FilterItemInfo("ID", idset, CompareType.INCLUDE));
			 filter.getFilterItems().add(new FilterItemInfo("PurchaseOrg.ID", orgs, CompareType.INCLUDE));
		 }else{
			 filter.getFilterItems().add(new FilterItemInfo("sourceObjectID", idset, CompareType.INCLUDE));
			 filter.getFilterItems().add(new FilterItemInfo("PurchaseOrg.ID", orgs, CompareType.INCLUDE));
		 }
	     SelectorItemCollection sic = new SelectorItemCollection();
		 sic.add(new SelectorItemInfo("id"));
		 EntityViewInfo view = new EntityViewInfo();
		 view.setSelector(sic);
		 view.setFilter(filter);
	 	try {
			SupplyInfoCollection coll = SupplyInfoFactory.getLocalInstance(ctx).getSupplyInfoCollection(view);
			List<String> sfids = new ArrayList<String>();
			Map sendMp = new HashMap();
			List ls = new ArrayList();
	 		 for(int i = 0; i < coll.size(); i++){
				 SupplyInfoInfo info = coll.get(i);
				 if(info != null && info.getId() !=null && !"".equals(info.getId().toString())){
					 sfids.add(info.getId().toString());
					 Map mp = new HashMap();
					 mp.put("fid", info.getId().toString());
					 ls.add(mp);
				 }
			 }
	 		sendMp.put("count", coll.size());
	 		sendMp.put("fids", ls);
	 		 if(sfids !=null && sfids.size() > 0){
	 			 //����OA�ӿ�
	 			SupplyInfoChange.SyncSupplyinfoToMidByIds(ctx,"04",sfids);
		 		System.out.println("##########doSendSuppInfoToOA##########"); 
	 			SupplyInfoLogUnit.requestOAInterfaceNologin(sendMp); 
	 	 		// String msg ="";
		 		   String body = JSON.toJSONString(sendMp);
 		 		   if(body.length() <2000) 
 		 			 SupplyInfoLogUnit.insertLog(ctx, DateBaseProcessType.AddNew, DateBasetype.Material,"","","oper��"+oper,"msg�� "+body);//��¼��־
	 		 }
	 		 
		} catch (BOSException e) {
	 		e.printStackTrace();
		}  
		
	}
	
	private static void requestOAInterface(String json) {
		if(json !=null && !"".equals(json)){
		String token = SeeyonUtil.getToken();
		if(token !=null && !"".equals(token)){
			String url = SeeyonUtil.BASEHPATH+"/lolkk/thirdUrlController.do?method="+SeeyonUtil.UPDATESUPPLIERINFO+"&token="+token;
			String result = SeeyonUtil.doPostJson(url, json);
			System.out.println("########### OA "+SeeyonUtil.UPDATESUPPLIERINFO+" :"+result);
 		}
		}
	}
	
	private List<String> getCompanyList(Context ctx){
		List<String> list = new ArrayList<String>();
		String sql ="select COMID from EAS_CITY_COMPANY";
		try {
			IRowSet rs = DbUtil.executeQuery(ctx,sql);
			while
				(rs.next()) { 
				if(rs.getObject("COMID")!=null && !"".equals(rs.getObject("COMID").toString()))
				list.add(rs.getObject("COMID").toString());
			}
		} catch (BOSException e) {
 			e.printStackTrace();
		} catch (SQLException e) {
 			e.printStackTrace();
		}
		return list;
	}
	
	
}
