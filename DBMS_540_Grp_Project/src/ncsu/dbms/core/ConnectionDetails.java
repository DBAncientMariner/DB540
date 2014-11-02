/**
 * 
 */
package ncsu.dbms.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author ravi
 *
 */
public class ConnectionDetails {
	
//		public static String UserName = "SYSTEM";
//		public static String Password = "aCZ6q7F";
//		public static String Url = "jdbc:oracle:thin:System@//AM-THINKPAD:1521/xe";
	
	public static String UserName = "";
	public static String Password = "";
	public static String Url = "";
		public static void ReadConfig()
		{
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader("configfile.txt"));
				String line = null;
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserName=line.toString();
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Password=line.toString();
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Url=line.toString();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	
}
