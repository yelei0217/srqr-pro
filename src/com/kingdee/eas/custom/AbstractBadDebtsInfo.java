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
     * Object:�ձ�����׼��'s �Ƿ�����ƾ֤property 
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
     * Object:�ձ�����׼��'s HIS�������property 
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
     * Object:�ձ�����׼��'s ���property 
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
     * Object:�ձ�����׼��'s ״̬property 
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
     * Object:�ձ�����׼��'s HIS����ʱ��property 
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
     * Object:�ձ�����׼��'s EAS����ʱ��property 
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
     * Object:�ձ�����׼��'s ƾ֤�ֺ�property 
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
     * Object:�ձ�����׼��'s �Ƿ�ɾ��property 
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
     * Object:�ձ�����׼��'s EAS����IDproperty 
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
     * Object:�ձ�����׼��'s EAS�������property 
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
     * Object:�ձ�����׼��'s EAS��������property 
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