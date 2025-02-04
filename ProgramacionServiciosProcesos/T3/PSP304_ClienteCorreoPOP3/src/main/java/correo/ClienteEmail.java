package correo;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;

public class ClienteEmail {

	private static final String HOST = "damplaya.hopto.org";
	private static final String USERNAME = "oscar";
	private static final String PASSWORD = "usuario";
	private static final int PORT = 110;

	public static void main(String[] args) {
		Properties properties = new Properties();

		// Propiedades del servidor de correo
		properties.put("mail.store.protocol", "pop3"); // Protocolo POP3
		properties.put("mail.pop3.host", HOST); // Servidor de correo entrante
		properties.put("mail.pop3.port", String.valueOf(PORT)); // Sin TLS
		properties.put("mail.pop3.auth", "true"); // Requiere autenticación

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		try {
			// Conexión al servidor de correo
			Store store = session.getStore("pop3");
			store.connect(HOST, USERNAME, PASSWORD);

			// Acceso a la carpeta INBOX
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Lectura de mensajes
			Message[] messages = inbox.getMessages();
			for (Message message : messages) {
				System.out.println("De: " + InternetAddress.toString(message.getFrom()));
				System.out.println("Asunto: " + message.getSubject());
				System.out.println("Contenido: " + message.getContent().toString());
				System.out.println("---------------------------------------");
			}

			// Cierre de la conexión
			inbox.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
