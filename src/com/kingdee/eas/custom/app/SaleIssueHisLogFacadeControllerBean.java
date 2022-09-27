package com.kingdee.eas.custom.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;
import com.kingdee.eas.custom.EAISynTemplate;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.util.LowTimer;

import java.lang.String;

public class SaleIssueHisLogFacadeControllerBean extends AbstractSaleIssueHisLogFacadeControllerBean
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4188761913912311823L;
	
	private static Logger logger = Logger.getLogger("com.kingdee.eas.custom.app.SaleIssueHisLogFacadeControllerBean");
	private LowTimer timer = new LowTimer();
	
	/**
	 * 
	 * 同步销售出库单 至中间记录单
	 */
	@Override
	protected void _doCostSyncByMonth(Context ctx, String month)throws BOSException {
		this.timer.reset(); 
		
		if(month == null || "".equals(month)){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);// 月份减1  
			Date resultDate = calendar.getTime(); // 结果  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
			month =  sdf.format(resultDate) ;
		}
			int y = Integer.parseInt(month.substring(0,4));
			int m = Integer.parseInt(month.substring(4,6));
 			
			DbUtil.execute(ctx, "delete from CT_SRQ_SALEISSUEHISLOGENTRY where FPARENTID in ( select FID FROM CT_SRQ_SALEISSUEHISLOG where CFYEAR="+y+" and CFPERIOD="+m+" ) ");
			
 			DbUtil.execute(ctx, "delete FROM CT_SRQ_SALEISSUEHISLOG where CFYEAR="+y+" and CFPERIOD="+m+" ");

 			StringBuffer sbr = new StringBuffer();
 			sbr.append(" /*dialect*/insert into CT_SRQ_SALEISSUEHISLOG (FCREATORID, FCREATETIME, FLASTUPDATEUSERID, FLASTUPDATETIME, FCONTROLUNITID, FNUMBER, FBIZDATE, FHANDLERID, FDESCRIPTION,").append("\r\n");
 			sbr.append(" FID, FFIVOUCHERED, CFISSNUMBER, CFCOMPANYID, CFHISID, CFYEAR, CFPERIOD, CFISSID, CFSYNCSTATUS, CFSTATUS )").append("\r\n");
 			sbr.append(" select distinct '256c221a-0106-1000-e000-10d7c0a813f413B7DE7F',sysdate,'256c221a-0106-1000-e000-10d7c0a813f413B7DE7F',sysdate,'00000000-0000-0000-0000-000000000000CCE7AED4',").append("\r\n");
 			sbr.append(" (sysdate-to_date('1970-01-01 08:00:00','yyyy-mm-dd hh24:mi:ss'))*1000*24*3600||FNUMBER,sysdate,'256c221a-0106-1000-e000-10d7c0a813f413B7DE7F',").append("\r\n");
 			sbr.append(" FNUMBER||(sysdate-to_date('1970-01-01 08:00:00','yyyy-mm-dd hh24:mi:ss'))*1000*24*3600, newbosid('EE852308'),0,FNUMBER,FSTORAGEORGUNITID,").append("\r\n");
 			sbr.append(" decode(substr(CFHISREQID,1,4),'SALE',substr(CFHISREQID,5),CFHISREQID) hisid, FYEAR,FPERIOD,FID,1,3 from (").append("\r\n");
 			sbr.append(" select distinct a.FID ,a.FNUMBER,a.FSTORAGEORGUNITID,a.CFHISREQID,a.FMONTH,a.FYEAR,a.FPERIOD").append("\r\n");
 			sbr.append(" from T_IM_SaleIssueBill a").append("\r\n");
 			sbr.append(" INNER JOIN T_IM_SALEISSUEENTRY b on a.FID =b.FPARENTID").append("\r\n");
 			sbr.append(" where   a.FMONTH ='"+month+"' and a.fbasestatus >=4 and a.FBIZTYPEID in ('d8e80652-010e-1000-e000-04c5c0a812202407435C','d8e80652-0110-1000-e000-04c5c0a812202407435C')").append("\r\n");
 			sbr.append(" and b.FINVUPDATETYPEID in ('8r0AAAAEaOnC73rf','8r0AAAAEaPbC73rf','CeUAAAAIdBvC73rf')").append("\r\n");
 			sbr.append(" and b.CFHISMINGXIID is not null and b.CFHISSFXMID is not null and a.cfhisdanjubianma is not null )").append("\r\n");
 			DbUtil.execute(ctx,sbr.toString());
 			sbr.setLength(0);
 			sbr = new StringBuffer();
 			sbr.append(" /*dialect*/insert into CT_SRQ_SALEISSUEHISLOGENTRY (FSEQ, FID, FPARENTID, CFQTY, CFAMOUNT, CFHISPAYITEM, CFHISID, CFISSENTRYID, CFPINPAI, CFGUIGE, CFHUOHAO, CFXINGHAO, CFMATERIALNO )").append("\r\n");
 			sbr.append(" select distinct b.FSEQ, newbosid('27DF9A4A'),c.FID,").append("\r\n");
 			sbr.append(" decode(b.FINVUPDATETYPEID,'8r0AAAAEaPbC73rf',b.FQTY,'CeUAAAAIdBvC73rf',b.FQTY,'8r0AAAAEaOnC73rf', (case when substr(a.FNUMBER,1,10) ='*VMISIssue' then 0 else b.FQTY end) ,b.FQTY) qty,").append("\r\n");
 			sbr.append(" decode(b.FINVUPDATETYPEID,'8r0AAAAEaPbC73rf',b.FSTANDARDCOST,'CeUAAAAIdBvC73rf',0,'8r0AAAAEaOnC73rf',b.FACTUALCOST,b.FACTUALCOST) cost,").append("\r\n");
 			sbr.append(" b.CFHISSFXMID itemid,decode(substr(b.CFHISMINGXIID,1,4),'SALE',substr(b.CFHISMINGXIID,5),b.CFHISMINGXIID) sfxmid,b.FID,").append("\r\n");
 			sbr.append(" m.CFPINPAI,m.FMODEL,m.CFHUOHAO,m.CFXINGHAO,m.FNUMBER").append("\r\n");
 			sbr.append(" from T_IM_SaleIssueBill a").append("\r\n");
 			sbr.append(" INNER JOIN T_IM_SALEISSUEENTRY b on a.FID = b.FPARENTID").append("\r\n");
 			sbr.append(" inner join CT_SRQ_SALEISSUEHISLOG c on c.CFISSID = a.FID").append("\r\n");
 			sbr.append(" inner join t_bd_material m on b.FMATERIALID = m.fid").append("\r\n");
 			sbr.append(" where a.FMONTH ='"+month+"' and a.fbasestatus >=4 and a.FBIZTYPEID in ('d8e80652-010e-1000-e000-04c5c0a812202407435C','d8e80652-0110-1000-e000-04c5c0a812202407435C')").append("\r\n");
 			sbr.append(" and b.FINVUPDATETYPEID in ('8r0AAAAEaOnC73rf','8r0AAAAEaPbC73rf','CeUAAAAIdBvC73rf')").append("\r\n");
 			sbr.append(" and b.CFHISMINGXIID is not null and b.CFHISSFXMID is not null and a.cfhisdanjubianma is not null").append("\r\n");
 			DbUtil.execute(ctx,sbr.toString());
		 
		 logger.info("do _doCostSyncByMonth method cost :" + this.timer.msValue());
	}
	
	
	/**
	 * 
	 * 同步 中间记录单 至 中间库
	 */
	@Override
	protected void _doSyncIssueLogToMid(Context ctx, String month,String database) throws BOSException {
		this.timer.reset(); 
		if( database !=null && !"".equals(database) ){
			
			if(month == null || "".equals(month)){
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);// 月份减1  
				Date resultDate = calendar.getTime(); // 结果  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
				month =  sdf.format(resultDate) ;
			}
			
			int y = Integer.parseInt(month.substring(0,4));
			int m = Integer.parseInt(month.substring(4,6));
			String delSQL ="delete from EAS_SALEISSUE_SUB_HIS where fid in ( select distinct b.CFISSENTRYID from GAVIN.CT_SRQ_SALEISSUEHISLOG a " +
			" inner join GAVIN.CT_SRQ_SALEISSUEHISLOGentry b on b.FParentID = a.fid where a.CFYEAR="+y+" and a.CFPERIOD="+m+" ) ";
			
			//删除中间表表明细表
			EAISynTemplate.execute(ctx, database, delSQL);
			
			delSQL ="delete from EAS_SALEISSUE_HIS where fid in (select distinct a.CFISSID from GAVIN.CT_SRQ_SALEISSUEHISLOG a where a.CFYEAR="+y+" and a.CFPERIOD="+m+" ) ";
			//删除中间表 主表
			EAISynTemplate.execute(ctx, database, delSQL);

			//插入高值数量主表 
			StringBuffer sbr = new StringBuffer("insert into EAS_SALEISSUE_HIS(FID,FNumber,FOrgID,FYear,FPeriod,FHISID,EASSIGN,HISSIGN,update_time)").append("\r\n");
			sbr.append(" select CFISSID,CFISSNUMBER,CFCOMPANYID,CFYEAR,CFPERIOD,CFHISID,0,0,FLASTUPDATETIME").append("\r\n");
			sbr.append(" from GAVIN.CT_SRQ_SALEISSUEHISLOG where CFYEAR="+y+" and CFPERIOD="+m+" ").append("\r\n");
			EAISynTemplate.execute(ctx, database, sbr.toString());
			sbr.setLength(0);
 			sbr = new StringBuffer("insert into EAS_SALEISSUE_SUB_HIS(FID,FParentID,FQty,FActualCost,FHISPayItem,FHISID,FMATERIALNUMBER,FPINPAI,FHUOHAO,FMODEL,FXINGHAO)").append("\r\n");
 			sbr.append(" select distinct  b.CFISSENTRYID,a.CFISSID,b.CFQTY,b.CFAMOUNT,b.CFHISPAYITEM,b.CFHISID,b.CFMATERIALNO,b.CFPINPAI,b.CFHUOHAO,b.CFGUIGE,b.CFXINGHAO ").append("\r\n");
 		    sbr.append(" from GAVIN.CT_SRQ_SALEISSUEHISLOG a inner join GAVIN.CT_SRQ_SALEISSUEHISLOGentry b on b.FParentID = a.fid ").append("\r\n");
 			sbr.append(" where a.CFYEAR="+y+" and a.CFPERIOD="+m+" ").append("\r\n");
 			EAISynTemplate.execute(ctx, database, sbr.toString());   
			
		}
		 logger.info("do _doSyncIssueLogToMid method cost :" + this.timer.msValue());
	}

}