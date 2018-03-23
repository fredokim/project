package admin.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.model.dao.BoardDAO;
import admin.model.vo.BoardBean;
import common.action.*;

public class BoardWriteAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		BoardDAO boarddao=new BoardDAO();
	   	BoardBean boarddata=new BoardBean();
	   	ActionForward forward=new ActionForward();
	   	
		String realFolder="";
   		String saveFolder="boardupload";
   		
   		int fileSize=5*1024*1024;
   		
   		realFolder=request.getSession().getServletContext().getRealPath(saveFolder);
   		//realFolder=request.getRealPath(saveFolder);
   		
   		boolean result=false;
   		
   		try{
   			MultipartRequest multi=null;
   			
   			multi=new MultipartRequest(request,
   					realFolder,
   					fileSize,
   					"UTF-8",
   					new DefaultFileRenamePolicy());
   			
   			boarddata.setBoard_id(multi.getParameter("board_id"));
	   		boarddata.setBoard_subject(multi.getParameter("board_subject"));
	   		boarddata.setBoard_content(multi.getParameter("board_content"));
	   		boarddata.setBoard_file(
	   				multi.getFilesystemName(
	   						(String)multi.getFileNames().nextElement()));
	   		
	   		result=boarddao.boardInsert(boarddata);
	   		
	   		if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		System.out.println("게시판 등록 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardList.ad");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}