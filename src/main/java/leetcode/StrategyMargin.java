package leetcode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class StrategyMargin {

    public static class VarCon implements Comparable<VarCon> {
        //品种
        private String variety;

        //合约
        private String contract;

        //结算价
        private double clearPrice;

        /**
         * @param variety
         * @param contract
         * @param clearPrice
         */
        public VarCon(String variety, String contract, double clearPrice) {
            this.variety = variety;
            this.contract = contract;
            this.clearPrice = clearPrice;
        }

        @Override
        public int compareTo(VarCon o) {
            //合约排序也就代表了品种进行了排序
            return this.contract.compareTo(o.contract);
        }
    }

    //对锁组合参数设置
    public static class Lock {
        //品种
        private String variety;

        //高腿保证金比例
        private double highMargin;

        //低腿保证金比例
        private double lowMargin;

        /**
         * @param variety
         * @param highMargin
         * @param lowMargin
         */
        public Lock(String variety, double highMargin, double lowMargin) {
            this.variety = variety;
            this.highMargin = highMargin;
            this.lowMargin = lowMargin;
        }
    }

    //跨期组合参数设置
    public static class CrossPeriod {
        //品种
        private String variety;

        //高腿保证金
        private double highMargin;

        //低腿保证金
        private double lowMargin;

        /**
         * @param variety
         * @param highMargin
         * @param lowMargin
         */
        public CrossPeriod(String variety, double highMargin, double lowMargin) {
            this.variety = variety;
            this.highMargin = highMargin;
            this.lowMargin = lowMargin;
        }
    }

    //跨品种组合参数设置
    public static class CrossVariety {
        //跨品种组合中的品种1
        private String variety1;

        //跨品种组合中的品种2
        private String variety2;

        //高腿保证金比例
        private double highMargin;

        //低腿保证金比例
        private double lowMargin;

        //跨品种组合的优先级
        private int priority;

        /**
         * @param variety1
         * @param variety2
         * @param highMargin
         * @param lowMargin
         * @param priority
         */
        public CrossVariety(String variety1, String variety2, double highMargin, double lowMargin, int priority) {
            this.variety1 = variety1;
            this.variety2 = variety2;
            this.highMargin = highMargin;
            this.lowMargin = lowMargin;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    //组合的优先级
    public static class CombinationPriority {

        //组合名称，包含lock,crossPeriod,crossVariety
        private String combinationName;

        //组合的优先级
        private int priority;

        /**
         * @param combinationName
         * @param priority
         */
        public CombinationPriority(String combinationName, int priority) {
            this.combinationName = combinationName;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }
    //投机套保组合的优先级设置
    public static class SpecHedgePriority {
        //buySHFlag * 10 + sellSHFlag
        private int shFlag;

        //投机套保组合的优先级
        private int priority;

        /**
         * @param shFlag
         * @param priority
         */
        public SpecHedgePriority(int shFlag, int priority) {
            this.shFlag = shFlag;
            this.priority = priority;
        }

        private int getPriority() {
            return priority;
        }
    }

    //组合总表结果
    public static class Combination implements Comparable<Combination> {

        //组合的腿1对应的合约
        private String leg1Contract;

        //组合的腿2对应的合约
        private String leg2Contract;

        //腿1的买卖标志
        private int leg1BSFlag;

        //腿2的买卖标志
        private int leg2BSFlag;

        //组合的投机套保标志
        private int combinationShFlag;

        //组合名称
        private String comName;

        //组合总表的优先级
        private int priority;

        //组合后的保证金金额
        private double margin;

        public Combination() {
        }
        /**
         * @param leg1Contract
         * @param leg2Contract
         * @param leg1bsFlag
         * @param leg2bsFlag
         * @param combinationShFlag
         * @param comName
         * @param priority
         * @param margin
         */
        public Combination(String leg1Contract, String leg2Contract, int leg1bsFlag, int leg2bsFlag,
                int combinationShFlag, String comName, int priority) {
            this.leg1Contract = leg1Contract;
            this.leg2Contract = leg2Contract;
            leg1BSFlag = leg1bsFlag;
            leg2BSFlag = leg2bsFlag;
            this.combinationShFlag = combinationShFlag;
            this.comName = comName;
            this.priority = priority;
        }

        @Override
        public int compareTo(Combination o) {
            return this.priority - o.priority;
        }

        @Override
        public String toString() {
            return comName + "," + priority + "," + leg1Contract + "," + leg1BSFlag + "," + combinationShFlag / 10
                + "," + leg2Contract + "," + leg2BSFlag + "," + combinationShFlag % 10 + "," + margin;
        }
    }

    //交易编码对应的持仓结构
    public static class Posi {
        //持仓合约
        private String contract;

        //spec=1
        //hedge=2
        //买投机套保标志
        private int buyShFlag;

        //卖投机套保标志
        private int sellShFlag;

        //买持仓量
        private long buyQty;

        //卖持仓量
        private long sellQty;

        /**
         * @param tradeNo
         * @param contract
         * @param buyShFlag
         * @param sellShFlag
         * @param buyQty
         * @param sellQty
         */
        public Posi(String contract, int buyShFlag, int sellShFlag, long buyQty, long sellQty) {
            this.contract = contract;
            this.buyShFlag = buyShFlag;
            this.sellShFlag = sellShFlag;
            this.buyQty = buyQty;
            this.sellQty = sellQty;
        }
    }

    public static class ConInfo {
        private String variety;

        private double clearPrice;

        public ConInfo(String variety, double clearPrice) {
            this.variety = variety;
            this.clearPrice = clearPrice;
        }
    }

    public static class HighLowMargin {
        private double highMargin;

        private double lowMargin;

        public HighLowMargin(double highMargin, double lowMargin) {
            this.highMargin = highMargin;
            this.lowMargin = lowMargin;
        }
    }

    public static class PosiKey {
        private String contract;

        private int bsFlag;

        private int shFlag;

        public PosiKey(String contract, int bsFlag, int shFlag) {
            this.contract = contract;
            this.bsFlag = bsFlag;
            this.shFlag = shFlag;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((contract == null) ? 0 : contract.hashCode());
            result = prime * result + bsFlag;
            result = prime * result + shFlag;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            PosiKey other = (PosiKey) obj;
            if (contract == null) {
                if (other.contract != null) {
                    return false;
                }
            } else if (!contract.equals(other.contract)) {
                return false;
            }
            if (bsFlag != other.bsFlag) {
                return false;
            }
            if (shFlag != other.shFlag) {
                return false;
            }
            return true;
        }
    }

    //组合总表的结果
    private TreeSet<Combination> combinationSet = new TreeSet<>();

    //组合总表中的优先级
    private int comPriority = 0;

    //合约对应的品种和结算价
    private Map<String, ConInfo> conInfoMap = new HashMap<>();

    //根据合约找到对锁保证金
    private Map<String, HighLowMargin> lockMarginMap = new HashMap<>();

    //根据合约找到跨期的高低腿保证金
    private Map<String, HighLowMargin> crossPeriodMarginMap = new HashMap<>();

    //根据合约找到跨品种的高低腿保证金
    private Map<String, HighLowMargin> crossVarietyMarginMap = new HashMap<>();

    //品种下有哪些合约
    private Map<String, List<String>> contractListByVarMap = new HashMap<>();

    //所有品种信息，针对品种合约数据已经排序，所以这里面的品种信息在加入的时候也是有序的
    private Set<String> varietySet = new HashSet<>();

    /**
     *
     * @param type lock,crossPeriod,crossVariety
     * @param contract1
     * @param contract2
     * @return 根据三种组合类型，以及高低腿保证金参数，获得contract1&contract2的组合保证金
     */
    private double getMargin(String type, String contract1, String contract2) {
        //计算逻辑如下
        //max(腿1固定保证金,腿2固定保证金)*高腿比例+min(腿1固定保证金,腿2固定保证金)*低腿比例
        if ("lock".equals(type)) {
            String variety = conInfoMap.get(contract1).variety;
            double clearPrice = conInfoMap.get(contract1).clearPrice;
            //对锁因为是同一个合约的不同方向，可以简单按照这个逻辑处理
            return lockMarginMap.get(variety).highMargin * clearPrice + lockMarginMap.get(variety).lowMargin * clearPrice;
        } else if ("crossPeriod".equals(type)) {
            String variety = conInfoMap.get(contract1).variety;
            double price1 = conInfoMap.get(contract1).clearPrice;
            double price2 = conInfoMap.get(contract2).clearPrice;
            double priceHigh = price1 >= price2 ? price1 : price2;
            double priceLow = price1 >= price2 ? price2 : price1;
            return crossPeriodMarginMap.get(variety).highMargin * priceHigh + crossPeriodMarginMap.get(variety).lowMargin * priceLow;
        } else if ("crossVariety".equals(type)) {
            String variety1 = conInfoMap.get(contract1).variety;
            String variety2 = conInfoMap.get(contract2).variety;
            double price1 = conInfoMap.get(contract1).clearPrice;
            double price2 = conInfoMap.get(contract2).clearPrice;
            if (price1 >= price2) {
                return crossVarietyMarginMap.get(variety1 + variety2).highMargin * price1 +
                    crossVarietyMarginMap.get(variety1 + variety2).lowMargin * price2;
            } else {
                return crossVarietyMarginMap.get(variety1 + variety2).lowMargin * price1 +
                    crossVarietyMarginMap.get(variety1 + variety2).highMargin * price2;
            }
        } else {
            System.out.println("unexpected combination name:" + type);
            return -10;
        }
    }

    /**
     *
    * @param varConPara 品种合约参数
    * @param lockPara 对锁参数，包含品种，高低腿保证金
    * @param specHedgePriority 投机套保的优先级参数
    * 对锁组合处理
    * 根据持仓结构，找到符合对锁组合的持仓
    */
    public void lock(List<VarCon> varConPara, List<Lock> lockPara, List<SpecHedgePriority> specHedgePriority) {
        //一个合约根据投机套保，买卖生成4条组合信息，由于是同一个合约，买卖颠倒的组合还是同一个组合，因此另外4个不生成
        //a1（买投机）&a1（卖投机）
        //a1（买套保）&a1（卖套保）
        //a1（买套保）&a1（卖投机）
        //a1（买投机）&a1（卖套保）
        for (VarCon varConItem: varConPara) {
            //每个合约下，根据投机套保优先级进行对锁组合总表数据的生成
            for (SpecHedgePriority shItem : specHedgePriority) {
                Combination com = new Combination();
                com.leg1Contract = varConItem.contract;
                com.leg2Contract = varConItem.contract;
                //buy:1  sell:3
                com.leg1BSFlag = 1;
                com.leg2BSFlag = 3;
                com.combinationShFlag = shItem.shFlag;
                com.comName = "lock";
                com.priority = comPriority++;
                com.margin = getMargin("lock", com.leg1Contract, com.leg2Contract);
                combinationSet.add(com);
            }
        }
    }

    /**
     *
     * @param varConPara 品种合约参数
     * @param crossPeriodPara 跨期参数
     * @param specHedgePriority 投机套保的优先级参数
     */
    public void crossPeriod(List<VarCon> varConPara, List<CrossPeriod> crossPeriodPara, List<SpecHedgePriority> specHedgePriority) {
        for (String variety : varietySet) {
            List<String> contractList = contractListByVarMap.get(variety);
            if (contractList == null) {
                continue;
            }
            for (int i = 0; i < contractList.size(); i++) {
                for (int j = i + 1; j < contractList.size(); j++) {
                    for (SpecHedgePriority shItem : specHedgePriority) {
                        for (int k = 0; k < 2; k++) {
                            Combination com = new Combination();
                            com.leg1Contract = contractList.get(i);
                            com.leg2Contract = contractList.get(j);
                            //对于跨期组合，由于组合的2个合约是不同的合约，需要区分买卖
                            //a1(买投机)&a2(卖投机)  和 a1(卖投机)&a2(买投机)是不一样的组合
                            if (k == 0) {
                                com.leg1BSFlag = 1;
                                com.leg2BSFlag = 3;
                            } else {
                                com.leg1BSFlag = 3;
                                com.leg2BSFlag = 1;

                            }
                            com.combinationShFlag = shItem.shFlag;
                            com.comName = "crossPeriod";
                            com.priority = comPriority++;
                            com.margin = getMargin("crossPeriod", com.leg1Contract, com.leg2Contract);
                            combinationSet.add(com);
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param varConPara 品种合约参数
     * @param crossVarietyPara 跨品种参数
     * @param specHedgePriority 投机套保的优先级参数
     */
    public void crossVariety(List<VarCon> varConPara, List<CrossVariety> crossVarietyPara, List<SpecHedgePriority> specHedgePriority) {
        for (CrossVariety crossVarietyItem: crossVarietyPara) {
            String variety1 = crossVarietyItem.variety1;
            String variety2 = crossVarietyItem.variety2;
            List<String> contractList1 = contractListByVarMap.get(variety1);
            List<String> contractList2 = contractListByVarMap.get(variety2);
            if (contractList1 == null || contractList2 == null) {
                return;
            }
            for (String contract1: contractList1) {
                for (String contract2: contractList2) {
                    for (SpecHedgePriority shItem : specHedgePriority) {
                        for (int k = 0; k < 2; k++) {
                            Combination com = new Combination();
                            com.leg1Contract = contract1;
                            com.leg2Contract = contract2;
                            //对于跨品种组合，由于组合的2个合约是不同的合约，需要区分买卖
                            //a1(买投机)&b1(卖投机)  和 b1(卖投机)&a1(买投机)是不一样的组合
                            if (k == 0) {
                                com.leg1BSFlag = 1;
                                com.leg2BSFlag = 3;
                            } else {
                                com.leg1BSFlag = 3;
                                com.leg2BSFlag = 1;
                            }
                            com.combinationShFlag = shItem.shFlag;
                            com.comName = "crossVariety";
                            com.priority = comPriority++;
                            com.margin = getMargin("crossVariety", com.leg1Contract, com.leg2Contract);
                            combinationSet.add(com);
                        }
                    }
                }
            }
        }
    }

    public double computeMargin(List<VarCon> varConPara, List<Lock> lockPara, List<CrossPeriod> crossPeriodPara, List<CrossVariety> crossVarietyPara,
        List<CombinationPriority> combinationPriorityPara, List<SpecHedgePriority> specHedgePriority, List<Posi> posi) {
        if (varConPara == null) {
            return -1;
        }
        if (lockPara == null) {
            return -2;
        }
        if (crossPeriodPara == null) {
            return -3;
        }
        if (crossVarietyPara == null) {
            return -4;
        }
        if (combinationPriorityPara == null) {
            return -5;
        }
        if (specHedgePriority == null) {
            return -6;
        }
        boolean debug = true;

        double sumMargin = 0;
        //根据优先级设置，对各个组合设定进行排序
        Collections.sort(combinationPriorityPara, Comparator.comparingInt(CombinationPriority::getPriority));
        //根据合约进行排序
        Collections.sort(varConPara);
        //根据投机套保优先级，进行排序
        Collections.sort(specHedgePriority, Comparator.comparingInt(SpecHedgePriority::getPriority));
        String variety = "";
        List<String> contractList = new ArrayList<>();
        //根据品种合约信息，生成额外的3个结构
        //1. 一个map实现根据品种找到该品种下所有的合约
        //2. 一个map实现根据合约找到<品种,结算价>
        //3. 一个set负责存放所有的品种
        for (VarCon varConItem : varConPara) {
            conInfoMap.put(varConItem.contract, new ConInfo(varConItem.variety, varConItem.clearPrice));
            if (varConItem.variety.compareTo(variety) != 0) {
                if (variety.length() != 0) {
                    contractListByVarMap.put(variety, contractList);
                    contractList = new ArrayList<>();
                }
                variety = varConItem.variety;
                varietySet.add(variety);
            }
            contractList.add(varConItem.contract);
        }
        contractListByVarMap.put(variety, contractList);

        //根据lockPara，实现根据品种找到对应的对锁高低腿保证金
        for (Lock lockItem : lockPara) {
            lockMarginMap.put(lockItem.variety, new HighLowMargin(lockItem.highMargin, lockItem.lowMargin));
        }
        //根据crossPeriodPara，实现根据品种找到对应的跨期高低腿保证金
        for (CrossPeriod crossPeriodItem : crossPeriodPara) {
            crossPeriodMarginMap.put(crossPeriodItem.variety, new HighLowMargin(crossPeriodItem.highMargin, crossPeriodItem.lowMargin));
        }
        //根据crossVarietyPara，实现根据品种找到对应的跨品种高低腿保证金
        for (CrossVariety crossVarietyItem : crossVarietyPara) {
            crossVarietyMarginMap.put(crossVarietyItem.variety1 + crossVarietyItem.variety2, new HighLowMargin(crossVarietyItem.highMargin, crossVarietyItem.lowMargin));
        }

        try {
            Class<?> cls = StrategyMargin.class;
            //根据组合优先级设置，生成组合总表，默认应该是先对锁，然后跨期，最后跨品种
            for (CombinationPriority comPri: combinationPriorityPara) {
                String funName = comPri.combinationName;
                Method method = cls.getMethod(funName, List.class, List.class, List.class);
                if ("lock".equals(funName)) {
                    method.invoke(this, varConPara, lockPara, specHedgePriority);
                } else if ("crossPeriod".equals(funName)) {
                    method.invoke(this, varConPara, crossPeriodPara, specHedgePriority);
                } else if ("crossVariety".equals(funName)) {
                    method.invoke(this, varConPara, crossVarietyPara, specHedgePriority);
                } else {
                    System.out.println("unexpected combination name in combinationPriorityPara:" + funName);
                    return -7;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return -8;
        }

        if (debug) {
            //打印组合总表信息
            for (Combination item : combinationSet) {
                System.out.println(item.toString());
            }
            System.out.println("combination size: " + combinationSet.size());
        }

        //通过持仓信息构建对应的map结构，key为posi
        String contract1;
        String contract2;
        int leg1BSFlag;
        int leg2BSFlag;
        int leg1SHFlag;
        int leg2SHFlag;
        String comInfo;

        //存放最终根据组合总表和持仓结构生成的保证金结果
        Map<String, Double> marginMap = new HashMap<>();
        //根据持仓结构构建map，key=(contract,bsflag,shflag),value=qty
        Map<PosiKey, Long> posiMap = new HashMap<>();
        for (Posi p : posi) {
            if (p.buyQty > 0) {
                PosiKey pk = new PosiKey(p.contract, 1, p.buyShFlag);
                posiMap.put(pk, p.buyQty);
            }
            if (p.sellQty > 0) {
                PosiKey pk = new PosiKey(p.contract, 3, p.sellShFlag);
                posiMap.put(pk, p.sellQty);
            }
        }
        //循环组合总表
        for (Combination com : combinationSet) {
            contract1 = com.leg1Contract;
            contract2 = com.leg2Contract;
            leg1BSFlag = com.leg1BSFlag;
            leg2BSFlag = com.leg2BSFlag;
            leg1SHFlag = com.combinationShFlag / 10;
            leg2SHFlag = com.combinationShFlag % 10;
            PosiKey pk1 = new PosiKey(contract1, leg1BSFlag, leg1SHFlag);
            PosiKey pk2 = new PosiKey(contract2, leg2BSFlag, leg2SHFlag);
            if (posiMap.containsKey(pk1) && posiMap.containsKey(pk2)) {
                long minQty = posiMap.get(pk1) >= posiMap.get(pk2) ? posiMap.get(pk2) : posiMap.get(pk1);
                comInfo = String.format("%s,%d,%d,%s,%d,%d,%d,%f,%f\n", contract1, leg1BSFlag, leg1SHFlag,
                    contract2, leg2BSFlag, leg2SHFlag, minQty, com.margin, minQty * com.margin);
                sumMargin += minQty * com.margin;
                if (debug) {
                    System.out.print(comInfo);
                }
                marginMap.put(comInfo, minQty * com.margin);
                if (posiMap.get(pk1) == minQty && posiMap.get(pk2) == minQty) {
                    posiMap.remove(pk1);
                    posiMap.remove(pk2);
                } else if (posiMap.get(pk1) == minQty && posiMap.get(pk2) > minQty) {
                    posiMap.remove(pk1);
                    posiMap.computeIfPresent(pk2, (key, value) -> value - minQty);
                } else if (posiMap.get(pk1) > minQty && posiMap.get(pk2) == minQty) {
                    posiMap.remove(pk2);
                    posiMap.computeIfPresent(pk1, (key, value) -> value - minQty);
                } else {
                    posiMap.computeIfPresent(pk1, (key, value) -> value - minQty);
                    posiMap.computeIfPresent(pk2, (key, value) -> value - minQty);
                }
            }
        }

        if (debug) {
            System.out.println("sumMargin:" + sumMargin);
        }
        return sumMargin;
    }

    public static void main(String[] args) {
        StrategyMargin margin = new StrategyMargin();

        //构建品种合约参数
        List<VarCon> varConPara = new ArrayList<>();
        VarCon vc1 = new VarCon("a", "a2501", 1000);
        VarCon vc2 = new VarCon("a", "a2502", 1100);
        VarCon vc3 = new VarCon("a", "a2503", 1200);
        VarCon vc4 = new VarCon("b", "b2501", 2000);
        VarCon vc5 = new VarCon("b", "b2502", 2100);
        VarCon vc6 = new VarCon("b", "b2503", 2200);
        VarCon vc7 = new VarCon("c", "c2501", 3000);
        VarCon vc8 = new VarCon("c", "c2502", 3100);
        VarCon vc9 = new VarCon("c", "c2503", 3200);
        varConPara.addAll(List.of(vc1, vc2, vc3, vc4, vc5, vc6, vc7, vc8, vc9));

        List<Lock> lockPara = new ArrayList<>();
        Lock l1 = new Lock("a", 1, 0);
        Lock l2 = new Lock("b", 1, 0);
        Lock l3 = new Lock("c", 1, 0);
        lockPara.addAll(List.of(l1, l2, l3));
        // lockPara.addAll(List.of(l1, l2));

        List<CrossPeriod> crossPeriodPara = new ArrayList<>();
        CrossPeriod cp1 = new CrossPeriod("a", 1, 0);
        CrossPeriod cp2 = new CrossPeriod("b", 1, 0);
        CrossPeriod cp3 = new CrossPeriod("c", 1, 0);
        crossPeriodPara.addAll(List.of(cp1, cp2, cp3));
        // crossPeriodPara.addAll(List.of(cp1, cp2));

        List<CrossVariety> crossVarietyPara = new ArrayList<>();
        CrossVariety cv1 = new CrossVariety("a", "b", 1, 0, 1);
        CrossVariety cv2 = new CrossVariety("a", "c", 1, 0, 2);
        CrossVariety cv3 = new CrossVariety("b", "c", 1, 0, 3);
        crossVarietyPara.addAll(List.of(cv1, cv2, cv3));
        // crossVarietyPara.addAll(List.of(cv1, cv2));

        List<CombinationPriority> combinationPriorityPara = new ArrayList<>();
        CombinationPriority com1 = new CombinationPriority("lock", 1);
        CombinationPriority com2 = new CombinationPriority("crossPeriod", 2);
        CombinationPriority com3 = new CombinationPriority("crossVariety", 3);
        combinationPriorityPara.addAll(List.of(com1, com2, com3));

        List<SpecHedgePriority> specHedgePriority = new ArrayList<>();
        SpecHedgePriority sh1 = new SpecHedgePriority(11, 1);
        SpecHedgePriority sh2 = new SpecHedgePriority(33, 2);
        SpecHedgePriority sh3 = new SpecHedgePriority(13, 3);
        SpecHedgePriority sh4 = new SpecHedgePriority(31, 4);
        specHedgePriority.addAll(List.of(sh1, sh2, sh3, sh4));


        List<Posi> posi = new ArrayList<>();
        Posi p1 = new Posi("a2501", 1, 1, 100, 210);
        Posi p2 = new Posi("a2502", 1, 3, 50, 30);
        Posi p3 = new Posi("b2501", 1, 0, 75, 0);
        Posi p4 = new Posi("c2501", 3, 0, 15, 0);
        posi.addAll(List.of(p1, p2, p3, p4));

        // Posi p1 = new Posi("a2501", 1, 1, 100, 210);
        // Posi p2 = new Posi("a2501", 3, 3, 270, 110);
        // Posi p3 = new Posi("a2502", 1, 1, 50, 100);
        // Posi p4 = new Posi("a2502", 3, 3, 60, 100);
        // Posi p5 = new Posi("b2501", 1, 1, 100, 50);
        // Posi p6 = new Posi("c2501", 3, 1, 10, 20);
        // posi.addAll(List.of(p1, p2, p3, p4, p5, p6));

        margin.computeMargin(varConPara, lockPara, crossPeriodPara, crossVarietyPara, combinationPriorityPara, specHedgePriority, posi);
    }

}
