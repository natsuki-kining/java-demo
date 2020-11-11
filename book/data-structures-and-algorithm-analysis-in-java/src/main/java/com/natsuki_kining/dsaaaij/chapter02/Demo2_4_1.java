package com.natsuki_kining.dsaaaij.chapter02;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/12/16 11:13
 **/
public class Demo2_4_1 {

    /**
     * 计算 \sum_{i=1}^N i^3
     *
     * 所有的声明均不计时间。
     * 第1行和第4行各占一个时间单元。
     * 第3行每执行-次占用4个时间单元(两次乘法，一次加法和一次赋值)，而执行N次共占用4N个时间单元。
     * 第2行在初始化i、测试i≤N和对i的自增运算隐含着开销。
     * 所有这些的总开销是初始化1个单元时间，所有的测试为N+1个单元时间，而所有的自增运算为N个单元时间，共2N+2个时间单元。
     * 我们忽略调用方法和返回值的开销,得到总量是6N+4个时间单元。因此,我们说该方法是O(N)。
     *
     * @param n
     * @return
     */
    public static int sum( int n ){
        int partialSum;
        partialSum = 0;                     // 1
        for( int i = 1; i <= n; i++ ) {     //2
            partialSum += i * i * i;        //3 每执行一次占用4个时间单元(两次乘法，一次加法和一次赋值) 4N
        }
        return partialSum;                  //4
    }

    public static void main(String[] args) {
        System.out.println(Demo2_4_1.sum(1));
    }

}
