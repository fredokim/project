
package test.model.dao;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import dictionary.model.dao.WordDAO;
import dictionary.model.vo.*;

public class TestDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public TestDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public List getDictionary(String currentPage) {
		List wordlist = new ArrayList();

		try {
			con = ds.getConnection();
			System.out.println("현재페이지 : " + currentPage);
			if (currentPage.equals("DicIPE")) {
				pstmt = con.prepareStatement("select * from word_ipe");
			} else if (currentPage.equals("DicSQL")) {
				pstmt = con.prepareStatement("select * from word_sql");
			} else if (currentPage.equals("DicLinux")) {
				pstmt = con.prepareStatement("select * from word_linux");
			} else if (currentPage.equals("DicETC")) {
				pstmt = con.prepareStatement("select * from word_etc");
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				WordBean wordbean = new WordBean();
				wordbean.setNumber(rs.getInt(1));
				wordbean.setWord(rs.getString(2));
				wordbean.setContent(rs.getString(3));
				wordlist.add(wordbean);
			}

			return wordlist;

		} catch (Exception ex) {
			System.out.println("getDictionary 에러: " + ex);
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

	public boolean updateRank(int rightAnswer, String id) {

		int result = 0;
		int rankpt = 0;

		WordDAO worddao = new WordDAO();
		WordmemberBean wmb = new WordmemberBean();
		List wordlist = new ArrayList();

		wordlist = (List) worddao.getMynote(id);
		wmb = (WordmemberBean) wordlist.get(0);

		rankpt = wmb.getMember_rankpoint();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("update wordmember set member_rankpoint=? where member_id=?");
			pstmt.setInt(1, rankpt + (rightAnswer * 10));
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}

		} catch (Exception ex) {
			System.out.println("getDictionaryIPE 에러: " + ex);
		} finally {
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

	public List selTestlist(String[] wordNum) {
		String sql = "select * from wordview where num=? ";
		List list = new ArrayList();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < wordNum.length; i++) {

				pstmt.setString(1, wordNum[i]);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					WordBean wb = new WordBean();
					wb.setNumber(rs.getInt("num"));
					wb.setWord(rs.getString("word"));
					wb.setContent(rs.getString("content"));
					list.add(wb);
				}

			}

			return list;
		} catch (Exception ex) {
			System.out.println("selTestlist 에러: " + ex);
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

	public boolean updateMywan(String wrongNum, String id) {

		int result = 0;

		WordDAO worddao = new WordDAO();
		WordmemberBean wmb = new WordmemberBean();
		List wordlist = new ArrayList();

		wordlist = (List) worddao.getMynote(id);
		wmb = (WordmemberBean) wordlist.get(0);

		System.out.println("원래 있던 값" + wmb.getMember_mywan());
		if (wmb.getMember_mywan().equals("0")) {
			wrongNum = "" + wrongNum;
		} else if (wmb.getMember_mywan().contains(wrongNum)) {
			return false;
		} else {
			wrongNum = wmb.getMember_mywan() + "," + wrongNum;
		}

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("update wordmember set member_mywan=? where member_id=?");
			pstmt.setString(1, wrongNum);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}

		} catch (Exception ex) {
			System.out.println("getDictionaryIPE 에러: " + ex);
		} finally {
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
