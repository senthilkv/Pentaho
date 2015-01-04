package pentaho.kettle.step.plugs.spchr;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.trans.step.BaseStepDialog;


public class SpChRDialog extends BaseStepDialog implements StepDialogInterface{
	
	private static Class<?> PKG = SpChRMeta.class; // for il8n purposes

	private SpChRMeta meta;

	private Text wFieldName;
	private Text wFieldNum;

	public SpChRDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
		super(parent, (BaseStepMeta) in, transMeta, sname);
		meta = (SpChRMeta) in;
	}

	public String open() {

		// store some convenient SWT variables
		Shell parent = getParent();
		Display display = parent.getDisplay();

		// SWT code for preparing the dialog
		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN
				| SWT.MAX);
		props.setLook(shell);
		setShellImage(shell, meta);

		// Save the value of the changed flag on the meta object. If the user
		// cancels
		// the dialog, it will be restored to this saved value.
		// The "changed" variable is inherited from BaseStepDialog
		changed = meta.hasChanged();

		// The ModifyListener used on all controls. It will update the meta
		// object to
		// indicate that changes are being made.
		ModifyListener lsMod = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				meta.setChanged();
			}
		};

		// ------------------------------------------------------- //
		// SWT code for building the actual settings dialog //
		// ------------------------------------------------------- //
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		//shell.setText(BaseMessages.getString("Special Character Removal")); //disabling use of property file due to issue
		shell.setText(meta.getTITLE());

		int middle = props.getMiddlePct();
		int margin = Const.MARGIN;

		// Stepname line
		wlStepname = new Label(shell, SWT.RIGHT);
		wlStepname.setText(BaseMessages.getString(PKG, "System.Label.StepName")); //System variables are working fine.. only the property files are having trouble
				
		props.setLook(wlStepname);
		fdlStepname = new FormData();
		fdlStepname.left = new FormAttachment(0, 0);
		fdlStepname.right = new FormAttachment(middle, -margin);
		fdlStepname.top = new FormAttachment(0, margin);
		wlStepname.setLayoutData(fdlStepname);

		wStepname = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wStepname.setText(stepname);
		props.setLook(wStepname);
		wStepname.addModifyListener(lsMod);
		fdStepname = new FormData();
		fdStepname.left = new FormAttachment(middle, 0);
		fdStepname.top = new FormAttachment(0, margin);
		fdStepname.right = new FormAttachment(100, 0);
		wStepname.setLayoutData(fdStepname);

		// output field value
		Label wlValName = new Label(shell, SWT.RIGHT);
		//wlValName.setText(BaseMessages.getString(PKG,"SpChR.FieldName.Label")); //disabling the use of property file due to issue
		wlValName.setText(meta.getFIELDNAMELABEL());
		
		props.setLook(wlValName);
		FormData fdlValName = new FormData();
		fdlValName.left = new FormAttachment(0, 0);
		fdlValName.right = new FormAttachment(middle, -margin);
		fdlValName.top = new FormAttachment(wStepname, margin);
		wlValName.setLayoutData(fdlValName);

		wFieldName = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		props.setLook(wFieldName);
		wFieldName.addModifyListener(lsMod);
		FormData fdValName = new FormData();
		fdValName.left = new FormAttachment(middle, 0);
		fdValName.right = new FormAttachment(100, 0);
		fdValName.top = new FormAttachment(wStepname, margin);
		wFieldName.setLayoutData(fdValName);
		
		// field value
		Label wlFieldNum = new Label(shell, SWT.RIGHT);
		//wlFieldNum.setText(BaseMessages.getString(PKG,"SpChR.FieldNum.Label"));
		wlFieldNum.setText(meta.getFIELDNUMLABEL());
		
		props.setLook(wlFieldNum);
		FormData fdlFieldNum = new FormData();
		fdlFieldNum.left = new FormAttachment(0, 0);
		fdlFieldNum.right = new FormAttachment(middle, -margin);
		fdlFieldNum.top = new FormAttachment(wFieldName, margin);
		wlFieldNum.setLayoutData(fdlFieldNum);

		wFieldNum = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		props.setLook(wFieldNum);
		wFieldNum.addModifyListener(lsMod);
		FormData fdFieldNum = new FormData();
		fdFieldNum.left = new FormAttachment(middle, 0);
		fdFieldNum.right = new FormAttachment(100, 0);
		fdFieldNum.top = new FormAttachment(wFieldName, margin);
		wFieldNum.setLayoutData(fdFieldNum);

		// OK and cancel buttons
		wOK = new Button(shell, SWT.PUSH);
		wOK.setText(BaseMessages.getString(PKG, "System.Button.OK"));
		wCancel = new Button(shell, SWT.PUSH);
		wCancel.setText(BaseMessages.getString(PKG, "System.Button.Cancel"));

		BaseStepDialog.positionBottomButtons(shell,
				new Button[] { wOK, wCancel }, margin, wFieldNum);

		// Add listeners for cancel and OK
		lsCancel = new Listener() {
			public void handleEvent(Event e) {
				cancel();
			}
		};
		lsOK = new Listener() {
			public void handleEvent(Event e) {
				ok();
			}
		};

		wCancel.addListener(SWT.Selection, lsCancel);
		wOK.addListener(SWT.Selection, lsOK);

		// default listener (for hitting "enter")
		lsDef = new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				ok();
			}
		};
		
		wStepname.addSelectionListener(lsDef);
		wFieldName.addSelectionListener(lsDef);
		wFieldNum.addSelectionListener(lsDef);
		
		// Detect X or ALT-F4 or something that kills this window and cancel the
		// dialog properly
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				cancel();
			}
		});

		// Set/Restore the dialog size based on last position on screen
		// The setSize() method is inherited from BaseStepDialog
		setSize();

		// populate the dialog with the values from the meta object
		populateDialog();

		// restore the changed flag to original value, as the modify listeners
		// fire during dialog population
		meta.setChanged(changed);

		// open dialog and enter event loop
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		// at this point the dialog has closed, so either ok() or cancel() have
		// been executed
		// The "stepname" variable is inherited from BaseStepDialog
		return stepname;
	}

	private void populateDialog() {
		wStepname.selectAll();
		wFieldName.setText(meta.getOutputField());
		wFieldNum.setText(meta.getFieldNum());
	}

	/**
	 * Called when the user cancels the dialog.
	 */
	private void cancel() {
		// The "stepname" variable will be the return value for the open()
		// method.
		// Setting to null to indicate that dialog was cancelled.
		stepname = null;
		// Restoring original "changed" flag on the met aobject
		meta.setChanged(changed);
		// close the SWT dialog window
		dispose();
	}

	/**
	 * Called when the user confirms the dialog
	 */
	private void ok() {
		// The "stepname" variable will be the return value for the open()
		// method.
		// Setting to step name from the dialog control
		stepname = wStepname.getText();
		// Setting the settings to the meta object
		meta.setOutputField(wFieldName.getText());
		meta.setFieldNum(wFieldNum.getText());
		// close the SWT dialog window
		dispose();
	}

}
