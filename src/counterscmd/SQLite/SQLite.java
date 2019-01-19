package counterscmd.SQLite;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 http://www.sqlitetutorial.net/sqlite-java/create-table/
 */
public class SQLite {

	private final String DB_URL = "jdbc:sqlite:";
	private final String DB_Name;

	public SQLite(String fileName) {
		this.DB_Name = fileName;
	}

	Connection connect() throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL + DB_Name);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public void createNewDatabase() {
		try (Connection conn = this.connect()) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createNewTable() {

		String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
				+ "	id integer PRIMARY KEY, name text NOT NULL,\n"
				+ "	capacity real);";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void processPDO(String pdo) {
		try (Connection conn = connect();
				Statement stmt = conn.createStatement()) {
			stmt.execute(pdo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertCapacity(String name, double capacity) {
		String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setDouble(2, capacity);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectAll() {
		String sql = "SELECT * FROM warehouses";
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t"
						+ rs.getInt("capacity"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	//not yet ready
	public String selectAllString() {
		String sql = "SELECT * FROM warehouses";
		try (Connection conn = this.connect();
				Statement state = conn.createStatement();
				ResultSet rs = state.executeQuery(sql);) {
			while (rs.next()) {
				System.out.println("ee");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void getCapacityGreaterThan(double capacity) {
		String sql = "SELECT id, name, capacity FROM warehouses WHERE capacity > ?";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDouble(1, capacity);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t"
						+ rs.getDouble("capacity"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void update(int id, String name, double capacity) {
		String sql = "UPDATE warehouses SET name = ? , capacity = ? WHERE id = ?";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);
			pstmt.setDouble(2, capacity);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteByID(int id) {
		String sql = "DELETE FROM warehouses WHERE id = ?";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updatePicture(int materialId, String filename) {
        // update sql
        String updateSQL = "UPDATE materials "
                + "SET picture = ? "
                + "WHERE id=?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
 
            // set parameters
            pstmt.setBytes(1, readFile(filename));
            pstmt.setInt(2, materialId);
 
            pstmt.executeUpdate();
            System.out.println("Stored the file in the BLOB column.");
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	private byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }

	public static void main(String[] args) {
		String createNewTablePDO = "CREATE TABLE IF NOT EXISTS warehouses (\n"
				+ "	id integer PRIMARY KEY,\n name text NOT NULL,\n capacity real\n"
				+ ");";
		SQLite sqlite = new SQLite("test.db");
		// sqlite.insertCapacity("qweqwe qewrggggggg", 987987);
		System.out.println("===================");
		sqlite.getCapacityGreaterThan(1);
		System.out.println("SELECT ALL II");
		sqlite.selectAllString();
	}
}