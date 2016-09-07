package localpost.nodejsapi.nodejsapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * 
 * This will add people to our database
 *
 *	Brendan Kroneberger
 *			2016-09-06
 *
 */
public class add {
	
	//the URL of the API we want to connect to
	
	protected static String endpoint = "http://localhost:1337/employee";
	
	//the character set to use the encoding URL parameters
	
	protected static String charset = "UTF-8";
	
	
	public static void main(String[] args) {
		
		try{
			//new user's first name
			String firstName = "Brendan";
			//new user's last name
			String lastName = "Kroneberger";
			//new user's email address
			String email = "brendan.k@theironyard.com";
			//new user's home phone number
			String homePhone = "410-558-1554";
			//new user's cell phone number
			String cellPhone = "443-558-1554";
			//new user's password
			String password = "Passw0rd";
			//new user's id
			String active = "1";
			
			//creates the url parameters as a string encoding them with the defined charset		
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone,charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset));
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL nodejsapi = new URL(endpoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) nodejsapi.openConnection();
			connection.setRequestMethod("POST");
			
			//if we did not get a 201 (success) throw an exception
			if (connection.getResponseCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it return null meaning there are no more lines
			while (br.readLine() !=null){
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
		}catch (MalformedURLException e ) {
			
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}