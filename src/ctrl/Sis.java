package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Engine;
import model.StudentBean;

@WebServlet("/Sis.do")
public class Sis extends HttpServlet
{
        private static final long serialVersionUID = 1L;

        public Sis()
        {
                super();
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

            Engine model = Engine.getInstance();

            String prefix = request.getParameter("prefix");
            String minGpa = request.getParameter("minGpa");
            String sortBy = request.getParameter("sortBy");

            Writer out = response.getWriter();
            response.setContentType("text/json");
            response.setHeader("Access-Control-Allow-Origin", "*");

            try
            {
                    List<StudentBean> result = model.doSis(prefix, minGpa, sortBy);
                    //System.out.println(result.toString());
                    //System.out.println(result.size());
                    if (result.size() == 0)
                            out.write("{\"status\":false, \"data\":\"" + "Invalid data" + "\"}");
                    else
                            out.write("{\"status\":true, \"data\":" + (new Gson()).toJson(result) + "}");

            } catch (Exception e)
            {
                    out.write("{\"status\":false, \"data\":" + (new Gson()).toJson(e.getMessage()) + "}");
            }

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
