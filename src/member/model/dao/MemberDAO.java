package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.model.vo.MemberBean;

public class MemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public MemberDAO() {// 데이터 베이스 접속
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}

	}

	public int isMember(MemberBean member) {// 아이디 존재 여부 확인
		String sql = "select member_pw from wordmember where member_id=?";
		int result = -1;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			System.out.println("아이디 =" + member.getMember_id());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("member_pw").equals(member.getMember_pw())) {
					result = 1;// 일치.
				} else {
					result = 0;// 불일치.
				}
			} else {
				result = -1;// 아이디 존재하지 않음.
			}
		} catch (Exception ex) {
			System.out.println("isMember 에러: " + ex);
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

	public boolean joinMember(MemberBean member) {// word member 보드에 넣는것
		String sql = "insert into wordmember(member_num,member_id,member_pw,member_nickname) VALUES (num_seq.nextval,?,?,?)";
		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_pw());
			pstmt.setString(3, member.getMember_nickname());
			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("joinMember 에러: " + ex);
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

	public List getMemberList() {
		String sql = "select * from wordmember ";
		List memberlist = new ArrayList();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setMember_num(rs.getInt("member_num"));
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_pw(rs.getString("member_pw"));
				mb.setMember_nickname(rs.getString("member_nickname"));
				memberlist.add(mb);
			}

			return memberlist;
		} catch (Exception ex) {
			System.out.println("getMemberlist 에러: " + ex);
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

	/*
	 * public boolean deleteMember(String id){ String sql
	 * ="DELETE FROM MEMBER2 WHERE member_id=?"; int result = 0; try{ con =
	 * ds.getConnection(); pstmt=con.prepareStatement(sql); pstmt.setString(1,id);
	 * 
	 * result = pstmt.executeUpdate(); if(result != 0){ return true; }
	 * 
	 * } catch(Exception ex){ System.out.println("deleteMember 에러: " + ex); }
	 * 
	 * finally{ if(rs!=null) try{rs.close();}catch(SQLException ex){}
	 * if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} if(con!=null)
	 * try{con.close();}catch(SQLException ex){} } return false; }
	 */

	public int getListCount() {
		int x = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from wordmemberboard");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
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
		return x;
	}
	
	public List getPointList() {
		String sql = "select * from  wordmember order by member_rankpoint desc ";
		List memberlist = new ArrayList();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setMember_nickname(rs.getString("member_nickname"));
				mb.setMember_rankpoint(rs.getInt("member_rankpoint"));
				memberlist.add(mb);
			}

			return memberlist;
		} catch (Exception ex) {
			System.out.println("getMemberlist 에러: " + ex);
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