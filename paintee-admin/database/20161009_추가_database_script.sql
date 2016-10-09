-- 좋아요 관련 스크립트
create table TB_PAINTING_LIKE (
   user_id varchar(64) not null comment '좋아요를 하는 회원 아이디',
   painting_id varchar(64) not null comment '좋아요 대상 그림 ID',
  created_date datetime default now() comment '생성일시',
   primary key (user_id, painting_id)
) COMMENT = '그림 좋아요';

ALTER TABLE tb_user ADD like_cnt int DEFAULT 0 COMMENT '회원들에 의해서 좋아요된 전체 숫자' AFTER post_cnt;

-- 코멘트 관련 스크립트
create table TB_COMMENT_PAINTING (
	seq int primary key auto_increment comment '코멘트한 페인팅 고유 번호',
    painting_id varchar(64) comment '그림의 고유 ID',
	user_id varchar(64) comment '코멘트한 사람의 id', 
    sentence varchar(600) comment '그림 소갯말',
    created_date datetime default now() comment '생성일자'
) COMMENT = '사용자가 코멘트한 페인팅';

ALTER TABLE tb_user ADD comment_cnt INT DEFAULT '0' COMMENT 'Comment 한 전체 숫자' AFTER post_cnt;
