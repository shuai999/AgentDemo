package cn.novate.agent.agent_static;

import cn.novate.agent.agent_static.IBank;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/4/29 18:34
 * Version 1.0
 * Params:
 * Description:    办卡的人
*/

public class Man implements IBank {


    @Override
    public void applyBank() {
        System.out.println("办卡");
    }
}
