package jmh;

import java.math.BigDecimal;

public class Order {

    private String tradeDate;
    private BigDecimal orderNo;
    private BigDecimal pid;
    private String memberId;
    private String clientId;
    private String arbiContractId;
    private String contractId;
    private BigDecimal traderNo;
    private String orderSort;
    private BigDecimal orderBatchNo;
    private BigDecimal localNo;
    private BigDecimal price;
    private BigDecimal qty;
    private String eoFlag;
    private String bsFlag;
    private String shFlag;
    private String orderAttr;
    private String orderType;
    private String orderTime;
    private String status;
    private BigDecimal lastMatchPrice;
    private BigDecimal matchQty;
    private String orderSrc;
    private String ifMktmk;
    private BigDecimal concelTid;
    private String ifHfExempt;

    public Order(String tradeDate, BigDecimal orderNo, BigDecimal pid, String memberId, String clientId, String arbiContractId, String contractId, BigDecimal traderNo, String orderSort, BigDecimal orderBatchNo,
                 BigDecimal localNo, BigDecimal price, BigDecimal qty, String eoFlag, String bsFlag, String shFlag, String orderAttr, String orderType, String orderTime, String status,
                 BigDecimal lastMatchPrice, BigDecimal matchQty, String orderSrc, String ifMktmk, BigDecimal concelTid, String ifHfExempt) {
        this.tradeDate = tradeDate;
        this.orderNo = orderNo;
        this.pid = pid;
        this.memberId = memberId;
        this.clientId = clientId;
        this.arbiContractId = arbiContractId;
        this.contractId = contractId;
        this.traderNo = traderNo;
        this.orderSort = orderSort;
        this.orderBatchNo = orderBatchNo;
        this.localNo = localNo;
        this.price = price;
        this.qty = qty;
        this.eoFlag = eoFlag;
        this.bsFlag = bsFlag;
        this.shFlag = shFlag;
        this.orderAttr = orderAttr;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.status = status;
        this.lastMatchPrice = lastMatchPrice;
        this.matchQty = matchQty;
        this.orderSrc = orderSrc;
        this.ifMktmk = ifMktmk;
        this.concelTid = concelTid;
        this.ifHfExempt = ifHfExempt;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPid() {
        return pid;
    }

    public void setPid(BigDecimal pid) {
        this.pid = pid;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getArbiContractId() {
        return arbiContractId;
    }

    public void setArbiContractId(String arbiContractId) {
        this.arbiContractId = arbiContractId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getTraderNo() {
        return traderNo;
    }

    public void setTraderNo(BigDecimal traderNo) {
        this.traderNo = traderNo;
    }

    public String getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }

    public BigDecimal getOrderBatchNo() {
        return orderBatchNo;
    }

    public void setOrderBatchNo(BigDecimal orderBatchNo) {
        this.orderBatchNo = orderBatchNo;
    }

    public BigDecimal getLocalNo() {
        return localNo;
    }

    public void setLocalNo(BigDecimal localNo) {
        this.localNo = localNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getEoFlag() {
        return eoFlag;
    }

    public void setEoFlag(String eoFlag) {
        this.eoFlag = eoFlag;
    }

    public String getBsFlag() {
        return bsFlag;
    }

    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag;
    }

    public String getShFlag() {
        return shFlag;
    }

    public void setShFlag(String shFlag) {
        this.shFlag = shFlag;
    }

    public String getOrderAttr() {
        return orderAttr;
    }

    public void setOrderAttr(String orderAttr) {
        this.orderAttr = orderAttr;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getLastMatchPrice() {
        return lastMatchPrice;
    }

    public void setLastMatchPrice(BigDecimal lastMatchPrice) {
        this.lastMatchPrice = lastMatchPrice;
    }

    public BigDecimal getMatchQty() {
        return matchQty;
    }

    public void setMatchQty(BigDecimal matchQty) {
        this.matchQty = matchQty;
    }

    public String getOrderSrc() {
        return orderSrc;
    }

    public void setOrderSrc(String orderSrc) {
        this.orderSrc = orderSrc;
    }

    public String getIfMktmk() {
        return ifMktmk;
    }

    public void setIfMktmk(String ifMktmk) {
        this.ifMktmk = ifMktmk;
    }

    public BigDecimal getConcelTid() {
        return concelTid;
    }

    public void setConcelTid(BigDecimal concelTid) {
        this.concelTid = concelTid;
    }

    public String getIfHfExempt() {
        return ifHfExempt;
    }

    public void setIfHfExempt(String ifHfExempt) {
        this.ifHfExempt = ifHfExempt;
    }
}
