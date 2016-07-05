package com.paintee.common.repository.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaintingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaintingExample() {
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

        public Criteria andSeqIsNull() {
            addCriterion("seq is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("seq is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(Integer value) {
            addCriterion("seq =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(Integer value) {
            addCriterion("seq <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(Integer value) {
            addCriterion("seq >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(Integer value) {
            addCriterion("seq <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(Integer value) {
            addCriterion("seq <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<Integer> values) {
            addCriterion("seq in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<Integer> values) {
            addCriterion("seq not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(Integer value1, Integer value2) {
            addCriterion("seq between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("seq not between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIsNull() {
            addCriterion("painting_id is null");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIsNotNull() {
            addCriterion("painting_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaintingIdEqualTo(String value) {
            addCriterion("painting_id =", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotEqualTo(String value) {
            addCriterion("painting_id <>", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdGreaterThan(String value) {
            addCriterion("painting_id >", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdGreaterThanOrEqualTo(String value) {
            addCriterion("painting_id >=", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLessThan(String value) {
            addCriterion("painting_id <", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLessThanOrEqualTo(String value) {
            addCriterion("painting_id <=", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLike(String value) {
            addCriterion("painting_id like", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotLike(String value) {
            addCriterion("painting_id not like", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIn(List<String> values) {
            addCriterion("painting_id in", values, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotIn(List<String> values) {
            addCriterion("painting_id not in", values, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdBetween(String value1, String value2) {
            addCriterion("painting_id between", value1, value2, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotBetween(String value1, String value2) {
            addCriterion("painting_id not between", value1, value2, "paintingId");
            return (Criteria) this;
        }

        public Criteria andUploadDateIsNull() {
            addCriterion("upload_date is null");
            return (Criteria) this;
        }

        public Criteria andUploadDateIsNotNull() {
            addCriterion("upload_date is not null");
            return (Criteria) this;
        }

        public Criteria andUploadDateEqualTo(Date value) {
            addCriterion("upload_date =", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotEqualTo(Date value) {
            addCriterion("upload_date <>", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateGreaterThan(Date value) {
            addCriterion("upload_date >", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_date >=", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateLessThan(Date value) {
            addCriterion("upload_date <", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateLessThanOrEqualTo(Date value) {
            addCriterion("upload_date <=", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateIn(List<Date> values) {
            addCriterion("upload_date in", values, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotIn(List<Date> values) {
            addCriterion("upload_date not in", values, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateBetween(Date value1, Date value2) {
            addCriterion("upload_date between", value1, value2, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotBetween(Date value1, Date value2) {
            addCriterion("upload_date not between", value1, value2, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andArtistIdIsNull() {
            addCriterion("artist_id is null");
            return (Criteria) this;
        }

        public Criteria andArtistIdIsNotNull() {
            addCriterion("artist_id is not null");
            return (Criteria) this;
        }

        public Criteria andArtistIdEqualTo(String value) {
            addCriterion("artist_id =", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdNotEqualTo(String value) {
            addCriterion("artist_id <>", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdGreaterThan(String value) {
            addCriterion("artist_id >", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdGreaterThanOrEqualTo(String value) {
            addCriterion("artist_id >=", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdLessThan(String value) {
            addCriterion("artist_id <", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdLessThanOrEqualTo(String value) {
            addCriterion("artist_id <=", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdLike(String value) {
            addCriterion("artist_id like", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdNotLike(String value) {
            addCriterion("artist_id not like", value, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdIn(List<String> values) {
            addCriterion("artist_id in", values, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdNotIn(List<String> values) {
            addCriterion("artist_id not in", values, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdBetween(String value1, String value2) {
            addCriterion("artist_id between", value1, value2, "artistId");
            return (Criteria) this;
        }

        public Criteria andArtistIdNotBetween(String value1, String value2) {
            addCriterion("artist_id not between", value1, value2, "artistId");
            return (Criteria) this;
        }

        public Criteria andSentenceIsNull() {
            addCriterion("sentence is null");
            return (Criteria) this;
        }

        public Criteria andSentenceIsNotNull() {
            addCriterion("sentence is not null");
            return (Criteria) this;
        }

        public Criteria andSentenceEqualTo(String value) {
            addCriterion("sentence =", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotEqualTo(String value) {
            addCriterion("sentence <>", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceGreaterThan(String value) {
            addCriterion("sentence >", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceGreaterThanOrEqualTo(String value) {
            addCriterion("sentence >=", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLessThan(String value) {
            addCriterion("sentence <", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLessThanOrEqualTo(String value) {
            addCriterion("sentence <=", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLike(String value) {
            addCriterion("sentence like", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotLike(String value) {
            addCriterion("sentence not like", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceIn(List<String> values) {
            addCriterion("sentence in", values, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotIn(List<String> values) {
            addCriterion("sentence not in", values, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceBetween(String value1, String value2) {
            addCriterion("sentence between", value1, value2, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotBetween(String value1, String value2) {
            addCriterion("sentence not between", value1, value2, "sentence");
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

        public Criteria andPostedNumIsNull() {
            addCriterion("posted_num is null");
            return (Criteria) this;
        }

        public Criteria andPostedNumIsNotNull() {
            addCriterion("posted_num is not null");
            return (Criteria) this;
        }

        public Criteria andPostedNumEqualTo(Integer value) {
            addCriterion("posted_num =", value, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumNotEqualTo(Integer value) {
            addCriterion("posted_num <>", value, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumGreaterThan(Integer value) {
            addCriterion("posted_num >", value, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("posted_num >=", value, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumLessThan(Integer value) {
            addCriterion("posted_num <", value, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumLessThanOrEqualTo(Integer value) {
            addCriterion("posted_num <=", value, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumIn(List<Integer> values) {
            addCriterion("posted_num in", values, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumNotIn(List<Integer> values) {
            addCriterion("posted_num not in", values, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumBetween(Integer value1, Integer value2) {
            addCriterion("posted_num between", value1, value2, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("posted_num not between", value1, value2, "postedNum");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntIsNull() {
            addCriterion("posted_people_cnt is null");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntIsNotNull() {
            addCriterion("posted_people_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntEqualTo(Integer value) {
            addCriterion("posted_people_cnt =", value, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntNotEqualTo(Integer value) {
            addCriterion("posted_people_cnt <>", value, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntGreaterThan(Integer value) {
            addCriterion("posted_people_cnt >", value, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("posted_people_cnt >=", value, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntLessThan(Integer value) {
            addCriterion("posted_people_cnt <", value, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntLessThanOrEqualTo(Integer value) {
            addCriterion("posted_people_cnt <=", value, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntIn(List<Integer> values) {
            addCriterion("posted_people_cnt in", values, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntNotIn(List<Integer> values) {
            addCriterion("posted_people_cnt not in", values, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntBetween(Integer value1, Integer value2) {
            addCriterion("posted_people_cnt between", value1, value2, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andPostedPeopleCntNotBetween(Integer value1, Integer value2) {
            addCriterion("posted_people_cnt not between", value1, value2, "postedPeopleCnt");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeIsNull() {
            addCriterion("original_size is null");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeIsNotNull() {
            addCriterion("original_size is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeEqualTo(String value) {
            addCriterion("original_size =", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeNotEqualTo(String value) {
            addCriterion("original_size <>", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeGreaterThan(String value) {
            addCriterion("original_size >", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeGreaterThanOrEqualTo(String value) {
            addCriterion("original_size >=", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeLessThan(String value) {
            addCriterion("original_size <", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeLessThanOrEqualTo(String value) {
            addCriterion("original_size <=", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeLike(String value) {
            addCriterion("original_size like", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeNotLike(String value) {
            addCriterion("original_size not like", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeIn(List<String> values) {
            addCriterion("original_size in", values, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeNotIn(List<String> values) {
            addCriterion("original_size not in", values, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeBetween(String value1, String value2) {
            addCriterion("original_size between", value1, value2, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeNotBetween(String value1, String value2) {
            addCriterion("original_size not between", value1, value2, "originalSize");
            return (Criteria) this;
        }

        public Criteria andViewCntIsNull() {
            addCriterion("view_cnt is null");
            return (Criteria) this;
        }

        public Criteria andViewCntIsNotNull() {
            addCriterion("view_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andViewCntEqualTo(Integer value) {
            addCriterion("view_cnt =", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntNotEqualTo(Integer value) {
            addCriterion("view_cnt <>", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntGreaterThan(Integer value) {
            addCriterion("view_cnt >", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_cnt >=", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntLessThan(Integer value) {
            addCriterion("view_cnt <", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntLessThanOrEqualTo(Integer value) {
            addCriterion("view_cnt <=", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntIn(List<Integer> values) {
            addCriterion("view_cnt in", values, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntNotIn(List<Integer> values) {
            addCriterion("view_cnt not in", values, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntBetween(Integer value1, Integer value2) {
            addCriterion("view_cnt between", value1, value2, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntNotBetween(Integer value1, Integer value2) {
            addCriterion("view_cnt not between", value1, value2, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntIsNull() {
            addCriterion("share_cnt is null");
            return (Criteria) this;
        }

        public Criteria andShareCntIsNotNull() {
            addCriterion("share_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andShareCntEqualTo(Integer value) {
            addCriterion("share_cnt =", value, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntNotEqualTo(Integer value) {
            addCriterion("share_cnt <>", value, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntGreaterThan(Integer value) {
            addCriterion("share_cnt >", value, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_cnt >=", value, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntLessThan(Integer value) {
            addCriterion("share_cnt <", value, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntLessThanOrEqualTo(Integer value) {
            addCriterion("share_cnt <=", value, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntIn(List<Integer> values) {
            addCriterion("share_cnt in", values, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntNotIn(List<Integer> values) {
            addCriterion("share_cnt not in", values, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntBetween(Integer value1, Integer value2) {
            addCriterion("share_cnt between", value1, value2, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andShareCntNotBetween(Integer value1, Integer value2) {
            addCriterion("share_cnt not between", value1, value2, "shareCnt");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqIsNull() {
            addCriterion("file_group_seq is null");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqIsNotNull() {
            addCriterion("file_group_seq is not null");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqEqualTo(Long value) {
            addCriterion("file_group_seq =", value, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqNotEqualTo(Long value) {
            addCriterion("file_group_seq <>", value, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqGreaterThan(Long value) {
            addCriterion("file_group_seq >", value, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqGreaterThanOrEqualTo(Long value) {
            addCriterion("file_group_seq >=", value, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqLessThan(Long value) {
            addCriterion("file_group_seq <", value, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqLessThanOrEqualTo(Long value) {
            addCriterion("file_group_seq <=", value, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqIn(List<Long> values) {
            addCriterion("file_group_seq in", values, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqNotIn(List<Long> values) {
            addCriterion("file_group_seq not in", values, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqBetween(Long value1, Long value2) {
            addCriterion("file_group_seq between", value1, value2, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andFileGroupSeqNotBetween(Long value1, Long value2) {
            addCriterion("file_group_seq not between", value1, value2, "fileGroupSeq");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusIsNull() {
            addCriterion("painting_status is null");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusIsNotNull() {
            addCriterion("painting_status is not null");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusEqualTo(String value) {
            addCriterion("painting_status =", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusNotEqualTo(String value) {
            addCriterion("painting_status <>", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusGreaterThan(String value) {
            addCriterion("painting_status >", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusGreaterThanOrEqualTo(String value) {
            addCriterion("painting_status >=", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusLessThan(String value) {
            addCriterion("painting_status <", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusLessThanOrEqualTo(String value) {
            addCriterion("painting_status <=", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusLike(String value) {
            addCriterion("painting_status like", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusNotLike(String value) {
            addCriterion("painting_status not like", value, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusIn(List<String> values) {
            addCriterion("painting_status in", values, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusNotIn(List<String> values) {
            addCriterion("painting_status not in", values, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusBetween(String value1, String value2) {
            addCriterion("painting_status between", value1, value2, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPaintingStatusNotBetween(String value1, String value2) {
            addCriterion("painting_status not between", value1, value2, "paintingStatus");
            return (Criteria) this;
        }

        public Criteria andPrivateAtIsNull() {
            addCriterion("private_at is null");
            return (Criteria) this;
        }

        public Criteria andPrivateAtIsNotNull() {
            addCriterion("private_at is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateAtEqualTo(String value) {
            addCriterion("private_at =", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotEqualTo(String value) {
            addCriterion("private_at <>", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtGreaterThan(String value) {
            addCriterion("private_at >", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtGreaterThanOrEqualTo(String value) {
            addCriterion("private_at >=", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtLessThan(String value) {
            addCriterion("private_at <", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtLessThanOrEqualTo(String value) {
            addCriterion("private_at <=", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtLike(String value) {
            addCriterion("private_at like", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotLike(String value) {
            addCriterion("private_at not like", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtIn(List<String> values) {
            addCriterion("private_at in", values, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotIn(List<String> values) {
            addCriterion("private_at not in", values, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtBetween(String value1, String value2) {
            addCriterion("private_at between", value1, value2, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotBetween(String value1, String value2) {
            addCriterion("private_at not between", value1, value2, "privateAt");
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