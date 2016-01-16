package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;

import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;

import model.CurrentQuery;
import model.QueryResult;
import sun.java2d.opengl.OGLContext;

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
		// TODO: get the entity 
		//
		// ArrayList<CurrentQuery> candidates = ...
		ArrayList<CurrentQuery> candidates = sampleList();
		ArrayList<QueryResult> results = new ArrayList<>();
		try {
			for(int i = 0; i < candidates.size(); i++){
				QueryResult result = query.query(userContent, candidates.get(i));
				results.add(result);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println(results.get(0).getLength());
		String html = "";
		int last = 0;
		for (QueryResult result : results) {
			String surface = userContent.substring(result.getOffset(), result.getOffset() + result.getLength());
			surface = "<a href='" + result.getUrl() + ">" + surface + "</a>";
			html += userContent.substring(last, result.getOffset()) + surface;
			last = result.getOffset() + result.getLength();
		}
		html += userContent.substring(last);
		PrintWriter writer = resp.getWriter();
		writer.write(html);
		writer.flush();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	private ArrayList<CurrentQuery> sampleList(){
		ArrayList<CurrentQuery> result = new ArrayList<>();
		
		ArrayList<String> names = new ArrayList<>();
		names.add("china");
		
		CurrentQuery query = new CurrentQuery();
		query.setOffset(0);
		query.setLength(1);
		query.setName("Hello");
		query.setEntityName(names);
		
		result.add(query);
		return result; 
	}

}
