package bl.hotelbl;

import util.HotelNotFoundException;
import util.InfoInvalidException;
import util.ResultMessage;
import vo.FilterFlagsVO;
import vo.Hotel_DetailVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/30.
 */
public class Hotel {


    /**
     * 通过指定的筛选条件筛选酒店
     * @param flags
     * @return 符合条件的酒店VO列表
     */
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags){
        return  null;
    }

    /**
     * 通过酒店ID查找酒店
     * @param hotelID
     * @return 符合ID的酒店VO
     * @throws HotelNotFoundException
     */
    public Hotel_DetailVO getHotel(String hotelID) throws HotelNotFoundException {
        return  null;
    }

    /**
     * 修改指定酒店信息
     * @param hvo
     * @return 修改成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage updateHotelInfo(Hotel_DetailVO hvo) throws InfoInvalidException {
        return null;
    }

    /**
     * 添加酒店信息
     * @param hvo
     * @return 添加成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage addHotel(Hotel_DetailVO hvo) throws InfoInvalidException {
        return  null;
    }

    /**
     * 删除酒店信息
     * @param hotelID
     * @return 删除成功与否
     * @throws HotelNotFoundException
     */
    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException {
        return  null;
    }

}
