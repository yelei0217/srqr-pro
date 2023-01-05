/**
 * output package name
 */
package com.kingdee.eas.custom.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractBadDebtsEditUIHandler extends com.kingdee.eas.framework.app.CoreBillEditUIHandler

{
	public void handleActionSyncMidData(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSyncMidData(request,response,context);
	}
	protected void _handleActionSyncMidData(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionVerifyData(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionVerifyData(request,response,context);
	}
	protected void _handleActionVerifyData(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionGenerVoucher(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionGenerVoucher(request,response,context);
	}
	protected void _handleActionGenerVoucher(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}