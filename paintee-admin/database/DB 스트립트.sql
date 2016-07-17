create table TB_USER (
	user_id varchar(64) primary key comment 'User 고유번호 (사용자가 만드는 건 아님)', 
	password varchar(255) NOT NULL comment '비밀번호',
	email varchar(50) NOT NULL comment 'email 주소(실제 사용자 ID)',
	name varchar(30) NOT NULL comment '표시되는 이름(중복 허용)',
	provider_id varchar(255) comment '회원가입 소셜정보',
	introduce varchar(600) comment '소갯말',
	basic_addr varchar(200) comment '기본 주소',
	detail_addr varchar(200) comment '상세 주소',
	zipcode varchar(200) comment '우편번호',
	city varchar(200) comment '도시명',
	location varchar(40) comment '국가',
	language varchar(8) comment '언어',
	upload_cnt int default 0 comment 'Upload 한 전체 숫자',
	post_cnt int default 0 comment 'Post 한 전체 숫자',
	earn_total_money float default 0 comment '수익의 전체 금액',
	earn_reword_money float default 0  comment '리워드로 받은 전체 금액',
	resent_send_basic_addr varchar(200) comment '최근 보낸 기본 주소',
	resent_send_detail_addr varchar(200) comment '최근 보낸 상세 주소',
	resent_send_zipcode varchar(200) comment '최근 보낸 우편번호',
	resent_send_city varchar(200) comment '최근 보낸 도시명',
	resent_send_location varchar(40) comment '최근 보낸 국가',
	resent_send_name varchar(30) comment '최근 보낸 이름',
	point int  default 0 comment '엽서를 구매할 수 있는 포인트',
	user_status char(1) default 'T' comment '계정 상태(임시(회원가입후 email confirm 전)-T/정상(회원가입후 email confirm 후)-N/정지-S/휴먼-Q)',
	sns_type char(1) comment '쇼셜을 통한 회원가입 (F-facebook, T-twitter)',
    created_date datetime default now() comment '생성일시',
    service_cnt int default 3 comment '베타테스트 구매카운트 제공'
) comment = '회원';

create table TB_FOLLOW (
	user_id varchar(64) not null comment 'Follow를 하는 사람 ID (follower)',
	following varchar(64) not null comment 'Follow되는 대상 ID',
    created_date datetime default now() comment '생성일시',
	primary key (user_id, following)
) COMMENT = '팔로워';

create  table TB_PURCHASE (
	seq int primary key auto_increment comment '구매 고유 번호',
	user_id varchar(64) comment '구매한 사람의 id', 
	painting_id varchar(64) comment '구매된 그림의 id', 
	purchase_date datetime default now() comment '구매된 날짜',
	sentence varchar(200) comment '구매하면서 작성된 한마디',
	private_at char(1) comment 'sentence의 공개/비공개 여부',
	receiver_basic_addr varchar(200) comment '엽서 수신자 기본 주소',
	receiver_detail_addr varchar(200) comment '엽서 수신자 상세 주소',
	receiver_zipcode varchar(200) comment '엽서 수신자 우편번호',
	receiver_city varchar(200) comment '엽서 수신자 도시명',
	receiver_name varchar(200) comment '엽서 수신자 이름',
	sender_addr varchar(30) comment '엽서 발신자 주소',
	sender_name varchar(30) comment '엽서 발신자 이름',
	location varchar(40) comment '구매자의 국가',
	purchase_status varchar(8) default '1' comment '구매 진행 상태 (요청-1/발송-2/환불요청-3/재발송요청-4/재발송처리-5/환불처리-6/삭제-7/종료-99)',
	status_update_date datetime comment '구매된 날짜',
    created_date datetime default now() comment '생성일시'
) COMMENT = '구매';

create table TB_REWARD ( 
	seq int primary key auto_increment comment '리워드 고유 번호',
    user_id varchar(64) comment '리워드 요청자 id',
    bank varchar(8) comment '입금 요청된 은행(TB_CODE 테이블 확인)',
    direct_input_bank varchar(40) comment '직접 입력한 입금 요청된 은행',
    account_no varchar(20) comment '입금 요청된 계좌번호',
    account_name varchar(30) comment '입금 요청된 계좌주 이름',
    earm_requested_money int comment '입금 요청된 금액',
    earm_requested_commission int comment '입금 요청된 금액의 수수료',
    reward_status char(1) default 'R' comment '입금 진행 상태 (요청-R/비정상-A/완료-C)',
    created_date datetime default now() comment '생성일시'
) COMMENT = '리워드';

