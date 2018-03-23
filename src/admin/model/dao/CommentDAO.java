package admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import admin.model.vo.CommentBean;

public class CommentDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public CommentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public int getSeq() {
		int result = 1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select comment_seq.nextval from dual");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getSeq 에러" + e);
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
		return result;
	}

	public boolean insertComment(CommentBean comment) {
		boolean result = false;
		int flag = 0;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(
					"insert into board_comment" + "(comment_num,comment_board,comment_id,comment_date,"
							+ "comment_parent,comment_content) values(?,?,?,sysdate,?,?)");
			pstmt.setInt(1, comment.getComment_num());
			pstmt.setInt(2, comment.getComment_board());
			pstmt.setString(3, comment.getComment_id());
			pstmt.setInt(4, comment.getComment_parent());
			pstmt.setString(5, comment.getComment_content());
			flag = pstmt.executeUpdate();

			if (flag > 0) {
				result = true;
				con.commit();
			}

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
		return result;
	}

	public List getComentList(int boardNum) {
		String sql = "select comment_num,comment_board,comment_id,comment_date,comment_parent,comment_content from board_comment "
				+ "where comment_board=? " + "start with comment_parent=0 "
				+ "connect by prior comment_num=comment_parent ";

		List list = new ArrayList();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CommentBean comment = new CommentBean();
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setComment_board(rs.getInt("comment_board"));
				comment.setComment_id(rs.getString("comment_id"));
				comment.setComment_date(rs.getDate("comment_date"));
				comment.setComment_parent(rs.getInt("comment_parent"));
				comment.setComment_content(rs.getString("comment_content"));
				list.add(comment);
			}
		} catch (Exception e) {
			System.out.println("getCommentList 에러" + e);
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
		return list;
	}

	public boolean isCommentWriter(int num, String id) {
		System.out.println("id=" + id);
		String sql = "select * from board_comment where comment_num=?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();

			if (id.equals(rs.getString("comment_id"))) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("isCommentWriter 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return false;
	}

	public boolean commetDelete(int num) {
		String sql = "delete from board_comment where comment_num=?";

		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("CommentDelete 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}

		return false;
	}

}