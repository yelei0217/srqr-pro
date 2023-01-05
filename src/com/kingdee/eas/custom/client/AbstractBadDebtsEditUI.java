/**
 * output package name
 */
package com.kingdee.eas.custom.client;

import org.apache.log4j.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.kdf.table.*;
import com.kingdee.bos.ctrl.kdf.data.event.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.ui.util.ResourceBundleHelper;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.ctrl.swing.event.*;
import com.kingdee.bos.ctrl.kdf.table.event.*;
import com.kingdee.bos.ctrl.extendcontrols.*;
import com.kingdee.bos.ctrl.kdf.util.render.*;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.appframework.client.servicebinding.ActionProxyFactory;
import com.kingdee.bos.appframework.uistatemanage.ActionStateConst;
import com.kingdee.bos.appframework.validator.ValidateHelper;
import com.kingdee.bos.appframework.uip.UINavigator;


/**
 * output class name
 */
public abstract class AbstractBadDebtsEditUI extends com.kingdee.eas.framework.client.CoreBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractBadDebtsEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInClinicHisID;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contVoucherStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contHisInsTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contEASInsTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contVoucherNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDelete;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInEasOrgID;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInEasOrgNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInEasOrgName;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateCreateTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInClinicHisID;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtAmount;
    protected com.kingdee.bos.ctrl.swing.KDComboBox VoucherStatus;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtHisInsTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtEASInsTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtVoucherNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDelete;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInEasOrgID;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInEasOrgNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInEasOrgName;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemSyncData;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemVerifyData;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemGenerVoucher;
    protected com.kingdee.eas.custom.BadDebtsInfo editData = null;
    protected ActionSyncMidData actionSyncMidData = null;
    protected ActionVerifyData actionVerifyData = null;
    protected ActionGenerVoucher actionGenerVoucher = null;
    /**
     * output class constructor
     */
    public AbstractBadDebtsEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "editData";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractBadDebtsEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
        actionPrint.setEnabled(true);
        actionPrint.setDaemonRun(false);

        actionPrint.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
        _tempStr = resHelper.getString("ActionPrint.SHORT_DESCRIPTION");
        actionPrint.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.LONG_DESCRIPTION");
        actionPrint.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.NAME");
        actionPrint.putValue(ItemAction.NAME, _tempStr);
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrintPreview
        actionPrintPreview.setEnabled(true);
        actionPrintPreview.setDaemonRun(false);

        actionPrintPreview.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl P"));
        _tempStr = resHelper.getString("ActionPrintPreview.SHORT_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.LONG_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.NAME");
        actionPrintPreview.putValue(ItemAction.NAME, _tempStr);
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionSyncMidData
        this.actionSyncMidData = new ActionSyncMidData(this);
        getActionManager().registerAction("actionSyncMidData", actionSyncMidData);
        this.actionSyncMidData.setExtendProperty("canForewarn", "true");
        this.actionSyncMidData.setExtendProperty("userDefined", "true");
        this.actionSyncMidData.setExtendProperty("isObjectUpdateLock", "false");
         this.actionSyncMidData.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSyncMidData.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionVerifyData
        this.actionVerifyData = new ActionVerifyData(this);
        getActionManager().registerAction("actionVerifyData", actionVerifyData);
        this.actionVerifyData.setExtendProperty("canForewarn", "true");
        this.actionVerifyData.setExtendProperty("userDefined", "true");
        this.actionVerifyData.setExtendProperty("isObjectUpdateLock", "false");
         this.actionVerifyData.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionVerifyData.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionGenerVoucher
        this.actionGenerVoucher = new ActionGenerVoucher(this);
        getActionManager().registerAction("actionGenerVoucher", actionGenerVoucher);
        this.actionGenerVoucher.setBindWorkFlow(true);
        this.actionGenerVoucher.setExtendProperty("canForewarn", "true");
        this.actionGenerVoucher.setExtendProperty("userDefined", "true");
        this.actionGenerVoucher.setExtendProperty("isObjectUpdateLock", "false");
         this.actionGenerVoucher.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionGenerVoucher.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInClinicHisID = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contVoucherStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contHisInsTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contEASInsTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contVoucherNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDelete = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInEasOrgID = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInEasOrgNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInEasOrgName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtInClinicHisID = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.VoucherStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtHisInsTime = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtEASInsTime = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtVoucherNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtDelete = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtInEasOrgID = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtInEasOrgNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtInEasOrgName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.menuItemSyncData = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemVerifyData = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemGenerVoucher = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contDescription.setName("contDescription");
        this.contAuditor.setName("contAuditor");
        this.contInClinicHisID.setName("contInClinicHisID");
        this.contAmount.setName("contAmount");
        this.contVoucherStatus.setName("contVoucherStatus");
        this.contHisInsTime.setName("contHisInsTime");
        this.contEASInsTime.setName("contEASInsTime");
        this.contVoucherNumber.setName("contVoucherNumber");
        this.contDelete.setName("contDelete");
        this.contInEasOrgID.setName("contInEasOrgID");
        this.contInEasOrgNumber.setName("contInEasOrgNumber");
        this.contInEasOrgName.setName("contInEasOrgName");
        this.prmtCreator.setName("prmtCreator");
        this.kDDateCreateTime.setName("kDDateCreateTime");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.kDDateLastUpdateTime.setName("kDDateLastUpdateTime");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.txtDescription.setName("txtDescription");
        this.prmtAuditor.setName("prmtAuditor");
        this.txtInClinicHisID.setName("txtInClinicHisID");
        this.txtAmount.setName("txtAmount");
        this.VoucherStatus.setName("VoucherStatus");
        this.txtHisInsTime.setName("txtHisInsTime");
        this.txtEASInsTime.setName("txtEASInsTime");
        this.txtVoucherNumber.setName("txtVoucherNumber");
        this.txtDelete.setName("txtDelete");
        this.txtInEasOrgID.setName("txtInEasOrgID");
        this.txtInEasOrgNumber.setName("txtInEasOrgNumber");
        this.txtInEasOrgName.setName("txtInEasOrgName");
        this.menuItemSyncData.setName("menuItemSyncData");
        this.menuItemVerifyData.setName("menuItemVerifyData");
        this.menuItemGenerVoucher.setName("menuItemGenerVoucher");
        // CoreUI		
        this.btnTraceUp.setVisible(false);		
        this.btnTraceDown.setVisible(false);		
        this.btnCreateTo.setVisible(true);		
        this.btnAddLine.setVisible(false);		
        this.btnCopyLine.setVisible(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnAuditResult.setVisible(false);		
        this.separator1.setVisible(false);		
        this.menuItemCreateTo.setVisible(true);		
        this.separator3.setVisible(false);		
        this.menuItemTraceUp.setVisible(false);		
        this.menuItemTraceDown.setVisible(false);		
        this.menuTable1.setVisible(false);		
        this.menuItemAddLine.setVisible(false);		
        this.menuItemCopyLine.setVisible(false);		
        this.menuItemInsertLine.setVisible(false);		
        this.menuItemRemoveLine.setVisible(false);		
        this.menuItemViewSubmitProccess.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);		
        this.contCreator.setEnabled(false);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setEnabled(false);
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);		
        this.contLastUpdateUser.setEnabled(false);		
        this.contLastUpdateUser.setVisible(false);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);		
        this.contLastUpdateTime.setEnabled(false);		
        this.contLastUpdateTime.setVisible(false);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);		
        this.contBizDate.setBoundLabelAlignment(7);		
        this.contBizDate.setVisible(true);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);
        // contInClinicHisID		
        this.contInClinicHisID.setBoundLabelText(resHelper.getString("contInClinicHisID.boundLabelText"));		
        this.contInClinicHisID.setBoundLabelLength(100);		
        this.contInClinicHisID.setBoundLabelUnderline(true);		
        this.contInClinicHisID.setVisible(true);
        // contAmount		
        this.contAmount.setBoundLabelText(resHelper.getString("contAmount.boundLabelText"));		
        this.contAmount.setBoundLabelLength(100);		
        this.contAmount.setBoundLabelUnderline(true);		
        this.contAmount.setVisible(true);
        // contVoucherStatus		
        this.contVoucherStatus.setBoundLabelText(resHelper.getString("contVoucherStatus.boundLabelText"));		
        this.contVoucherStatus.setBoundLabelLength(100);		
        this.contVoucherStatus.setBoundLabelUnderline(true);		
        this.contVoucherStatus.setVisible(true);
        // contHisInsTime		
        this.contHisInsTime.setBoundLabelText(resHelper.getString("contHisInsTime.boundLabelText"));		
        this.contHisInsTime.setBoundLabelLength(100);		
        this.contHisInsTime.setBoundLabelUnderline(true);		
        this.contHisInsTime.setVisible(true);
        // contEASInsTime		
        this.contEASInsTime.setBoundLabelText(resHelper.getString("contEASInsTime.boundLabelText"));		
        this.contEASInsTime.setBoundLabelLength(100);		
        this.contEASInsTime.setBoundLabelUnderline(true);		
        this.contEASInsTime.setVisible(true);
        // contVoucherNumber		
        this.contVoucherNumber.setBoundLabelText(resHelper.getString("contVoucherNumber.boundLabelText"));		
        this.contVoucherNumber.setBoundLabelLength(100);		
        this.contVoucherNumber.setBoundLabelUnderline(true);		
        this.contVoucherNumber.setVisible(true);
        // contDelete		
        this.contDelete.setBoundLabelText(resHelper.getString("contDelete.boundLabelText"));		
        this.contDelete.setBoundLabelLength(100);		
        this.contDelete.setBoundLabelUnderline(true);		
        this.contDelete.setVisible(true);
        // contInEasOrgID		
        this.contInEasOrgID.setBoundLabelText(resHelper.getString("contInEasOrgID.boundLabelText"));		
        this.contInEasOrgID.setBoundLabelLength(100);		
        this.contInEasOrgID.setBoundLabelUnderline(true);		
        this.contInEasOrgID.setVisible(true);
        // contInEasOrgNumber		
        this.contInEasOrgNumber.setBoundLabelText(resHelper.getString("contInEasOrgNumber.boundLabelText"));		
        this.contInEasOrgNumber.setBoundLabelLength(100);		
        this.contInEasOrgNumber.setBoundLabelUnderline(true);		
        this.contInEasOrgNumber.setVisible(true);
        // contInEasOrgName		
        this.contInEasOrgName.setBoundLabelText(resHelper.getString("contInEasOrgName.boundLabelText"));		
        this.contInEasOrgName.setBoundLabelLength(100);		
        this.contInEasOrgName.setBoundLabelUnderline(true);		
        this.contInEasOrgName.setVisible(true);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);
        // kDDateCreateTime		
        this.kDDateCreateTime.setTimeEnabled(true);		
        this.kDDateCreateTime.setEnabled(false);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setEnabled(false);
        // kDDateLastUpdateTime		
        this.kDDateLastUpdateTime.setTimeEnabled(true);		
        this.kDDateLastUpdateTime.setEnabled(false);
        // txtNumber		
        this.txtNumber.setMaxLength(80);
        // pkBizDate		
        this.pkBizDate.setVisible(true);		
        this.pkBizDate.setEnabled(true);
        // txtDescription		
        this.txtDescription.setMaxLength(80);
        // prmtAuditor		
        this.prmtAuditor.setEnabled(false);
        // txtInClinicHisID		
        this.txtInClinicHisID.setVisible(true);		
        this.txtInClinicHisID.setHorizontalAlignment(2);		
        this.txtInClinicHisID.setMaxLength(100);		
        this.txtInClinicHisID.setRequired(false);
        // txtAmount		
        this.txtAmount.setVisible(true);		
        this.txtAmount.setHorizontalAlignment(2);		
        this.txtAmount.setDataType(1);		
        this.txtAmount.setSupportedEmpty(true);		
        this.txtAmount.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtAmount.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtAmount.setPrecision(2);		
        this.txtAmount.setRequired(false);
        // VoucherStatus		
        this.VoucherStatus.setVisible(true);		
        this.VoucherStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.custom.app.VoucherStatus").toArray());		
        this.VoucherStatus.setRequired(false);
        // txtHisInsTime		
        this.txtHisInsTime.setVisible(true);		
        this.txtHisInsTime.setHorizontalAlignment(2);		
        this.txtHisInsTime.setMaxLength(100);		
        this.txtHisInsTime.setRequired(false);
        // txtEASInsTime		
        this.txtEASInsTime.setVisible(true);		
        this.txtEASInsTime.setHorizontalAlignment(2);		
        this.txtEASInsTime.setMaxLength(100);		
        this.txtEASInsTime.setRequired(false);
        // txtVoucherNumber		
        this.txtVoucherNumber.setVisible(true);		
        this.txtVoucherNumber.setHorizontalAlignment(2);		
        this.txtVoucherNumber.setMaxLength(100);		
        this.txtVoucherNumber.setRequired(false);
        // txtDelete		
        this.txtDelete.setVisible(true);		
        this.txtDelete.setHorizontalAlignment(2);		
        this.txtDelete.setMaxLength(100);		
        this.txtDelete.setRequired(false);
        // txtInEasOrgID		
        this.txtInEasOrgID.setVisible(true);		
        this.txtInEasOrgID.setHorizontalAlignment(2);		
        this.txtInEasOrgID.setMaxLength(100);		
        this.txtInEasOrgID.setRequired(false);
        // txtInEasOrgNumber		
        this.txtInEasOrgNumber.setVisible(true);		
        this.txtInEasOrgNumber.setHorizontalAlignment(2);		
        this.txtInEasOrgNumber.setMaxLength(100);		
        this.txtInEasOrgNumber.setRequired(false);
        // txtInEasOrgName		
        this.txtInEasOrgName.setVisible(true);		
        this.txtInEasOrgName.setHorizontalAlignment(2);		
        this.txtInEasOrgName.setMaxLength(100);		
        this.txtInEasOrgName.setRequired(false);
        // menuItemSyncData
        this.menuItemSyncData.setAction((IItemAction)ActionProxyFactory.getProxy(actionSyncMidData, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemSyncData.setText(resHelper.getString("menuItemSyncData.text"));
        // menuItemVerifyData
        this.menuItemVerifyData.setAction((IItemAction)ActionProxyFactory.getProxy(actionVerifyData, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemVerifyData.setText(resHelper.getString("menuItemVerifyData.text"));
        // menuItemGenerVoucher
        this.menuItemGenerVoucher.setAction((IItemAction)ActionProxyFactory.getProxy(actionGenerVoucher, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemGenerVoucher.setText(resHelper.getString("menuItemGenerVoucher.text"));
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtInClinicHisID,txtAmount,VoucherStatus,txtHisInsTime,txtEASInsTime,txtVoucherNumber,txtDelete,txtInEasOrgID,txtInEasOrgNumber,txtInEasOrgName}));
        this.setFocusCycleRoot(true);
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

	public com.kingdee.bos.ctrl.swing.KDToolBar[] getUIMultiToolBar(){
		java.util.List list = new java.util.ArrayList();
		com.kingdee.bos.ctrl.swing.KDToolBar[] bars = super.getUIMultiToolBar();
		if (bars != null) {
			list.addAll(java.util.Arrays.asList(bars));
		}
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(0, 0, 1027, 217));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1027, 217));
        contCreator.setBounds(new Rectangle(367, 130, 270, 19));
        this.add(contCreator, new KDLayout.Constraints(367, 130, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(367, 160, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(367, 160, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateUser.setBounds(new Rectangle(33, 130, 270, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(33, 130, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateTime.setBounds(new Rectangle(33, 160, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(33, 160, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(33, 14, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(33, 14, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(705, 14, 270, 19));
        this.add(contBizDate, new KDLayout.Constraints(705, 14, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contDescription.setBounds(new Rectangle(705, 160, 270, 19));
        this.add(contDescription, new KDLayout.Constraints(705, 160, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditor.setBounds(new Rectangle(705, 130, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(705, 130, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contInClinicHisID.setBounds(new Rectangle(367, 14, 270, 19));
        this.add(contInClinicHisID, new KDLayout.Constraints(367, 14, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAmount.setBounds(new Rectangle(33, 101, 270, 19));
        this.add(contAmount, new KDLayout.Constraints(33, 101, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contVoucherStatus.setBounds(new Rectangle(33, 72, 270, 19));
        this.add(contVoucherStatus, new KDLayout.Constraints(33, 72, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contHisInsTime.setBounds(new Rectangle(367, 72, 270, 19));
        this.add(contHisInsTime, new KDLayout.Constraints(367, 72, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contEASInsTime.setBounds(new Rectangle(705, 72, 270, 19));
        this.add(contEASInsTime, new KDLayout.Constraints(705, 72, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contVoucherNumber.setBounds(new Rectangle(367, 101, 270, 19));
        this.add(contVoucherNumber, new KDLayout.Constraints(367, 101, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDelete.setBounds(new Rectangle(705, 101, 270, 19));
        this.add(contDelete, new KDLayout.Constraints(705, 101, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contInEasOrgID.setBounds(new Rectangle(33, 43, 270, 19));
        this.add(contInEasOrgID, new KDLayout.Constraints(33, 43, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contInEasOrgNumber.setBounds(new Rectangle(367, 43, 270, 19));
        this.add(contInEasOrgNumber, new KDLayout.Constraints(367, 43, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contInEasOrgName.setBounds(new Rectangle(705, 43, 270, 19));
        this.add(contInEasOrgName, new KDLayout.Constraints(705, 43, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDateLastUpdateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contInClinicHisID
        contInClinicHisID.setBoundEditor(txtInClinicHisID);
        //contAmount
        contAmount.setBoundEditor(txtAmount);
        //contVoucherStatus
        contVoucherStatus.setBoundEditor(VoucherStatus);
        //contHisInsTime
        contHisInsTime.setBoundEditor(txtHisInsTime);
        //contEASInsTime
        contEASInsTime.setBoundEditor(txtEASInsTime);
        //contVoucherNumber
        contVoucherNumber.setBoundEditor(txtVoucherNumber);
        //contDelete
        contDelete.setBoundEditor(txtDelete);
        //contInEasOrgID
        contInEasOrgID.setBoundEditor(txtInEasOrgID);
        //contInEasOrgNumber
        contInEasOrgNumber.setBoundEditor(txtInEasOrgNumber);
        //contInEasOrgName
        contInEasOrgName.setBoundEditor(txtInEasOrgName);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuSubmitOption);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator6);
        menuFile.add(menuItemSendMail);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator4);
        menuEdit.add(menuItemReset);
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(separator2);
        menuEdit.add(menuItemSyncData);
        menuEdit.add(menuItemVerifyData);
        menuEdit.add(menuItemGenerVoucher);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator7);
        menuView.add(menuItemLocate);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(MenuItemPCVoucher);
        menuBiz.add(menuItemDelPCVoucher);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkflow
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(kDSeparator5);
        menuWorkflow.add(kDMenuItemSendMessage);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);
        menuHelp.add(menuItemAbout);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnSave);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnPCVoucher);
        this.toolBar.add(btnDelPCVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.kDDateLastUpdateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("InClinicHisID", String.class, this.txtInClinicHisID, "text");
		dataBinder.registerBinding("Amount", java.math.BigDecimal.class, this.txtAmount, "value");
		dataBinder.registerBinding("VoucherStatus", com.kingdee.eas.custom.app.VoucherStatus.class, this.VoucherStatus, "selectedItem");
		dataBinder.registerBinding("HisInsTime", String.class, this.txtHisInsTime, "text");
		dataBinder.registerBinding("EASInsTime", String.class, this.txtEASInsTime, "text");
		dataBinder.registerBinding("VoucherNumber", String.class, this.txtVoucherNumber, "text");
		dataBinder.registerBinding("Delete", String.class, this.txtDelete, "text");
		dataBinder.registerBinding("InEasOrgID", String.class, this.txtInEasOrgID, "text");
		dataBinder.registerBinding("InEasOrgNumber", String.class, this.txtInEasOrgNumber, "text");
		dataBinder.registerBinding("InEasOrgName", String.class, this.txtInEasOrgName, "text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.app.BadDebtsEditUIHandler";
	}
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		if (clientHanlder != null) {
			RequestContext request = new RequestContext();
    		request.setClassName(getUIHandlerClassName());
			clientHanlder.setRequestContext(request);
		}
		return clientHanlder;
    }
	
	public boolean isPrepareInit() {
    	return false;
    }
    protected void initUIP() {
        super.initUIP();
    }


    /**
     * output onShow method
     */
    public void onShow() throws Exception
    {
        super.onShow();
        this.txtInClinicHisID.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.custom.BadDebtsInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"NONE",editData.getString("number"));
    }
    
    protected void recycleNumberByOrg(IObjectValue editData,String orgType,String number) {
        if (!StringUtils.isEmpty(number))
        {
            try {
            	String companyID = null;            
            	com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID =com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}				
				if (!StringUtils.isEmpty(companyID) && iCodingRuleManager.isExist(editData, companyID) && iCodingRuleManager.isUseIntermitNumber(editData, companyID)) {
					iCodingRuleManager.recycleNumber(editData,companyID,number);					
				}
            }
            catch (Exception e)
            {
                handUIException(e);
            }
        }
    }
    protected void setAutoNumberByOrg(String orgType) {
    	if (editData == null) return;
		if (editData.getNumber() == null) {
            try {
            	String companyID = null;
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
		        if (iCodingRuleManager.isExist(editData, companyID)) {
		            if (iCodingRuleManager.isAddView(editData, companyID)) {
		            	editData.setNumber(iCodingRuleManager.getNumber(editData,companyID));
		            }
	                txtNumber.setEnabled(false);
		        }
            }
            catch (Exception e) {
                handUIException(e);
                this.oldData = editData;
                com.kingdee.eas.util.SysUtil.abort();
            } 
        } 
        else {
            if (editData.getNumber().trim().length() > 0) {
                txtNumber.setText(editData.getNumber());
            }
        }
    }

    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("NONE");
        dataBinder.loadFields();
    }
		protected void setOrgF7(KDBizPromptBox f7,com.kingdee.eas.basedata.org.OrgType orgType) throws Exception
		{
			com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer oufip = new com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer(orgType);
			oufip.getModel().setIsCUFilter(true);
			f7.setFilterInfoProducer(oufip);
		}

    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InClinicHisID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("VoucherStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("HisInsTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("EASInsTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("VoucherNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Delete", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InEasOrgID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InEasOrgNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InEasOrgName", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
        } else if (STATUS_EDIT.equals(this.oprtState)) {
        } else if (STATUS_VIEW.equals(this.oprtState)) {
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
        }
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
		String selectorAll = System.getProperty("selector.all");
		if(StringUtils.isEmpty(selectorAll)){
			selectorAll = "true";
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("creator.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("creator.id"));
        	sic.add(new SelectorItemInfo("creator.number"));
        	sic.add(new SelectorItemInfo("creator.name"));
		}
        sic.add(new SelectorItemInfo("createTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("lastUpdateUser.id"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.name"));
		}
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
        sic.add(new SelectorItemInfo("InClinicHisID"));
        sic.add(new SelectorItemInfo("Amount"));
        sic.add(new SelectorItemInfo("VoucherStatus"));
        sic.add(new SelectorItemInfo("HisInsTime"));
        sic.add(new SelectorItemInfo("EASInsTime"));
        sic.add(new SelectorItemInfo("VoucherNumber"));
        sic.add(new SelectorItemInfo("Delete"));
        sic.add(new SelectorItemInfo("InEasOrgID"));
        sic.add(new SelectorItemInfo("InEasOrgNumber"));
        sic.add(new SelectorItemInfo("InEasOrgName"));
        return sic;
    }        
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
    	if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
        if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionSyncMidData_actionPerformed method
     */
    public void actionSyncMidData_actionPerformed(ActionEvent e) throws Exception
    {
        com.kingdee.eas.custom.BadDebtsFactory.getRemoteInstance().syncMidData(editData);
    }
    	

    /**
     * output actionVerifyData_actionPerformed method
     */
    public void actionVerifyData_actionPerformed(ActionEvent e) throws Exception
    {
        com.kingdee.eas.custom.BadDebtsFactory.getRemoteInstance().verifyData(editData);
    }
    	

    /**
     * output actionGenerVoucher_actionPerformed method
     */
    public void actionGenerVoucher_actionPerformed(ActionEvent e) throws Exception
    {
        com.kingdee.eas.custom.BadDebtsFactory.getRemoteInstance().generVoucher(editData);
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
    }
	public RequestContext prepareActionPrint(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrint(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrint() {
    	return false;
    }
	public RequestContext prepareActionPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrintPreview(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPreview() {
    	return false;
    }
	public RequestContext prepareActionSyncMidData(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSyncMidData() {
    	return false;
    }
	public RequestContext prepareActionVerifyData(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionVerifyData() {
    	return false;
    }
	public RequestContext prepareActionGenerVoucher(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionGenerVoucher() {
    	return false;
    }

    /**
     * output ActionSyncMidData class
     */     
    protected class ActionSyncMidData extends ItemAction {     
    
        public ActionSyncMidData()
        {
            this(null);
        }

        public ActionSyncMidData(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSyncMidData.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSyncMidData.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSyncMidData.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractBadDebtsEditUI.this, "ActionSyncMidData", "actionSyncMidData_actionPerformed", e);
        }
    }

    /**
     * output ActionVerifyData class
     */     
    protected class ActionVerifyData extends ItemAction {     
    
        public ActionVerifyData()
        {
            this(null);
        }

        public ActionVerifyData(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionVerifyData.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVerifyData.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVerifyData.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractBadDebtsEditUI.this, "ActionVerifyData", "actionVerifyData_actionPerformed", e);
        }
    }

    /**
     * output ActionGenerVoucher class
     */     
    protected class ActionGenerVoucher extends ItemAction {     
    
        public ActionGenerVoucher()
        {
            this(null);
        }

        public ActionGenerVoucher(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionGenerVoucher.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionGenerVoucher.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionGenerVoucher.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractBadDebtsEditUI.this, "ActionGenerVoucher", "actionGenerVoucher_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.client", "BadDebtsEditUI");
    }
    /**
     * output isBindWorkFlow method
     */
    public boolean isBindWorkFlow()
    {
        return true;
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.custom.client.BadDebtsEditUI.class.getName();
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
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.custom.BadDebtsInfo objectValue = new com.kingdee.eas.custom.BadDebtsInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/custom/BadDebts";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.custom.app.BadDebtsQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {        
        return null;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("VoucherStatus","1");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}