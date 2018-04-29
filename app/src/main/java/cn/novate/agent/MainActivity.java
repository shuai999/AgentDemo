package cn.novate.agent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.novate.agent.agent_static.IBank;
import cn.novate.agent.agent_static.Man;
import cn.novate.agent.agent_static.SalesMan;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 测试静态代理
//        testStaticAgent() ;
        // 测试动态代理
        testDynamicAgent() ;
    }

    /**
     * 测试静态代理
     */
    private void testStaticAgent() {
        // 举个例子：比如别人帮你去买饭，钱是你掏，饭是别人帮你买
        Man man = new Man() ;
        SalesMan salesMan = new SalesMan(man) ;
        // 饭是别人帮你买
        salesMan.applyBank();

        // 运行结果如下
        /*System.out: 银行业务员先搞完一些流程
        System.out: 办卡
        System.out: 办理完毕*/
    }


    /**
     * 测试动态代理
     *      动态代理其实是代理了所有的方法，并且都会执行InvocationHandler 中的 invoke()方法
     */
    private void testDynamicAgent() {
        Man man = new Man() ;

        // 获取了代理
        IBank proxy = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader() ,    // 第一个参数
                new Class<?>[]{IBank.class},                    // 第二个参数：接口类数组
                new BankInvokationHandler(man));                 // 第三个参数：回调

        // 然后调用代理对应的方法就ok
        // 动态代理：解析interface中所有的方法，新建一个类class，class类名字的就是：包名 + $Proxy，并且实例化了一个 Proxy对象
        // Proxy对象中包含 InvocationHandler，所以我们每次调用方法其实调用的是 InvocationHandler中的 invoke()方法
        proxy.applyBank();
        System.out.println("----------------------");

    }

    private static class BankInvokationHandler implements InvocationHandler{

        private IBank man ;
        public BankInvokationHandler(Man man){
            this.man = man ;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 方法回调 方法最终还是需要Man来执行
//            System.out.println("方法执行了" + method.getName());

            // 打印结果如下：
            /*System.out: 方法执行了applyBank
            System.out: 办卡*/


            // 如果 man 不为null，就执行具体方法
            if (man != null) {
                System.out.println("做一些事情");
                Object object = method.invoke(man, args);
                System.out.println("操作完毕");

                // 打印结果如下：
                /*System.out: 做一些事情
                System.out: 办卡
                System.out: 操作完毕
                System.out: ----------------------*/
                return object;
            }
            return null ;   // 如果 man为null ，就返回null
        }
    }
}
