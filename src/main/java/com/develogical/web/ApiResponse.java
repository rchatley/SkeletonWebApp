package com.develogical.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class ApiResponse {

  private final String answer;

  public ApiResponse(String answer) {
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    PrintWriter writer = resp.getWriter();
    writer.println(answer);
  }
}
