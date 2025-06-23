package cadastro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/cadUsuario")
public class CadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
		// Pegando os dados do formulário HTML pelo request
		String idNome = request.getParameter("idNome");
		String idEmail = request.getParameter("idEmail");
		int idProtocoo = Integer.parseInt(request.getParameter("idProtocoo"));
		int idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
		// Gerando o JSON para enviar ao webservice
		JSONObject json = new JSONObject();
		json.put("idNome", idNome);
		json.put("idEmail", idEmail);
		json.put("idProtocoo", idProtocoo);
		json.put("idProjeto", idProjeto);
		// Definindo o endpoint (URL) do web service
		URL url = new URL("http://localhost/cadusuario.php");
		// Criando o objeto para conexão HTTP
		HttpURLConnection conn = (HttpURLConnection)
		url.openConnection();
		// Configurando a conexão
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
		"application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);
		try {
			// Enviando o json gerado pelo request
			OutputStream os = conn.getOutputStream();
			byte[] input = json.toString().getBytes("utf-8");
			os.write(input, 0, input.length);
			// recebendo a resposta (response) do web service
			StringBuilder responseContent = new
			StringBuilder();
			InputStreamReader isr = new
			InputStreamReader(conn.getInputStream(),
			"utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				responseContent.append(line.trim());
			}
		// Enviando response para o cliente http
		response.setContentType("application/json");
		response.getWriter().write(
		responseContent.toString());
		} catch (Error e){
		System.out.println(e.getMessage());
		}
		}
		}
	}
catch(Exception e)
	{
		System.out.print(e.getMessage());
	}
}
