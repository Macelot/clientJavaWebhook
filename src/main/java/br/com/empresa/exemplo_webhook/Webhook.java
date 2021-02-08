package br.com.empresa.exemplo_webhook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Webhook {
	
	public String chave;
	
	public Webhook() {
		System.setProperty("javax.net.ssl.keyStore", "prod123456.p12");
		chave = "colocar sua chave";
	}
	
	public String doWebHook(String token) {
		String payload;
		payload = "{\r\n"
				+ "	\"webhookUrl\": \"https://www. colocar seu end point/\"\r\n"
				+ "}";
    	StringBuilder responseBuilder = new StringBuilder();
    	HttpsURLConnection conn = null;

    	try {
    		URL url = new URL ("https://api-pix.gerencianet.com.br/v2/webhook/"+chave); //Para ambiente de Produção              
    		conn = (HttpsURLConnection)url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("PUT");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer "+ token);
	        OutputStream os = conn.getOutputStream();
	        os.write(payload.getBytes());
	        os.flush();   
	        //showDebug(url,conn);
	        if(conn.getResponseCode()==400) {
	        	responseBuilder.append("Não foi possível configurar o webhook");
	        	return responseBuilder.toString();
	        }
	        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(reader);
	        String response;        
	        while ((response = br.readLine()) != null) {
	          responseBuilder.append(response);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseBuilder.toString();
	}
	
	public String getWebHookChave(String token) {
    	StringBuilder responseBuilder = new StringBuilder();
    	HttpsURLConnection conn = null;
    	try {
    		URL url = new URL ("https://api-pix.gerencianet.com.br/v2/webhook/"+chave); //Para ambiente de Produção              
	        conn = (HttpsURLConnection)url.openConnection();
	        conn.setConnectTimeout(5000);
	        conn.setDoOutput(true);
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer "+ token);
	        //showDebug(url,conn);
	        if(conn.getResponseCode()==400) {
	        	responseBuilder.append("Não foi possível obter dados do webhook");
	        	return responseBuilder.toString();
	        }
	        
	        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(reader);

	        String response;        
	        while ((response = br.readLine()) != null) {
	          responseBuilder.append(response);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseBuilder.toString();
	}
	
	public String getWebHookInicioFim(String token) {
    	StringBuilder responseBuilder = new StringBuilder();
    	HttpsURLConnection conn = null;
    	try {
	        String periodo = "?inicio=2021-02-01T08:00:00Z&fim=2021-02-08T23:59:59Z";
    		URL url = new URL ("https://api-pix.gerencianet.com.br/v2/webhook/"+chave+periodo); //Para ambiente de Produção              
    		conn = (HttpsURLConnection)url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer "+ token);
	        //showDebug(url,conn);
	        if(conn.getResponseCode()==400) {
	        	responseBuilder.append("Não foi possível obter dados do webhook");
	        	return responseBuilder.toString();
	        }
	        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(reader);

	        String response;        
	        while ((response = br.readLine()) != null) {
	          responseBuilder.append(response);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseBuilder.toString();
	}
	
	public String deleteWebHookChave(String token) {
    	StringBuilder responseBuilder = new StringBuilder();
    	HttpsURLConnection conn = null;

    	try {
    		URL url = new URL ("https://api-pix.gerencianet.com.br/v2/webhook/"+chave); //Para ambiente de Produção              
    		conn = (HttpsURLConnection)url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("DELETE");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer "+ token);  
	        //showDebug(url,conn);
	        if(conn.getResponseCode()==400) {
	        	responseBuilder.append("Não foi possível deletar o webhook");
	        	return responseBuilder.toString();
	        }
	        if(conn.getResponseCode()==204) {
	        	responseBuilder.append("Webhook para notificações Pix foi cancelado");
	        	return responseBuilder.toString();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseBuilder.toString();	
	}
	
	public void showDebug(URL url, HttpsURLConnection conn ) {
		System.out.println("URL-->"+url);
		try {
			System.out.println("RESP->"+conn.getResponseCode());
	        System.out.println("MESS->"+conn.getResponseMessage());
	        System.out.println("1->"+conn.getRequestProperty("Content-Type"));
	        System.out.println("2->"+conn.getRequestProperty("Authorization"));
	        System.out.println("3->"+conn.getRequestMethod());
	        System.out.println("4->"+conn.getHeaderFields());
	        System.out.println("5->"+conn.getURL());
		} catch (Exception e) {
			e.printStackTrace();
		}  	
	}
}