package admin.model.dao;

//SQL과 관련된 객체와 List객체, JNDI 관련 객체를 사용하기 위해 import 합니다.

import java.sql.*;
import java.util.*;
import javax.sql.*;

import admin.model.vo.BoardBean;

import javax.naming.Context;
import javax.naming.InitialContext;

/*
		DAO(Data Access Object) 클래스
		- 데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스입니다.
		- 어떤 Action 클래스가 호출되더라도 그에 대항하는 데이터 베이스 연동 처리는 DAO클래스에서 이루어지게 됩니다.
*/
public class BoardDAO {
	// 데이터 베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	// 글의 개수 구하기.
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

	// 글 목록 보기.
	public List getBoardList(int page, int limit) {
		String board_list_sql = "select * from " + "(select rownum rnum,board_num,board_id,board_subject,"
				+ "board_content,board_file,board_re_ref,board_re_lev,"
				+ "board_re_seq,board_readcount,board_date from " + "(select * from wordmemberboard order by "
				+ "board_re_ref desc,board_re_seq asc)) " + "where rnum>=? and rnum<=?";

		List list = new ArrayList();

		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호.
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호.
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getDate("board_date"));
				list.add(board);
			}

			return list;
		} catch (Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
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

	// 글 내용 보기.
	public BoardBean getDetail(int num) throws Exception {
		BoardBean board = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from wordmemberboard where board_num = ?");
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getDate("board_date"));
			}
			return board;
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
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

	public boolean boardDelete(int num) {
		String board_delete_sql = "delete from wordmemberboard where BOARD_num=?";

		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
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

	// 글의 개수 구하기- 자유 게시판.
	public int getListCount2() {
		int x = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from wordmemberboard2");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount2 에러: " + ex);
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

	// 글 목록 보기.
	public List getBoardList2(int page, int limit) {
		String board_list_sql = "select * from " + "(select rownum rnum,board_num2,board_id2,board_subject2,"
				+ "board_content2,board_file2,board_re_ref2,board_re_lev2,"
				+ "board_re_seq2,board_readcount2,board_date2 from " + "(select * from wordmemberboard2 order by "
				+ "board_re_ref2 desc,board_re_seq2 asc)) " + "where rnum>=? and rnum<=?";

		List list = new ArrayList();

		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호.
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호.
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num2"));
				board.setBoard_id(rs.getString("board_id2"));
				board.setBoard_subject(rs.getString("board_subject2"));
				board.setBoard_content(rs.getString("board_content2"));
				board.setBoard_file(rs.getString("board_file2"));
				board.setBoard_re_ref(rs.getInt("board_re_ref2"));
				board.setBoard_re_lev(rs.getInt("board_re_lev2"));
				board.setBoard_re_seq(rs.getInt("board_re_seq2"));
				board.setBoard_readcount(rs.getInt("board_readcount2"));
				board.setBoard_date(rs.getDate("board_date2"));
				list.add(board);
			}

			return list;
		} catch (Exception ex) {
			System.out.println("getBoardList2 에러 : " + ex);
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

	// 글 내용 보기.
	public BoardBean getDetail2(int num) throws Exception {
		BoardBean board = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from wordmemberboard2 where board_num2 = ?");
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num2"));
				board.setBoard_id(rs.getString("board_id2"));
				board.setBoard_subject(rs.getString("board_subject2"));
				board.setBoard_content(rs.getString("board_content2"));
				board.setBoard_file(rs.getString("board_file2"));
				board.setBoard_re_ref(rs.getInt("board_re_ref2"));
				board.setBoard_re_lev(rs.getInt("board_re_lev2"));
				board.setBoard_re_seq(rs.getInt("board_re_seq2"));
				board.setBoard_readcount(rs.getInt("board_readcount2"));
				board.setBoard_date(rs.getDate("board_date2"));
			}
			return board;
		} catch (Exception ex) {
			System.out.println("getDetail2 에러 : " + ex);
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

	public boolean boardDelete2(int num) {
		String board_delete_sql = "delete from wordmemberboard2 where BOARD_num2=?";

		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
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

	public int boardReply(BoardBean board) {
		String board_max_sql = "select max(board_num) from wordmemberboard";
		String sql = "";
		int num = 0;
		int result = 0;

		int re_ref = board.getBoard_re_ref();
		int re_lev = board.getBoard_re_lev();
		int re_seq = board.getBoard_re_seq();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "update wordmemberboard set board_re_seq=board_re_seq+1 ";
			sql += "where board_re_ref=? and board_re_seq>?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			result = pstmt.executeUpdate();

			re_seq = re_seq + 1;
			re_lev = re_lev + 1;

			sql = "insert into wordmemberboard (board_num,board_id,board_subject,";
			sql += "board_content,board_file,board_re_ref,board_re_lev,";
			sql += "board_re_seq,board_readcount,board_date) ";
			sql += "values(?,?,?,?,?,?,?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_id());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, ""); // 답장에는 파일을 업로드하지 않음.
			pstmt.setInt(6, re_ref);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			pstmt.setInt(9, 0);
			pstmt.executeUpdate();
			return num;
		} catch (SQLException ex) {
			System.out.println("boardReply 에러 : " + ex);
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
		return 0;
	}

	public int boardReply2(BoardBean board) {
		String board_max_sql = "select max(board_num2) from wordmemberboard2";
		String sql = "";
		int num = 0;
		int result = 0;

		int re_ref = board.getBoard_re_ref();
		int re_lev = board.getBoard_re_lev();
		int re_seq = board.getBoard_re_seq();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "update wordmemberboard2 set board_re_seq2=board_re_seq2+1 ";
			sql += "where board_re_ref2=? and board_re_seq2>?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			result = pstmt.executeUpdate();

			re_seq = re_seq + 1;
			re_lev = re_lev + 1;

			sql = "insert into wordmemberboard2 (board_num2,board_id2,board_subject2,";
			sql += "board_content2,board_file2,board_re_ref2,board_re_lev2,";
			sql += "board_re_seq2,board_readcount2,board_date2) ";
			sql += "values(?,?,?,?,?,?,?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_id());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, ""); // 답장에는 파일을 업로드하지 않음.
			pstmt.setInt(6, re_ref);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			pstmt.setInt(9, 0);
			pstmt.executeUpdate();
			return num;
		} catch (SQLException ex) {
			System.out.println("boardReply 에러 : " + ex);
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
		return 0;
	}

	public List getSearch(String search, String condition) {
		List list = new ArrayList();
		String sql = "";
		if (condition.equals("board_id")) {
			sql = "select * from wordmemberboard2 where board_id2=?";
		} else {
			sql = "select * from wordmemberboard2 where board_subject2=?";
		}

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num2"));
				board.setBoard_id(rs.getString("board_id2"));
				board.setBoard_subject(rs.getString("board_subject2"));
				board.setBoard_content(rs.getString("board_content2"));
				board.setBoard_file(rs.getString("board_file2"));
				board.setBoard_re_ref(rs.getInt("board_re_ref2"));
				board.setBoard_re_lev(rs.getInt("board_re_lev2"));
				board.setBoard_re_seq(rs.getInt("board_re_seq2"));
				board.setBoard_readcount(rs.getInt("board_readcount2"));
				board.setBoard_date(rs.getDate("board_date2"));
				list.add(board);
			}
			return list;
		} catch (SQLException ex) {
			System.out.println("isgetSearch 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public boolean boardInsert(BoardBean board) {
		int num = 0;
		String sql = "";

		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(board_num) from wordmemberboard");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "insert into wordmemberboard (board_num,board_id,board_subject,";
			sql += "board_content, board_file,board_re_ref," + "board_re_lev,board_re_seq,board_readcount,"
					+ "board_date) values(?,?,?,?,?,?,?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_id());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);

			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("boardInsert 에러 : " + ex);
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

	public boolean boardInsert2(BoardBean board) {
		int num = 0;
		String sql = "";

		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(board_num2) from wordmemberboard2");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "insert into wordmemberboard2 (board_num2,board_id2,board_subject2,";
			sql += "board_content2, board_file2,board_re_ref2," + "board_re_lev2,board_re_seq2,board_readcount2,"
					+ "board_date2) values(?,?,?,?,?,?,?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_id());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);

			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("boardInsert2 에러 : " + ex);
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
