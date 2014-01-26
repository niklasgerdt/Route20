package eu.route20.distributed;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

//TODO java ee 7 rest
@SuppressWarnings("serial")
@WebServlet({ "/route", "/r20" })
public class RoutingService extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***");
		System.out.println(req.getPathInfo());
		System.out.println("---");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getContextPath());
	}
}
