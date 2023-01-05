package com.kingdee.eas.custom.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.custom.BadDebtsCollection;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.eas.custom.BadDebtsInfo;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.app.CoreBillBaseController;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface BadDebtsController extends CoreBillBaseController
{
    public BadDebtsCollection getBadDebtsCollection(Context ctx) throws BOSException, RemoteException;
    public BadDebtsCollection getBadDebtsCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public BadDebtsCollection getBadDebtsCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public BadDebtsInfo getBadDebtsInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public BadDebtsInfo getBadDebtsInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public BadDebtsInfo getBadDebtsInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void syncMidData(Context ctx, BadDebtsInfo model) throws BOSException, RemoteException;
    public void verifyData(Context ctx, BadDebtsInfo model) throws BOSException, RemoteException;
    public void generVoucher(Context ctx, BadDebtsInfo model) throws BOSException, RemoteException;
    public void autoSyncMidData(Context ctx, String database) throws BOSException, RemoteException;
    public void autoGenerVoucher(Context ctx, String database) throws BOSException, RemoteException;
}