/**
 * 
 */
package ncsu.dbms.core;

import java.util.Date;

/**
 * @author ravi
 *
 */
public class QuestionBank {
	public int QUESTIONBANK_ID=0;
	public String QUESTIONBANK_TEXT ="";
	public String QUESTIONBANK_HINT  ="";
	public String QUESTIONBANK_EXPLANATION  ="";
	public int QUESTIONBANK_DIFFICULTYLEVEL =0;
	public int QUESTIONBANK_CREATEDBY =0;
	public Date QUESTIONBANK_CREATEDDATE =new Date();
	public int QUESTIONBANK_MODIFIEDBY =0;
	public Date QUESTIONBANK_MODIFIEDDATE =new Date();
	public boolean CSC_QB_IS_PARAMETERIZED=false;
	public int CSC_QUESTIONBANK_TOPIC_ID =0;
}
