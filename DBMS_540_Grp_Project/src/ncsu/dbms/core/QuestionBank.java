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
	int QUESTIONBANK_ID=0;
	String QUESTIONBANK_TEXT ="";
	String QUESTIONBANK_HINT  ="";
	String QUESTIONBANK_EXPLANATION  ="";
	int QUESTIONBANK_DIFFICULTYLEVEL =0;
	int QUESTIONBANK_CREATEDBY =0;
	Date QUESTIONBANK_CREATEDDATE =new Date();
	int QUESTIONBANK_MODIFIEDBY =0;
	Date QUESTIONBANK_MODIFIEDDATE =new Date();
}
