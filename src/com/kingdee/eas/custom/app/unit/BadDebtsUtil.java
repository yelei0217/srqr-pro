package com.kingdee.eas.custom.app.unit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.assistant.CurrencyInfo;
import com.kingdee.eas.basedata.assistant.PeriodInfo;
import com.kingdee.eas.basedata.assistant.VoucherTypeInfo;
import com.kingdee.eas.basedata.master.account.AccountViewInfo;
import com.kingdee.eas.basedata.master.auxacct.AssistantHGInfo;
import com.kingdee.eas.basedata.master.auxacct.AsstAccountInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitFactory;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.custom.BadDebtsFactory;
import com.kingdee.eas.custom.BadDebtsInfo;
import com.kingdee.eas.custom.EAISynTemplate;
import com.kingdee.eas.custom.IBadDebts;
import com.kingdee.eas.custom.IPreReport;
import com.kingdee.eas.custom.PreReportFactory;
import com.kingdee.eas.custom.PreReportInfo;
import com.kingdee.eas.custom.app.DateBaseProcessType;
import com.kingdee.eas.custom.app.DateBasetype;
import com.kingdee.eas.custom.app.VoucherStatus;
import com.kingdee.eas.custom.app.insertbill.PreReportBillSupport;
import com.kingdee.eas.fi.gl.EntryDC;
import com.kingdee.eas.fi.gl.IVoucher;
import com.kingdee.eas.fi.gl.ReqStatus;
import com.kingdee.eas.fi.gl.SourceType;
import com.kingdee.eas.fi.gl.VoucherAssistRecordInfo;
import com.kingdee.eas.fi.gl.VoucherEntryCollection;
import com.kingdee.eas.fi.gl.VoucherEntryInfo;
import com.kingdee.eas.fi.gl.VoucherFactory;
import com.kingdee.eas.fi.gl.VoucherInfo;
import com.kingdee.eas.fi.gl.VoucherStatusEnum;
import com.kingdee.eas.fi.gl.cslacct.ReqStatusEnum;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.SystemEnum;

/**
 * 
 * @author Lei.ye
 *  坏账准备工具类
 */

public class BadDebtsUtil {

