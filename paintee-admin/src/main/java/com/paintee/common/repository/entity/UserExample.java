package com.paintee.common.repository.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andProviderIdIsNull() {
            addCriterion("provider_id is null");
            return (Criteria) this;
        }

        public Criteria andProviderIdIsNotNull() {
            addCriterion("provider_id is not null");
            return (Criteria) this;
        }

        public Criteria andProviderIdEqualTo(String value) {
            addCriterion("provider_id =", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotEqualTo(String value) {
            addCriterion("provider_id <>", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThan(String value) {
            addCriterion("provider_id >", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThanOrEqualTo(String value) {
            addCriterion("provider_id >=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThan(String value) {
            addCriterion("provider_id <", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThanOrEqualTo(String value) {
            addCriterion("provider_id <=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLike(String value) {
            addCriterion("provider_id like", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotLike(String value) {
            addCriterion("provider_id not like", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdIn(List<String> values) {
            addCriterion("provider_id in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotIn(List<String> values) {
            addCriterion("provider_id not in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdBetween(String value1, String value2) {
            addCriterion("provider_id between", value1, value2, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotBetween(String value1, String value2) {
            addCriterion("provider_id not between", value1, value2, "providerId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andBasicAddrIsNull() {
            addCriterion("basic_addr is null");
            return (Criteria) this;
        }

        public Criteria andBasicAddrIsNotNull() {
            addCriterion("basic_addr is not null");
            return (Criteria) this;
        }

        public Criteria andBasicAddrEqualTo(String value) {
            addCriterion("basic_addr =", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrNotEqualTo(String value) {
            addCriterion("basic_addr <>", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrGreaterThan(String value) {
            addCriterion("basic_addr >", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrGreaterThanOrEqualTo(String value) {
            addCriterion("basic_addr >=", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrLessThan(String value) {
            addCriterion("basic_addr <", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrLessThanOrEqualTo(String value) {
            addCriterion("basic_addr <=", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrLike(String value) {
            addCriterion("basic_addr like", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrNotLike(String value) {
            addCriterion("basic_addr not like", value, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrIn(List<String> values) {
            addCriterion("basic_addr in", values, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrNotIn(List<String> values) {
            addCriterion("basic_addr not in", values, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrBetween(String value1, String value2) {
            addCriterion("basic_addr between", value1, value2, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andBasicAddrNotBetween(String value1, String value2) {
            addCriterion("basic_addr not between", value1, value2, "basicAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrIsNull() {
            addCriterion("detail_addr is null");
            return (Criteria) this;
        }

        public Criteria andDetailAddrIsNotNull() {
            addCriterion("detail_addr is not null");
            return (Criteria) this;
        }

        public Criteria andDetailAddrEqualTo(String value) {
            addCriterion("detail_addr =", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotEqualTo(String value) {
            addCriterion("detail_addr <>", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrGreaterThan(String value) {
            addCriterion("detail_addr >", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrGreaterThanOrEqualTo(String value) {
            addCriterion("detail_addr >=", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrLessThan(String value) {
            addCriterion("detail_addr <", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrLessThanOrEqualTo(String value) {
            addCriterion("detail_addr <=", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrLike(String value) {
            addCriterion("detail_addr like", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotLike(String value) {
            addCriterion("detail_addr not like", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrIn(List<String> values) {
            addCriterion("detail_addr in", values, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotIn(List<String> values) {
            addCriterion("detail_addr not in", values, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrBetween(String value1, String value2) {
            addCriterion("detail_addr between", value1, value2, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotBetween(String value1, String value2) {
            addCriterion("detail_addr not between", value1, value2, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andZipcodeIsNull() {
            addCriterion("zipcode is null");
            return (Criteria) this;
        }

        public Criteria andZipcodeIsNotNull() {
            addCriterion("zipcode is not null");
            return (Criteria) this;
        }

        public Criteria andZipcodeEqualTo(String value) {
            addCriterion("zipcode =", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotEqualTo(String value) {
            addCriterion("zipcode <>", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThan(String value) {
            addCriterion("zipcode >", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThanOrEqualTo(String value) {
            addCriterion("zipcode >=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThan(String value) {
            addCriterion("zipcode <", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThanOrEqualTo(String value) {
            addCriterion("zipcode <=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLike(String value) {
            addCriterion("zipcode like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotLike(String value) {
            addCriterion("zipcode not like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeIn(List<String> values) {
            addCriterion("zipcode in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotIn(List<String> values) {
            addCriterion("zipcode not in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeBetween(String value1, String value2) {
            addCriterion("zipcode between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotBetween(String value1, String value2) {
            addCriterion("zipcode not between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andUploadCntIsNull() {
            addCriterion("upload_cnt is null");
            return (Criteria) this;
        }

        public Criteria andUploadCntIsNotNull() {
            addCriterion("upload_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andUploadCntEqualTo(Integer value) {
            addCriterion("upload_cnt =", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntNotEqualTo(Integer value) {
            addCriterion("upload_cnt <>", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntGreaterThan(Integer value) {
            addCriterion("upload_cnt >", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("upload_cnt >=", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntLessThan(Integer value) {
            addCriterion("upload_cnt <", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntLessThanOrEqualTo(Integer value) {
            addCriterion("upload_cnt <=", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntIn(List<Integer> values) {
            addCriterion("upload_cnt in", values, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntNotIn(List<Integer> values) {
            addCriterion("upload_cnt not in", values, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntBetween(Integer value1, Integer value2) {
            addCriterion("upload_cnt between", value1, value2, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntNotBetween(Integer value1, Integer value2) {
            addCriterion("upload_cnt not between", value1, value2, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntIsNull() {
            addCriterion("post_cnt is null");
            return (Criteria) this;
        }

        public Criteria andPostCntIsNotNull() {
            addCriterion("post_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andPostCntEqualTo(Integer value) {
            addCriterion("post_cnt =", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntNotEqualTo(Integer value) {
            addCriterion("post_cnt <>", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntGreaterThan(Integer value) {
            addCriterion("post_cnt >", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_cnt >=", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntLessThan(Integer value) {
            addCriterion("post_cnt <", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntLessThanOrEqualTo(Integer value) {
            addCriterion("post_cnt <=", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntIn(List<Integer> values) {
            addCriterion("post_cnt in", values, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntNotIn(List<Integer> values) {
            addCriterion("post_cnt not in", values, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntBetween(Integer value1, Integer value2) {
            addCriterion("post_cnt between", value1, value2, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntNotBetween(Integer value1, Integer value2) {
            addCriterion("post_cnt not between", value1, value2, "postCnt");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyIsNull() {
            addCriterion("earn_total_money is null");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyIsNotNull() {
            addCriterion("earn_total_money is not null");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyEqualTo(Float value) {
            addCriterion("earn_total_money =", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyNotEqualTo(Float value) {
            addCriterion("earn_total_money <>", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyGreaterThan(Float value) {
            addCriterion("earn_total_money >", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("earn_total_money >=", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyLessThan(Float value) {
            addCriterion("earn_total_money <", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyLessThanOrEqualTo(Float value) {
            addCriterion("earn_total_money <=", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyIn(List<Float> values) {
            addCriterion("earn_total_money in", values, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyNotIn(List<Float> values) {
            addCriterion("earn_total_money not in", values, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyBetween(Float value1, Float value2) {
            addCriterion("earn_total_money between", value1, value2, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyNotBetween(Float value1, Float value2) {
            addCriterion("earn_total_money not between", value1, value2, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyIsNull() {
            addCriterion("earn_reword_money is null");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyIsNotNull() {
            addCriterion("earn_reword_money is not null");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyEqualTo(Float value) {
            addCriterion("earn_reword_money =", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyNotEqualTo(Float value) {
            addCriterion("earn_reword_money <>", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyGreaterThan(Float value) {
            addCriterion("earn_reword_money >", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("earn_reword_money >=", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyLessThan(Float value) {
            addCriterion("earn_reword_money <", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyLessThanOrEqualTo(Float value) {
            addCriterion("earn_reword_money <=", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyIn(List<Float> values) {
            addCriterion("earn_reword_money in", values, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyNotIn(List<Float> values) {
            addCriterion("earn_reword_money not in", values, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyBetween(Float value1, Float value2) {
            addCriterion("earn_reword_money between", value1, value2, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyNotBetween(Float value1, Float value2) {
            addCriterion("earn_reword_money not between", value1, value2, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrIsNull() {
            addCriterion("resent_send_basic_addr is null");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrIsNotNull() {
            addCriterion("resent_send_basic_addr is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrEqualTo(String value) {
            addCriterion("resent_send_basic_addr =", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrNotEqualTo(String value) {
            addCriterion("resent_send_basic_addr <>", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrGreaterThan(String value) {
            addCriterion("resent_send_basic_addr >", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrGreaterThanOrEqualTo(String value) {
            addCriterion("resent_send_basic_addr >=", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrLessThan(String value) {
            addCriterion("resent_send_basic_addr <", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrLessThanOrEqualTo(String value) {
            addCriterion("resent_send_basic_addr <=", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrLike(String value) {
            addCriterion("resent_send_basic_addr like", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrNotLike(String value) {
            addCriterion("resent_send_basic_addr not like", value, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrIn(List<String> values) {
            addCriterion("resent_send_basic_addr in", values, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrNotIn(List<String> values) {
            addCriterion("resent_send_basic_addr not in", values, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrBetween(String value1, String value2) {
            addCriterion("resent_send_basic_addr between", value1, value2, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendBasicAddrNotBetween(String value1, String value2) {
            addCriterion("resent_send_basic_addr not between", value1, value2, "resentSendBasicAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrIsNull() {
            addCriterion("resent_send_detail_addr is null");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrIsNotNull() {
            addCriterion("resent_send_detail_addr is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrEqualTo(String value) {
            addCriterion("resent_send_detail_addr =", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrNotEqualTo(String value) {
            addCriterion("resent_send_detail_addr <>", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrGreaterThan(String value) {
            addCriterion("resent_send_detail_addr >", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrGreaterThanOrEqualTo(String value) {
            addCriterion("resent_send_detail_addr >=", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrLessThan(String value) {
            addCriterion("resent_send_detail_addr <", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrLessThanOrEqualTo(String value) {
            addCriterion("resent_send_detail_addr <=", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrLike(String value) {
            addCriterion("resent_send_detail_addr like", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrNotLike(String value) {
            addCriterion("resent_send_detail_addr not like", value, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrIn(List<String> values) {
            addCriterion("resent_send_detail_addr in", values, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrNotIn(List<String> values) {
            addCriterion("resent_send_detail_addr not in", values, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrBetween(String value1, String value2) {
            addCriterion("resent_send_detail_addr between", value1, value2, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendDetailAddrNotBetween(String value1, String value2) {
            addCriterion("resent_send_detail_addr not between", value1, value2, "resentSendDetailAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeIsNull() {
            addCriterion("resent_send_zipcode is null");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeIsNotNull() {
            addCriterion("resent_send_zipcode is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeEqualTo(String value) {
            addCriterion("resent_send_zipcode =", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeNotEqualTo(String value) {
            addCriterion("resent_send_zipcode <>", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeGreaterThan(String value) {
            addCriterion("resent_send_zipcode >", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeGreaterThanOrEqualTo(String value) {
            addCriterion("resent_send_zipcode >=", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeLessThan(String value) {
            addCriterion("resent_send_zipcode <", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeLessThanOrEqualTo(String value) {
            addCriterion("resent_send_zipcode <=", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeLike(String value) {
            addCriterion("resent_send_zipcode like", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeNotLike(String value) {
            addCriterion("resent_send_zipcode not like", value, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeIn(List<String> values) {
            addCriterion("resent_send_zipcode in", values, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeNotIn(List<String> values) {
            addCriterion("resent_send_zipcode not in", values, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeBetween(String value1, String value2) {
            addCriterion("resent_send_zipcode between", value1, value2, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendZipcodeNotBetween(String value1, String value2) {
            addCriterion("resent_send_zipcode not between", value1, value2, "resentSendZipcode");
            return (Criteria) this;
        }

        public Criteria andResentSendCityIsNull() {
            addCriterion("resent_send_city is null");
            return (Criteria) this;
        }

        public Criteria andResentSendCityIsNotNull() {
            addCriterion("resent_send_city is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendCityEqualTo(String value) {
            addCriterion("resent_send_city =", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityNotEqualTo(String value) {
            addCriterion("resent_send_city <>", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityGreaterThan(String value) {
            addCriterion("resent_send_city >", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityGreaterThanOrEqualTo(String value) {
            addCriterion("resent_send_city >=", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityLessThan(String value) {
            addCriterion("resent_send_city <", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityLessThanOrEqualTo(String value) {
            addCriterion("resent_send_city <=", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityLike(String value) {
            addCriterion("resent_send_city like", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityNotLike(String value) {
            addCriterion("resent_send_city not like", value, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityIn(List<String> values) {
            addCriterion("resent_send_city in", values, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityNotIn(List<String> values) {
            addCriterion("resent_send_city not in", values, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityBetween(String value1, String value2) {
            addCriterion("resent_send_city between", value1, value2, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendCityNotBetween(String value1, String value2) {
            addCriterion("resent_send_city not between", value1, value2, "resentSendCity");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationIsNull() {
            addCriterion("resent_send_location is null");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationIsNotNull() {
            addCriterion("resent_send_location is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationEqualTo(String value) {
            addCriterion("resent_send_location =", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationNotEqualTo(String value) {
            addCriterion("resent_send_location <>", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationGreaterThan(String value) {
            addCriterion("resent_send_location >", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationGreaterThanOrEqualTo(String value) {
            addCriterion("resent_send_location >=", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationLessThan(String value) {
            addCriterion("resent_send_location <", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationLessThanOrEqualTo(String value) {
            addCriterion("resent_send_location <=", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationLike(String value) {
            addCriterion("resent_send_location like", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationNotLike(String value) {
            addCriterion("resent_send_location not like", value, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationIn(List<String> values) {
            addCriterion("resent_send_location in", values, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationNotIn(List<String> values) {
            addCriterion("resent_send_location not in", values, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationBetween(String value1, String value2) {
            addCriterion("resent_send_location between", value1, value2, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendLocationNotBetween(String value1, String value2) {
            addCriterion("resent_send_location not between", value1, value2, "resentSendLocation");
            return (Criteria) this;
        }

        public Criteria andResentSendNameIsNull() {
            addCriterion("resent_send_name is null");
            return (Criteria) this;
        }

        public Criteria andResentSendNameIsNotNull() {
            addCriterion("resent_send_name is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendNameEqualTo(String value) {
            addCriterion("resent_send_name =", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotEqualTo(String value) {
            addCriterion("resent_send_name <>", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameGreaterThan(String value) {
            addCriterion("resent_send_name >", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameGreaterThanOrEqualTo(String value) {
            addCriterion("resent_send_name >=", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameLessThan(String value) {
            addCriterion("resent_send_name <", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameLessThanOrEqualTo(String value) {
            addCriterion("resent_send_name <=", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameLike(String value) {
            addCriterion("resent_send_name like", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotLike(String value) {
            addCriterion("resent_send_name not like", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameIn(List<String> values) {
            addCriterion("resent_send_name in", values, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotIn(List<String> values) {
            addCriterion("resent_send_name not in", values, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameBetween(String value1, String value2) {
            addCriterion("resent_send_name between", value1, value2, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotBetween(String value1, String value2) {
            addCriterion("resent_send_name not between", value1, value2, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("point is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("point is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(Integer value) {
            addCriterion("point =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(Integer value) {
            addCriterion("point <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(Integer value) {
            addCriterion("point >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("point >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(Integer value) {
            addCriterion("point <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(Integer value) {
            addCriterion("point <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<Integer> values) {
            addCriterion("point in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<Integer> values) {
            addCriterion("point not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(Integer value1, Integer value2) {
            addCriterion("point between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(Integer value1, Integer value2) {
            addCriterion("point not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("user_status is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("user_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(String value) {
            addCriterion("user_status =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(String value) {
            addCriterion("user_status <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(String value) {
            addCriterion("user_status >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(String value) {
            addCriterion("user_status >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(String value) {
            addCriterion("user_status <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(String value) {
            addCriterion("user_status <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLike(String value) {
            addCriterion("user_status like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotLike(String value) {
            addCriterion("user_status not like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<String> values) {
            addCriterion("user_status in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<String> values) {
            addCriterion("user_status not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(String value1, String value2) {
            addCriterion("user_status between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(String value1, String value2) {
            addCriterion("user_status not between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andSnsTypeIsNull() {
            addCriterion("sns_type is null");
            return (Criteria) this;
        }

        public Criteria andSnsTypeIsNotNull() {
            addCriterion("sns_type is not null");
            return (Criteria) this;
        }

        public Criteria andSnsTypeEqualTo(String value) {
            addCriterion("sns_type =", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeNotEqualTo(String value) {
            addCriterion("sns_type <>", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeGreaterThan(String value) {
            addCriterion("sns_type >", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sns_type >=", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeLessThan(String value) {
            addCriterion("sns_type <", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeLessThanOrEqualTo(String value) {
            addCriterion("sns_type <=", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeLike(String value) {
            addCriterion("sns_type like", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeNotLike(String value) {
            addCriterion("sns_type not like", value, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeIn(List<String> values) {
            addCriterion("sns_type in", values, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeNotIn(List<String> values) {
            addCriterion("sns_type not in", values, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeBetween(String value1, String value2) {
            addCriterion("sns_type between", value1, value2, "snsType");
            return (Criteria) this;
        }

        public Criteria andSnsTypeNotBetween(String value1, String value2) {
            addCriterion("sns_type not between", value1, value2, "snsType");
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

        public Criteria andLanguageIsNull() {
            addCriterion("language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("language not between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andServiceCntIsNull() {
            addCriterion("service_cnt is null");
            return (Criteria) this;
        }

        public Criteria andServiceCntIsNotNull() {
            addCriterion("service_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andServiceCntEqualTo(Integer value) {
            addCriterion("service_cnt =", value, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntNotEqualTo(Integer value) {
            addCriterion("service_cnt <>", value, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntGreaterThan(Integer value) {
            addCriterion("service_cnt >", value, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_cnt >=", value, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntLessThan(Integer value) {
            addCriterion("service_cnt <", value, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntLessThanOrEqualTo(Integer value) {
            addCriterion("service_cnt <=", value, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntIn(List<Integer> values) {
            addCriterion("service_cnt in", values, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntNotIn(List<Integer> values) {
            addCriterion("service_cnt not in", values, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntBetween(Integer value1, Integer value2) {
            addCriterion("service_cnt between", value1, value2, "serviceCnt");
            return (Criteria) this;
        }

        public Criteria andServiceCntNotBetween(Integer value1, Integer value2) {
            addCriterion("service_cnt not between", value1, value2, "serviceCnt");
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