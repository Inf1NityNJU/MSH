package blservice.userblservice;

import util.ResultMessage;
import vo.LevelVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public interface LevelService {

    /**
     * 增加一条等级信息
     *
     * @return
     */
    public ResultMessage addLevel(LevelVO levelVO);

    /**
     * 更新一条等级信息
     *
     * @return
     */
    public ResultMessage updateLevel(LevelVO levelVO);

    /**
     * 删除一条等级信息
     *
     * @return
     */
    public ResultMessage deleteLevel(String ID);

    /**
     * 等到一条等级信息
     *
     * @param ID
     * @return
     */
    public LevelVO getLevel(String ID);
}