	/***
	 * 生成 坏账凭证 
	 */
	public static void doSyncMidData(Context ctx, String database) throws BOSException {
		System.out.println("修改 EAS_BADDEBTS_REPORT_MIDTABLE 中间表--门诊在中间表不存在--------begin");
		String updateSQL = " update EAS_BADDEBTS_REPORT_MIDTABLE a set a.F_EAS_FLAG = -1,a.F_REMARK ='门诊在中间表不存在' ,a.F_EAS_REC_TIME = to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') where a.F_EAS_FLAG = 0 and not exists (select 1 from EAS_ORG_MIDTABLE c where a.F_CLINIC_NUMBER = c.FID) ";
		EAISynTemplate.execute(ctx, database, updateSQL); 
		System.out.println("修改 EAS_BADDEBTS_REPORT_MIDTABLE 中间表--门诊在中间表不存在--------end");

		String sql = "select a.F_ID FID, a.F_AMOUNT amount, a.F_DATE fdate, F_CLINIC_HIS_ID hisid, a.F_CLINIC_NAME easname, F_CLINIC_NUMBER easid, F_HIS_INSERT_TIME HISTIME from EAS_BADDEBTS_REPORT_MIDTABLE a where a.F_EAS_FLAG = 0 and exists(select 1 from EAS_ORG_MIDTABLE b where a.F_CLINIC_NUMBER = b.FID)";

		String userId = "ZypTdtSLS8S90LPPdhP1MxO33n8=";
		UserInfo userInfo = new UserInfo();
		userInfo.setId(BOSUuid.read(userId));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> list = EAISynTemplate.query(ctx, database, sql.toString());
		if ((list != null) && (list.size() > 0)) {
			System.out.println("--------------------------" + list.size());
			BigDecimal amount = new BigDecimal(0);
			String date = "";
			String hisid = "";
			String easname = "";
			String easid = ""; 
			String number = "";
			String insTime = "";
			String id = "";
			String customer = "";
 			CoreBaseCollection collection = new CoreBaseCollection();
			Map retInMap = new HashMap();
			//Map retOutMap = new HashMap();
			List updateStatusSqls = new ArrayList();
			CoreBillBaseCollection errorColl = new CoreBillBaseCollection();
			boolean flag1 = true;
			boolean flag2 = true;
			boolean flag = true;
			Date bizDate = null;
			String msg = "";

			for (Map map : list) {
				BadDebtsInfo info = new BadDebtsInfo();
				date = (String) map.get("FDATE");
				amount = new BigDecimal(map.get("AMOUNT").toString());
				hisid = (String) map.get("HISID");
				easname = (String) map.get("EASNAME");
				easid = (String) map.get("EASID"); 
				insTime = (String) map.get("HISTIME");
				customer = (String) map.get("CUSTOMER");
				id = (String) map.get("FID");
			 
				if ((easid != null) && (!("".equals(easid)))) {
					if ("15hAmVnhTQKwvR+BRqNalMznrtQ=".equals(easid))
						easid = "fFZRnpwtSuCYhbW5gBckscznrtQ=";
					retInMap = AppUnit.getInfoByCompanyID(ctx, easid);
				} else {
					retInMap.put("FNumber", "M");
				}  
				number = ((String) retInMap.get("FNumber"))+"-"+ hisid+"-" + date;
				info.setNumber(number);
				info.setId(BOSUuid.create("F42D6A80"));
				info.setAmount(amount);
				info.setAvailable(false);
				bizDate = DateUnit.strToDate(date);
				info.setBizDate(bizDate);
				CompanyOrgUnitInfo companyInfo = null;
				try {
					companyInfo = CompanyOrgUnitFactory.getLocalInstance(ctx)
							.getCompanyOrgUnitInfo(new ObjectUuidPK(easid));
				} catch (EASBizException e) {
					e.printStackTrace();
				}
				CtrlUnitInfo cuInfo = companyInfo.getCU();
				info.setCU(cuInfo);
				info.setHisInsTime(insTime);
				info.setCreateTime(new Timestamp(new Date().getTime()));
				info.setCreator(userInfo);
				info.setEASInsTime(sdf.format(new Date()));
				//info.setVoucherNumber("_" + customer);

				if ((info.getAmount() == null) || (BigDecimal.ZERO.compareTo(info.getAmount()) == 0)) {
					flag = false;
					msg = "金额为0";
				}

				if ((easid != null) && (!("".equals(easid)))) {
					PeriodInfo periodInfo = VoucherUnit.getPeriodInfoByComany(ctx, easid, bizDate);
					if ((periodInfo != null) && (periodInfo.getId() != null)
							&& (!("".equals(periodInfo.getId().toString())))) {
						flag1 = true;
					} else {
						flag1 = false;
						msg = "会计期间查询失败";
					}
				} 

				if ((!(flag)) || (!(flag1)) || (!(flag2))) {
					info.setVoucherStatus(VoucherStatus.VerifyFail);
					info.setDescription(msg);

					errorColl.add(info);
				} else {
					info.setVoucherStatus(VoucherStatus.VerifySuccess);
				}

				info.setInClinicHisID(hisid);
				info.setInEasOrgID(easid);
				info.setInEasOrgName(easname);
				info.setInEasOrgNumber((String) retInMap.get("FNumber"));
			 
				collection.add(info);
				if (info.getVoucherStatus() == VoucherStatus.VerifyFail)
					updateStatusSqls.add("update EAS_BADDEBTS_REPORT_MIDTABLE set F_EAS_FLAG = -1,F_REMARK = '"+ msg+ "验证失败' ,F_EAS_REC_TIME = to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') where F_ID = '"+ id + "'");
				else {
					updateStatusSqls.add("update EAS_BADDEBTS_REPORT_MIDTABLE set F_EAS_FLAG = 1,F_REMARK = '同步成功' ,F_EAS_REC_TIME = to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') where F_ID = '"+ id + "'");
				}
			}
			IBadDebts ibiz = BadDebtsFactory.getLocalInstance(ctx);
			EAISynTemplate.executeBatch(ctx, database, updateStatusSqls);
			try {
				ibiz.save(collection);
				if (msg !=null && !"".equals(msg))
					errorDatePush(ctx, errorColl);
			} catch (EASBizException e) {
				e.printStackTrace();
			}
		}
	}

