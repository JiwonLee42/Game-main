package client.main;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomManager {
    private int id;
    private static ArrayList<GameRoom> gameRooms;
    private static AtomicInteger automicInteger;

//    static {
//        room
//    }

    public RoomManager() {

    }

    /**
     * 빈 방을 생성
     * @return GameRoom
     */
    public static GameRoom createRoom() { // 빈 방을 새로 생성
        int roomId = automicInteger.incrementAndGet(); // room id 채번
        GameRoom room = new GameRoom(roomId);
        gameRooms.add(room);
        System.out.println("빈 방이 생성되었습니다!");
        return room;
    }

    /**
     * 유저가 방을 생성
     * @param owner 방장
     * @return GameRoom
     */
    public static GameRoom createRoom(GameUser owner) {
        int roomId = automicInteger.incrementAndGet();

        GameRoom room = new GameRoom(roomId, owner);
        gameRooms.add(room);

        System.out.println("유저가 방을 만들었습니다! 해당 유저는 방장이 됩니다.");

        return room;
    }

    /**
     * 유저 리스트로 방을 생성
     * @param users 입장시킬 유저 리스트
     * @return GameRoom
     */
    public static GameRoom createRoom(ArrayList<GameUser> users) {
        int roomId = automicInteger.incrementAndGet();

        GameRoom room = new GameRoom(roomId, users);
        gameRooms.add(room);

        System.out.println("유저 리스트로 방을 생성했습니다!");

        return room;
    }

    public static GameRoom getRoom(GameRoom gameRoom){

        int idx = gameRooms.indexOf(gameRoom);

        if(idx > 0){
            return gameRooms.get(idx);
        }
        else{
            return null;
        }
    }

    /**
     * 인자로 받은 게임방을 제거
     * @param room 제거할 방
     */
    public static void removeRoom(GameRoom room) {
        room.close();
        gameRooms.remove(room);
        System.out.println("해당 게임방이 제거되었습니다!");
    }

    /**
     * 현재 존재하는 게임방들의 개수 반환
     * @return
     */
    public static int roomCount() {
        return gameRooms.size();
    }





}
