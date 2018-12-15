package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

@WebServlet("/Prime.do")
public class Prime extends HttpServlet
{
        private static final long serialVersionUID = 1L;

        public Prime()
        {
                super();
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {

                Engine engine = Engine.getInstance();
                String min = request.getParameter("min");
                String max = request.getParameter("max");
                Writer out = response.getWriter();
                response.setContentType("text/json");
                response.setHeader("Access-Control-Allow-Origin", "*");

                try
                {
                        String result = engine.doPrime(min, max);
                        out.write("{\"status\":true, \"data\":" + result + "}");
                } catch (Exception e)
                {
                        out.write("{\"status\":false, \"data\":\"" + e.getMessage() + "\"}");
                }

        }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
