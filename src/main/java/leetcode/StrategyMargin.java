package leetcode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StrategyMargin {

    //对锁组合参数设置
    public static class Lock {
        //品种
        public String variety;
        //高腿保证金比例
        public double hightMargin;
        //低腿保证金比例
        public double lowMargin;
    }

    //跨期组合参数设置
    public static class CrossPeriod {
        public String variety;
        public double hightMargin;
        public double lowMargin;
    }

    //跨品种组合参数设置
    public static class CrossVariety {
        //跨品种组合中的品种1
        public String variety1;
        //跨品种组合中的品种2
        public String variety2;
        //高腿保证金比例
        public double hightMargin;
        //低腿保证金比例
        public double lowMargin;
        //跨品种组合的优先级
        private int priority;

        public int getPriority() {
            return priority;
        }

        // public int compareTo(CrossVariety o) {
        //     return this.priority - o.priority;
        // }
    }

    //组合的优先级
    public static class CombinationPriority {
        //组合的优先级
        private int priority;

        public String combinationName;

        public int getPriority() {
            return priority;
        }
        // public int compareTo(CombinationPriority o) {
        //     return this.priority - o.priority;
        // }
    }
    //投机套保组合的优先级设置
    public static class SpecHedgePriority {
        //投机套保组合的优先级
        private int priority;
        //投机和投机的组合的标志为11
        public final int specSpec = 11;
        //投机和投机的组合的标志为33
        public final int hedgeHedge = 33;
        //投机和投机的组合的标志为13
        public final int specHedge = 13;
        //投机和投机的组合的标志为31
        public final int hedgeSpec = 31;

        public int getPriority() {
            return priority;
        }

        // public int compareTo(specHedgePriority o) {
        //     return this.priority - o.priority;
        // }
    }

    //组合总表结果
    public static class Combination {

        //组合的腿1对应的品种
        public String variety1;
        //组合的腿2对应的品种
        public String variety2;
        //组合的腿1对应的合约
        public String leg1Contract;
        //组合的腿2对应的合约
        public String leg2Contract;
        //腿1的买卖标志
        public int leg1BSFlag;
        //腿2的买卖标志
        public int leg2BSFlag;
        //组合的投机套保标志
        public String combinationShFlag;
    }

    //交易编码对应的持仓结构
    public static class Posi {
        //交易编码
        public String tradeNo;
        //持仓合约
        public String contract;
        //买投机套保标志
        public int buyShFlag;
        //卖投机套保标志
        public int sellShFlag;
        //买持仓量
        public long buyQty;
        //卖持仓量
        public long sellQty;
    }

    public List<Combination> combinationList = new ArrayList<>();
    private void lock(List<Lock> lockPara, List<SpecHedgePriority> specHedgePriority, List<Posi> posi) {

        Iterator<Posi> posiIter = posi.iterator();
        while (posiIter.hasNext()) {
            Posi posiItem = posiIter.next();
            if (posiItem.shFlag == 1 && posiItem.buyQty > 0 && posiItem.sellQty > 0) {
                // 获取锁仓品种
                String variety = posiItem.contract.substring(0, 6);
                // 获取锁仓合约
                String contract = posiItem.contract;
                // 获取锁仓买卖标志
                int shFlag = posiItem.shFlag;
                // 获取锁仓买持仓量
            }
        }
    }

    public double margin(List<Lock> lockPara, List<CrossPeriod> crossPeriodPara, List<CrossVariety> crossVarietyPara,
        List<CombinationPriority> combinationPriorityPara, List<SpecHedgePriority> specHedgePriority, List<Posi> posi) {
        if (lockPara == null && crossPeriodPara == null && crossVarietyPara == null) {
            return -1;
        }
        if (combinationPriorityPara == null) {
            return -2;
        }
        if (specHedgePriority == null) {
            return -3;
        }
        Collections.sort(combinationPriorityPara, Comparator.comparingInt(CombinationPriority::getPriority));
        try {
            // Class<?> cls = StrategyMargin.class;
            Class<?> cls = Class.forName("StrategyMargin");
            for (CombinationPriority comPri: combinationPriorityPara) {
                String funName = comPri.combinationName;
                Method method = cls.getMethod(funName);
                if (funName.equals("lock")) {
                    method.invoke(lockPara, specHedgePriority, posi);
                } else if (funName.equals("crossPeriod")) {
                    return 0;
                } else if (funName.equals("crossVariety")) {
                    return 1;

                } else {
                    return -4;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public static void main(String[] args) {
        StrategyMargin margin = new StrategyMargin();

    }

}
