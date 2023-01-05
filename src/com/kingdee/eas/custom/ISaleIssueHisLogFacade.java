package com.kingdee.eas.custom;

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

public interface ISaleIssueHisLogFacade extends IBizCtrl
{
    public void doCostSyncByMonth(String month) throws BOSException;
    public void doSyncIssueLogToMid(String month, String database) throws BOSException;
    public void syncBadDebtMidData(String database) throws BOSException;
    public void genBadDebtVoucher() throws BOSException;
}