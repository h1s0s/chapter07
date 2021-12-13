package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		// 2. TCP 소켓 프로그래밍 - 멀티스레드

		//3. ~클라이언트 소켓 생성~
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("====================");
		
		System.out.println("[서버에 연결을 요청합니다.]");
		
		//4. ~connect하기~
		socket.connect(new InetSocketAddress("172.30.1.51", 10001));
		//유동아이피를 적음
		
		System.out.println("서버에 연결되었습니다.");
		
		//문자열을 서버로 보내자
		//소켓이 가지고 있는 메소드를 사용하여 보낸다.
		OutputStream os = socket.getOutputStream();//주스트림
		OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");//번역해주는애
		BufferedWriter bw = new BufferedWriter(osw);//출력 높여주는 애
		
		//메세지를 받아주는 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//Scanner (키보드 입력용)
		Scanner sc = new Scanner(System.in);
		 //InputStream in = System.in;
		 //InputStreamReader sisr = new InputStreamReader(in);
		 //BufferedReader sbr = new BufferedReader(sisr);
		
		//메세지 보내기
		while(true) {
			//String str = sbr.readLine();
			String str = sc.nextLine();
			if("/q".equals(str)) { //탈출조건
				//거꾸로 써준 이유, str에 null값이 오게 되면
				//예외처리가 생김.(null값으로 인한 예외)
				System.out.println("종료키 입력");
				break;
			}
			bw.write(str);
			bw.newLine();
			bw.flush();//보내주는애, 굳이 안써줘도 되지만 안쓰면 오류날때도 있기때문에 그냥 써줌
		
			//메세지 받기
			String remsg = br.readLine();
			System.out.println("server:"+remsg);
		}
		
		System.out.println("====================");
		System.out.println("<클라이언트 종료>");
		sc.close();
		bw.close();
		socket.close();
	}
}
