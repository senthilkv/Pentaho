package org.pentaho.kettle.step.plugs.ddlgenerator;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class DDLGenDialog extends BaseStepDialog implements StepDialogInterface {

	
	public DDLGenDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
		super(parent, (BaseStepMeta) in, transMeta, sname);
		//meta = (SpChRMeta) in;
	}

	public String open() {
		// TODO Auto-generated method stub
		return null;
	}
}
