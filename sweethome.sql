create table tbl_user(
userid varchar2(10) not null primary key,
grade varchar2(1) not null,
name varchar2(10) not null,
pw varchar2(5)
);

insert into tbl_user values('DAD','P','아빠','1234');
insert into tbl_user values('MOM','P','엄마','1234');
insert into tbl_user values('MIN','K','민찬', '12');
insert into tbl_user values('DO','K','도현', '12');
commit;

select * from tbl_user;

create table tbl_doit(
seq number(10) not null,
basedate date not null,
userid varchar2(10) not null,
content varchar2(1000) not null,
done varchar2(1),
CONSTRAINT DOIT_PK PRIMARY KEY(seq, basedate, userid)
);

create table tbl_doitbatch(
seq number(10) not null,
defineday varchar2(7) not null,
userid varchar2(10) not null,
content varchar2(1000) not null,
CONSTRAINT DOITBATCH_PK PRIMARY KEY(seq, defineday, userid)
);
select max(seq) from tbl_doitbatch where defineday='월' and userid='MIN';

create table tbl_schedule(
seq number(10) not null,
basedate date not null,
content varchar2(1000) not null,
partition varchar2(20) not null,
CONSTRAINT SCHEDULE_PK PRIMARY KEY(seq, basedate)
);

create table tbl_bookreport(
seq number(10) not null,
basedate date not null,
userid varchar2(10) not null,
bookseq number(10) not null,
report varchar2(4000),
CONSTRAINT BOOKREPORT_PK PRIMARY KEY(seq, basedate, userid)
);

create table tbl_bookcollect(
seq number(10) not null primary key,
name varchar2(100) not null,
delyn varchar2(1) 
);

create table tbl_books(
seq number(10) not null primary key,
name varchar2(100) not null,
delyn varchar2(1) 
);

create table tbl_suggest(
seq number(10) not null,
basedate date not null,
userid varchar2(10) not null,
type varchar2(20) not null,
content varchar2(1000) not null,
okflag varchar2(1),
CONSTRAINT SUGGEST_PK PRIMARY KEY(seq, basedate, userid)
);

create table tbl_coupon(
seq number(10) not null,
basedate date not null,
userid varchar2(10) not null,
type varchar2(20) not null,
content varchar2(1000) not null,
playtime number(5) not null,
CONSTRAINT COUPON_PK PRIMARY KEY(seq, basedate, userid)
);

create table tbl_coupontime(
userid varchar2(10) not null primary key,
totaltime number(5) not null
);
insert into tbl_coupontime values('MIN',0);
insert into tbl_coupontime values('DO',0);

