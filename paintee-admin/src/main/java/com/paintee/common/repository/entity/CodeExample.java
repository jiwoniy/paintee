package com.paintee.common.repository.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCodeGroupIsNull() {
            addCriterion("code_group is null");
            return (Criteria) this;
        }

        public Criteria andCodeGroupIsNotNull() {
            addCriterion("code_group is not null");
            return (Criteria) this;
        }

        public Criteria andCodeGroupEqualTo(String value) {
            addCriterion("code_group =", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupNotEqualTo(String value) {
            addCriterion("code_group <>", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupGreaterThan(String value) {
            addCriterion("code_group >", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupGreaterThanOrEqualTo(String value) {
            addCriterion("code_group >=", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupLessThan(String value) {
            addCriterion("code_group <", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupLessThanOrEqualTo(String value) {
            addCriterion("code_group <=", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupLike(String value) {
            addCriterion("code_group like", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupNotLike(String value) {
            addCriterion("code_group not like", value, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupIn(List<String> values) {
            addCriterion("code_group in", values, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupNotIn(List<String> values) {
            addCriterion("code_group not in", values, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupBetween(String value1, String value2) {
            addCriterion("code_group between", value1, value2, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeGroupNotBetween(String value1, String value2) {
            addCriterion("code_group not between", value1, value2, "codeGroup");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNull() {
            addCriterion("code_value is null");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNotNull() {
            addCriterion("code_value is not null");
            return (Criteria) this;
        }

        public Criteria andCodeValueEqualTo(String value) {
            addCriterion("code_value =", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotEqualTo(String value) {
            addCriterion("code_value <>", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThan(String value) {
            addCriterion("code_value >", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThanOrEqualTo(String value) {
            addCriterion("code_value >=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThan(String value) {
            addCriterion("code_value <", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThanOrEqualTo(String value) {
            addCriterion("code_value <=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLike(String value) {
            addCriterion("code_value like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotLike(String value) {
            addCriterion("code_value not like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueIn(List<String> values) {
            addCriterion("code_value in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotIn(List<String> values) {
            addCriterion("code_value not in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueBetween(String value1, String value2) {
            addCriterion("code_value between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotBetween(String value1, String value2) {
            addCriterion("code_value not between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNull() {
            addCriterion("code_name is null");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNotNull() {
            addCriterion("code_name is not null");
            return (Criteria) this;
        }

        public Criteria andCodeNameEqualTo(String value) {
            addCriterion("code_name =", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotEqualTo(String value) {
            addCriterion("code_name <>", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThan(String value) {
            addCriterion("code_name >", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("code_name >=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThan(String value) {
            addCriterion("code_name <", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThanOrEqualTo(String value) {
            addCriterion("code_name <=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLike(String value) {
            addCriterion("code_name like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotLike(String value) {
            addCriterion("code_name not like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameIn(List<String> values) {
            addCriterion("code_name in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotIn(List<String> values) {
            addCriterion("code_name not in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameBetween(String value1, String value2) {
            addCriterion("code_name between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotBetween(String value1, String value2) {
            addCriterion("code_name not between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andUseYnIsNull() {
            addCriterion("use_yn is null");
            return (Criteria) this;
        }

        public Criteria andUseYnIsNotNull() {
            addCriterion("use_yn is not null");
            return (Criteria) this;
        }

        public Criteria andUseYnEqualTo(String value) {
            addCriterion("use_yn =", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotEqualTo(String value) {
            addCriterion("use_yn <>", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThan(String value) {
            addCriterion("use_yn >", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThanOrEqualTo(String value) {
            addCriterion("use_yn >=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThan(String value) {
            addCriterion("use_yn <", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThanOrEqualTo(String value) {
            addCriterion("use_yn <=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLike(String value) {
            addCriterion("use_yn like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotLike(String value) {
            addCriterion("use_yn not like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnIn(List<String> values) {
            addCriterion("use_yn in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotIn(List<String> values) {
            addCriterion("use_yn not in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnBetween(String value1, String value2) {
            addCriterion("use_yn between", value1, value2, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotBetween(String value1, String value2) {
            addCriterion("use_yn not between", value1, value2, "useYn");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNull() {
            addCriterion("created_date is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNotNull() {
            addCriterion("created_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateEqualTo(Date value) {
            addCriterion("created_date =", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotEqualTo(Date value) {
            addCriterion("created_date <>", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThan(Date value) {
            addCriterion("created_date >", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("created_date >=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThan(Date value) {
            addCriterion("created_date <", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
            addCriterion("created_date <=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIn(List<Date> values) {
            addCriterion("created_date in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotIn(List<Date> values) {
            addCriterion("created_date not in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateBetween(Date value1, Date value2) {
            addCriterion("created_date between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
            addCriterion("created_date not between", value1, value2, "createdDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}