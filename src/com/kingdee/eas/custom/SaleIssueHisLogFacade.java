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
     *ͬ�����۳�������¼��-User defined method
     *@param month ����ִ���·�
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
     *@param month ִ�������·�
     *@param database ���Ŀ����
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
    /**
     *ͬ�������м������-User defined method
     *@param database �м�����
     */
    public void syncBadDebtMidData(String database) throws BOSException
    {
        try {
            getController().syncBadDebtMidData(getContext(), database);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���ɻ���ƾ֤-User defined method
     */
    public void genBadDebtVoucher() throws BOSException
    {
        try {
            getController().genBadDebtVoucher(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}