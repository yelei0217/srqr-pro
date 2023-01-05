package com.kingdee.eas.custom.app;

import javax.ejb.*;
import java.rmi.RemoteException;
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
import com.kingdee.eas.framework.Result;
import com.kingdee.eas.framework.LineResult;
import com.kingdee.eas.framework.exception.EASMultiException;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;

import java.lang.String;



public abstract class AbstractSaleIssueHisLogFacadeControllerBean extends AbstractBizControllerBean implements SaleIssueHisLogFacadeController
{
    protected AbstractSaleIssueHisLogFacadeControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("D499D659");
    }

    public void doCostSyncByMonth(Context ctx, String month) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7473f20f-1304-43d6-994f-bcdd0b29018c"), new Object[]{ctx, month});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _doCostSyncByMonth(ctx, month);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _doCostSyncByMonth(Context ctx, String month) throws BOSException
    {    	
        return;
    }

    public void doSyncIssueLogToMid(Context ctx, String month, String database) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("747cab22-db1b-4178-852b-ea06932f5a18"), new Object[]{ctx, month, database});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _doSyncIssueLogToMid(ctx, month, database);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _doSyncIssueLogToMid(Context ctx, String month, String database) throws BOSException
    {    	
        return;
    }

    public void syncBadDebtMidData(Context ctx, String database) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("07e1a812-c1a3-48e5-a442-ac7d1c293bed"), new Object[]{ctx, database});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _syncBadDebtMidData(ctx, database);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _syncBadDebtMidData(Context ctx, String database) throws BOSException
    {    	
        return;
    }

    public void genBadDebtVoucher(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e1dd7c0b-41be-4f52-a690-4f5fadfa8379"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _genBadDebtVoucher(ctx);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _genBadDebtVoucher(Context ctx) throws BOSException
    {    	
        return;
    }

}