	public static void errorDatePush(Context ctx, CoreBillBaseCollection c) throws BOSException {
 		if ((c != null) && (c.size() > 0)) { 
			 AppUnit.insertLog(ctx, DateBaseProcessType.GOrder, DateBasetype.HIS_Report,new Date().toString(), new Date().toString(),"坏账准备同步中间表数据，异常信息插入HIS异常数据列表异常", "参数异常");
		} else
			AppUnit.insertLog(ctx, DateBaseProcessType.GOrder, DateBasetype.HIS_Report, new Date().toString(), new Date().toString(), "坏账准备同步中间表数据，异常信息插入HIS异常数据列表异常","model为空");
	}
	
	
	/***
	 * 同步 坏账中间表数据 至 EAS坏账准备单据
	 */
	public static void doGenerVoucher(Context ctx, IObjectValue model)
			throws BOSException {
		BadDebtsInfo bill = (BadDebtsInfo) model;
		BigDecimal amount = bill.getAmount();
		String companyID = bill.getInEasOrgID();
 		IBadDebts ibr = BadDebtsFactory.getLocalInstance(ctx);
		IObjectPK pk = new ObjectUuidPK(bill.getId());
		Date bizDate = bill.getBizDate();
  			try {
				VoucherInfo ventryInfo = createVoucherInfo(ctx, amount, companyID, bizDate);
				if(ventryInfo !=null ){
					IVoucher iv = VoucherFactory.getLocalInstance(ctx);
					iv.save(ventryInfo);
					bill.setVoucherNumber(ventryInfo.getNumber());
					bill.setVoucherStatus(VoucherStatus.GENSUCCESS);
				//	bill.setDescription("凭证编号：" + ventryInfo.getNumber());
					ibr.update(pk, bill);
					return;
				}else{
					bill.setVoucherStatus(VoucherStatus.VerifyFail);
					bill.setDescription("数据校验失败，请检查数据。");
					ibr.update(pk, bill);
					//errorColl.add(bill); 
					AppUnit.insertLog(ctx, DateBaseProcessType.GOrder, DateBasetype.HIS_Report, new Date().toString(), new Date().toString(), "坏账准备同步中间表数据","数据校验失败，请检查数据。");
				}
			} catch (EASBizException e) {
				e.printStackTrace();
			}
	}
	
