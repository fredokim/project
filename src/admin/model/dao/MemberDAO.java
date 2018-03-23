package admin.model.dao;

//SQL과 관련된 객체와 List객체, JNDI 관련 객체를 사용하기 위해 import 합니다.

import java.sql.*;
import java.util.*;
import javax.sql.*;

import admin.model.vo.MemberBean;

import javax.naming.Context;
import javax.naming.InitialContext;

/*
		DAO(Data Access Object) 클래스
		- 데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스입니다.
		- 어떤 Action 클래스가 호출되더라도 그에 대항하는 데이터 베이스 연동 처리는 DAO클래스에서 이루어지게 됩니다.
*/
public class MemberDAO {
	// 데이터 베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public List getMember() {
		MemberBean member = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from wordmember");
			rs = pstmt.executeQuery();

			List list = new ArrayList();

			while (rs.next()) {
				member = new MemberBean();
				member.setMember_num(rs.getInt("member_num"));
				member.setMember_id(rs.getString("member_id"));
				member.setMember_pw(rs.getString("member_pw"));
				member.setMember_nickname(rs.getString("member_nickname"));

				list.add(member);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getMember() 에러 : " + ex);
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
	}// getDetail 메소드 end;

	public List updateMember() {
		MemberBean member = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from wordmember where member_id=?");
			rs = pstmt.executeQuery();

			List list = new ArrayList();

			while (rs.next()) {
				member = new MemberBean();
				member.setMember_num(rs.getInt("member_num"));
				member.setMember_id(rs.getString("member_id"));
				member.setMember_pw(rs.getString("member_pw"));
				member.setMember_nickname(rs.getString("member_nickname"));

				list.add(member);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("updateMember() 에러 : " + ex);
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
	}// getDetail 메소드 end;

	public boolean deleteMember(String deleteId) {
		String sql1 = "delete from wordmemberboard where board_id=?";
		String sql2 = "delete from wordmember where member_id=?";
		boolean isSuccess = false;
		int result1 = 0;
		int result2 = 0;
		System.out.println("deleteId=" + deleteId);
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			// 멤버 삭제
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, deleteId);
			result1 = pstmt.executeUpdate();
			System.out.println("result1 = " + result1);
			// 삭제할 멤버가 작성한 게시판 글도 삭제
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, deleteId);
			result2 = pstmt.executeUpdate();
			System.out.println("result2 = " + result2);

			// 삭제할 멤버의 게시한 글이 없는 경우에도 삭제합니다.
			if (result1 >= 0 && result2 >= 0) {
				isSuccess = true;
			}

			System.out.println("isSuccess = " + isSuccess);
		} catch (Exception ex) {
			System.out.println("deleteMember() 에러: " + ex);
		} finally {
			try {
				if (isSuccess) {
					con.commit();
				} else {
					con.rollback();
				}
				con.setAutoCommit(true);// 다시 true로 설정합니다.
			} catch (Exception e) {
			}
			;
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
		return isSuccess;
	}

	public MemberBean getDetailMember(String id) {
		String sql = "select member_num,member_id,member_pw,member_nickname,member_rankpoint from wordmember where member_id= ?  ";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();

			MemberBean mb = new MemberBean();
			mb.setMember_num(rs.getInt("member_num"));
			mb.setMember_id(rs.getString("member_id"));
			mb.setMember_pw(rs.getString("member_pw"));
			mb.setMember_nickname(rs.getString("member_nickname"));
			mb.setMember_rankpoint(rs.getInt("member_rankpoint"));

			return mb;
		} catch (Exception ex) {
			System.out.println("getDeatilMember 에러: " + ex);
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

	public boolean MemberModify(MemberBean memberdata) throws Exception {
		String sql = "update wordmember set member_pw=?,member_nickname=? where member_num=?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberdata.getMember_pw());
			pstmt.setString(2, memberdata.getMember_nickname());
			pstmt.setInt(3, memberdata.getMember_num());
			pstmt.executeUpdate();
			return true;

		} catch (Exception ex) {
			System.out.println("getUpdateMember 에러: " + ex);
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
