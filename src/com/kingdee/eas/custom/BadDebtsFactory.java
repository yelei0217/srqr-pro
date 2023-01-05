package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class BadDebtsFactory
{
    private BadDebtsFactory()
    {
    }
    public static com.kingdee.eas.custom.IBadDebts getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IBadDebts)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("F42D6A80") ,com.kingdee.eas.custom.IBadDebts.class);
    }
    
    public static com.kingdee.eas.custom.IBadDebts getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IBadDebts)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("F42D6A80") ,com.kingdee.eas.custom.IBadDebts.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IBadDebts getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IBadDebts)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("F42D6A80"));
    }
    public static com.kingdee.eas.custom.IBadDebts getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IBadDebts)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("F42D6A80"));
    }
}