package com.kingdee.eas.basedata.master.auxacct.client;

import java.awt.event.ActionEvent;

public class GeneralAsstActTypeEditUICTEx extends GeneralAsstActTypeEditUI {

	public GeneralAsstActTypeEditUICTEx() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1927899684876014437L;

	@Override
	public void actionRemove_actionPerformed(ActionEvent arg0) throws Exception {
		if(this.editData !=null && this.editData.getGroup()!=null && this.editData.getGroup().getId()!=null && !"".equals( this.editData.getGroup().getId().toString())){
 			if("jbYAAAMrxb0F0s0M".equals(this.editData.getGroup().getId().toString())){
 				 
 			}
 		}
 		super.actionRemove_actionPerformed(arg0);
	}

	@Override
	public void actionSubmit_actionPerformed(ActionEvent arg0) throws Exception {
 		super.actionSubmit_actionPerformed(arg0); 
  		 
 		if( "EDIT".equals(this.oprtState)){
 			if(this.txtNumber.getText() !=null && !"".equals(this.txtNumber.getText()) && 
 	 			this.txtName.getSelectedItemData() !=null && !"".equals(this.txtName.getSelectedItemData().toString())){
 	 				if(!this.editData.getNumber().equals(this.txtNumber.getText()) || !this.editData.getNumber().equals(this.txtName.getSelectedItemData().toString()))
 	 					//名称或者编码修改了。调用修改功能
 	 					System.out.println();
 	 					
 			}
 		}
 			 			 
 		 
 		 
 		if(this.editData !=null   ){
 			if("jbYAAAMrxb0F0s0M".equals(this.editData.getGroup().getId().toString())){
 		 
 			}
		}  
	}
 

	
	
}
