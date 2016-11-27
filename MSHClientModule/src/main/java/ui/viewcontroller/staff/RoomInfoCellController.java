package ui.viewcontroller.staff;

import ui.viewcontroller.client.ClientOrderListViewController;
import vo.HotelRoomVO;

/**
 * Created by SilverNarcissus on 2016/11/27.
 */
public class RoomInfoCellController {
    private RoomInfoListViewController roomInfoListViewController;

    public void setRoomInfoListViewController(RoomInfoListViewController roomInfoListViewController) {
        this.roomInfoListViewController = roomInfoListViewController;
    }
    public void setRoom(HotelRoomVO hotelRoomVO){
        System.out.println("OK!");
    }
}
