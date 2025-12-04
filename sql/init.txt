create table if not exists users(
    id int primary key auto_increment comment 'id，主键',
    status int not null default 1 comment '账号状态，1正常，2冻结...',
    role int default 1 comment '账号角色，1用户，2管理员...',
    username varchar(50) unique not null comment '用户名',
    password varchar(256) not null  comment '密码',
    email varchar(100) unique comment '邮箱地址',
    name varchar(100) comment '真实姓名',
    telephone varchar(21) unique comment '手机号码',
    gender tinyint comment '性别,1男，0女...',
    birthday date comment '出生日期',
    create_time datetime not null default now()  comment '注册时间',
    update_time datetime comment '更新时间',
    last_login datetime comment '最近一次登录'
) comment '用户信息表';

insert into users(username,password,email,name,telephone,gender,birthday,create_time,update_time)
values
    ('pvzer','$2a$10$Gjt1zc4ondG9p5oqna1oZ.E6fISXG23z9YI7n0zNlGjQVbTn8ACBu',
     'pvzer@gamil.com','coffee','19574587026',1,'2006-04-12',now(),now()),

    ('mytest','$2a$10$aVlK/Jt7ZiCsoO1JFeNqHemfizUoqkIBraiPF7u.Xix5tOo/UkTWS',
     'test@gamil.com','jquesss','19574587027',0,'2001-07-25',now(),now());

create table if not exists user_roles(
    id int primary key auto_increment comment 'id，主键',
    role_type varchar(50) unique not null comment '属性',
    role_name varchar(50) unique not null comment '身份名称',
    create_time datetime not null default now() comment '创建时间'
);

insert into user_roles(role_type,role_name,create_time) values
('regular_user','用户',now()),
('admin','管理员',now());

create table if not exists user_status(
  id int primary key auto_increment comment 'id，主键',
  status_name varchar(50) unique not null comment '状态名称',
  create_time datetime not null default now() comment '创建时间'
);
insert into user_status(status_name, create_time) values
('正常',now()),
('冻结',now()),
('注销',now());


