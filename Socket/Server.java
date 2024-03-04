import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
	Socket socket;

	BufferedReader br; // reading
	PrintWriter out; // writing

	public Server() {

		try {

			serverSocket = new ServerSocket(9014);
			System.out.println("Server is ready to accept connection.....");

			System.out.println("Waiting.....");
			socket = serverSocket.accept();

			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream());

			startReading();
			startWriting();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startReading() {

		Runnable r1 = () -> {

			System.out.println("reader starter...");

			while (true) {
				try {

					String str = br.readLine();
					if (str.equals("exit")) {
						System.out.println("client terminated the chat.");
						break;
					}
					System.out.println("Client : " + str);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		new Thread(r1).start();
	}

	public void startWriting() {
		Runnable r2 = () -> {

			System.out.println("Writer started...");
			while (true) {
				try {

					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String str = reader.readLine();
					out.println(str);
					out.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r2).start();
	}


    public static void main(String[] args) {
        
		try {

			System.out.println("-----###---- Server Start ----###-----");

			new Server();

		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}