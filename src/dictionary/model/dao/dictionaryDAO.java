package dictionary.model.dao;


import dictionary.model.vo.*;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;



public class dictionaryDAO {
	
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public dictionaryDAO(){
		try{
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");			
		}catch(Exception ex){
			System.out.println("DB 접속 실패 : " + ex);
			return;
		}
	}
	
	
	
	public int getListCount(String word){
		int x = 0;
		try{
			con = ds.getConnection();
			
			
			if(word.equals("word_ipe")) {
				pstmt = con.prepareStatement("select count(*) from word_ipe");
			}else if(word.equals("word_linux")) {
				pstmt = con.prepareStatement("select count(*) from word_linux");
			}else if(word.equals("word_sql")) {
				pstmt = con.prepareStatement("select count(*) from word_sql");
			}else if(word.equals("word_etc")) {
				pstmt = con.prepareStatement("select count(*) from word_etc");
			}
			
			//pstmt = con.prepareStatement("select count(*) from board");					
			rs = pstmt.executeQuery();		
			
			if(rs.next()){
				x = rs.getInt(1);
			}			
			
		}catch(Exception ex){
			System.out.println("getListCount() 에러 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return x;
	}//getListCount()메서드 end;
	
	
	
	public List<dictionaryBean> getdicList(int page, int limit, String word){
		//page : 페이지
		//limit : 페이지 당 목록의 수
		//Board_re_ref desc, board_re_seq asc에 의해 정렬한 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문
		
		String dic_list_sql="";
		if(word.equals("word_ipe")) {
			dic_list_sql=
		"select * from (select rownum rnum, num, word, content from "
		+ "(select num, word, content from word_ipe order by num) ) where rnum>=? and rnum<=?";
		}else if(word.equals("word_linux")) {
			dic_list_sql=
		"select * from (select rownum rnum, num, word, content from "
		+ "(select num, word, content from word_linux order by num) ) where rnum>=? and rnum<=?";
		}else if(word.equals("word_sql")) {
			dic_list_sql=
		"select * from (select rownum rnum, num, word, content from "
				+ "(select num, word, content from word_sql order by num) ) where rnum>=? and rnum<=?";
		}else if(word.equals("word_etc")) {
			dic_list_sql=
		"select * from (select rownum rnum, num, word, content from "
				+ "(select num, word, content from word_etc order by num) ) where rnum>=? and rnum<=?";
		}
		
		
		List<dictionaryBean> list = new ArrayList<dictionaryBean>();
								//한 페이지 당 10개씩 목록인 경우		1페이지	2페이지	3페이지	4페이지
		int startrow=(page-1) * limit + 1;	//읽기 시작할 row 번호(	1		11		21		31
		int endrow = startrow + limit - 1;  //읽을 마지막 row 번호(	10		20		30		40
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(dic_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()){
				dictionaryBean dictionary = new dictionaryBean();
				dictionary.setdictionary_num(rs.getInt("num"));
				dictionary.setdictionary_word(rs.getString("word"));
				dictionary.setdictionary_content(rs.getString("content"));
				list.add(dictionary);	//값을 담은 객체를 리스트에 저장합니다.
			}
			return list;	//값을 담은 객체를 저장한 리스트를 호출한 곳으로 가져갑니다.
		}catch(Exception ex){
			System.out.println("getBoardList() 에러 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return null;		
	}//getBoardList() 메서드 end
	
	
	
	
	
	//마이노트에 단어를 추가하는 메소드(테이블이름, 회원번호, 추가될 단어 번호);
	public boolean insert_word(String WordTable, String id, String wordnum) {	
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from wordmember where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String get = "";
			if(WordTable.equals("word_ipe")) {
				get = "member_myipe";
			}else if(WordTable.equals("word_linux")) {
				get = "member_mylinux";
			}else if(WordTable.equals("word_sql")) {
				get = "member_mysql";
			}else if(WordTable.equals("word_etc")) {
				get = "member_myetc";
			}
			System.out.println("단어추가메서드 => 진입한 칼럼 = " + get);
			
			rs.next();
			String myWord[] = rs.getString(get).split(",");
			
			for(int n=0; n<myWord.length; n++)
				System.out.println("mywords 내 단어["+n+"] =" + myWord[n]);
			
			List<String> MyWords = new ArrayList<String>();
			
			for(int n=0; n<myWord.length; n++) {
				MyWords.add(myWord[n]);
			}
			
			int go = MyWords.indexOf(wordnum);
			
			if(go > -1) {	//이미 단어는 추가되어 있따.
				System.out.println("이미 내 단어장에 있다.");
				return false;
			}else {		//새로 추가하는 거다
				System.out.println("MyWords.indexOf(0) = " + MyWords.indexOf("0"));
				if(MyWords.indexOf("0") > -1 || MyWords.indexOf(" ") > -1) {
					MyWords.clear();
				}
				MyWords.add(wordnum);
				for(int n=0; n<MyWords.size();n++) {
					System.out.println("우선보자 = " + MyWords.get(n));
				}
				
				String resultWord="";
				
				System.out.println("==============결과===============");
				for(int n=0; n<MyWords.size(); n++) {
					System.out.println("MyWords["+n+"] =" + MyWords.get(n));
					resultWord += MyWords.get(n) + ",";
				}
				System.out.println("변경될 값 = " + resultWord.substring(0, resultWord.length()-1));
				resultWord = resultWord.substring(0, resultWord.length()-1);
				
				if(WordTable.equals("word_ipe")) {
					pstmt = con.prepareStatement("update wordmember set member_myipe = ? where member_id = ?");
				}else if(WordTable.equals("word_linux")) {
					pstmt = con.prepareStatement("update wordmember set member_mylinux = ? where member_id = ?");
				}else if(WordTable.equals("word_sql")) {
					pstmt = con.prepareStatement("update wordmember set member_mysql = ? where member_id = ?");
				}else if(WordTable.equals("word_etc")) {
					pstmt = con.prepareStatement("update wordmember set member_myetc = ? where member_id = ?");
				}
				
				pstmt.setString(1, resultWord);
				pstmt.setString(2, id);
				
				int go123 = pstmt.executeUpdate();
				System.out.println("*********** " + WordTable + "의" + wordnum + "번 단어 마이노트에 추가 완료 ***********");
				return true;
			}
			
			
		}catch(Exception ex){
			System.out.println("마이노트에 단어 추가하기 다오에서 실패 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;	
	}//insert_word end;
		
	
	
}
























