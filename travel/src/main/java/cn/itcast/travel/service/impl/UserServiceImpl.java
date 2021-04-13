package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    /**
     * 注册user
     *
     * @param user
     * @return 是否注册成功
     */
    @Override
    public boolean registUser(User user) {
        /*
        1.查询数据库中是否存在 相同name值
            名字是否可用
        2.调用方法进行注册
         */
        User db_user = userDao.findByUsername(user);
        if (db_user != null) {
            //用户名已存在，注册失败
            return false;
        }

        //设置激活状态及激活码
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());


        //进行注册
        boolean flag = userDao.save(user);
        if (flag){
            //数据库存储成功，发送以后邮件
            //发送激活邮件
            String mailText = "<a href='http://localhost:80/travel/activeUser?code="+user.getCode()+ "'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),mailText,"激活邮件");
        }


        return true;
    }

    /**
     * 激活用户
     * @param code 激活码
     * @return int 0: 激活码错误
     *             1: 激活成功
     */
    @Override
    public int active(String code) {
        User user = new User();
        user.setCode(code);
        //查询数据库中是否存在该用户
        User db_user = userDao.findByCode(user);
        if (db_user == null){
            //不存在该用户
            return 0;
        }
        //调用 dao 进行激活
        userDao.updateStatus(db_user);

        return 1;
    }

    /**
     * 登陆方法
     * @param user
     * @return int 0:登陆成功
     *             1:未注册
     *             2:
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPwd(user);
    }
}
