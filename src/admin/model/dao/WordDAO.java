package admin.model.dao;

//SQL과 관련된 객체와 List객체, JNDI 관련 객체를 사용하기 위해 import 합니다.

import java.sql.*;
import java.util.*;
import javax.sql.*;

import admin.model.vo.WordBean;

import javax.naming.Context;
import javax.naming.InitialContext;

/*
		DAO(Data Access Object) 클래스
		- 데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스입니다.
		- 어떤 Action 클래스가 호출되더라도 그에 대항하는 데이터 베이스 연동 처리는 DAO클래스에서 이루어지게 됩니다.
*/
public class WordDAO {
	// 데이터 베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public WordDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public List getWords(int category) {
		WordBean dictionary = null;
		try {
			con = ds.getConnection();
			if (category == 1) {
				pstmt = con.prepareStatement("select * from word_ipe ");
			} else if (category == 2) {
				pstmt = con.prepareStatement("select * from word_sql ");
			} else if (category == 3) {
				pstmt = con.prepareStatement("select * from word_linux ");
			} else if (category == 4) {
				pstmt = con.prepareStatement("select * from word_etc ");
			}
			System.out.println("카테고리 : " + category);

			rs = pstmt.executeQuery();

			List list = new ArrayList();

			while (rs.next()) {
				dictionary = new WordBean();
				dictionary.setWord_num(rs.getInt(1));
				dictionary.setWord_name(rs.getString(2));
				dictionary.setWord_info(rs.getString(3));
				list.add(dictionary);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getWords() 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return null;
	}

	public boolean insertWord(WordBean wordbean) {
		String sql = "";
		String wordtablename = wordbean.getCategory();

		if (wordtablename.equals("word_ipe")) {
			sql = "insert into word_ipe values(1000+?,?,?)";
		} else if (wordtablename.equals("word_sql")) {
			sql = "insert into word_sql values(2000+?,?,?)";
		} else if (wordtablename.equals("word_linux")) {
			sql = "insert into word_linux values(3000+?,?,?)";
		} else if (wordtablename.equals("word_etc")) {
			sql = "insert into word_etc values(4000+?,?,?)";
		}
		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wordbean.getWord_num());
			pstmt.setString(2, wordbean.getWord_name());
			pstmt.setString(3, wordbean.getWord_info());

			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}

		} catch (Exception ex) {
			System.out.println("insertWord() 에러 : " + ex);

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return false;

	}

	public List getSearch(String search) {
		String sql = "select * from wordview where word like ? ";
		List list = new ArrayList();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();

			if (rs == null) {
				list.add("검색결과가 없습니다.");
			} else {
				while (rs.next()) {
					WordBean wb = new WordBean();
					wb.setWord_num(rs.getInt(1));
					wb.setWord_name(rs.getString(2));
					wb.setWord_info(rs.getString(3));
					list.add(wb);
				}
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getSearch() 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return null;
	}

	public boolean updateWord(WordBean wordbean) {
		String sql = "";
		String wordtablename = wordbean.getCategory();

		if (wordtablename.equals("1")) {
			sql = "update word_ipe set word = ?, content = ? where num = ?";
		} else if (wordtablename.equals("2")) {
			sql = "update word_sql set word = ?, content = ? where num = ?";
		} else if (wordtablename.equals("3")) {
			sql = "update word_linux set word = ?, content = ? where num = ?";
		} else if (wordtablename.equals("4")) {
			sql = "update word_etc set word = ?, content = ? where num = ?";
		}
		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wordbean.getWord_name());
			pstmt.setString(2, wordbean.getWord_info());
			pstmt.setInt(3, wordbean.getWord_num());

			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}

		} catch (Exception ex) {
			System.out.println("insertWord() 에러 : " + ex);

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return false;

	}

	public boolean deleteWord(int deletenum, String category) {
		String sql = "";

		if (category.equals("1")) {
			sql = "delete from word_ipe where num = ?";
		} else if (category.equals("2")) {
			sql = "delete from word_sql where num = ?";
		} else if (category.equals("3")) {
			sql = "delete from word_linux where num = ?";
		} else if (category.equals("4")) {
			sql = "delete from word_etc where num = ?";
		}
		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deletenum);

			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}

		} catch (Exception ex) {
			System.out.println("deleteWord() 에러 : " + ex);

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}
}
