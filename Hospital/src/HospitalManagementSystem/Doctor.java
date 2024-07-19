package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	private Connection con;

	public Doctor(Connection con) {
		this.con = con;
	}

	public void viewDoctor() {
		String query = "select * from doctor";
		try {
			PreparedStatement ptsmt = con.prepareStatement(query);
			ResultSet rs = ptsmt.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+-----------+----------------+--------------------+");
			System.out.println("|Doctor Id | Name           | Specialization     |");
			System.out.println("+-----------+----------------+--------------------+");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String Specialization = rs.getString("Specialization");
       			System.out.println("|"+id+"\t|\t"+name+"\t|\t"+Specialization+"|");
				System.out.println("+--------+----------------+--------------------+");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean getDoctorById(int id) {
		String query = "select *From doctor where id=?";
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
