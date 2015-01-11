package pentaho.kettle.step.plugs.spchr;

import org.pentaho.di.core.exception.KettleValueException;
import org.pentaho.di.core.row.RowMetaInterface;

public class SpChRPattern {
	
		
	public String getCleanPattern(RowMetaInterface data,Object[] r,String fieldNum,String selectedPattern,String customCode) throws KettleValueException{
	
		String replace_pattern="";
		
		int _intfieldNum=Integer.parseInt(fieldNum);
		
		String data_row=data.getString(r, _intfieldNum);
		
		String SpecialCleaner=data_row.replaceAll(selectedPattern, replace_pattern);
		
		return SpecialCleaner;
	
	}

}
