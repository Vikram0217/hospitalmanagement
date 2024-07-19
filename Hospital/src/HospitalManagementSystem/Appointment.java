package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment {
	private Patient patient;
	private Doctor doctor;
	private Connection con;
	private Scanner scn;
	public   Appointment(Patient patient,Doctor doctor, Connection con, Scanner scn) {
		this.patient=patient;
		this.doctor=doctor;
		this.con=con;
		this.scn=scn;
	}
	public void bookAppointment() {
		System.out.print("Enter patient Id:");
		int patientId=scn.nextInt();
		System.out.print("Enter doctor Id:");
		int doctorId=scn.nextInt();
		
		if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId) ) {
			System.out.print("Enter appointment date(YYYY-MM-DD):");
			String appointmentDate=scn.next();
			if(checkDoctorAvailability(doctorId,appointmentDate,con)) {
				String appointmentQuery="insert into appointments(Patient_Id,Doctor_Id,Appointment_Date) values(?,?,?)";
				try {
					PreparedStatement pstmt=con.prepareStatement(appointmentQuery);
					pstmt.setInt(1,patientId);
					pstmt.setInt(2,doctorId);
					pstmt.setString(3,appointmentDate);
					int rowAffected=pstmt.executeUpdate();
					if(rowAffected!=0) {
						System.out.println("Appointment booked!!!");
					}
					else {
						System.out.println("Failed to book Appointment....");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Doctor not available");
			}
		}
		else {
			System.out.println("Either doctor or patient doesn't exist!!!!");
		}
	}
	public static boolean checkDoctorAvailability(int doctorId,String appointmentDate,Connection con) {
		String query="select count(*) from appointments where Doctor_Id=? and Appointment_Date=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, doctorId);
			pstmt.setString(2, appointmentDate);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				if(count==0) {
					return true;
				}
				else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return false;	
	}
}


























