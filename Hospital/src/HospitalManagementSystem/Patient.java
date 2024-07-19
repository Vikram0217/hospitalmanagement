package HospitalManagementSystem;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
	private Connection con;
	private Scanner scn;

	public Patient(Connection con, Scanner scn) {
		this.con = con;
		this.scn = scn;
	}

	public void addPatient() {
		System.out.print("Enter patient name: ");
		String name = scn.next();
		System.out.print("Enter patient Age: ");
		int age = scn.nextInt();
		System.out.print("Enter patient gender: ");
		String gender = scn.next();

		try {
			String query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, gender);

			int count = pstmt.executeUpdate();
			if (count != 0) {
				System.out.println("Patient added successfully");
			} else {
				System.out.println("Failed to upload");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewpatient() {
		String query = "select * from patients";
		try {
			PreparedStatement ptsmt = con.prepareStatement(query);
			ResultSet rs = ptsmt.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+------------+----------------+-----------------+---------------+");
			System.out.println("| Patient Id | Name           | Age             | Gender        |");
			System.out.println("+------------+----------------+-----------------+---------------+");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				System.out.printf("|%-13s|%-16s||%-17s|%-15s|\n", id, name, age, gender);
				System.out.println("+--------+--------------+------+-----+---------+----------------+");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean getPatientById(int id) {
		String query = "select *From patients where id=?";
		try {
			PreparedStatement ptsmt = con.prepareStatement(query);
			ptsmt.setInt(1, id);
			ResultSet rs = ptsmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
