import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
	private static Path path = Paths.get("./accounting.db");
	private Connection c = null;

	public Database() {
		// check if file exist
		boolean new_db = false;
		if (Files.notExists(path)) {
			try {
				create_file();
				new_db = true;
				System.out.println("File created");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// open connection
		open_Connection();

		// if db = new -> create table && standard accounts
		if (new_db) {
			try {
				Statement stmt = c.createStatement();
				String sql = "create table TPasswd(" + "PassId int primary key," + "PassTitel varchar(30) not null,"
						+ "PassEMail varchar(50)," + "PassTel varchar(30)," + "PassNutzername varchar(50),"
						+ "PassPasswd varchar(30)," + "PassNote text" + ");";
				stmt.execute(sql);
				stmt.close();
				System.out.println("TPasswd created");
			} catch (SQLException e) {
				System.err.println("Error by making Tables");
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
		close_Connection();
	}

	public void create_file() throws IOException {
		File newFile = new File(String.valueOf(path));
		boolean success = newFile.createNewFile();
	}

	public void open_Connection() {
		try {

			c = DriverManager.getConnection("jdbc:sqlite:accounting.db");
		} catch (Exception e) {
			System.err.println("Error by opening DB:");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public void close_Connection() {
		try {
			c.close();
			System.out.println("Closed database sucessfully");
		} catch (SQLException e) {
			System.err.println("Error by closing DB:");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public String[][] select_all() {
		open_Connection();
		ArrayList<ArrayList<String>> listOfLists = new ArrayList();
		String select = "SELECT * FROM TPasswd order by PassTitel";
		int count = 0;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(select);

			// loop through the result set
			while (rs.next()) {
				System.out.println(
						rs.getString("PassTitel") + "\t" + rs.getString("PassEMail") + "\t" + rs.getString("PassTel")
								+ "\t" + rs.getString("PassNutzername") + "\t" + rs.getString("PassPasswd"));
				listOfLists.add(new ArrayList<String>());
				listOfLists.get(count).add(rs.getString("PassId"));
				listOfLists.get(count).add(rs.getString("PassTitel"));
				listOfLists.get(count).add(rs.getString("PassEMail"));
				listOfLists.get(count).add(rs.getString("PassTel"));
				listOfLists.get(count).add(rs.getString("PassNutzername"));
				listOfLists.get(count).add(rs.getString("PassPasswd"));
				listOfLists.get(count).add(rs.getString("PassNote"));
				count++;
			}
			System.out.println("end of output");
			close_Connection();
		} catch (SQLException e) {
			System.err.println("Error by getting Tables");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		String[][] array = new String[listOfLists.size()][];
		for (int i = 0; i < listOfLists.size(); i++) {
			ArrayList<String> row = listOfLists.get(i);
			array[i] = row.toArray(new String[row.size()]);
		}
		return array;
	}

	public String[][] select_search(String search, String search_case) {
		open_Connection();
		ArrayList<ArrayList<String>> listOfLists = new ArrayList();
		String sql = "SELECT * FROM TPasswd where " + search_case + " like '%" + search + "%';";
		int count = 0;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			while (rs.next()) {
				System.out.println(
						rs.getString("PassTitel") + "\t" + rs.getString("PassEMail") + "\t" + rs.getString("PassTel")
								+ "\t" + rs.getString("PassNutzername") + "\t" + rs.getString("PassPasswd"));
				listOfLists.add(new ArrayList<String>());
				listOfLists.get(count).add(rs.getString("PassId"));
				listOfLists.get(count).add(rs.getString("PassTitel"));
				listOfLists.get(count).add(rs.getString("PassEMail"));
				listOfLists.get(count).add(rs.getString("PassTel"));
				listOfLists.get(count).add(rs.getString("PassNutzername"));
				listOfLists.get(count).add(rs.getString("PassPasswd"));
				listOfLists.get(count).add(rs.getString("PassNote"));
				count++;
			}
			System.out.println("end of output");
			close_Connection();
		} catch (SQLException e) {
			System.err.println("Error by getting Tables");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		String[][] array = new String[listOfLists.size()][];
		for (int i = 0; i < listOfLists.size(); i++) {
			ArrayList<String> row = listOfLists.get(i);
			array[i] = row.toArray(new String[row.size()]);
		}
		return array;
	}

	public void add_passwd(int id, String name, String email, String telnum, String user, String passwd, String note) {
		String sql = "insert into TPasswd values('" + id + "', '" + name + "', '" + email + "', '" + telnum + "', '"
				+ user + "', '" + passwd + "', '" + note + "');";
		open_Connection();
		try {
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error by adding data to the Tables");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		close_Connection();
	}

	public int get_id() {
		open_Connection();
		int id = 0;
		String select = "SELECT * FROM TPasswd order by PassId desc";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(select);

			// loop through the result set
			while (rs.next()) {
				// System.out.println(rs.getInt("PassId"));
				id = rs.getInt("PassId");
				break;
			}
			// System.out.println("end of output");
			close_Connection();
		} catch (SQLException e) {
			System.err.println("Error by getting if for entering Tables");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return id;
	}

	public void delete(int id) {
		open_Connection();
		String sql = "DELETE from TPasswd where PassId= " + String.valueOf(id) + ";";
		try {
			Statement stmt = c.createStatement();
			stmt.execute(sql);
			stmt.close();
			System.out.println("data deleted");
		} catch (SQLException e) {
			System.err.println("Error by deleting data");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		close_Connection();
	}
	
	public void change(int id, String name, String email, String telnum, String user, String passwd, String note) {
		open_Connection();
		String sql = "update TPasswd set PassTitel = '"+name+"', PassEMail='"+email+"', PassTel= '"+telnum+"', PassNutzername='"+user+"', PassPasswd='"+passwd+"', PassNote = '"+note+"' where PassId ='"+ String.valueOf(id) +"';";
		try {
			Statement stmt = c.createStatement();
			stmt.execute(sql);
			stmt.close();
			System.out.println("updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
