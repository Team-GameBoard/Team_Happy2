package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/board")
public class BoardController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		HttpSession session1 = request.getSession();
//		session1.setAttribute("userId", "test");
//		System.out.println(session1.getAttribute("userId"));
		String command = request.getParameter("command");

		if (command == null) {
			command = "list";
		}

		if (command.equals("list")) {
			list(request, response);
		} else if (command.contentEquals("write")) {
			write(request, response);
		} else if (command.equals("read")) {
			read(request, response);
		} else if (command.equals("updateForm")) {
			updateForm(request, response);
		} else if (command.equals("update")) {
			update(request, response);
		} else if (command.equals("delete")) {
			delete(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strNum = request.getParameter("num");
		String userId = request.getParameter("userId");

		if (strNum == null || strNum.trim().length() == 0) {
			response.sendRedirect("board");
			return;
		}
		boolean result = false;
		try {
			result = BoardDAO.deleteContent(Integer.parseInt(strNum), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "해당 게시글 삭제 실패했습니다.");
		}
		if (result) {
			response.sendRedirect("board");
			return;
		} else {
			request.setAttribute("error", "삭제하려는 게시글이 존재하지 않습니다");
		}
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNum = request.getParameter("num");
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		if (strNum == null || strNum.trim().length() == 0 || title == null || title.trim().length() == 0
				|| content == null || content.trim().length() == 0) {
			response.sendRedirect("board");
			return;
		}

		boolean result = false;

		try {
			// ,userId
			result = BoardDAO.updateContent(new Board(Integer.parseInt(strNum), title, content));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 수정 실패");
		}
		if (result) {
			response.sendRedirect("board");
			return;
		}
		request.setAttribute("error", "게시글 수정 실패");
		request.getRequestDispatcher("error.jsp").forward(request, response);

	}

	private void updateForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String strNum = request.getParameter("num");

		if (strNum == null || strNum.trim().length() == 0) {
			response.sendRedirect("board");
			return;
		}
		String url = "error.jsp";
		Board gContent = null;
		try {
			gContent = BoardDAO.getContent(Integer.parseInt(strNum), false);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "수정하고자 하는 게시글 검색 실패했습니다");
		}
		if (gContent == null) {
			request.setAttribute("error", "수정하고자 하는 게시글이 존재하지 않습니다");
		} else {
			request.setAttribute("resultContent", gContent);
			url = "Update.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void read(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strNum = request.getParameter("num");
		if (strNum == null || strNum.length() == 0) {
			response.sendRedirect("board");
			return;
		}
		String url = "error.jsp";
		Board gContent = null;
		try {
			gContent = BoardDAO.getContent(Integer.parseInt(strNum), true);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 읽기 실패");
		}
		if (gContent == null) {
			request.setAttribute("error", "해당 게시글이 존재하지 않습니다");
		} else {
			request.setAttribute("resultContent", gContent);
			url = "GameboardDetail.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void write(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session1 = request.getSession();
		String userId = (String) session1.getAttribute("userId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String game_num = request.getParameter("game_num");

		// 데이터값 입력 유무만 유효성 검증
		if (userId == null || userId.trim().length() == 0 || title == null || title.trim().length() == 0
				|| content == null || content.trim().length() == 0) {
			response.sendRedirect("write.html");
			return;// write() 메소드 종료
		}

		boolean result = false;

		try {
			result = BoardDAO.writeContent(new Board(userId, title, content, Integer.parseInt(game_num)));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 저장 시도 실패 재 시도 하세요");
		}

		if (result) {
			response.sendRedirect("board?game_num=" + game_num);
		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		String game_num = request.getParameter("game_num");

		try {
			if(game_num.equals("10")) {
				request.setAttribute("list", BoardDAO.getAllContents(Integer.parseInt(game_num)));
				url = "Gameboard_Lostark.jsp";
			}else if(game_num.equals("20")) {
				request.setAttribute("list", BoardDAO.getAllContents(Integer.parseInt(game_num)));
				url = "Gameboard_LoL.jsp";
			}else if(game_num.equals("30")) {
				request.setAttribute("list", BoardDAO.getAllContents(Integer.parseInt(game_num)));
				url = "Gameboard_Overwatch.jsp";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "모두 보기 실패 재 실행 해 주세요");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
