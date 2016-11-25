package blservice.orderblservice;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 2016/11/25.
 */
public interface OrderHotelInfo {

    /**
     * 通过客户ID得到其预订过的酒店ID<br>
     * 注：接受方认为这些ID不重复
     *
     * @param clientID 客户ID
     * @return 客户预定过的酒店ID
     */
    public ArrayList<String> getBookedHotelIDByClientID(String clientID);

}
