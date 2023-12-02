package client.main.socket;

import client.main.member.Member;

import java.io.*;
import java.net.Socket;

public class MultiClient {

    public static void main(String[] args) {
        MultiClient multiClient = new MultiClient();
        multiClient.start();
    }

    public void start() {
        Socket socket = null;
        BufferedReader in = null;
        try {
            socket = new Socket("localhost" , 18501);
            System.out.println("서버와 연결되었습니다.");


            //서버에 회원 객체 보내기
            send(socket);



        } catch (IOException e) {
            System.out.println("서버 접속 끊김");
        }
    }

    public static void send(Socket socket) throws IOException {
        // given. Member 객체 생성. 테스트용, 연결 필요
        Member testM1 = new Member("kkk", "111", "Kim");


        //생성한 Member 객체를 byte array로 변환
        byte[] data = toByteArray(testM1);
        //서버로 내보내기 위한 출력 스트림 뚫음
        OutputStream os = socket.getOutputStream();
        //출력 스트림에 데이터 쓰기
        os.write(data);
        //서버로 송신
        os.flush();

        System.out.println("회원 객체 보냄");
    }

    // 소켓을 통해 Server에 보내기 위해 Object를 byte로 변환
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            bos.close();
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            // TODO: Handle the exception
            ex.printStackTrace();
        }
        return bytes;
    }
}
