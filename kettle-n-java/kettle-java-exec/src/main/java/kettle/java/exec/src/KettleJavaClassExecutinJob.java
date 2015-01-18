package kettle.java.exec.src;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;


public class KettleJavaClassExecutinJob {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		String file="src/main/resources/J_KettleJava_Master_Job.kjb";
		Repository repository=null;
		
		try {
			KettleEnvironment.init();
						
			JobMeta jobmeta=new JobMeta(file,repository);
			Job job=new Job(repository, jobmeta);
			
			job.start();
			job.waitUntilFinished();
			
			if(job.getErrors()>0){
				System.out.println("Error Executing Job");
			}
			
		} catch (KettleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
