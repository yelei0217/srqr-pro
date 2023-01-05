package com.kingdee.eas.custom;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.CoreBillBase;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.custom.app.*;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class BadDebts extends CoreBillBase implements IBadDebts
{
    public BadDebts()
    {
        super();
        registerInterface(IBadDebts.class, this);
    }
    public BadDebts(Context ctx)
    {
        super(ctx);
        registerInterface(IBadDebts.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("F42D6A80");
    }
    private BadDebtsController getController() throws BOSException
    {
        return (BadDebtsController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public BadDebtsCollection getBadDebtsCollection() throws BOSException
    {
        try {
            return getController().getBadDebtsCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public BadDebtsCollection getBadDebtsCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getBadDebtsCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public BadDebtsCollection getBadDebtsCollection(String oql) throws BOSException
    {
        try {
            return getController().getBadDebtsCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public BadDebtsInfo getBadDebtsInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getBadDebtsInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public BadDebtsInfo getBadDebtsInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getBadDebtsInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public BadDebtsInfo getBadDebtsInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getBadDebtsInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *syncMidData-User defined method
     *@param model model
     */
    public void syncMidData(BadDebtsInfo model) throws BOSException
    {
        try {
            getController().syncMidData(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *数据验证-User defined method
     *@param model model
     */
    public void verifyData(BadDebtsInfo model) throws BOSException
    {
        try {
            getController().verifyData(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *生成凭证-User defined method
     *@param model model
     */
    public void generVoucher(BadDebtsInfo model) throws BOSException
    {
        try {
            getController().generVoucher(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *autoSyncMidData-User defined method
     *@param database database
     */
    public void autoSyncMidData(String database) throws BOSException
    {
        try {
            getController().autoSyncMidData(getContext(), database);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *生成凭证-User defined method
     *@param database database
     */
    public void autoGenerVoucher(String database) throws BOSException
    {
        try {
            getController().autoGenerVoucher(getContext(), database);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}