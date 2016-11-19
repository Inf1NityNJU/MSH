package bl.hotelbl;

import po.HotelPO;
import vo.Hotel_DetailVO;

import java.util.Comparator;

/**
 * Created by SilverNarcissus on 2016/11/19.
 */
public class PriceDescendingComparator implements Comparator<Hotel_DetailVO> {

    @Override
    public int compare(Hotel_DetailVO o1, Hotel_DetailVO o2) {
        return (int)(o2.maxPrice-o1.maxPrice);
    }
}
