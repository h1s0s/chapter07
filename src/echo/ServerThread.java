package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	//필드
	private Socket socket;
	
	//생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	//메소드 g/s
	
	//메소드 일반
	@Override
	public void run() {
		System.out.println("[클라이언트에 연결되었습니다]");
		
		try {
			//메세지를 받아주는 스트림
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			//메세지를 보내주는 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			//메세지 받기
			while(true) {
				String msg = br.readLine();
				if(msg == null) {
					System.out.println("클라이언트 종료키 입력");
					break;
				}
				System.out.println("받은메세지:"+msg);
				//메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}
			br.close();
			bw.close();
			socket.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
