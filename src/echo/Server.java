package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {//예외처리를 위한 경고문을 제거해줌.
		// 2. TCP 소켓 프로그래밍 - 멀티스레드

		//1. ~서버 소켓을 메모리에 올린다~
		ServerSocket serverSocket = new ServerSocket();
		//2. ~포트를 만든다(바인드)
		serverSocket.bind(new InetSocketAddress("172.30.1.51", 10001));
		
		System.out.println("<서버시작>");
		System.out.println("================================");
		System.out.println("[연결을 기다리고 있습니다.]");
		
		//5. ~accept~ : connect가 오면 소켓을 쓰레드로 만듬 이걸 반복
		while(true) {
			Socket socket = serverSocket.accept();
			Thread thread = new ServerThread(socket);
			thread.start();
			/*
			if() {
				break;
			}
			*/
		}
		/*
		System.out.println("====================");
		System.out.println("<서버종료>");

		serverSocket.close();
		*/	
		
	}
}