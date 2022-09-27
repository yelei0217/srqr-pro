package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SaleIssueHisLogFacadeFactory
{
    private SaleIssueHisLogFacadeFactory()
    {
    }
    public static com.kingdee.eas.custom.ISaleIssueHisLogFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.ISaleIssueHisLogFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("D499D659") ,com.kingdee.eas.custom.ISaleIssueHisLogFacade.class);
    }
    
    public static com.kingdee.eas.custom.ISaleIssueHisLogFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.ISaleIssueHisLogFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("D499D659") ,com.kingdee.eas.custom.ISaleIssueHisLogFacade.class, objectCtx);
    }
    public static com.kingdee.eas.custom.ISaleIssueHisLogFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.ISaleIssueHisLogFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("D499D659"));
    }
    public static com.kingdee.eas.custom.ISaleIssueHisLogFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.ISaleIssueHisLogFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("D499D659"));
    }
}