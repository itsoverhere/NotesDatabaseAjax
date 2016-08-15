package edu.webapde.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.webapde.dto.Note;
import edu.webapde.service.NotesService;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns={"/Controller", "/add", "/main", "/toggle"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		PrintWriter pw = response.getWriter();
		
		switch(action){
			case "/add":
				String note = request.getParameter(Note.COLUMN_NOTE);
				NotesService.addNote(note);
				pw.write(note);
				break;
			case "/main":
				ArrayList<Note> notes = NotesService.getAllNotes();
				request.setAttribute("notes", notes);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			case "/toggle":
				int id = Integer.parseInt(request.getParameter(Note.COLUMN_ID));
				int result = NotesService.toggleNoteVisibility(id);
				pw.write(String.valueOf(result));
				break;
		}
	}

}
