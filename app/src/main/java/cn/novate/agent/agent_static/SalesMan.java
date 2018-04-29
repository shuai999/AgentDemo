package cn.novate.agent.agent_static;

import cn.novate.agent.agent_static.IBank;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/4/29 18:34
 * Version 1.0
 * Params:
 * Description:    银行业务员
*/

public class SalesMan implements IBank {

    private IBank man ;

    public SalesMan(IBank man){
        this.man = man ;
    }


    @Override
    public void applyBank() {
        if (man != null) {
            // 买饭时别人可以帮你砍价
            System.out.println("银行业务员先搞完一些流程");

            // 调用我们办卡人的方法：比如让你输入密码等其他东西
            // 钱是你掏
            man.applyBank();

            // 等你输入完密码之后，提示你办理完毕
            System.out.println("办理完毕");
        }
    }
}
