package leetcode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class StrategyMargin {

    public static class VarCon implements Comparable<VarCon> {
        public String variety;
        public String contract;
        public double clearPrice;
        @Override
        public int compareTo(VarCon o) {
            return (this.variety + this.contract).compareTo(o.variety + o.contract);
        }
    }

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
        //buySHFlag * 10 + sellSHFlag
        public int shFlag;

        public int getPriority() {
            return priority;
        }

        // public int compareTo(specHedgePriority o) {
        //     return this.priority - o.priority;
        // }
    }

    //组合总表结果
    public static class Combination implements Comparable<Combination> {

        //组合的腿1对应的品种
        // public String variety1;
        //组合的腿2对应的品种
        // public String variety2;
        //组合的腿1对应的合约
        public String leg1Contract;
        //组合的腿2对应的合约
        public String leg2Contract;
        //腿1的买卖标志
        public int leg1BSFlag;
        //腿2的买卖标志
        public int leg2BSFlag;
        //组合的投机套保标志
        public int combinationShFlag;
        //组合名称
        public String comName;
        //组合总表的优先级
        public int priority;
        //组合后的保证金金额
        public double margin;

        //type=0 按照对锁持仓的优先级进行排序
        //type=1 按照跨期持仓的优先级进行排序
        //type=2 按照跨品种持仓的优先级进行排序
        // public int type;

        List<SpecHedgePriority> specHedgePriority;
        // public Combination(int type) {
        //     this.type = type;
        // }
        @Override
        public int compareTo(Combination o) {
            return this.priority - o.priority;
        }
    }

    //交易编码对应的持仓结构
    public static class Posi {
        //交易编码
        public String tradeNo;
        //持仓合约
        public String contract;
        //spec=1
        //hedge=2
        //买投机套保标志
        public int buyShFlag;
        //卖投机套保标志
        public int sellShFlag;
        //买持仓量
        public long buyQty;
        //卖持仓量
        public long sellQty;
    }

    public static class ConInfo {
        public String variety;
        public double clearPrice;
        public ConInfo(String variety, double clearPrice) {
            this.variety = variety;
            this.clearPrice = clearPrice;
        }
    }

    public static class HightLowMargin {
        public double hightMargin;
        public double lowMargin;
        public HightLowMargin(double hightMargin, double lowMargin) {
            this.hightMargin = hightMargin;
            this.lowMargin = lowMargin;
        }
    }

    public static class PosiKey {
        private String contract;
        private int bsFlag;
        private int shFlag;
        /**
         * @return the contract
         */
        public String getContract() {
            return contract;
        }
        /**
         * @param contract the contract to set
         */
        public void setContract(String contract) {
            this.contract = contract;
        }
        /**
         * @return the bsFlag
         */
        public int getBsFlag() {
            return bsFlag;
        }
        /**
         * @param bsFlag the bsFlag to set
         */
        public void setBsFlag(int bsFlag) {
            this.bsFlag = bsFlag;
        }
        /**
         * @return the shFlag
         */
        public int getShFlag() {
            return shFlag;
        }
        /**
         * @param shFlag the shFlag to set
         */
        public void setShFlag(int shFlag) {
            this.shFlag = shFlag;
        }
        public PosiKey(String contract, int bsFlag, int shFlag) {
            this.contract = contract;
            this.bsFlag = bsFlag;
            this.shFlag = shFlag;
        }
    }

    //组合总表的结果
    public TreeSet<Combination> combinationSet = new TreeSet<>();
    //组合总表中的优先级
    public int comPriority = 0;

    //合约对应的品种和结算价
    public Map<String, ConInfo> conInfoMap = new HashMap<>();

    //根据合约找到对锁保证金
    public Map<String, HightLowMargin> lockMarginMap = new HashMap<>();
    //根据合约找到跨期的高低腿保证金
    public Map<String, HightLowMargin> crossPeriodMarginMap = new HashMap<>();
    //根据合约找到跨品种的高低腿保证金
    public Map<String, HightLowMargin> crossVarietyMarginMap = new HashMap<>();
    //品种下有哪些合约
    public Map<String, List<String>> contractListByVar = new HashMap<>();

    public double getMargin(String type, String contract1, String contract2) {
        //计算逻辑如下
        //max(腿1固定保证金,腿2固定保证金)*高腿比例+min(腿1固定保证金,腿2固定保证金)*低腿比例
        if (type.equals("lock")) {
            String variety = conInfoMap.get(contract1).variety;
            double clearPrice = conInfoMap.get(contract1).clearPrice;
            //对锁因为是同一个合约的不同方向，可以简单按照这个逻辑处理
            return lockMarginMap.get(variety).hightMargin * clearPrice + lockMarginMap.get(variety).lowMargin * clearPrice;
        } else if (type.equals("crossPeriod")) {
            String variety = conInfoMap.get(contract1).variety;
            double price1 = conInfoMap.get(contract1).clearPrice;
            double price2 = conInfoMap.get(contract2).clearPrice;
            double priceHight = price1 >= price2 ? price1 : price2;
            double priceLow = price1 >= price2 ? price2 : price1;
            return crossPeriodMarginMap.get(variety).hightMargin * priceHight + crossPeriodMarginMap.get(variety).lowMargin * priceLow;
        } else if (type.equals("crossVariety")) {
            String variety1 = conInfoMap.get(contract1).variety;
            String variety2 = conInfoMap.get(contract2).variety;
            double price1 = conInfoMap.get(contract1).clearPrice;
            double price2 = conInfoMap.get(contract2).clearPrice;
            if (price1 >= price2) {
                return crossPeriodMarginMap.get(variety1 + variety2).hightMargin * price1 +
                    crossPeriodMarginMap.get(variety1 + variety2).lowMargin * price2;
            } else {
                return crossPeriodMarginMap.get(variety1 + variety2).lowMargin * price1 +
                    crossPeriodMarginMap.get(variety1 + variety2).hightMargin * price2;
            }
        } else {
            return -1;
        }
    }

    /*
     * 对锁组合处理
     * 根据持仓结构，找到符合对锁组合的持仓
     */
    @SuppressWarnings("unused")
    private void lock(List<VarCon> varConPara, List<Lock> lockPara, List<SpecHedgePriority> specHedgePriority) {

        //遍历所有品种合约
        for (VarCon varConItem: varConPara) {
            //每个合约下，根据投机套保优先级进行对锁组合总表数据的生成
            for (SpecHedgePriority shItem : specHedgePriority) {
                Combination com = new Combination();
                com.leg1Contract = varConItem.contract;
                com.leg2Contract = varConItem.contract;
                //buy
                com.leg1BSFlag = 1;
                //sell
                com.leg2BSFlag = 3;
                com.combinationShFlag = shItem.shFlag;
                com.comName = "lock";
                com.priority = comPriority++;
                com.margin = getMargin("lock", com.leg1Contract, com.leg2Contract);
                combinationSet.add(com);
            }
        }
    }

    @SuppressWarnings("unused")
    private void crossPeriod(List<VarCon> varConPara, List<CrossPeriod> crossPeriodPara, List<SpecHedgePriority> specHedgePriority) {
        String variety = "";
        List<String> contractList = new ArrayList<>();
        for (VarCon varConItem: varConPara) {
            //新的品种
            if (varConItem.variety.compareTo(variety) != 0) {
                for (int i = 0; i < contractList.size(); i++) {
                    for (int j = i + 1; j < contractList.size(); j++) {
                        for (SpecHedgePriority shItem : specHedgePriority) {
                            Combination com = new Combination();
                            com.leg1Contract = contractList.get(i);
                            com.leg2Contract = contractList.get(j);
                            //buy
                            com.leg1BSFlag = 1;
                            //sell
                            com.leg2BSFlag = 3;
                            com.combinationShFlag = shItem.shFlag;
                            com.comName = "crossPeriod";
                            com.priority = comPriority++;
                            com.margin = getMargin("crossPeriod", com.leg1Contract, com.leg2Contract);
                            combinationSet.add(com);
                        }
                    }
                }
                variety = varConItem.variety;
                contractList.clear();
                //新品种的第一条记录
                // contractList.add(varConItem.contract);
            }
            contractList.add(varConItem.contract);
        }
    }

    @SuppressWarnings("unused")
    private void crossVariety(List<VarCon> varConPara, List<CrossVariety> crossVarietyPara, List<SpecHedgePriority> specHedgePriority) {
        for (CrossVariety crossVarietyItem: crossVarietyPara) {
            String variety1 = crossVarietyItem.variety1;
            String variety2 = crossVarietyItem.variety2;
            List<String> contractList1 = contractListByVar.get(variety1);
            List<String> contractList2 = contractListByVar.get(variety2);
            for (String contract1: contractList1) {
                for (String contract2: contractList2) {
                    for (SpecHedgePriority shItem : specHedgePriority) {
                        Combination com = new Combination();
                        com.leg1Contract = contract1;
                        com.leg2Contract = contract2;
                        //buy
                        com.leg1BSFlag = 1;
                        //sell
                        com.leg2BSFlag = 3;
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

    public double computeMargin(List<VarCon> varConPara, List<Lock> lockPara, List<CrossPeriod> crossPeriodPara, List<CrossVariety> crossVarietyPara,
        List<CombinationPriority> combinationPriorityPara, List<SpecHedgePriority> specHedgePriority, List<Posi> posi) {
        if (lockPara == null || crossPeriodPara == null || crossVarietyPara == null) {
            return -1;
        }
        if (combinationPriorityPara == null) {
            return -2;
        }
        if (specHedgePriority == null) {
            return -3;
        }
        double sumMargin = 0;
        //根据优先级设置，对各个组合设定进行排序
        Collections.sort(combinationPriorityPara, Comparator.comparingInt(CombinationPriority::getPriority));
        //根据品种合约关系，进行排序，排序规则为variety+contract
        Collections.sort(varConPara);
        //根据投机套保优先级，进行排序
        Collections.sort(specHedgePriority, Comparator.comparingInt(SpecHedgePriority::getPriority));
        String variety = "";
        List<String> contractList = new ArrayList<>();
        for (VarCon varConItem : varConPara) {
            conInfoMap.put(varConItem.contract, new ConInfo(varConItem.variety, varConItem.clearPrice));
            if (varConItem.variety.compareTo(variety) != 0) {
                contractListByVar.put(variety, contractList);
                variety = varConItem.variety;
                contractList = new ArrayList<>();
            }
            contractList.add(varConItem.contract);
        }
        for (Lock lockItem : lockPara) {
            lockMarginMap.put(lockItem.variety, new HightLowMargin(lockItem.hightMargin, lockItem.lowMargin));
        }
        for (CrossPeriod crossPeriodItem : crossPeriodPara) {
            crossPeriodMarginMap.put(crossPeriodItem.variety, new HightLowMargin(crossPeriodItem.hightMargin, crossPeriodItem.lowMargin));
        }
        for (CrossVariety crossVarietyItem : crossVarietyPara) {
            crossVarietyMarginMap.put(crossVarietyItem.variety1 + crossVarietyItem.variety2, new HightLowMargin(crossVarietyItem.hightMargin, crossVarietyItem.lowMargin));
        }
        try {
            Class<?> cls = StrategyMargin.class;
            //生成组合总表
            for (CombinationPriority comPri: combinationPriorityPara) {
                String funName = comPri.combinationName;
                Method method = cls.getMethod(funName);
                if (funName.equals("lock")) {
                    method.invoke(varConPara, lockPara, specHedgePriority);
                } else if (funName.equals("crossPeriod")) {
                    method.invoke(varConPara, crossPeriodPara, specHedgePriority);
                } else if (funName.equals("crossVariety")) {
                    method.invoke(varConPara, crossVarietyPara, specHedgePriority);
                } else {
                    return -4;
                }
            }
            //通过持仓信息构建对应的map结构，key为posi
            String contract1, contract2;
            int leg1BSFlag, leg2BSFlag;
            int leg1SHFlag, leg2SHFlag;
            // int comSHFlag;
            Map<String, Double> marginMap = new HashMap<>();
            String comInfo;
            //根据持仓结构构建买卖两个map，key=(contract,bsflag,shflag),value=qty
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
            for (Combination com : combinationSet) {
                // comSHFlag = com.combinationShFlag;
                contract1 = com.leg1Contract;
                contract2 = com.leg2Contract;
                leg1BSFlag = com.leg1BSFlag;
                leg2BSFlag = com.leg2BSFlag;
                leg1SHFlag = com.combinationShFlag / 10;
                leg2SHFlag = com.combinationShFlag % 10;
                PosiKey pk1 = new PosiKey(contract1, leg1BSFlag, leg1SHFlag);
                PosiKey pk2 = new PosiKey(contract2, leg2BSFlag, leg2BSFlag);
                if (posiMap.containsKey(pk1) && posiMap.containsKey(pk2)) {
                    long minQty = posiMap.get(pk1) >= posiMap.get(pk2) ? posiMap.get(pk2) : posiMap.get(pk1);
                    comInfo = contract1 + "-" + "bs:" + leg1BSFlag + "-" + "sh:" + leg1SHFlag + ","
                        + contract2 + "-" + "bs:" + leg2BSFlag + "-" + "sh:" + leg2SHFlag + "-" + "comQty:" + minQty;
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
            for (Map.Entry<String, Double> entry : marginMap.entrySet()) {
                String key = entry.getKey();
                sumMargin += entry.getValue().doubleValue();
                System.out.println(key + ":" + entry.getValue());
            }
            System.out.println("sumMargin:" + sumMargin);
        } catch (Exception e) {
            System.out.println(e);
        }
        return sumMargin;
    }
    public static void main(String[] args) {
        StrategyMargin margin = new StrategyMargin();
        margin.computeMargin(null, null, null, null, null, null, null);
    }

}
