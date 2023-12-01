package server.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSocketServer {
    public static void main(String[] args) throws IOException {
        TestSocketServer socketServer = new TestSocketServer();
        socketServer.run();

//        System.out.println(RoomManager.createRoom());
//        System.out.println(RoomManager.createRoom());
//
//        Thread thread1 = new Thread(() -> System.out.println(RoomManager.createRoom()));
//        Thread thread2 = new Thread(() -> System.out.println(RoomManager.createRoom()));
//
//        thread1.start();
//        thread2.start();

    }

    public void run() throws IOException {
        try {
            int port = 18501;
            ServerSocket server = new ServerSocket(port);

            while(true) {
                System.out.println("--- 접속 대기중 ---");
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress() + "로부터의 연결 요청이 들어옴");


                InputStream is = socket.getInputStream();
                byte[] bytes = new byte[1024];

                int readByteCount = is.read(bytes);

                if (readByteCount > 0) {
                    System.out.println("클라이언트로부터 데이터 수신");
                    sendData(bytes, socket);

                    // client가 요청할 때마다 새로운 스레드 생성
                    ReceiveThread receiveThread = new ReceiveThread(socket);
                    receiveThread.run();
                }
                System.out.println("**** 재전송 완료 ****");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(byte[] bytes, Socket socket) {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(bytes);
            os.flush();
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}

// client가 요청할 때마다 새로운 스레드 생성
class ReceiveThread extends Thread {

    static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());

    Socket socket = null;
    BufferedReader in = null;
    PrintWriter out = null;

    public ReceiveThread (Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            list.add(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {


    }

}
