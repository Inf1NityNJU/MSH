package blimpl.hotelblimpl;

import vo.Hotel_DetailVO;

import java.util.Comparator;

/**
 * Created by SilverNarcissus on 2016/11/19.
 * All Done on 16/11/26
 */
public class PriceAscendingComparator implements Comparator<Hotel_DetailVO> {

    @Override
    public int compare(Hotel_DetailVO o1, Hotel_DetailVO o2) {
        if(o1.minPrice<o2.minPrice){
            return -1;
        }
        if(o1.minPrice>o2.minPrice){
            return 1;
        }
        return 0;
    }
}
