package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;

import model.DBPedia;

/**
 * HttpServlet: get the response from index.html, call QueryFromLucene.java and return the results to result.jsp 
 * @author Gaoxiang Zhang
 *
 */
public class QueryController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public QueryController(){
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userContent = req.getParameter("content");
		QueryFromLucene query = new QueryFromLucene();
		try {
			List<DBPedia> list = query.query(userContent);
			req.setAttribute("results", list);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
			dispatcher.forward(req, resp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

}
