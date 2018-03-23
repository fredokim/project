

package dictionary.model.dao;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import dictionary.model.vo.*;


public class WordDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public WordDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public int getListCount() {
		int x = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from word");
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

	public List getWordList(int page, int limit) {
		String word_list_sql = "select * from " + "(select rownum rnum, word_number,word,info from word)"
				+ "where rnum>=? and rnum<=?";

		List list = new ArrayList();

		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호.
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호.
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(word_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				WordBean word = new WordBean();
				word.setNumber(rs.getInt("word_number"));
				word.setWord(rs.getString("word"));
				word.setContent(rs.getString("info"));
				list.add(word);
			}

			return list;
		} catch (Exception ex) {
			System.out.println("getWordList 에러 : " + ex);
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

	public List getSearch(String word) {
		String sql = "select * from wordview where word like ?";
		List list = new ArrayList();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + word + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				WordBean wb = new WordBean();
				wb.setNumber(rs.getInt("num"));
				wb.setWord(rs.getString("word"));
				wb.setContent(rs.getString("content"));
				list.add(wb);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getSearch 에러: " + ex);
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

	
	
	//memeberword select해서 memberwordbean에 넣기
	public List getMynote(String id) {
		String sql = "select * from wordmember where member_id=?";
		List list = new ArrayList();
		
		System.out.println(id);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				WordmemberBean wmb = new WordmemberBean();
				
				wmb.setMemeber_num(rs.getInt(1));
				wmb.setMember_id(rs.getString(2));
				wmb.setMember_pw(rs.getString(3));
				wmb.setMember_nickname(rs.getString(4));
				
				wmb.setMember_myipe(rs.getString(5));
				wmb.setMember_mysql(rs.getString(6));
				wmb.setMember_mylinux(rs.getString(7));
				wmb.setMember_myetc(rs.getString(8));
				wmb.setMember_rankpoint(rs.getInt(9));
				wmb.setMember_mywan(rs.getString(10));
				list.add(wmb);
				
				
			}
			return list;
			
			
		} catch (Exception ex) {
			System.out.println("getMynote 에러: " + ex);
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
	
	//매개변수 : 카테고리번호 (일련번호 맨앞자리 숫자 한개), id, 추가할 번호 명, wordmemberBeanlist
	public boolean updateMynote(int categoryNum, String id, int word_number, List wordmemberlist) {
		
		//업데이트할 때 다른 값이 null이 되기 떄문에 공백 줌(공백은 getMynote에서 trim으로 없애줌)
		String addWordNum[] = {"","","",""};
		String word_numTostring = String.valueOf(word_number);
		WordmemberBean wmb = (WordmemberBean)wordmemberlist.get(0);
		String getMynote[] = {wmb.getMember_myipe(), wmb.getMember_mysql(), wmb.getMember_mylinux(), wmb.getMember_myetc()};
		
		switch (categoryNum) {
		case 1:
			
			//추가하려는 단어번호가 단어장에 이미 있을 경우 false 반환해서 alert처리
			if(!wmb.getMember_myipe().contains(word_numTostring)) {
				//값이 없을 경우에는 처음 등록이니까  콤마없이
				if(wmb.getMember_myipe().equals("0")) {
					getMynote[0] = getMynote[0].replace("0", "");
					addWordNum[0] = word_numTostring;
				} else {
					addWordNum[0] = "," + word_numTostring;
				}
			} else {
				return false;
			}
			break;
		case 2:
			if(!wmb.getMember_mysql().contains(word_numTostring)) {
				if(wmb.getMember_mysql().equals("0")) {
					getMynote[0] = getMynote[0].replace("0", "");
					addWordNum[1] = word_numTostring;
				} else {
					addWordNum[1] = "," + word_numTostring;
				}
			} else {
				return false;
			}
			break;
		case 3:
			if(!wmb.getMember_mylinux().contains(word_numTostring)) {
				if(wmb.getMember_mylinux().equals("0")) {
					getMynote[2] = getMynote[0].replace("0", "");
					addWordNum[2] = word_numTostring;
				} else {
					addWordNum[2] = "," + word_numTostring;
				}
			} else {
				return false;
			}
			break;
		case 4:
			if(!wmb.getMember_myetc().contains(word_numTostring)) {
				if(wmb.getMember_myetc().equals("0")) {
					getMynote[3] = getMynote[0].replace("0", "");
					addWordNum[3] = word_numTostring;
				} else {
					addWordNum[3] = "," + word_numTostring;
				}
			} else {
				return false;
			}
			break;
		
		}
		
		
		String sql = "update wordmember "
				   + "set member_myipe=?, member_mysql=?, member_mylinux=?, member_myetc=? "
				   + "where member_id=?";
		int result = 0;
		
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, getMynote[0] + addWordNum[0]);
			pstmt.setString(2, getMynote[1] + addWordNum[1]);
			pstmt.setString(3, getMynote[2] + addWordNum[2]);
			pstmt.setString(4, getMynote[3] + addWordNum[3]);
			pstmt.setString(5, id);
			result = pstmt.executeUpdate();
	
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("updateMynote 에러: " + ex);
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


