package com.develogical;

import com.develogical.web.ApiResponse;
import com.develogical.web.IndexPage;
import com.develogical.web.ResultsPage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebServer {

  public WebServer() throws Exception {

    Server server = new Server(portNumberToUse());

    ServletHandler handler = new ServletHandler();
    handler.addServletWithMapping(new ServletHolder(new Website()), "/*");
    handler.addServletWithMapping(new ServletHolder(new Api()), "/api/*");
    server.setHandler(handler);

    server.start();
  }

  private static String queryFrom(HttpServletRequest req) {
    String query;
    if (req.getParameter("q") != null) {
      query = req.getParameter("q");
    } else {
      query = "";
    }
    return query;
  }

  public static void main(String[] args) throws Exception {
    new WebServer();
  }

  private Integer portNumberToUse() {
    return System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
  }

  static class Website extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String query = queryFrom(req);
      if (query.isEmpty()) {
        new IndexPage().writeTo(resp);
      } else {
        new ResultsPage(query, new QueryProcessor().process(query)).writeTo(resp);
      }
    }
  }

  static class Api extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      System.out.println(req.getRequestURI());
      System.out.println(req.getParameterMap());
      String query = queryFrom(req);
      new ApiResponse(new QueryProcessor().process(query)).writeTo(resp);
    }
  }
}
