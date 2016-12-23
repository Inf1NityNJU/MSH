package blimpl.hotelblimpl;

import blimpl.orderblimpl.OrderBLFactory;
import blservice.orderblservice.OrderBLInfo;
import network.HotelClientNetworkImpl;
import network.HotelClientNetworkService;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.*;
import vo.FilterFlagsVO;
import vo.Hotel_DetailVO;

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
    private HotelClientNetworkService hotelClientNetworkService;
    /**
     * 用于缓存HotelVO
     */
    private Map<String, Hotel_DetailVO> cache;
    /**
     * 用于从Order模块拿到信息
     */
    private OrderBLInfo orderBLInfo;

    protected Hotel() {
        System.out.println("hotel");
        hotelClientNetworkService = new HotelClientNetworkImpl();
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
         *初始化order信息
         */
        orderBLInfo= OrderBLFactory.getOrderBLInfo();
        /*
         * 用于保存搜索结果
         */
        ArrayList<HotelPO> result = null;
        /*
         * 保存返回结果为空
         */
        ArrayList<Hotel_DetailVO> nullResult=new ArrayList<Hotel_DetailVO>();
        /*
         * 用于保存筛选条件
         */
        ArrayList<CriteriaClause> hotelCriteriaClauses = new ArrayList<CriteriaClause>();

        //按照酒店所属城市搜索酒店
        if (flags.city != null) {
            hotelCriteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("city", flags.city, QueryMethod.Full));
        }

        //按照酒店所属商圈搜索酒店
        if (flags.place != null) {
            hotelCriteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("place", flags.place, QueryMethod.Full));
        }

        //按照酒店名称搜索酒店
        if (flags.name != null) {
            hotelCriteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("name", flags.name, QueryMethod.Fuzz));
        }

        //按照酒店星级查询酒店
        if (flags.star != -1) {
            hotelCriteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("star", flags.star, QueryMethod.Full));
        }

        //按照评分区间查询酒店
        if (flags.minScore != flags.maxScore) {
            hotelCriteriaClauses.add(CriteriaClauseImpl.createRangeValueQuery("score", flags.minScore, flags.maxScore, QueryMethod.Range));
        }

        //按照预定过的情况查询酒店
        if (flags.bookedClientID != null) {
            result = new ArrayList<HotelPO>();
            for (String hotelID : orderBLInfo.getBookedHotelIDByClientID(flags.bookedClientID)) {
                result.add(hotelClientNetworkService.getHotel(hotelID));
            }
            //判断是否找不到酒店
            if (result.size() == 0) {
                return nullResult;
            }
        }

        //
        //先将列表进行合并，缩小酒店房间的搜索范围
        if (result == null) {
            result = hotelClientNetworkService.multiSearchHotel(hotelCriteriaClauses);
        } else {
            result.retainAll(hotelClientNetworkService.multiSearchHotel(hotelCriteriaClauses));
        }
        //判断是否找不到酒店
        if (result.size() == 0) {
            return nullResult;
        }


        //---下面进入按照房间搜索酒店---
        ArrayList<CriteriaClause> roomStockCriteriaClauses = new ArrayList<CriteriaClause>();
        //确定房间可用数量
        if (flags.quantity != 0) {
            roomStockCriteriaClauses.add(CriteriaClauseImpl.createRangeValueQuery("availableQuantity", flags.quantity, Integer.MAX_VALUE, QueryMethod.Range));
        }
        //确定入住日期
        if (flags.checkInDate != null) {
            roomStockCriteriaClauses.add(CriteriaClauseImpl.createRangeValueQuery("date", flags.checkInDate.toString(), flags.checkOutDate.toString(), QueryMethod.Range));
        }
        //确定房间类型
        if(flags.roomType != null){
            roomStockCriteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("roomType",flags.roomType,QueryMethod.Full));
        }

        /*
         * 用于保存满足条件的酒店ID
         */
        Set<String> hotelIDs = new HashSet<String>();

        //---下面执行按照价格筛选酒店---
        if (flags.minPrice != flags.maxPrice) {
            /*
            * 用于保存满足条件的酒店房间ID
            */
            Set<String> hotelRoomIDs = new HashSet<String>();

            //生成满足条件的酒店房间ID
            for (RoomStockPO roomStockPO : hotelClientNetworkService.multiSearchRoomStockPO(roomStockCriteriaClauses)) {
                hotelRoomIDs.add(ToolKit.generateID(roomStockPO.getHotelID(), roomStockPO.getRoomType().ordinal()));
            }

            //判断这些房间的价格是否在区间内，并将在区间内的房间所属酒店ID记录
            for (String hotelRoomID : hotelRoomIDs) {
                HotelRoomPO hotelRoomPO = hotelClientNetworkService.getRoomByID(hotelRoomID);
                if (hotelRoomPO.getPrice() <= flags.maxPrice && hotelRoomPO.getPrice() >= flags.minPrice) {
                    hotelIDs.add(hotelRoomPO.getHotelID());
                }
            }

        } else {
            for (RoomStockPO roomStockPO : hotelClientNetworkService.multiSearchRoomStockPO(roomStockCriteriaClauses)) {
                hotelIDs.add(roomStockPO.getHotelID());
            }
        }

        //将结果添加到总结果
        ArrayList<HotelPO> roomSearchResult=new ArrayList<HotelPO>();
        for (String hotelID : hotelIDs) {
            roomSearchResult.add(hotelClientNetworkService.getHotel(hotelID));
        }
        //判断是否找不到酒店
        if (roomSearchResult.size() == 0) {
            return nullResult;
        }
        if(flags.roomIsSet()) {
//            System.out.println("1asdj");
            result.retainAll(roomSearchResult);
        }

        //所有筛选任务已经完成，现在开始合并列表
        //检查是否有查询结果
        if (result.size() == 0) {
            return nullResult;
        }
        //现在得到了HotelPO的集合，现在将其转为HotelVO
        ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<Hotel_DetailVO>();
        for (HotelPO hotelPO : result) {
            //为HotelVO添加价格区间属性
            Hotel_DetailVO hotel_detailVO = poToVO(hotelPO);
            addMinAndMaxPrice(hotel_detailVO);
            //加入列表
            hotel_detailVOs.add(hotel_detailVO);
        }
        //System.out.println(hotel_detailVOs.size());
        return hotel_detailVOs;
    }

    /**
     * 通过酒店ID查找酒店
     *
     * @param hotelID 需要查找的酒店ID
     * @return 符合ID的酒店VO
     */
    public Hotel_DetailVO getHotel(String hotelID) {
        //先在cache中寻找
        Hotel_DetailVO hotelDetailVO = cache.get(hotelID);
        if (hotelDetailVO != null) {
            return addMinAndMaxPrice(hotelDetailVO);
        }
        //cache中未找到
        HotelPO hotelPO = hotelClientNetworkService.getHotel(hotelID);
        //数据库里也没找到
        if (hotelPO == null) {
            return null;
        }
        Hotel_DetailVO hotel_detailVO = poToVO(hotelPO);
        addMinAndMaxPrice(hotel_detailVO);
        //
        cache.put(hotelPO.getID(), hotel_detailVO);
        //
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
        ResultMessage resultMessage = hotelClientNetworkService.updateHotel(hotelPO);
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
        ResultMessage resultMessage = hotelClientNetworkService.addHotel(hotelPO);
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
        ArrayList<HotelPO> hotelPOs = hotelClientNetworkService.prefixSearchHotel(HOTEL_ID_NAME, first + second);
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
        ResultMessage resultMessage = hotelClientNetworkService.deleteHotel(hotelID);
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
     * 添加指定酒店的最小和最大房间价格
     *
     * @param hotel_detailVO 要添加价格对的酒店VO
     * @return 添加好价格对的酒店VO
     */
    private Hotel_DetailVO addMinAndMaxPrice(Hotel_DetailVO hotel_detailVO) {
        double min = Double.MAX_VALUE;
        double max = 0;
        for (HotelRoomPO hotelRoomPO : hotelClientNetworkService.getRoom(hotel_detailVO.ID)) {
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
        hotel_detailVO.minPrice=min;
        hotel_detailVO.maxPrice=max;
        return hotel_detailVO;
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
        HotelPO hotelPO = hotelClientNetworkService.getHotel(hotelID);
        //如果找不到酒店，返回不存在
        if (hotelPO == null) {
            return ResultMessage.NOT_EXIST;
        }
        //
        hotelPO.setScoreAmount(hotelPO.getScoreAmount() + 1);
        hotelPO.setScore(((hotelPO.getScore() * ((double) hotelPO.getScoreAmount() - 1) + score) / (double) hotelPO.getScoreAmount()));
        System.out.println(hotelPO.getScore());
        System.out.println(hotelPO.getScoreAmount());
        hotelClientNetworkService.updateHotel(hotelPO);
        //更新cache
        cache.remove(hotelID);
        cache.put(hotelID, poToVO(hotelPO));
        return ResultMessage.SUCCESS;
    }
}
