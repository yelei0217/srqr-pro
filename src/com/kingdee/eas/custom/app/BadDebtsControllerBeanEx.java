package com.kingdee.eas.custom.app;

import org.apache.log4j.Logger;
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

import com.kingdee.eas.framework.app.CoreBillBaseControllerBean;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.custom.BadDebtsInfo;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.lang.String;
import com.kingdee.eas.custom.BadDebtsCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class BadDebtsControllerBeanEx extends com.kingdee.eas.custom.app.BadDebtsControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.custom.app.BadDebtsControllerBeanEx");
    protected void _syncMidData(Context ctx, IObjectValue model)throws BOSException
    {
	     super._syncMidData(ctx, model);
    }
    protected void _verifyData(Context ctx, IObjectValue model)throws BOSException
    {
	     super._verifyData(ctx, model);
    }
    protected void _generVoucher(Context ctx, IObjectValue model)throws BOSException
    {
	     super._generVoucher(ctx, model);
    }
    protected void _autoSyncMidData(Context ctx, String database)throws BOSException
    {
	     super._autoSyncMidData(ctx, database);
    }
    protected void _autoGenerVoucher(Context ctx, String database)throws BOSException
    {
	     super._autoGenerVoucher(ctx, database);
    }
}				