create table TB_PAINTING (
	seq int primary key auto_increment comment '그림 고유 번호',
    painting_id varchar(64) unique comment '그림의 고유 ID',
    upload_date datetime comment '그림이 업로드 된 날짜',
    artist_id varchar(64) comment '그림을 올린 사람 ID',
    sentence varchar(600) comment '그림 소갯말',
    location varchar(40) comment '업로드한 사람의 국가',
    posted_num int default 0 comment '구매된 숫자',
    posted_people_cnt int default 0 comment '구매된 숫자(사람수 기준)',
    original_size varchar(10) comment '원본 이미지의 size',
    view_cnt int default 0 comment '조회수',
    share_cnt int default 0 comment 'share 버튼을 통해 공유된 횟수',
    file_group_seq bigint comment '첨부파일 그룹 아이디',
    painting_status char(1) comment '현재 그림의 상태(정상-N/블라인드-B/삭제-D)',
	private_at char(1) comment '공개/비공개 여부(N-공개/Y-비공개)',
    created_date datetime default now() comment '생성일시'
) COMMENT = '페인팅';


create table TB_NEW_PAINTING (
	seq int primary key auto_increment comment '신규 페인팅 고유 번호',
    painting_id varchar(64) comment '그림의 고유 ID'
) COMMENT = '신규 페인팅';

create table TB_POPULAR_PAINTING (
	seq int primary key auto_increment comment '인기 페인팅 고유 번호',
    painting_id varchar(64) comment '그림의 고유 ID',
    purchase_count int comment '그림 구매 숫자'
) COMMENT = '인기 페인팅';

create table TB_LOGIN (
	seq int primary key auto_increment comment '로그인 구분 고유 번호',
    user_id varchar(64) comment '로그인 사용자 id',
	hash    varchar(256) comment '사용자 구분 해쉬값',
	expire_date  datetime comment '로그인 유효 날짜',
	access_gubun  char(1) comment '접속 구분 (Android App-A/Web Application-W/IOS-I)'
) COMMENT = '로그인';

create table TB_FILE_GROUP (
    seq bigint not null auto_increment comment '파일그룹아이디',
    group_name varchar(255) comment '그룹명',
    created_date datetime default now() comment '생성일자',
    primary key (seq)
) COMMENT = '파일 그룹';

create table TB_FILE_INFO (
    id varchar(64) not null comment '파일 아이디',
    file_group_seq bigint not null comment '파일그룹아이디',
    content_type varchar(255) comment '파일 mime type',
    extension varchar(255) comment '파일 확장자',
    name varchar(255) comment '서버저장 파일명(확장자 제외)',
    ori_name varchar(255) comment '사용자가 업로드한 파일명(확장자 포함)',
    display_name varchar(255) comment '화면표시용 파일명',
    path varchar(255) comment '파일명과 절대경로를 제외한 파일 경로',
    size bigint comment '파일 용량',
    created_date datetime default now() comment '생성일자',
    last_modified_date datetime default now() comment '수정일자',
    primary key (id)
) COMMENT = '파일 정보';

create table TB_CONFIRM_HASH (
	seq int primary key auto_increment comment '고유 번호',
    user_id varchar(64) comment '사용자 id',
	hash    varchar(256) comment 'confirm 해쉬값',
	expire_date  datetime comment 'hash 유효 날짜'
) COMMENT = '회원가입 후 계정활성화를 위한 hash 정보';

create table TB_CODE (
    code_group varchar(20) not null comment '코드 그룹',
    code_value varchar(8) not null comment '코드 값',
	code_name  varchar(30) not null comment '코드 이름',
    use_yn     char(1) not null  default 'Y'  comment '사용 여부',
    created_date datetime default now() comment '생성일시',
    primary key (code_group, code_value)
) COMMENT = '코드';

create table TB_USER_SOCIAL (
	user_id varchar(64) not null comment 'User 고유번호',
    provider_id varchar(255) not null comment '소셜 공급자 ID',
    provider_user_id varchar(255) comment '소셜 계정 ID',
    rank int comment '',
    display_name varchar(255) comment '소셜 계정 화면 표시 이름',
    profile_url varchar(512) comment '소셜 계정 프로필 사진 주소',
    image_url varchar(512) comment '소셜 계정 이미지 주소',
    access_token varchar(255) not null comment '소셜 계정 토큰 Facebook:access_token, twitter : oauth_token, google+: access_token 으로 매칭',					
    secret varchar(255) comment '소셜 계정 비밀키 Facebook:null, twitter : oauth_token_secret, google+: token_type 으로 매칭',
    refresh_token varchar(255) comment '소셜 계정 새로고침 용 토큰',
    expire_time bigint comment '소셜 계정 만료시간',
    primary key (user_id, provider_id, provider_user_id)
) COMMENT = '사용자 소셜 계정 정보';
create unique index TB_USER_SOCIAL_IDX_01 on TB_USER_SOCIAL(user_id, provider_id, rank);