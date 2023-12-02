package server.main;

import client.main.GameRoom;
import client.main.GameUser;
import client.main.RoomManager;
import client.main.member.Member;

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

                    // client가 접속할 때마다 새로운 스레드 생성
                    ReceiveThread receiveThread = new ReceiveThread(socket);
                    receiveThread.start();
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

// 게임방 들어가는 요청 처리
// client가 접속할 때마다 새로운 스레드 생성
class ReceiveThread extends Thread {

    static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
    private final Object lock = new Object();

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
    public void run() {
        // given. 회원가입한 회원
        Member m1 = new Member("julia2039", " aa", "YJ");
        GameUser user1 = new GameUser(m1);
        GameRoom room = RoomManager.createRoom(user1);
        System.out.println("게임 방" + room.getId() + "가 생성되었습니다. 초대 code는 " + room.getRoomCode() + "입니다." );

        try {
            System.out.println("try 문 안");
            // 해당 유저가 게임 방을 생성
            // broadcast(room.getRoomCode(), "방" + room.getId() + "에 접속하였습니다.");

            // client 정보(Member 객체)를 수신
//            Member requestMember = null;
//            GameUser gameUser = null;

            // 다른 유저들이 생성된 방에 접속 요청
            synchronized (lock) {

                //BufferedReader initialReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("아");
                byte[] recvBuffer = new byte[100000];
                InputStream is = socket.getInputStream();
                int readSize = is.read(recvBuffer);
                Member requestMember = toObject(recvBuffer, Member.class);


                //======
                GameUser gameUser = new GameUser(requestMember);

                System.out.println("아");
                System.out.println(requestMember.getNickName());
                //String inputMsg = in.readLine();
                //int code = Integer.parseInt(inputMsg);
                if (RoomManager.enterRoomByCode(gameUser, room.getId())) {
                    System.out.println("게임방 접속에 실패하였습니다.");
                }
                else {
                    System.out.println("게임방에 접속하였습니다.");
                }

                while (true) {
                    requestMember = receive(socket);
                    if (requestMember == null) {
                        // 클라이언트가 연결을 끊었을 때
                        break;
                    }
                    gameUser = new GameUser(requestMember);
                    //int code = Integer.parseInt(inputMsg);
                    if (RoomManager.enterRoomByCode(gameUser, room.getId())) {
                        System.out.println("게임방 접속에 실패하였습니다.");
                    }
                    else {
                        System.out.println("게임방에 접속하였습니다.");
                    }
                }

                lock.notify();
            }

        } catch (IOException e) {
//            System.out.println("게임 방 접속 실패");
            e.printStackTrace();
        } finally {
            broadcast(room.getRoomCode(), "접속 성공");
        }
        System.out.println("끝");

    }

    public static synchronized Member receive(Socket socket) throws IOException {
        System.out.println("리시브");
        //수신 버퍼의 최대 사이즈 지정
        int maxBufferSize = 1024;
        //버퍼 생성
        byte[] recvBuffer = new byte[maxBufferSize];

        System.out.println("왜그래11");
        InputStream is = socket.getInputStream();
        System.out.println("왜그래1");
        //버퍼(recvBuffer) 인자로 넣어서 받음. 반환 값은 받아온 size
        int nReadSize = is.read(recvBuffer);


        System.out.println("왜그래");
        System.out.println(nReadSize);
        Member requestMember = null;
        //받아온 값이 0보다 클때
        if (nReadSize > 0) {
            //받아온 byte를 Object로 변환
            requestMember = toObject(recvBuffer, Member.class);
            // 확인을 위해 출력
            System.out.println(requestMember.getNickName());
        }

        return requestMember;
    }

    private void broadcast(int roomCode, String msg) {
        for (PrintWriter out: list) {
            out.println(msg);
            out.flush();
        }
    }

    // 역직렬화, 클라이언트로부터 받은 byte array를 Member객체로.
    public static <T> T toObject(byte[] bytes, Class<T> type) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return type.cast(obj);
    }
}
