package web;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import dao.FactoryDAO;
import dao.UserDAO;

@Component
public class MySQLUserDAO implements UserDAO {

	private User user;
	private static final String INSERT = "INSERT INTO users(name, loggin, password, age, gender, comment, address) VALUES (?,?,?,?,?,?,?)";
	private static final String SALT = "SALT";
	private static final String SELECT_NAME = "SELECT name FROM users WHERE login='";
	
	@Override
	public String getUserName() {
		Connection conn = getConnection() ;
		Statement stmt = null;
		ResultSet rs = null;
		String userName;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_NAME + user.getLogin() + "'");
			rs.next();
			userName = rs.getString("name");
		} catch (SQLException ex) {
			userName = "";
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userName;
	}

	public boolean isShow() {
		Connection conn = getConnection() ;
		Statement stmt = null;
		ResultSet rs = null;
		String existLogin;
		String existPassword;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT login, password FROM users WHERE login='" + user.getLogin()
					+ "' AND password='" + toHexString(hashSHA(user.getPassword())) + "'");
			rs.next();
			existLogin = rs.getString("login");
			existPassword = rs.getString("password");

		} catch (SQLException ex) {
			existLogin = "notExist";
			existPassword = "notExist";
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (user.getLogin().equals(existLogin) && !(existPassword.equals("notExist")));
	}

	public static byte[] hashSHA(String strToHash) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return md.digest((strToHash + SALT).getBytes(StandardCharsets.UTF_8));
	}

	public static String toHexString(byte[] hash) {	
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));

		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

	@Override
	public String getAccess() {
		return isShow() ? "Successfully logged" : "Incorrect username or password";
	}

	@Override
	public boolean getCheckLogin() {
		Connection conn = getConnection() ;
		Statement stmt = null;
		ResultSet rs = null;
		String existLogin;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT login FROM users WHERE login='" + user.getLogin() + "'");
			rs.next();
			existLogin = rs.getString("login");
		} catch (SQLException ex) {
			existLogin = "notExist";
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (user.getLogin().equals(existLogin));
	}

	@Override
	public String getRegistration() {
		return insertData() ? "success" : "fault";
	}

	@Override
	public boolean insertData() {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		boolean result = true;
		
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLogin());
			stmt.setString(3, toHexString(hashSHA(user.getPassword())));
			stmt.setInt(4, user.getAge());
			stmt.setString(5, user.getGender());
			stmt.setString(6, user.getComments());
			stmt.setString(7, user.getAddress());
			stmt.execute();
		} catch (SQLException ex) {
			result = false;
			ex.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/myshop?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return conn;
	}
}
