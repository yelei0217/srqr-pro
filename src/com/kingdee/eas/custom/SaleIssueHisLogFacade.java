package com.kingdee.eas.custom;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.custom.app.*;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;

public class SaleIssueHisLogFacade extends AbstractBizCtrl implements ISaleIssueHisLogFacade
{
    public SaleIssueHisLogFacade()
    {
        super();
        registerInterface(ISaleIssueHisLogFacade.class, this);
    }
    public SaleIssueHisLogFacade(Context ctx)
    {
        super(ctx);
        registerInterface(ISaleIssueHisLogFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("D499D659");
    }
    private SaleIssueHisLogFacadeController getController() throws BOSException
    {
        return (SaleIssueHisLogFacadeController)getBizController();
    }
    /**
     *同步销售出库至记录单-User defined method
     *@param month 任务执行月份
     */
    public void doCostSyncByMonth(String month) throws BOSException
    {
        try {
            getController().doCostSyncByMonth(getContext(), month);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *doSyncIssueLogToMid-User defined method
     *@param month 执行任务月份
     *@param database 中心库编码
     */
    public void doSyncIssueLogToMid(String month, String database) throws BOSException
    {
        try {
            getController().doSyncIssueLogToMid(getContext(), month, database);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}