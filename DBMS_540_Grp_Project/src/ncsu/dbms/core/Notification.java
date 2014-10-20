/**
 * 
 */
package ncsu.dbms.core;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ravi
 *
 */
public class Notification {
	public String NotificationTitle;
	public String NotificationDue;

	public static ArrayList<Notification> GetDummyList() {
		ArrayList<Notification> listNotification = new ArrayList<Notification>();
		for (int index = 1; index < 10; index++) {
			Notification notification = new Notification();
			notification.NotificationTitle = "Notification Number" + index;
			Date date = new Date();
			notification.NotificationDue = new SimpleDateFormat(
					"yyyy.MM.dd  HH:mm").format(date);
			listNotification.add(notification);
		}
		return listNotification;
	}
	public static ArrayList<Notification> GetDummyListFaculty() {
		ArrayList<Notification> listNotification = new ArrayList<Notification>();
		for (int index = 1; index < 10; index++) {
			Notification notification = new Notification();
			notification.NotificationTitle = "Notification For Faculty" + index;
			Date date = new Date();
			notification.NotificationDue = new SimpleDateFormat(
					"yyyy.MM.dd  HH:mm").format(date);
			listNotification.add(notification);
		}
		return listNotification;
	}
}
