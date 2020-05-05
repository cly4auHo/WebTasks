package data;

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
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;

public class DBWorker {
	private final static String SELECT_ALL = "SELECT * FROM products";
	private final static String ID_FORM = "SELECT * FROM products WHERE id=?";
	private final static String GET_BY_CATEGORY = "SELECT * FROM products WHERE category=?";
	private static final String INSERT = "INSERT INTO users(name, password, loggin, age, gender, comment, address) VALUES (?,?,?,?,?,?,?)";
	private static final String SALT = "SALT";
	private static final String GET_LOG = "SELECT id FROM users WHERE loggin='";
	private static final String GET_PAS = "' AND password=";
	private static final String NAME_OF_COLUMN = "name";

	public DBWorker() {
	}

	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<Product>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/myshop?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				productList.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
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

		return productList;
	}

	public List<Product> getProduct(int id) {
		List<Product> productList = new ArrayList<Product>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/myshop?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(ID_FORM);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				productList.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
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

		return productList;
	}

	public List<Product> getProductsCategory(String category) {
		List<Product> productList = new ArrayList<Product>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/myshop?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(GET_BY_CATEGORY);
			stmt.setString(1, category);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				productList.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
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

		return productList;
	}

	public void addUser(String name, String password, String loggin, int age, String gender, String comment,
			String address) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/myShop?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, name);
			stmt.setString(2, hashMD5(password));
			stmt.setString(3, loggin);
			stmt.setInt(4, age);
			stmt.setString(5, gender);
			stmt.setString(6, comment);
			stmt.setString(7, address);
			stmt.execute();
		} catch (SQLException ex) {
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
		}
	}

    public String getName(String loginToCheck, String passwordToCheck) {
    	String name = "";
    	
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/myShop?" + "user=root&password=");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Statement stmt = null;
        ResultSet rs = null;     
       
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_LOG + loginToCheck + GET_PAS + hashMD5(SALT + passwordToCheck));
            rs.next();
            name = rs.getString(NAME_OF_COLUMN);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        }

        return name;
    }
	
	public String hashMD5(String strToHash) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update((SALT + strToHash).getBytes());
			return String.format("%032x", new BigInteger(messageDigest.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {

	}
}
