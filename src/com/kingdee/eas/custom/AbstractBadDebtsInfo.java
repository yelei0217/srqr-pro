package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractBadDebtsInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractBadDebtsInfo()
    {
        this("id");
    }
    protected AbstractBadDebtsInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:日报坏账准备's 是否生成凭证property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    /**
     * Object:日报坏账准备's HIS门诊编码property 
     */
    public String getInClinicHisID()
    {
        return getString("InClinicHisID");
    }
    public void setInClinicHisID(String item)
    {
        setString("InClinicHisID", item);
    }
    /**
     * Object:日报坏账准备's 金额property 
     */
    public java.math.BigDecimal getAmount()
    {
        return getBigDecimal("Amount");
    }
    public void setAmount(java.math.BigDecimal item)
    {
        setBigDecimal("Amount", item);
    }
    /**
     * Object:日报坏账准备's 状态property 
     */
    public com.kingdee.eas.custom.app.VoucherStatus getVoucherStatus()
    {
        return com.kingdee.eas.custom.app.VoucherStatus.getEnum(getString("VoucherStatus"));
    }
    public void setVoucherStatus(com.kingdee.eas.custom.app.VoucherStatus item)
    {
		if (item != null) {
        setString("VoucherStatus", item.getValue());
		}
    }
    /**
     * Object:日报坏账准备's HIS插入时间property 
     */
    public String getHisInsTime()
    {
        return getString("HisInsTime");
    }
    public void setHisInsTime(String item)
    {
        setString("HisInsTime", item);
    }
    /**
     * Object:日报坏账准备's EAS插入时间property 
     */
    public String getEASInsTime()
    {
        return getString("EASInsTime");
    }
    public void setEASInsTime(String item)
    {
        setString("EASInsTime", item);
    }
    /**
     * Object:日报坏账准备's 凭证字号property 
     */
    public String getVoucherNumber()
    {
        return getString("VoucherNumber");
    }
    public void setVoucherNumber(String item)
    {
        setString("VoucherNumber", item);
    }
    /**
     * Object:日报坏账准备's 是否删除property 
     */
    public String getDelete()
    {
        return getString("Delete");
    }
    public void setDelete(String item)
    {
        setString("Delete", item);
    }
    /**
     * Object:日报坏账准备's EAS门诊IDproperty 
     */
    public String getInEasOrgID()
    {
        return getString("InEasOrgID");
    }
    public void setInEasOrgID(String item)
    {
        setString("InEasOrgID", item);
    }
    /**
     * Object:日报坏账准备's EAS门诊编码property 
     */
    public String getInEasOrgNumber()
    {
        return getString("InEasOrgNumber");
    }
    public void setInEasOrgNumber(String item)
    {
        setString("InEasOrgNumber", item);
    }
    /**
     * Object:日报坏账准备's EAS门诊名称property 
     */
    public String getInEasOrgName()
    {
        return getString("InEasOrgName");
    }
    public void setInEasOrgName(String item)
    {
        setString("InEasOrgName", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("F42D6A80");
    }
}