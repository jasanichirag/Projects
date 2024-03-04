import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    

    Socket socket;

	BufferedReader br;
	PrintWriter out;

	public Client() {

		try {

			System.out.println("Sending request to server....");
			socket = new Socket("127.0.0.1", 9014);
			System.out.println("Connection done...");

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

			System.out.println("Reader started...");

			while (true) {

				try {
					String msg = br.readLine();
					if (msg.equals("exit")) {
						System.out.println("Server terminated the chat....");

						break;
					}
					System.out.println("Server : " + msg);

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

					BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
					String str = br1.readLine();
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
        System.out.println("This is client....");
		new Client();
    }
}
