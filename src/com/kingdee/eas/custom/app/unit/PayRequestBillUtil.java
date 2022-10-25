 package com.kingdee.eas.custom.app.unit;
 
 
import java.sql.SQLException;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.custom.util.VerifyUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;
 
 public class PayRequestBillUtil
 {
   public static boolean queryPersonStatus(Context ctx, String fid) {
     boolean flag = false;
     if (VerifyUtil.notNull(fid)) {
       try {
         String sql = "select COUNT(1) C from V_HR_PersonMain_oa where FID ='" + fid + "' and FEmployeeTypeName ='ÆôÓÃ' ";
         IRowSet rs = DbUtil.executeQuery(ctx, sql);
         if (rs.next() && 
           rs.getObject("C") != null && !"".equals(rs.getObject("C").toString()) && 
           Integer.valueOf(rs.getObject("C").toString()).compareTo(Integer.valueOf(1)) >= 0) {
           flag = true;
         }
       }
       catch (BOSException e) {
         e.printStackTrace();
       } catch (SQLException e) {
         e.printStackTrace();
       } 
     }
     return flag;
   } 
   
 }