	private static VoucherInfo createVoucherInfo(Context ctx, BigDecimal amount,String companyId, Date bizDate) throws BOSException {
		VoucherInfo info = null;
		if ( amount != null && (companyId != null) && (!("".equals(companyId)))) {
			String debitAccountViewNo = "6701.01";  //借方科目
			String creditAccountViewNo = "1231.01";  //贷方科目 
//			String credithg = "jbYAAAAEFghBimy7"; // 贷方 横表ID 
 			String msg = " 欠款无法收回转损失";  
// 			AsstAccountInfo caa = new AsstAccountInfo();
//			caa.setId(BOSUuid.read("5Mq4xNniRwSj7zyOUzaOLkZaEPg=")); // 辅助账类型：客户 
			BigDecimal exchangeRate = new BigDecimal(1); 
			info = new VoucherInfo(); 
			info.setAttachments(0);
			info.setAuditor(null);
			info.setAvailable(false);
			info.setBizDate(bizDate);

			PeriodInfo periodInfo = VoucherUnit.getPeriodInfoByComany(ctx,companyId, bizDate);
			info.setBizStatus(VoucherStatusEnum.TEMP);
			info.setBookedDate(bizDate);
			UserInfo userInfo = new UserInfo();
			userInfo.setId(BOSUuid.read("ZypTdtSLS8S90LPPdhP1MxO33n8="));
			Timestamp time = new Timestamp(new Date().getTime());
			info.setCreateTime(time);
			info.setCreator(userInfo);
			info.setLastUpdateTime(time);
			info.setLastUpdateUser(userInfo);
			info.setHandler(userInfo);

			CurrencyInfo currency = new CurrencyInfo();
			currency.setId(BOSUuid
					.read("dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"));
			info.setCurrency(currency);
			CompanyOrgUnitInfo companyInfo = null;
			try {
				companyInfo = CompanyOrgUnitFactory.getLocalInstance(ctx)
						.getCompanyOrgUnitInfo(new ObjectUuidPK(companyId));
			} catch (EASBizException e) {
				e.printStackTrace();
			}
			info.setCompany(companyInfo);
			CtrlUnitInfo ctrlUnitInfo = companyInfo.getCU();
			info.setCU(ctrlUnitInfo);
			info.setDebitEntryCount(1);
			info.setCreditEntryCount(1);
			info.setReportingCreditAmount(BigDecimal.ZERO);
			info.setReportingDebitAmount(BigDecimal.ZERO);
			info.setLocalCreditAmount(amount);
			info.setLocalDebitAmount(amount);
			info.setPeriod(periodInfo);
			info.setSourceSys(SystemEnum.GENERALLEDGER);
			info.setSourceType(SourceType.HANDCRAFT);
			VoucherTypeInfo voucherTypeInfo = new VoucherTypeInfo();
			voucherTypeInfo.setId(BOSUuid.read("LCoeYmyISCmCVHVO7E0ShHSH010="));
			info.setVoucherType(voucherTypeInfo);
			info.setWebVoucher(false);
			info.setDescription("自动生成凭证");

			VoucherEntryCollection vec = new VoucherEntryCollection();

			VoucherEntryInfo debitInfo = new VoucherEntryInfo();  //借方 凭证明细行
			debitInfo.setSeq(1);
			debitInfo.setCompany(companyInfo);
			debitInfo.setPeriod(periodInfo);
			//debitInfo.setCAA(caa);
			debitInfo.setProfitCenter(null);
			debitInfo.setLocalExchangeRate(exchangeRate);
			debitInfo.setReportingExchangeRate(BigDecimal.ZERO);
			debitInfo.setPrice(BigDecimal.ZERO);
			debitInfo.setQuantity(BigDecimal.ZERO);
			debitInfo.setStandardQuantity(BigDecimal.ZERO);
			debitInfo.setDescription(DateUnit.dateToStr(bizDate) + msg);
			debitInfo.setIsCheck(false);
			debitInfo.setIsHand(true);
			debitInfo.setIsVerify(false);
			debitInfo.setCurrency(currency);
			debitInfo.setMaCtrlAmount(amount);
			debitInfo.setMaHoldAmount(BigDecimal.ZERO);
			debitInfo.setEntryDC(EntryDC.DEBIT);
			debitInfo.setOriginalAmount(amount);
			debitInfo.setLocalAmount(amount);
			debitInfo.setReportingAmount(BigDecimal.ZERO);
			AccountViewInfo debitaccountViewInfo = VoucherUnit.getAccountViewInfoByNumberAndComany(ctx,debitAccountViewNo, companyId);
			debitInfo.setAccount(debitaccountViewInfo);
			
			vec.add(debitInfo);

			VoucherEntryInfo creditInfo = new VoucherEntryInfo(); // 贷方凭证明细行
			creditInfo.setSeq(2);
			creditInfo.setCompany(companyInfo);
			creditInfo.setPeriod(periodInfo);
//			creditInfo.setCAA(caa);
			creditInfo.setProfitCenter(null);
			creditInfo.setLocalExchangeRate(exchangeRate);
			creditInfo.setReportingExchangeRate(BigDecimal.ZERO);
			creditInfo.setPrice(BigDecimal.ZERO);
			creditInfo.setQuantity(BigDecimal.ZERO);
			creditInfo.setStandardQuantity(BigDecimal.ZERO);
			creditInfo.setDescription(DateUnit.dateToStr(bizDate) + msg);
			creditInfo.setIsCheck(false);
			creditInfo.setIsHand(true);
			creditInfo.setIsVerify(false);
			creditInfo.setCurrency(currency);
			creditInfo.setMaCtrlAmount(amount);
			creditInfo.setMaHoldAmount(BigDecimal.ZERO);
			creditInfo.setEntryDC(EntryDC.CREDIT);
			creditInfo.setOriginalAmount(amount);
			creditInfo.setLocalAmount(amount);
			creditInfo.setReportingAmount(BigDecimal.ZERO);
			AccountViewInfo creditAccountViewInfo = VoucherUnit.getAccountViewInfoByNumberAndComany(ctx,creditAccountViewNo, companyId);
			creditInfo.setAccount(creditAccountViewInfo);

//			VoucherAssistRecordInfo aic = new VoucherAssistRecordInfo();
//			aic.setSeq(1);
//			aic.setCompany(companyInfo);
//			aic.setPeriod(periodInfo);
//			aic.setAccount(creditAccountViewInfo);
//
//			AssistantHGInfo hgc = new AssistantHGInfo();
//			hgc.setId(BOSUuid.read(credithg));
//			aic.setAssGrp(hgc);
//			aic.setAssistQty(BigDecimal.ZERO);
//			aic.setBizDate(bizDate);
//			aic.setOriginalAmount(amount);
//			aic.setLocalAmount(amount);
//			aic.setReportingAmount(BigDecimal.ZERO);
//			aic.setQuantity(BigDecimal.ZERO);
//			aic.setDescription(DateUnit.dateToStr(bizDate) + msg);
//			aic.setEndDate(bizDate);
//			aic.setStandardQuantity(BigDecimal.ZERO);
//			aic.setIsFullProp(false);
//			aic.setReqStatus(ReqStatus.NONE);
//			aic.setReqCheckStatus(ReqStatusEnum.UNAUDIT);
//			aic.setIsVierified(false);
//			aic.setPrice(BigDecimal.ZERO);
//			aic.setMaCtrlAmount(amount);
//			aic.setMaHoldAmount(BigDecimal.ZERO);
//			creditInfo.getAssistRecords().add(aic);
			vec.add(creditInfo);

			info.getEntries().addCollection(vec);
		}
		return info;
	}

	
}
