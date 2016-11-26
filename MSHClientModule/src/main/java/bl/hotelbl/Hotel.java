package bl.hotelbl;

import blservice.orderblservice.OrderHotelInfo;
import dataimpl.hoteldataimpl.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.DateUtil;
import util.ResultMessage;
import vo.FilterFlagsVO;
import vo.HotelRoomVO;
import vo.Hotel_DetailVO;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Silver Narcissus on 16/10/30.
 * All Done on 16/11/26
 */
public class Hotel {
    private final static String HOTEL_ID_NAME = "ID";
    /**
     * 用于访问data层的接口
     */
    private HotelDataService hotelDataService;
    /**
     * 用于缓存HotelVO
     */
    private Map<String, Hotel_DetailVO> cache;
    /**
     * 用于从Order模块拿到信息
     */
    private OrderHotelInfo orderHotelInfo;

    protected Hotel() {
        hotelDataService = HotelDataServiceFactory.getHotelDataService();
        cache = new HashMap<String, Hotel_DetailVO>();
    }

    /**
     * 通过指定的筛选条件筛选酒店
     *
     * @param flags 筛选条件
     * @return 符合条件的酒店列表<br>
     * 如果找不到符合条件的酒店，则返回null
     */
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags) {
        /*
         * 用于保存搜索结果
         */
        ArrayList<ArrayList<HotelPO>> result = new ArrayList<ArrayList<HotelPO>>();
        //按照酒店所属城市搜索酒店
        if (flags.city != null) {
            ArrayList<HotelPO> cityResult = hotelDataService.fullSearchHotel("city", flags.city);
            //System.out.println(cityResult.size());
            //判断是否找不到酒店
            if (cityResult.size() == 0) {
                return null;
            }
            result.add(cityResult);
        }


        //按照酒店所属商圈搜索酒店
        if (flags.place != null) {
            ArrayList<HotelPO> placeResult = hotelDataService.fullSearchHotel("place", flags.place);
            //判断是否找不到酒店
            if (placeResult.size() == 0) {
                return null;
            }
            result.add(placeResult);
        }


        //按照酒店名称搜索酒店
        if (flags.name != null) {
            ArrayList<HotelPO> nameResult = hotelDataService.fuzzySearchHotel("name", flags.name);
            //判断是否找不到酒店
            if (nameResult.size() == 0) {
                return null;
            }
            result.add(nameResult);
        }


        //按照房间搜索酒店
        if (flags.roomType != null) {
            ArrayList<HotelRoomPO> hotelRoom = hotelDataService.fullSearchHotelRoom("roomType", flags.roomType);
            //进一步确定价格
            if (flags.minPrice != flags.maxPrice) {
                for (int i = 0; i < hotelRoom.size(); i++) {
                    if (hotelRoom.get(i).getPrice() < flags.minPrice || hotelRoom.get(i).getPrice() > flags.maxPrice) {
                        hotelRoom.remove(i);
                        i--;
                    }
                }
            }
            //确定预订日期
            if (flags.checkInDate != null) {
                for (int i = 0; i < hotelRoom.size(); i++) {
                    HotelRoomPO hotelRoomPO = hotelRoom.get(i);
                    /*
                     * 保存要查询的房屋库存
                     */
                    ArrayList<RoomStockPO> roomStockPOs = hotelDataService.getRoomStock(hotelRoomPO.getID());
                    //得到要查询的房屋库存
                    for (int a = 0; a < roomStockPOs.size(); a++) {
                        DateUtil dateUtil = new DateUtil(roomStockPOs.get(i).getDate());
                        if (!dateUtil.isInRange(flags.checkInDate, flags.checkOutDate)) {
                            roomStockPOs.remove(a);
                            a--;
                        }
                    }
                    //如果没有符合的预订区间，则返回
                    if (roomStockPOs.size()==0){
                        return null;
                    }
                    //如果不符合数量要求，则删除
                    if (!HotelRoom.checkChangeIsValidByPO(roomStockPOs, flags.quantity)) {
                        hotelRoom.remove(i);
                    }
                }
            }
            //
            ArrayList<HotelPO> roomResult = new ArrayList<HotelPO>();
            for (HotelRoomPO hotelRoomPO : hotelRoom) {
                roomResult.add(hotelDataService.getHotel(hotelRoomPO.getHotelID()));
            }
            //判断是否找不到酒店
            if (roomResult.size() == 0) {
                return null;
            }
            result.add(roomResult);
        }
        //按照酒店星级查询酒店
        if (flags.star != -1) {
            ArrayList<HotelPO> starResult = hotelDataService.fullSearchHotel("star", flags.star);
            //判断是否找不到酒店
            if (starResult.size() == 0) {
                return null;
            }
            result.add(starResult);
        }
        //

        //按照评分区间查询酒店
        if (flags.minScore != flags.maxScore) {
            ArrayList<HotelPO> scoreResult = hotelDataService.rangeSearchHotel("score", flags.minScore, flags.maxScore);
            //判断是否找不到酒店
            if (scoreResult.size() == 0) {
                return null;
            }
            result.add(scoreResult);
        }

        //按照预定过的情况查询酒店
        if (flags.bookedClientID != null) {
            //保存该项结果
            ArrayList<HotelPO> bookedResult = new ArrayList<HotelPO>();
            //
            for (String hotelID : orderHotelInfo.getBookedHotelIDByClientID(flags.bookedClientID)) {
                bookedResult.add(hotelDataService.getHotel(hotelID));
            }
            //判断是否找不到酒店
            if (bookedResult.size() == 0) {
                return null;
            }
            result.add(bookedResult);
        }

        //所有筛选任务已经完成，现在开始合并列表
        //检查是否有查询结果
        if (result.size() == 0) {
            return null;
        }
        //
        ArrayList<HotelPO> hotelPOs = result.get(0);
        //System.out.println(hotelPOs.size());
        for (ArrayList<HotelPO> array : result) {
            hotelPOs.retainAll(array);
        }
        System.out.println(hotelPOs.size());
        //判断是否有查询结果
        if (hotelPOs.size() == 0) {
            return null;
        }
        //现在得到了HotelPO的集合，现在将其转为HotelVO
        ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<Hotel_DetailVO>();
        for (HotelPO hotelPO : hotelPOs) {
            //为HotelVO添加价格区间属性
            PricePair pricePair = findMinAndMaxPrice(hotelPO.getID());
            Hotel_DetailVO hotel_detailVO = poToVO(hotelPO);
            hotel_detailVO.minPrice = pricePair.minPrice;
            hotel_detailVO.maxPrice = pricePair.maxPrice;
            //加入列表
            hotel_detailVOs.add(hotel_detailVO);
        }
        System.out.println(hotel_detailVOs.size());
        return hotel_detailVOs;
    }

    /**
     * 通过酒店ID查找酒店
     *
     * @param hotelID 需要查找的酒店ID
     * @return 符合ID的酒店VO
     */
    public Hotel_DetailVO getHotel(String hotelID) {
        PricePair pricePair = findMinAndMaxPrice(hotelID);
        //先在cache中寻找
        Hotel_DetailVO hotelDetailVO = cache.get(hotelID);
        if (hotelDetailVO != null) {
            hotelDetailVO.minPrice = pricePair.minPrice;
            hotelDetailVO.maxPrice = pricePair.maxPrice;
            return hotelDetailVO;
        }
        //cache中未找到
        HotelPO hotelPO = hotelDataService.getHotel(hotelID);
        //没找到
        if (hotelPO == null) {
            return null;
        }
        Hotel_DetailVO hotel_detailVO = poToVO(hotelPO);
        //
        cache.put(hotelPO.getID(), hotel_detailVO);
        //
        hotel_detailVO.maxPrice = pricePair.maxPrice;
        hotel_detailVO.minPrice = pricePair.minPrice;
        return hotel_detailVO;
    }

    /**
     * 修改指定酒店信息
     *
     * @param hvo 新的酒店信息
     * @return 修改成功与否
     */
    public ResultMessage updateHotel(Hotel_DetailVO hvo) {
        HotelPO hotelPO = voToPO(hvo);
        ResultMessage resultMessage = hotelDataService.updateHotel(hotelPO);
        //更新cache
        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            cache.remove(hotelPO.getID());
            cache.put(hvo.ID, hvo);
        }
        return resultMessage;
    }

    /**
     * 添加酒店信息
     *
     * @param hvo 新的酒店信息
     * @return 添加成功与否
     */
    public ResultMessage addHotel(Hotel_DetailVO hvo) {
        HotelPO hotelPO = voToPO(hvo);
        generateHotelID(hotelPO);
        //
        ResultMessage resultMessage = hotelDataService.addHotel(hotelPO);
        //更新cache
        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            cache.put(hvo.ID, hvo);
        }
        return resultMessage;
    }

    /**
     * 生成酒店ID
     *
     * @param hotelPO 需要生成ID的酒店
     */
    private void generateHotelID(HotelPO hotelPO) {
        String first = String.valueOf(hotelPO.getCity().ordinal());
        String second = String.valueOf(hotelPO.getPlace().ordinal());
        if (first.length() == 1) {
            first = "0" + first;
        }
        if (second.length() == 1) {
            second = "0" + second;
        }
        //顺序递增酒店编号
        ArrayList<HotelPO> hotelPOs = hotelDataService.prefixSearchHotel(HOTEL_ID_NAME, first + second);
        if (hotelPOs.size() == 0) {
            //如果没有该编号下的酒店，则生成编号0000
            hotelPO.setID(first + second + "0000");
        } else {
            int ID = Integer.parseInt(hotelPOs.get(hotelPOs.size() - 1).getID().substring(4));
            ID++;
            String number = String.valueOf(ID);
            int length = 4 - number.length();
            for (int i = 0; i < length; i++) {
                number = "0" + number;
            }
            hotelPO.setID(first + second + number);
        }
        //
    }

    /**
     * 删除酒店信息
     *
     * @param hotelID 需要删除的酒店ID
     * @return 删除成功与否
     */
    public ResultMessage deleteHotel(String hotelID) {
        ResultMessage resultMessage = hotelDataService.deleteHotel(hotelID);
        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            cache.remove(hotelID);
        }
        return resultMessage;
    }

    /**
     * 将hotelVO转换为hotelPO
     *
     * @param hotel_detailVO 需要转换的HotelVO
     * @return hotelPO
     */
    private HotelPO voToPO(Hotel_DetailVO hotel_detailVO) {
        return hotel_detailVO == null ? null : new HotelPO(hotel_detailVO.ID
                , hotel_detailVO.name
                , hotel_detailVO.city
                , hotel_detailVO.place
                , hotel_detailVO.address
                , hotel_detailVO.star
                , hotel_detailVO.introduction
                , hotel_detailVO.facilities
                , hotel_detailVO.score
                , hotel_detailVO.scoreAmount);
    }


    /**
     * 将hotelPO转换为hotelVO
     *
     * @param hotelPO 需要转换的HotelPO
     * @return hotel_DetailVO
     */
    private Hotel_DetailVO poToVO(HotelPO hotelPO) {
        return hotelPO == null ? null : new Hotel_DetailVO(hotelPO.getID()
                , hotelPO.getName()
                , hotelPO.getCity()
                , hotelPO.getAddress()
                , hotelPO.getPlace()
                , hotelPO.getStar()
                , hotelPO.getIntroduction()
                , hotelPO.getFacilities()
                , null
                , hotelPO.getScore()
                , hotelPO.getScoreAmount());
    }

    /**
     * 寻找指定酒店的最小和最大房间价格
     *
     * @param hotelID 指定酒店的ID
     * @return 最大最小价格对
     */
    private PricePair findMinAndMaxPrice(String hotelID) {
        double min = Double.MAX_VALUE;
        double max = 0;
        for (HotelRoomPO hotelRoomPO : hotelDataService.getRoom(hotelID)) {
            if (min > hotelRoomPO.getPrice()) {
                min = hotelRoomPO.getPrice();
            }
            if (max < hotelRoomPO.getPrice()) {
                max = hotelRoomPO.getPrice();
            }
        }
        //
        if (min == Double.MAX_VALUE) {
            min = 0;
        }
        //
        return new PricePair(min, max);
    }

    /**
     * 按照价格升序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> priceAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new PriceAscendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照价格降序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> priceDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new PriceDescendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照星级升序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> starAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new StarAscendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照星级降序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> starDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new StarDescendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照评分升序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> scoreAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new ScoreAscendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照评分降序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> scoreDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new ScoreDescendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 增加酒店评分
     *
     * @param score   本次订单的评分
     * @param hotelID 需要增加评分的目标酒店
     * @return 如果添加成功，返回SUCCESS<br>
     * 如果酒店不存在，返回NOT_EXIST
     */
    public ResultMessage addScore(double score, String hotelID) {
        HotelPO hotelPO = hotelDataService.getHotel(hotelID);
        //如果找不到酒店，返回不存在
        if (hotelPO == null) {
            return ResultMessage.NOT_EXIST;
        }
        //
        hotelPO.setScoreAmount(hotelPO.getScoreAmount() + 1);
        hotelPO.setScore((hotelPO.getScore() + score) / hotelPO.getScoreAmount());
        hotelDataService.updateHotel(hotelPO);
        //更新cache
        cache.remove(hotelID);
        cache.put(hotelID, poToVO(hotelPO));
        return ResultMessage.SUCCESS;
    }
}
