package usercase.service;

import com.sun.org.apache.xpath.internal.objects.XString;
import usercase.domain.PageBean;
import usercase.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户业务操作
 */
public interface UserService {

    /**
     * 返回数据库中的所有数据
     * @return
     */
    public List<User> findAll();

    /**
     * 查询数据库中的 特定user 信息
     * @param user
     * @return
     */
    public User findUser(User user);

    /**
     * 增加 user
     * @param addUser
     */
    public boolean addUser(User addUser);

    /**
     * 删除指定的 user 数据
     * @param delUser
     * @return
     */
    public boolean delUser(User delUser);

    /**
     * 更改数据库中指定 user 的值
     * @param updateUser
     * @return
     */
    public boolean updateUser(User updateUser);

    /**
     * 删除选中的 数据
     * 根据 ids
     * @param ids
     */
    public void delCheckedUser(String[] ids);

    /**
     * 分页查询
     * @param page
     * @param count
     * @return
     */
    public List<User> findByPage(int page, int count, Map<String,String[]> condition);

    /**
     * 查询总记录个数
     *
     * @return
     */
    public PageBean getPageBean(int count,Map<String,String[]> condition);
}
