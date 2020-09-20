/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kavin
 */
public class databasecon {
    public static void main(String[] args) {
		databasecon obj_DB_Connection = new databasecon();
		System.out.println(obj_DB_Connection.get_connection());
	}

	public Connection get_connection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookingtrip", "root", "");
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}
    
}