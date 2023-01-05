package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.lang.String;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IBadDebts extends ICoreBillBase
{
    public BadDebtsCollection getBadDebtsCollection() throws BOSException;
    public BadDebtsCollection getBadDebtsCollection(EntityViewInfo view) throws BOSException;
    public BadDebtsCollection getBadDebtsCollection(String oql) throws BOSException;
    public BadDebtsInfo getBadDebtsInfo(IObjectPK pk) throws BOSException, EASBizException;
    public BadDebtsInfo getBadDebtsInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public BadDebtsInfo getBadDebtsInfo(String oql) throws BOSException, EASBizException;
    public void syncMidData(BadDebtsInfo model) throws BOSException;
    public void verifyData(BadDebtsInfo model) throws BOSException;
    public void generVoucher(BadDebtsInfo model) throws BOSException;
    public void autoSyncMidData(String database) throws BOSException;
    public void autoGenerVoucher(String database) throws BOSException;
}