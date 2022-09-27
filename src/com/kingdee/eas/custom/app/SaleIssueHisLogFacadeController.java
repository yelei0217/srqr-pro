package com.kingdee.eas.custom.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface SaleIssueHisLogFacadeController extends BizController
{
    public void doCostSyncByMonth(Context ctx, String month) throws BOSException, RemoteException;
    public void doSyncIssueLogToMid(Context ctx, String month, String database) throws BOSException, RemoteException;
}