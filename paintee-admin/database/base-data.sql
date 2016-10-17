insert into `TB_USER`(`user_id`, `language`, `provider_id`, `password`,`email`, `name`,`user_status`,`created_date`) values 
('00000000-0000-0000-0000-000000000000','ko','PAINTEE','33275a8aa48ea918bd53a9181aa975f15ab0d0645398f5918a006d08675c1cb27d5c645dbd084eee56e675e25ba4019f2ecea37ca9e2995b49fcb12c096a032e','paintee.service@gmail.com','paintee','N', now());

insert into TB_CODE(code_group, code_value, code_name) values('bank', '0', 'direct input');
insert into TB_CODE(code_group, code_value, code_name) values('bank', '1', 'City Bank');
insert into TB_CODE(code_group, code_value, code_name) values('bank', '2', 'Kb Bank');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '1', '요청');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '2', '발송');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '3', '환불요청');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '4', '재발송요청');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '5', '재발송처리');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '6', '환불처리');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '7', '삭제');
insert into TB_CODE(code_group, code_value, code_name) values('purchaseStatus', '99', '완료');