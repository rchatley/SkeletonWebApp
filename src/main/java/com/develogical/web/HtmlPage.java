package com.develogical.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public abstract class HtmlPage {

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    writeHeaderTo(writer);
    writeContentTo(writer);
    writeFooterTo(writer);
  }

  private void writeHeaderTo(PrintWriter writer) {
    writer.println("<html>");
    writer.println("<head></head>");
    writer.println("<body>");
  }

  private void writeFooterTo(PrintWriter writer) {
    writer.println("</body>");
    writer.println("</html>");
  }

  protected abstract void writeContentTo(PrintWriter writer);
}
