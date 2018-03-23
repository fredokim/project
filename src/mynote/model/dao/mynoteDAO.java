package mynote.model.dao;

import mynote.model.vo.*;

import java.sql.*;
import java.util.*;
import javax.sql.*;


import javax.naming.Context;
import javax.naming.InitialContext;


public class mynoteDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public mynoteDAO(){
		try{
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");			
		}catch(Exception ex){
			System.out.println("mynote 디비 접속 실패! : " + ex);
			return;
		}
	}
	
	
	//내 단어장의 단어들을 가져온다. num에는 회원번호를 넣는다.
		public int getCount(String WordTable, String id) {
			mynoteBean mynote = null;		
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
				}else if(WordTable.equals("member_mywan")) {
					get = "member_mywan";
				}
				
				System.out.println("내 단어보기  =>진입할 멤버테이블의 칼럼 = " + get);
				
				rs.next();
				String result = rs.getString(get);
				int Count = 0;
				System.out.println("내 단어보기 => 진입 테이블 칼럼에 들어갈 값 = " + result);
				
				if(result.equals("0") || result.equals(" ")) {	//내 단어장의 단어들이 없을 때
					System.out.println("단어장이 0이면 일로 와야해");
					return 0;
				}else {
					String Words[] = result.split(",");
					for(int n=0; n<Words.length; n++)
						System.out.print("Words["+n+"] =" + Words[n] + ",");
					System.out.println();
					
					Count = Words.length;
					
					return Count;
				}
				
			
				
			}catch(Exception ex){
				System.out.println("getMyWords 내단어 가져오기 다오에서 실패 : " + ex);
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			
			return 0;
		}//getDetail  end;
	
	
	
	
		public List<mynoteBean> getmyList(int page, int limit, String WordTable, String id, int listcount){
			//page : 페이지
			//limit : 페이지 당 목록의 수
			try{
			mynoteBean mynote = null;		
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
			}else if(WordTable.equals("member_mywan")) {
				get = "member_mywan";
			}
			
			
			//System.out.println("내 단어보기  =>진입할 멤버테이블의 칼럼 = " + get);
			
			rs.next();
			String result = rs.getString(get);
			
			//System.out.println("내 단어보기 => 진입 테이블 칼럼에 들어갈 값 = " + result);
			
			int startrow=(page-1) * limit + 1;	//읽기 시작할 row 번호(	1		11		21		31
			int endrow = startrow + limit - 1;  //읽을 마지막 row 번호(	10		20		30		40
			
			if(result.equals("0") || result.equals(" ")) {	//내 단어장의 단어들이 없을 때
				List<mynoteBean> list = new ArrayList<mynoteBean>();
				System.out.println("단어장이 0이면 일로 와야해");
				mynote = new mynoteBean();
				mynote.setNum(0);
				list.add(mynote);
				return list;
			}else {
				String Words[] = result.split(",");
//				for(int n=0; n<Words.length; n++)
//					System.out.print("Words["+n+"] =" + Words[n] + ",");
				System.out.println();
				if(WordTable.equals("word_ipe")) {
					pstmt = con.prepareStatement("select * from word_ipe where num = ?");
				}else if(WordTable.equals("word_linux")) {
					pstmt = con.prepareStatement("select * from word_linux where num = ?");
				}else if(WordTable.equals("word_sql")) {
					pstmt = con.prepareStatement("select * from word_sql where num = ?");
				}else if(WordTable.equals("word_etc")) {
					pstmt = con.prepareStatement("select * from word_etc where num = ?");
				}
				
				List<mynoteBean> list = new ArrayList<mynoteBean>();
				
				System.out.println("startrow = " + startrow);
				System.out.println("endrow = " + endrow);
				System.out.println("listcount = " + listcount);
				
				if(endrow <= listcount) {
					for(int n=startrow-1; n<endrow; n++) {
						System.out.println("결과!!! = " + Words[n] );
					}
					
					
					for(int n=startrow-1; n<endrow; n++) {
						pstmt.setString(1, Words[n]);
						rs = pstmt.executeQuery();
						rs.next();
						mynote = new mynoteBean();
						mynote.setNum(rs.getInt("num"));
						mynote.setWord(rs.getString("word"));
						mynote.setContent(rs.getString("content"));
						list.add(mynote);
						
					}
					return list;
				}else if(endrow > listcount) {
					System.out.println("마지막페이지는 일로 와야해");
					for(int n=startrow-1; n<Words.length; n++) {
						System.out.println("결과!!! = " + Words[n] );
					}
					
					
					for(int n=startrow-1; n<Words.length; n++) {
						pstmt.setString(1, Words[n]);
						rs = pstmt.executeQuery();
						rs.next();
						mynote = new mynoteBean();
						mynote.setNum(rs.getInt("num"));
						mynote.setWord(rs.getString("word"));
						mynote.setContent(rs.getString("content"));
						list.add(mynote);
						
					}
					return list;
				}
				
				
				
			}
				
			}catch(Exception ex){
				System.out.println("마이노트 단어 리스트 불러오기,getmylist 에러 : " + ex);
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			return null;		
		}//getBoardList() 메서드 end
		
		
		
		
		
		
		
		
		
	
	//내 단어장의 단어들을 가져온다. num에는 회원번호를 넣는다.
	public List<mynoteBean> getMyWords(String WordTable, int num) {
		mynoteBean mynote = null;		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from wordmember where member_num = ?");
			pstmt.setInt(1, num);
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
			}else if(WordTable.equals("member_mywan")) {
				get = "member_mywan";
			}
			
			System.out.println("내 단어보기  =>진입할 멤버테이블의 칼럼 = " + get);
			
			rs.next();
			String result = rs.getString(get);
			
			System.out.println("내 단어보기 => 진입 테이블 칼럼에 들어갈 값 = " + result);
			
			if(result.equals("0")) {	//내 단어장의 단어들이 없을 때
				List<mynoteBean> list = new ArrayList<mynoteBean>();
				System.out.println("단어장이 0이면 일로 와야해");
				mynote = new mynoteBean();
				mynote.setNum(0);
				list.add(mynote);
				return list;
			}else {
				String Words[] = result.split(",");
				for(int n=0; n<Words.length; n++)
					System.out.print("Words["+n+"] =" + Words[n] + ",");
				System.out.println();
				if(WordTable.equals("word_ipe")) {
					pstmt = con.prepareStatement("select * from word_ipe where num = ?");
				}else if(WordTable.equals("word_linux")) {
					pstmt = con.prepareStatement("select * from word_linux where num = ?");
				}else if(WordTable.equals("word_sql")) {
					pstmt = con.prepareStatement("select * from word_sql where num = ?");
				}else if(WordTable.equals("word_etc")) {
					pstmt = con.prepareStatement("select * from word_etc where num = ?");
				}
				
				List<mynoteBean> list = new ArrayList<mynoteBean>();
				for(int n=0; n<Words.length; n++) {
					pstmt.setString(1, Words[n]);
					rs = pstmt.executeQuery();
					rs.next();
					mynote = new mynoteBean();
					mynote.setNum(rs.getInt("num"));
					mynote.setWord(rs.getString("word"));
					mynote.setContent(rs.getString("content"));
					list.add(mynote);
					
				}
				return list;
			}
			
		
			
		}catch(Exception ex){
			System.out.println("getMyWords 내단어 가져오기 다오에서 실패 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
		return null;
	}//getDetail  end;

	
	
	
	//선택된 칼럼을 삭제하는 메소드(테이블이름, 회원번호, 삭제될 단어 번호);
	public void sel_delete(String WordTable, String id, String wordnum) {	
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
			System.out.println("삭제 메소드 => 진입한 칼럼 = " + get);
			
			rs.next();
			String myWord[] = rs.getString(get).split(",");
			
			//for(int n=0; n<myWord.length; n++)
				//System.out.println("mywords 내 단어["+n+"] =" + myWord[n]);
			
			List<String> MyWords = new ArrayList<String>();
			
			for(int n=0; n<myWord.length; n++) {
				MyWords.add(myWord[n]);
			}
			
			int go = MyWords.indexOf(wordnum);
			System.out.println("go는 몇? = " + go);
			MyWords.remove(go);
			String resultWord = "";
			System.out.println("MyWords.size = " + MyWords.size());
			System.out.println("========결과===========");
			
			if(MyWords.size() == 0) {	//내 단어장의 단어가 1개 일 때
				resultWord = "0";
				System.out.println("변경될 값 = " + resultWord);
			}else {
				for(int n=0; n<MyWords.size(); n++) {
					System.out.println("MyWords["+n+"] =" + MyWords.get(n));
					resultWord += MyWords.get(n) + ",";
				}
				System.out.println("변경될 값 = " + resultWord.substring(0, resultWord.length()-1));
				resultWord = resultWord.substring(0, resultWord.length()-1);
			}
			
			
			
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
			System.out.println("******** " + WordTable + "의" + wordnum + "번 단어 삭제 완료 ***********");
			
			
		}catch(Exception ex){
			System.out.println("getMyWords 내단어 가져오기 다오에서 실패 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
	}//sel_delete end;
	
	
	
	
	
	
	
	public void selall_delete(String WordTable, int num, String selWord) {
		mynoteBean mynote = null;		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from wordmember where member_num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			String get = "";
			if(WordTable == "word_ipe") {
				get = "member_myipe";
			}else if(WordTable == "word_linux") {
				get = "member_mylinux";
			}else if(WordTable == "word_sql") {
				get = "member_mysql";
			}else if(WordTable == "word_etc") {
				get = "member_myetc";
			}
			System.out.println("진입한 칼럼 = " + get);
			
			rs.next();
			String myWords[] = rs.getString(get).split(",");
			for(int n=0; n<myWords.length; n++)
				System.out.println("myWords["+n+"] =" + myWords[n]);
			
			
			String newMywords[] = new String[myWords.length];
			String selwords[] = selWord.split(",");
			for(int n=0; n<selwords.length; n++)
				System.out.println("WordsNum["+n+"] =" + selwords[n]);

			for(int n=0; n<myWords.length; n++) {
				for(int m=0; m<selwords.length; m++ ) {
					if(myWords[n] == selwords[m]) {
						System.out.println("=====비교=====");
						System.out.print("myWords[n] = " + myWords[n] + "     ");
						System.out.print("selwords[m] = " + selwords[m]);
						newMywords[n] = myWords[n];
					}
				}
			}
			System.out.println("===============결과==================");
			for(int n=0; n<newMywords.length; n++)
				System.out.println("newMywords["+n+"] =" + newMywords[n]);
			
		}catch(Exception ex){
			System.out.println("getMyWords 내단어 가져오기 다오에서 실패 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
	}//selall_delete end;
	
	
	
		//오답노트 가져오기 메소드 (가져올 카테고리, 회원번호)
		public List<mynoteBean> getwrongnote(String WordTable, String id) {
			mynoteBean mynote = null;		
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement("select * from wordmember where member_id = ?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				rs.next();
				String result = rs.getString("member_mywan");
				
				System.out.println("내 단어보기 => 진입 테이블 칼럼에 들어갈 값 = " + result);
				
				if(result.equals("0")) {	//내 단어장의 단어들이 없을 때
					List<mynoteBean> list = new ArrayList<mynoteBean>();
					System.out.println("단어장이 0이면 일로 와야해");
					mynote = new mynoteBean();
					mynote.setNum(0);
					list.add(mynote);
					return list;
					
				}else {
					String Words[] = result.split(",");
					System.out.print("오답노트 내용 : ");
					for(int n=0; n<Words.length; n++)
						System.out.print("Words["+n+"] =" + Words[n] + ",");
					System.out.println();
					
					List<String> WordsEnd = new ArrayList<String>();
					if(WordTable.equals("word_ipe")) {
						for(int n=0; n<Words.length; n++) {
							if(Integer.parseInt(Words[n]) > 999 && Integer.parseInt(Words[n]) < 2000 ) {
								WordsEnd.add(Words[n]);
							}
						}
						pstmt = con.prepareStatement("select * from word_ipe where num = ?");
						
					}else if(WordTable.equals("word_sql")) {
						for(int n=0; n<Words.length; n++) {
							if(Integer.parseInt(Words[n]) > 1999 && Integer.parseInt(Words[n]) < 3000 ) {
								WordsEnd.add(Words[n]);
							}
						}
						pstmt = con.prepareStatement("select * from word_sql where num = ?");
						
					}else if(WordTable.equals("word_linux")) {
						for(int n=0; n<Words.length; n++) {
							if(Integer.parseInt(Words[n]) > 2999 && Integer.parseInt(Words[n]) < 4000 ) {
								WordsEnd.add(Words[n]);
							}
						}
						pstmt = con.prepareStatement("select * from word_linux where num = ?");
						
					}else if(WordTable.equals("word_etc")) {
						for(int n=0; n<Words.length; n++) {
							if(Integer.parseInt(Words[n]) > 3999 && Integer.parseInt(Words[n]) < 5000 ) {
								WordsEnd.add(Words[n]);
							}
						}
						pstmt = con.prepareStatement("select * from word_etc where num = ?");
					}
					
					
					List<mynoteBean> list = new ArrayList<mynoteBean>();
					for(int n=0; n<WordsEnd.size(); n++) {
						pstmt.setString(1, WordsEnd.get(n));
						rs = pstmt.executeQuery();
						rs.next();
						mynote = new mynoteBean();
						mynote.setNum(rs.getInt("num"));
						mynote.setWord(rs.getString("word"));
						mynote.setContent(rs.getString("content"));
						list.add(mynote);
						System.out.println("결과값 = " + mynote.getNum());
					}
					return list;
				}
				
			
				
			}catch(Exception ex){
				System.out.println("getMyWords 내단어 가져오기 다오에서 실패 : " + ex);
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			
			return null;
		}//getDetail  end;
	
		
		
		
		
		//오답노트용 삭제 메소드(테이블이름, 회원번호, 삭제될 단어 번호);
		public void wrongnote_delete(String WordTable, String id, String wordnum) {	
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement("select * from wordmember where member_id = ?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				
				
				rs.next();
				
				String myWord[] = rs.getString("member_mywan").split(",");
				
				System.out.print("진입한 칼럼의 값(원래값) = ");
				for(int n=0; n<myWord.length; n++)
					System.out.print(myWord[n] + " , ");
				System.out.println();
				
				List<String> MyWords = new ArrayList<String>();
				
				for(int n=0; n<myWord.length; n++) {
					MyWords.add(myWord[n]);
				}
				
				int go = MyWords.indexOf(wordnum);
				MyWords.remove(go);
				String resultWord = "";
				System.out.println("MyWords.size = " + MyWords.size());
				System.out.println("========결과===========");
				
				if(MyWords.size() == 0) {	//내 단어장의 단어가 1개 일 때
					resultWord = "0";
					System.out.println("변경될 값 = " + resultWord);
				}else {
					for(int n=0; n<MyWords.size(); n++) {
						System.out.println("MyWords["+n+"] =" + MyWords.get(n));
						resultWord += MyWords.get(n) + ",";
					}
					System.out.println("변경될 값 = " + resultWord.substring(0, resultWord.length()-1));
					resultWord = resultWord.substring(0, resultWord.length()-1);
				}
				
				
				
				pstmt = con.prepareStatement("update wordmember set member_mywan = ? where member_id = ?");
				
				pstmt.setString(1, resultWord);
				pstmt.setString(2, id);
				
				int go123 = pstmt.executeUpdate();
				System.out.println("******** " + WordTable + "의" + wordnum + "번 단어 삭제 완료 ***********");
				
				
			}catch(Exception ex){
				System.out.println("오답노트 다오 메소드 실패 : " + ex);
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			
		}//sel_delete end;
	
		
		
		
		
}
























