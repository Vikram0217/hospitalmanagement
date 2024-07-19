package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HopstialManagement {
	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/hospital?user=root&password=root";
			Connection con = DriverManager.getConnection(dburl);
			Patient patient=new Patient(con,scn);
			Doctor doctor=new Doctor(con);
			Appointment bookAppointment=new Appointment(patient, doctor, con, scn);
			while(true) {
				System.out.println("Hospital Management System");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patients");
				System.out.println("3. View Doctors");
				System.out.println("4. Book Appoinments");
				System.out.println("5. Exit");
				System.out.println("Enter your choice:");
				int choice =scn.nextInt();
				
				switch(choice) {
				case 1:
					patient.addPatient();
					System.out.println();
					break;
				case 2:
					patient.viewpatient();
					System.out.println();
					break;
				case 3:
					doctor.viewDoctor();
					System.out.println();
					break;
				case 4:
					bookAppointment.bookAppointment();
					System.out.println();
					break;
				case 5:
					return;
					default:
						System.out.println("Enter valid choice");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
