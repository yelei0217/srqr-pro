/**
 * output package name
 */
package com.kingdee.eas.custom.client;

import java.awt.event.*;

import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.custom.SaleIssueHisLogFacadeFactory;
import com.kingdee.eas.framework.*;

/**
 * output class name
 */
public class BadDebtsListUI extends AbstractBadDebtsListUI
{
    private static final Logger logger = CoreUIObject.getLogger(BadDebtsListUI.class);
    
    /**
     * output class constructor
     */
    public BadDebtsListUI() throws Exception
    {
        super();
    }

    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
    }

    @Override
    public void actionHelp_actionPerformed(ActionEvent e) throws Exception {
//     	super.actionHelp_actionPerformed(e);
//    	SaleIssueHisLogFacadeFactory.getRemoteInstance().syncBadDebtMidData("04");
    	SaleIssueHisLogFacadeFactory.getRemoteInstance().genBadDebtVoucher();
    }
    
    

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.custom.BadDebtsFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.custom.BadDebtsInfo objectValue = new com.kingdee.eas.custom.BadDebtsInfo();
		
        return objectValue;
    }

}