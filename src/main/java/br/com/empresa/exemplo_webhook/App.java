package br.com.empresa.exemplo_webhook;


public class App 
{
    public static void main( String[] args )
    {
        Auth authProd = new Auth();
    	String access_token;
    	
    	Webhook webhook = new Webhook();
    	String result_doWebHook="";
    	String result_getWebHookChave="";
    	String result_getWebHookInicioFim="";
    	String result_deleteWebHookChave="";

    	//----------------------------------------------------
    	//Autenticar
    	access_token = authProd.geraToken();
    	System.out.println("access_token = "+access_token);
    	//----------------------------------------------------

    	//PUT Configurar doWebHook informar a minha url
    	result_doWebHook = webhook.doWebHook(access_token);
    	System.out.println("#Resultado doWebHook = "+result_doWebHook);

    	//GET obter com chave getWebHookChave
    	try {
    		Thread.sleep(1000);
    		result_getWebHookChave = webhook.getWebHookChave(access_token);
        	System.out.println("#Resultado getWebHookChave = "+result_getWebHookChave);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	//GET obter com período getWebHookInicioFim
    	result_getWebHookInicioFim = webhook.getWebHookInicioFim(access_token);
    	System.out.println("#Resultado getWebHookInicioFim = "+result_getWebHookInicioFim);

    	//DELETE deletar webHook
    	result_deleteWebHookChave = webhook.deleteWebHookChave(access_token);
    	System.out.println("#Resultado result_deleteWebHookChave = "+result_deleteWebHookChave);
    }
}
