create table items(
    id int primary key auto_increment comment '主键，id',
    name varchar(100) unique comment '咨询分类名称'
);

insert into items(name) values
('床上用品'),('家居饰品'),('居家日用'),('家居收纳'),('厨房收纳'),('厨房餐饮');

create table messages(
    id int primary key auto_increment comment '主键，id',
    user_id int not null comment '发表者id，外键指向用户表的id',
    name varchar(200) comment '咨询者姓名',
    telephone varchar(25)  comment '咨询人电话',
    email varchar(100)  comment '咨询人邮箱',
    foreign key (user_id) references users(id)
);
alter table messages add text varchar(2000) not null comment '正文';
create table message_items_link(
    message_id int not null comment '外键关联留言主键',
    item_id int not null comment '外键关联项目主键',
    primary key (message_id,item_id),
    foreign key (message_id) references messages(id),
    foreign key (item_id) references items(id)
) comment '咨询和分类的多对多关联表';