package pentaho.kettle.step.plugs.spchr;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.CheckResult;
import org.pentaho.di.core.CheckResultInterface;
import org.pentaho.di.core.Counter;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleValueException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMeta;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.w3c.dom.Node;

public class SpChRMeta extends BaseStepMeta implements StepMetaInterface{
	
	private static Class<?> PKG = SpChRMeta.class;
	
	private final String TITLE="Special Character Removal";
	private final String FIELDNAMELABEL="Output Field Name";
	private final String FIELDNUMLABEL="Stream Field Number";

	private String outputField; //the new column or field name
	private String fieldNum; //the field number where we need to apply the spchr
	private String inputDropData;
	private String inputDropDataIndex;

	public SpChRMeta() {
		// TODO Auto-generated constructor stub
		super();
	}

	public StepDialogInterface getDialog(Shell shell, StepMetaInterface meta,
			TransMeta transMeta, String name) {
		return new SpChRDialog(shell, meta, transMeta, name);
	}

	public StepInterface getStep(StepMeta stepMeta,
			StepDataInterface stepDataInterface, int cnr, TransMeta transMeta,
			Trans disp) {
		return new SpChRStep(stepMeta, stepDataInterface, cnr, transMeta, disp);
	}

	public StepDataInterface getStepData() {
		// TODO Auto-generated method stub
		return new SpChRData();
	}

	public void setDefault() {
		// TODO Auto-generated method stub
		outputField = "Result";
		fieldNum = "0";
		//inputDropData= getInputDropData();
	}

	public String getOutputField() {
		return outputField;
	}

	public void setOutputField(String outputField) {
		this.outputField = outputField;
	}


	public String getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(String fieldNum) {
		this.fieldNum = fieldNum;
	}
	
	public String getInputDropData() {
		
		return inputDropData;
	}

	public void setInputDropData(String inputDropData) {
		
		this.inputDropData = inputDropData;
	}

	
	@Override
	public Object clone() {
		Object retval = super.clone();
		return retval;
	}

	@Override
	public String getXML() throws KettleValueException {

		// only one field to serialize
		String xml = XMLHandler.addTagValue("outputfield", outputField);
		return xml;
	}

	@Override
	public void loadXML(Node stepnode, List<DatabaseMeta> databases,
			Map<String, Counter> counters) throws KettleXMLException {

		try {
			setOutputField(XMLHandler.getNodeValue(XMLHandler.getSubNode(
					stepnode, "outputfield")));
		} catch (Exception e) {
			throw new KettleXMLException(
					"Plugin unable to read step info from XML node", e);
		}

	}

	@Override
	public void saveRep(Repository rep, ObjectId id_transformation,
			ObjectId id_step) throws KettleException {
		try {
			rep.saveStepAttribute(id_transformation, id_step,
					"outputfield", outputField); //$NON-NLS-1$
		} catch (Exception e) {
			throw new KettleException("Unable to save step into repository: "
					+ id_step, e);
		}
	}

	@Override
	public void readRep(Repository rep, ObjectId id_step,
			List<DatabaseMeta> databases, Map<String, Counter> counters)
			throws KettleException {
		try {
			outputField = rep.getStepAttributeString(id_step, "outputfield"); //$NON-NLS-1$
		} catch (Exception e) {
			throw new KettleException("Unable to load step from repository", e);
		}
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public void getFields(RowMetaInterface r, String origin,
			RowMetaInterface[] info, StepMeta nextStep, VariableSpace space) {

		/*
		 * This implementation appends the outputField to the row-stream
		 */

		// a value meta object contains the meta data for a field
		ValueMetaInterface v = new ValueMeta();

		// set the name of the new field
		v.setName(outputField);
		//v.setName(fieldNum);
		
		// type is going to be string
		v.setType(ValueMetaInterface.TYPE_STRING);

		// setting trim type to "both"
		v.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);

		// the name of the step that adds this field
		v.setOrigin(origin);

		// modify the row structure and add the field this step generates
		r.addValueMeta(v);

	}

	@Override
	public void check(List<CheckResultInterface> remarks, TransMeta transmeta,
			StepMeta stepMeta, RowMetaInterface prev, String input[],
			String output[], RowMetaInterface info) {

		CheckResult cr;

		// See if there are input streams leading to this step!
		if (input.length > 0) {
			cr = new CheckResult(CheckResultInterface.TYPE_RESULT_OK,
					BaseMessages.getString(PKG,
							"SpChR.CheckResult.ReceivingRows.OK"), stepMeta);
			remarks.add(cr);
		} else {
			cr = new CheckResult(CheckResultInterface.TYPE_RESULT_ERROR,
					BaseMessages.getString(PKG,
							"SpChR.CheckResult.ReceivingRows.ERROR"), stepMeta);
			remarks.add(cr);
		}

	}

	public String getTITLE() {
		return TITLE;
	}

	public String getFIELDNAMELABEL() {
		return FIELDNAMELABEL;
	}

	public String getFIELDNUMLABEL() {
		return FIELDNUMLABEL;
	}

	public String getInputDropDataIndex() {
		return inputDropDataIndex;
	}

	public void setInputDropDataIndex(String inputDropDataIndex) {
		this.inputDropDataIndex = inputDropDataIndex;
	}

	


}
