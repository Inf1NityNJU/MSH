package bl.hotelbl;

import po.HotelPO;
import vo.Hotel_DetailVO;

import java.util.Comparator;

/**
 * Created by SilverNarcissus on 2016/11/19.
 */
public class PriceAscendingComparator implements Comparator<Hotel_DetailVO> {

    @Override
    public int compare(Hotel_DetailVO o1, Hotel_DetailVO o2) {
        return (int)(o1.minPrice-o2.minPrice);
    }
}
